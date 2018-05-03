package com.restapi.demo.pkgrestapi;

public class Greeting {
	private long id;
	private String name;
	
	
	public Greeting(long id, String content) {
		this.id = id;
		this.name = content;
	}
	
	public long getId() {
		return id;
	}
	
	public String getname() {
		return name;
	}
}
