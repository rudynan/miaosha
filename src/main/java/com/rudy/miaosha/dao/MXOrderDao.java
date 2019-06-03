package com.rudy.miaosha.dao;

import com.rudy.miaosha.domain.MiaoshaOrder;
import com.rudy.miaosha.domain.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MXOrderDao {

    MiaoshaOrder getMiaoshaOrderByUseridGoodsId(Long userId,Long goodsId) throws Exception;

    OrderInfo insertOrder(OrderInfo orderInfo) throws Exception;
}
