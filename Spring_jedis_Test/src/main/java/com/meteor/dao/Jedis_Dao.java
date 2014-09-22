package com.meteor.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Repository
public class Jedis_Dao {

	private JedisPool pool;
	
	

private Jedis_Dao(){
		pool = new JedisPool( new JedisPoolConfig(), host_name);
		
	}
	
	/**
	 * 이름 입력
	 * @param key_name
	 * @param h_name
	 */
	public void human_write(String key_name, String h_name){
		Jedis jedis = get_Jedis();
		
		jedis.rpush(key_name, h_name);
		System.out.println("print_sss");
		
		pool.returnResource(jedis);
		
		
	}
	/**
	 * 세션 풀에서 Connection 생성
	 * @return
	 */
	private Jedis get_Jedis(){
		Jedis jedis;
			jedis = pool.getResource();
			
		return jedis;
	}
	/**
	 * 리스트 출력
	 * @param key_name
	 * @return
	 */
	public List<String> list_human(String key_name){
		
		Jedis jedis = get_Jedis();
		
		long list_len = jedis.llen(key_name);
		
		
		List<String> result_list = jedis.lrange( key_name, 0, list_len );
	
		pool.returnResource(jedis);
		
		return result_list;
		
	}
	/**
	 * 리스트 삭제
	 * @param key_name
	 * @param index
	 */
	public void human_delete(String key_name , int index){

		Jedis jedis = get_Jedis();
		jedis.lset(key_name, index, "");// 공백으로 만들고
		jedis.lrem(key_name, 1, "");// 공백을 지운다
		
		pool.returnResource(jedis);
		
	}
	
	
}
