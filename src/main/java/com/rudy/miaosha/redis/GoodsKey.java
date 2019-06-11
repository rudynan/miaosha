package com.rudy.miaosha.redis;

public class GoodsKey extends BasePrefix{
    public GoodsKey(String prefix) {
        super(prefix);
    }

    public GoodsKey(int expireSecond, String prefix) {
        super(expireSecond, prefix);
    }
}
