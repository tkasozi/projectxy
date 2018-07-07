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
	
	
	  fetch("http://localhost:8080/api/user/"+this.state.username, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    username: this.state.username,
                    password: this.state.password
                })
            })
            .then(res => res.json())
            .then(flag => {
                flag? console.log("passed"): console.log("failed");
            });