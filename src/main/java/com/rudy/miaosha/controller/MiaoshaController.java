package com.rudy.miaosha.controller;

import com.rudy.miaosha.domain.MXUser;
import com.rudy.miaosha.domain.MiaoshaOrder;
import com.rudy.miaosha.domain.OrderInfo;
import com.rudy.miaosha.result.CodeMsg;
import com.rudy.miaosha.result.Result;
import com.rudy.miaosha.service.GoodsService;
import com.rudy.miaosha.service.MiaoshaService;
import com.rudy.miaosha.service.OrderService;
import com.rudy.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/miaosha")
public class MiaoshaController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private MiaoshaService miaoshaService;
    @RequestMapping("/do_miaosha")
    public String miaosha(MXUser user, Model model,@RequestParam("goodsId") Long goodsId) throws Exception {
        model.addAttribute("user", user);
        if (user == null){
            return "login";
        }
        GoodsVo goodsVoById = goodsService.getGoodsVoById(goodsId);
        if (goodsVoById.getStockCount()<=0){
            model.addAttribute("errMsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_fail";
        }
        MiaoshaOrder orderByUserIdGoodId = orderService.getMiaoshaOrderByUserIdGoodId(user.getId(), goodsId);
        if (orderByUserIdGoodId != null){
            model.addAttribute("errMsg", CodeMsg.MIAO_SHA_REPEAT.getMsg());
            return "miaosha_fail";
        }
        // 减库存 下订单 写秒杀订单
        OrderInfo order = miaoshaService.miaosha(user, goodsVoById);

        model.addAttribute("orderInfo", order);
        model.addAttribute("goods", goodsVoById);
        return "order_detail";
    }
}
