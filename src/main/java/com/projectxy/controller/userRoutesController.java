package com.projectxy.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectxy.models.Account;
import com.projectxy.models.UserDao;

@RestController
@RequestMapping({ "/api" })
@CrossOrigin(origins = "http://localhost:3000")
public class userRoutesController {

	@Autowired
	@Qualifier("userDao")
	private UserDao<Account> dao;

	public static final Logger logger = LoggerFactory.getLogger(userRoutesController.class);

	@GetMapping("/users")
	public JSONObject get() {
		JSONObject json = new JSONObject();

		try {
			Account account = dao.get(1);

			JSONParser parser = new JSONParser();

			// logger.info(account.toString());

			json = (JSONObject) parser.parse(account.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}
}
