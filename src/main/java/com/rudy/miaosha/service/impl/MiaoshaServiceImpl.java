package com.rudy.miaosha.service.impl;

import com.rudy.miaosha.domain.MXUser;
import com.rudy.miaosha.domain.MiaoshaGoods;
import com.rudy.miaosha.domain.MiaoshaOrder;
import com.rudy.miaosha.domain.OrderInfo;
import com.rudy.miaosha.service.MiaoshaService;
import com.rudy.miaosha.vo.GoodsVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiaoshaServiceImpl implements MiaoshaService {
    @Override
    @Transactional
    public OrderInfo miaosha(MXUser user, GoodsVo goods) throws Exception {
        return null;
    }



}
