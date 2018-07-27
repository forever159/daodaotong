package com.hibi.www.service;

import org.springframework.util.MultiValueMap;

public interface LiveStreamService {

    public String getLiveLists(MultiValueMap<String, String> data);

}
