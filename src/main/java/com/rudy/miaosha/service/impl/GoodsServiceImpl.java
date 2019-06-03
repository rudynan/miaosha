package com.rudy.miaosha.service.impl;

import com.rudy.miaosha.dao.MXGoodsDao;
import com.rudy.miaosha.domain.Goods;
import com.rudy.miaosha.domain.MiaoshaGoods;
import com.rudy.miaosha.service.GoodsService;
import com.rudy.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private MXGoodsDao mxGoodsDao;

    @Override
    public List<GoodsVo> listGoodsVo() throws Exception {
        List<GoodsVo> goodsVoList = mxGoodsDao.getGoodsVoList();
        return goodsVoList;
    }

    @Override
    public GoodsVo getGoodsVoById(Long id) throws Exception {
        return mxGoodsDao.getGoodsVoById(id);
    }

    @Override
    public void reduceStock(MiaoshaGoods goods) throws Exception {
        mxGoodsDao.reduceStockById(goods.getId());
    }

}
