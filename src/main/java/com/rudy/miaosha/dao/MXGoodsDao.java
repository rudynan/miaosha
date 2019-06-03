package com.rudy.miaosha.dao;

import com.rudy.miaosha.domain.Goods;
import com.rudy.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MXGoodsDao {
    List<GoodsVo> getGoodsVoList()throws Exception;
    GoodsVo getGoodsVoById(@Param("id") Long id)throws Exception;
    void reduceStockById(@Param("id") Long goodsId) throws Exception;
}
