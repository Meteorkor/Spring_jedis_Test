package com.meteor.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meteor.dao.Jedis_Dao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class Tester {

	@Autowired
	Jedis_Dao jedis_Dao;

	
	@Test
	public void run(){
		
		jedis_Dao.human_write("human_list","222");
		
	}
}
