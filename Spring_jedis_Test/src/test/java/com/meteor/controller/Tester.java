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
		/*
		for(int idx=0;idx<1000;idx++){
			jedis_Dao.human_write("human_list","222");	
		}
		*/
		
		StringBuffer stb = new StringBuffer();
		for(int idx=0; idx<100;idx++){
			stb.append("긴 스트링 테스트123123");
		}
		//jedis_Dao.human_write("human_list","222asdasdasdqweqwe123123123123123123}dsasdas{asdasdzxczxcafdsdvjdsfnvjdfvdf}한글 긴거 테스트");
		jedis_Dao.key_value_write("human_list", stb.toString() );
	}
}
