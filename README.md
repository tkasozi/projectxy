# projectxy


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