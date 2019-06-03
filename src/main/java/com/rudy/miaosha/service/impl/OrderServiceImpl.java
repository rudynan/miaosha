package com.rudy.miaosha.service.impl;

import com.rudy.miaosha.dao.MXOrderDao;
import com.rudy.miaosha.domain.MiaoshaOrder;
import com.rudy.miaosha.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MXOrderDao mxOrderDao;

    @Override
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodId(Long userId, Long goodsId) throws Exception {

        return mxOrderDao.getMiaoshaOrderByUseridGoodsId(userId, goodsId);
    }
}
