package com.rudy.miaosha.service;

import com.rudy.miaosha.domain.MXUser;
import com.rudy.miaosha.domain.MiaoshaOrder;
import com.rudy.miaosha.domain.OrderInfo;
import com.rudy.miaosha.vo.GoodsVo;

public interface OrderService {
    MiaoshaOrder getMiaoshaOrderByUserIdGoodId(Long userId,Long goodsId) throws Exception;

    OrderInfo createOrder(MXUser user, GoodsVo goodsVo) throws Exception;
}
