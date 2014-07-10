package com.meteor.controller;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.meteor.model.Human;

import redis.clients.jedis.Jedis;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	final String key_name = "human_list";

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		ArrayList<Human> human_list = new ArrayList<Human>();

		Jedis jedis = new Jedis("localhost");
			for (String h_name : jedis.lrange(key_name, 0, jedis.llen(key_name))) {//리스트 출력
				human_list.add(new Human().setName(h_name));
			}
		jedis.close();

		model.addAttribute("list", human_list);

		return "home";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write_name(Locale locale, Model model,
			@RequestParam("h_name") String h_name) {

		Jedis jedis = new Jedis("localhost");
		jedis.rpush(key_name, h_name);
		jedis.close();

		return "redirect:.";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete_name(Locale locale, Model model,
			@RequestParam("index") int index) {

		Jedis jedis = new Jedis("localhost");
		//jedis.rpush(key_name, h_name);
		//
		jedis.lset(key_name, index, "");//공백으로 만들고
		
		jedis.lrem(key_name, 1, "");//공백을 지운다
		
		
		
		
		jedis.close();

		return "redirect:.";
	}
	
}
