package com.projectxy.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	public boolean getOne(@RequestParam("username") String itemid) {
		return dao.getByUsername(itemid) != null;
	}

	@GetMapping("/get")
	public JSONObject lookUp(@RequestParam("username") String itemid) {
		JSONObject json = new JSONObject();
		JSONParser parser = new JSONParser();

		try {
			if (this.getOne(itemid)) {
				Account account = dao.getByUsername(itemid);
				json = (JSONObject) parser.parse(account.toString());
			} else {
				json = (JSONObject) parser.parse("{\"err\"" + ":\"" + "user does not exist\"}");

				throw new Exception("user does not exists");
			}

		} catch (ParseException p) {
			System.out.println("parse: " + p.getMessage());
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return json;
	}

	@PostMapping("/signup")
	public JSONObject create(@RequestBody Account acc) {
		JSONObject json = new JSONObject();
		JSONParser parser = new JSONParser();
		
		logger.info(acc.toString());
		
		try {
			dao.saveOrUpdate(acc);
			json = (JSONObject) parser.parse(acc.toString());
		} catch (ParseException p) {
			logger.error("ParseException " + p.getMessage());
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e.getMessage());
		}

		return json;
	}

	@PostMapping("/login")
	public JSONObject login(@RequestBody Account acc) {
		JSONObject json = new JSONObject();
		JSONParser parser = new JSONParser();

		try {
			if (dao.authenticate(acc.getUserName(), acc.getPassword())) { // with password
				Account account = dao.getByUsername(acc.getUserName());
				json = (JSONObject) parser.parse(account.toString());
			} else {
				json = (JSONObject) parser.parse("{\"err\"" + ":\"" + "user does not exist. Signup!!!\"}");

				throw new Exception("user does not exists. Signup!!!");
			}

		} catch (ParseException p) {
			logger.error("ParseException " + p.getMessage());
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e.getMessage());
		}

		return json;
	}
}
