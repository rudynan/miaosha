package com.rudy.miaosha.service;

import com.rudy.miaosha.domain.MXUser;
import com.rudy.miaosha.domain.MiaoshaGoods;
import com.rudy.miaosha.domain.MiaoshaOrder;
import com.rudy.miaosha.domain.OrderInfo;
import com.rudy.miaosha.vo.GoodsVo;

public interface MiaoshaService {
    OrderInfo miaosha(MXUser user, GoodsVo goods)throws Exception;
}
