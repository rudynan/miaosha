package com.rudy.miaosha.controller;

import com.rudy.miaosha.domain.Goods;
import com.rudy.miaosha.domain.MXUser;
import com.rudy.miaosha.service.GoodsService;
import com.rudy.miaosha.service.UserService;
import com.rudy.miaosha.service.impl.UserServiceImpl;
import com.rudy.miaosha.vo.GoodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/to_list")
    public String toList(MXUser mxUser,Model model) throws Exception {
        if (mxUser == null){
            return "login";
        }else {
            model.addAttribute("user", mxUser);
            List<GoodsVo> goodsVoList = goodsService.listGoodsVo();
            model.addAttribute("goodsList", goodsVoList);
            return "goods_list";
        }
    }

    @RequestMapping(value = "/to_datail/{goodsId}")
    public String toDetail(Model model, MXUser user, @PathVariable("goodsId") Long goodsId) throws Exception {
        GoodsVo goodsVo = goodsService.getGoodsVoById(goodsId);
        long endTime = goodsVo.getEndDate().getTime();
        long startTime = goodsVo.getStartDate().getTime();
        long currentTime = System.currentTimeMillis();
        model.addAttribute("user", user);
        model.addAttribute("goods", goodsVo);
        int miaoshaStatus = 0;
        int remainSeconds = 0;

        if (currentTime<startTime){
            // 秒杀还未开始
            miaoshaStatus = 0;
            remainSeconds = (int)(startTime - currentTime)/1000;
        }else if (currentTime > endTime){
            // 秒杀已经结束
            miaoshaStatus = 1;
            remainSeconds = -1;
        }else {
            // 秒杀正在进行中
            miaoshaStatus = 2;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);

        return "goods_detail";
    }
}
