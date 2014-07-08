package com.meteor.controller;

import java.net.URL;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

public class Example {
/*
    // inject the actual template
    @Autowired
    private RedisTemplate<String, String> template;

    // inject the template as ListOperations
    // can also inject as Value, Set, ZSet, and HashOperations
    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    public void addLink(String userId, String value) {
        //listOps.leftPush(userId, url.toExternalForm());
    	listOps.leftPush(userId, value );
        // or use template directly
        //template.boundListOps(userId).leftPush(url.toExternalForm());
    	template.boundListOps(userId).leftPush( value );
    }
    */
}