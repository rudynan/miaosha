package com.rudy.miaosha.service.impl;

import com.rudy.miaosha.dao.MXOrderDao;
import com.rudy.miaosha.domain.MXUser;
import com.rudy.miaosha.domain.MiaoshaOrder;
import com.rudy.miaosha.domain.OrderInfo;
import com.rudy.miaosha.service.OrderService;
import com.rudy.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MXOrderDao mxOrderDao;

    @Override
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodId(Long userId, Long goodsId) throws Exception {

        return mxOrderDao.getMiaoshaOrderByUseridGoodsId(userId, goodsId);
    }

    @Override
    @Transactional
    public OrderInfo createOrder(MXUser user, GoodsVo goodsVo) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setGoodsCount(1);
        orderInfo.setDeliveryAddId(0L);
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        Long id = mxOrderDao.insertOrder(orderInfo);
        System.out.println("id:"+id);

        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goodsVo.getId());
        miaoshaOrder.setOrderId(orderInfo.getId());
        miaoshaOrder.setUserId(user.getId());
        mxOrderDao.insertMiaoshaOrder(miaoshaOrder);
        System.out.println(orderInfo);
        return orderInfo;
    }


}
