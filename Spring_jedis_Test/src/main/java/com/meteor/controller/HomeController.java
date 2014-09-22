package com.meteor.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

		for (String h_name :   jedis_dao.list_human(key_name)) {

			human_list.add(new Human().setName(h_name));// add human name
		}
		

		model.addAttribute("list", human_list);

		return "home";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write_name(Locale locale, Model model,
			@RequestParam("h_name") String h_name) {

		jedis_dao.human_write(key_name, h_name);

		return "redirect:.";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete_name(Locale locale, Model model,
			@RequestParam("index") int index) {

		
		jedis_dao.human_delete(key_name, index);

		return "redirect:.";
	}

}
