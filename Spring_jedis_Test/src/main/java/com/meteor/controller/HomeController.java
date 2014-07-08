package com.meteor.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meteor.model.Human;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	/*
    @Autowired
    private RedisTemplate<String, String> template;
*/
    // inject the template as ListOperations
    // can also inject as Value, Set, ZSet, and HashOperations
    /*
	@Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;
*/
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
	
		Jedis jedis = new Jedis("localhost");
		
		Human human = new Human();
		
		human.setName( "Kim" );
		
		
		jedis.set("foo", "bar");
		
		
		jedis.close();
		return "home";
	}
	
}
