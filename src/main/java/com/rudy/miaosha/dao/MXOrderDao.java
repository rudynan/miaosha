package com.rudy.miaosha.dao;

import com.rudy.miaosha.domain.MiaoshaOrder;
import com.rudy.miaosha.domain.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MXOrderDao {

    MiaoshaOrder getMiaoshaOrderByUseridGoodsId(@Param("userid") Long userId, @Param("goodsid")Long goodsId) throws Exception;

    Long insertOrder(OrderInfo orderInfo) throws Exception;

    void insertMiaoshaOrder(MiaoshaOrder miaoshaOrder) throws Exception;
}
