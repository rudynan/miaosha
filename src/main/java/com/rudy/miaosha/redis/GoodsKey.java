package com.rudy.miaosha.redis;

public class GoodsKey extends BasePrefix{
    private GoodsKey(String prefix) {
        super(prefix);
    }

    private GoodsKey(int expireSecond, String prefix) {
        super(expireSecond, prefix);
    }

    public static GoodsKey getGoodsList = new GoodsKey(60,"gl");
    public static GoodsKey getGoodsDetail = new GoodsKey(60,"gd");
}
