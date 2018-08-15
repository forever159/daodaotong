package com.hibi.www.reids;

public abstract class BasePrefix implements KeyPrefix{
    private int expireSeconds;
    private String prefix;
    public BasePrefix( int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }
    public BasePrefix(String prefix) {//0代表永不过期
        this(0, prefix);
    }
    @Override
    public int getExpireSeconds() {
        return expireSeconds;
    }
    @Override
    public String getPrefix() {
        return getClass().getSimpleName()+":"+prefix;
    }

}
