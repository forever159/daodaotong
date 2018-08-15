package com.hibi.www.controller.mq;


import com.hibi.www.queue.producer.ProductProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductProducer productProducer;


    @RequestMapping("/getId/{productId}")
    @ResponseBody
    public String getId(@PathVariable final  long productId){
        this.productProducer.sendMessage(productId);
        return String.valueOf(productId);
    }
}
