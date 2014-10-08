package com.example.hackaproj.joinr.classes;
public class Event {
	
    private String title;
    private String description;
    private int statusId;

    public Event() {
        this.title = "default";
        this.description = "";
        this.statusId = 0;
    }
    public Event(boolean b) {
        this.title = "Coding";
        this.description = "Holy crap I've been coding forever!";
        this.statusId = ((int)(Math.random()*4));
    }

    public Event(String title, String description, int statusId) {
		this.title = title;
		this.description = description;
		this.statusId = statusId;
	}

    public Event(Event e){
    	this.title = e.title;
    	this.description = e.description;
    	this.statusId = e.statusId;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatusId(){
        return statusId;
    }
    public void setStatusId(int i){
    	this.statusId = i;
    }

    @Override
	public String toString(){
		
    	return 	title + "&&" +
    			description + "&&" +
    			statusId;
     
    }
    public void importFromString(String s){
    	String[] instring= s.split("&&");
    	title = instring[0];
    	description = instring[1];
    	statusId = Integer.parseInt(instring[2]);
    }
}