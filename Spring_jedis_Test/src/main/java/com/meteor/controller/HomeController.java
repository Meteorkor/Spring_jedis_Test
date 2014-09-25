package com.meteor.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.meteor.dao.Jedis_Dao;
import com.meteor.model.Human;

/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {

	final String key_name = "human_list";

	@Autowired
	Jedis_Dao jedis_dao;
	
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		ArrayList<Human> human_list = new ArrayList<Human>();

		for (String h_name :   jedis_dao.get_value_list(key_name)) {

			human_list.add(new Human().setName(h_name));// add human name
		}

		model.addAttribute("list", human_list);

		jedis_dao.print_test();
		
		
		return "home";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public Callable<String> write_name(Locale locale, Model model,
			@RequestParam("h_name") final String h_name) {
		
		return new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				jedis_dao.key_value_write(key_name, h_name);
				
				return "redirect:.";
			}
		}; 
		
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete_name(Locale locale, Model model,
			@RequestParam("index") int index) {
		
		jedis_dao.del_idx_value(key_name, index);
		return "redirect:.";
	}

	
	public void test_pp(){
		
		System.out.println("test pp");
		
	}
}
