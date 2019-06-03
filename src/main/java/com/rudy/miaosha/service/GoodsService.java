package com.rudy.miaosha.service;

import com.rudy.miaosha.domain.Goods;
import com.rudy.miaosha.domain.MiaoshaGoods;
import com.rudy.miaosha.vo.GoodsVo;

import java.util.List;

public interface GoodsService {
    List<GoodsVo> listGoodsVo() throws Exception;

    GoodsVo getGoodsVoById(Long id) throws Exception;

     void reduceStock(MiaoshaGoods goods) throws Exception;
}
