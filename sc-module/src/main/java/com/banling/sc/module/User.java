package com.banling.sc.module;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4605505898375950513L;
	
	private int id;	
	private String userName;	
	private Date curDate;
	
	public User(){}
	
	public User(int id,String userName,Date curDate){
		this.id=id;
		this.userName=userName;
		this.curDate=curDate;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCurDate() {
		return curDate;
	}

	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}

	

}
