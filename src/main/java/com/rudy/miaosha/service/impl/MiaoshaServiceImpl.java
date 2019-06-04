package com.rudy.miaosha.service.impl;

import com.rudy.miaosha.domain.MXUser;
import com.rudy.miaosha.domain.MiaoshaGoods;
import com.rudy.miaosha.domain.MiaoshaOrder;
import com.rudy.miaosha.domain.OrderInfo;
import com.rudy.miaosha.service.GoodsService;
import com.rudy.miaosha.service.MiaoshaService;
import com.rudy.miaosha.service.OrderService;
import com.rudy.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiaoshaServiceImpl implements MiaoshaService {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Override
    @Transactional
    public OrderInfo miaosha(MXUser user, GoodsVo goods) throws Exception {
        // 减库存
        MiaoshaGoods miaoshaGoods = new MiaoshaGoods();
        miaoshaGoods.setId(goods.getId());
        goodsService.reduceStock(miaoshaGoods);

        // 添加订单
        OrderInfo order = orderService.createOrder(user, goods);
        return order;
    }



}
