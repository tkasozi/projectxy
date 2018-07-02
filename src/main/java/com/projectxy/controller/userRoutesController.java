package com.projectxy.controller;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.projectxy.models.Account;
import com.projectxy.models.UserDao;

@RestController
@RequestMapping({ "/api" })
@CrossOrigin(origins = "http://localhost:3000")
public class userRoutesController {
	public static final Logger logger = LoggerFactory.getLogger(userRoutesController.class);
	
	@Autowired
	@Qualifier("userDao")
	private UserDao<Account> dao; 
	
	
	@GetMapping("/users")
	public JSONObject get() {
		JSONObject json = new JSONObject();

		try {
			Account account = dao.get(0);
			
			System.out.println(account.toString());
			//JSONParser parser = new JSONParser();
			//json = (JSONObject) parser.parse(account.get(0).toString()); // only
																			// one
		} catch (Exception e){//(ParseException e) {
		 e.printStackTrace();
		}

		return json;
	}

	
	
	
	
	
	
	/**
	 * 
	 * @param jsonUser
	 * @return
	 */
	@PostMapping("/signup")
	public boolean postSignUp(@RequestBody String jsonUser){
		
		return true;
	}
	/**
	 * 
	 * @param jsonUser
	 * @return true if user exists false otherwise
	 */
	@PostMapping("/signin")
	public boolean postSignIn(@RequestBody String jsonUser) { 
		logger.debug("post");
		
		try{
		
		}catch(Exception e){
			e.printStackTrace();
		} 

		return  false;
	}
	
	//private fields
}
