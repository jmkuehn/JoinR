package com.example.hackaproj.joinr.classes;

import java.util.ArrayList;

public class Group {
	private String title;
	private ArrayList<User> friends;
	
	public Group(){
		title = "";
		friends = new ArrayList<User>();
	}
	public Group(String t){
		title = t;
		friends = new ArrayList<User>();
	}
		
	public void setTitle(String s){
		this.title = s;
	}
	public String getTitle(){
		return title;
	}
	public void addFriend(User friend){
		friends.add(friend);
	}
}