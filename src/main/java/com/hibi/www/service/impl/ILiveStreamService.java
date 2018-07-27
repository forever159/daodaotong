package com.hibi.www.service.impl;

import com.hibi.www.service.LiveStreamService;
import com.hibi.www.tools.PostUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
public class ILiveStreamService implements LiveStreamService {


    @Autowired
    PostUtil postUtil;

    @Override
    public String getLiveLists(MultiValueMap<String,String> data) {
        String url  = "http://e.vhall.com/api/vhallapi/v2/webinar/list";
        data.add("type=1","1");
        data.add("auth_type","1");
        data.add("account","v18965723");
        data.add("password","vh123456789");
        HttpMethod method = HttpMethod.POST;
        return postUtil.getPost(url,method, data);
    }
}
