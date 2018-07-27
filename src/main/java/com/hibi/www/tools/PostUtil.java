package com.hibi.www.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Component
public class PostUtil {


    private final Logger log = LoggerFactory.getLogger(PostUtil.class);
    public  String getPost(String url,HttpMethod net_type, MultiValueMap<String,String> data){
        ResponseEntity<String> exchange;
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity entity = new HttpEntity(data, headers);
        try{
            exchange = client.exchange(url, HttpMethod.POST, entity, String.class);
        }catch (RestClientException e){
            log.error(e.toString());
            throw e;
        }
        return exchange.getBody();
    }
}
