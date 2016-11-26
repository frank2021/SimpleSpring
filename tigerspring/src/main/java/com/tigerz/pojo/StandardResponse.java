package com.tigerz.pojo;


public class StandardResponse {
	private String jsonData = "{}";
	private String isOK = "yes";
	private String errorMessage = "";
	
	public StandardResponse(){
		
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getIsOK() {
		return isOK;
	}

	public void setIsOK(String isOK) {
		this.isOK = isOK;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
