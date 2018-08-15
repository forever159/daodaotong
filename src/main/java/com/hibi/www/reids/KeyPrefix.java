package com.hibi.www.reids;

public interface KeyPrefix {
    int getExpireSeconds();//过期时间
    String getPrefix();
}
