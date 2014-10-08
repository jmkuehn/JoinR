package com.example.hackaproj.joinr.classes;

import android.database.Cursor;
import android.graphics.Color;

import com.example.hackaproj.joinr.MainActivity;

import java.util.ArrayList;


public class User {

    private static String[] STATUS_OPTIONS = { "Join me!", "Available", "Busy",
            "Do Not Disturb" };

    //blue, green, yellow, red
    private static int[] CS_DEFAULT = { Color.rgb(76, 144, 255),
    	Color.rgb(69, 209, 77), Color.rgb(255, 221, 85),
    	Color.rgb(255, 107, 88) };
    private static int[] CS_VIVID = { Color.rgb(76, 144, 255),
    	Color.rgb(43, 190, 50), Color.rgb(255, 208, 61),
    	Color.rgb(255, 85, 68) };
    private static int[] CS_PASTEL = { Color.rgb(105, 240, 255),
    	Color.rgb(95, 228, 104), Color.rgb(255, 234, 110),
    	Color.rgb(255, 129, 108) };
    private static int[] CS_MIDNIGHT = { Color.rgb(255, 137, 48),
    	Color.rgb(188, 112, 246), Color.rgb(58, 36, 168),
    	Color.rgb(24, 10, 92) };
    private static int[] CS_COMPLEMENTARY = { Color.rgb(254, 169, 105),
    	Color.rgb(181, 110, 56), Color.rgb(56, 167, 181),
    	Color.rgb(12, 94, 105) };
    private static int[] CS_WARM = { Color.rgb(204, 148, 26),
    	Color.rgb(194, 22, 61), Color.rgb(181, 101, 32),
    	Color.rgb(204, 61, 26) };
    private static int[] CS_COOL = { Color.rgb(28, 204, 94),
    	Color.rgb(34, 181, 169), Color.rgb(28, 127, 204),
    	Color.rgb(40, 48, 194) };

    private static int[][] COLOR_SCHEMES = { CS_DEFAULT, CS_VIVID,
    	CS_PASTEL, CS_MIDNIGHT, CS_COMPLEMENTARY, CS_WARM , CS_COOL};
    private static String[] COLOR_SCHEME_NAMES = { "Original", "Vivid",
    "Pastel", "Midnight", "Complementary", "Warm", "Cool" };

    private String name; //username
    private String ID; //phone number
    private String password;
    private ArrayList<String> friends;
    private int colorScheme;
    private Event defaultEvent;
    private Event currentEvent;

    public User(boolean b) {
		this.name = "Bob Bobson";
		ID = "1234567890";
		this.password = "RandomPassword";
		this.friends = new ArrayList<String>();
		this.colorScheme = 0;
		defaultEvent = new Event();
        currentEvent = defaultEvent;
	}

    public User(String ID, String name, String password,int colorScheme, ArrayList<String> friends, Event currentEvent) {
		this.name = name;
		this.ID = ID;
		this.password = password;
		this.colorScheme = colorScheme;
		this.defaultEvent = new Event();
        this.currentEvent = defaultEvent;
		this.friends = friends;
		MainActivity.usersDB.insertUser(ID, name, password, Integer.toString(colorScheme) , friendsToString(), defaultEvent.toString(), currentEvent.toString());
	}
    
    public User(User u){
    	this.name = u.name;
		this.ID = u.ID;
		this.password = u.password;
		this.colorScheme = u.colorScheme;
		this.defaultEvent = u.defaultEvent;
        this.currentEvent = u.currentEvent;
		this.friends = u.friends;
	}
    public User() {
        this.name = "";
        this.ID = "";
        this.password = "";
        this.colorScheme = 0;
	    this.friends = new ArrayList<String>();
	    this.defaultEvent = new Event();
        this.currentEvent = this.defaultEvent;
    }

    

	public void setName(String n) {
        this.name = n;
    }
    public String getName() {
        return this.name;
    }

    public void setID(String s) {
        this.ID = s;
    }
    public String getID() {
        return this.ID;
    }

    public void setPassword(String s) {
        this.password = s;
    }
    public String getPassword() {
        return this.password;
    }

    public void setColorScheme(int i) {
        this.colorScheme = i;
    }

    public void setDefaultEvent(Event e){
    	defaultEvent = new Event(e);
    }
    public Event getDefaultEvent(){
        return defaultEvent;
    }

    public void setCurrentEvent(Event e){currentEvent = new Event(e);}
    public Event getCurrentEvent(){return this.currentEvent;}

    public int getStatusId() {
        return currentEvent.getStatusId();
    }




    public String getStatusName() {
        return STATUS_OPTIONS[getStatusId()];
    }

    public int[] getColorScheme() {
        return COLOR_SCHEMES[this.colorScheme];
    }

    public String getColorSchemeName() {
        return COLOR_SCHEME_NAMES[this.colorScheme];
    }

    public void addFriend(String s) {
        this.friends.add(s);
    }

    public ArrayList<String> getFriends(){
    	return friends;
    }
    

    public void sortFriends(){
    	SwagCalendarSorter.sort(friends, SwagCalendarSorter.userIdComparator);
    }


    public String idnumToNumber(String s){
    	String out = "";
    	out += s.substring(0, 3) + "-" + s.substring(3, 6) + "-" + s.substring(6);
    	return out;
    }
    
    public void updateUser(){
    	MainActivity.usersDB.updateUser(ID, name, password, Integer.toString(colorScheme), friendsToString(), defaultEvent.toString(), currentEvent.toString());
    }
    
    public String friendsToString(){
    	String output = "";
    	for(String s : friends){
    		output += s + "@@";
    	}
    	if(friends.size() > 0) output = output.substring(0, output.length() - 2);
    	return output;
    }
    
    public void inputFriendsFromString(String s){
    	String[] friendIDs = s.split("@@");
    	for (String f : friendIDs) friends.add(f);
    	this.sortFriends();
    }


    
    public static User importUserFromDataBase(String id){
    	User fuser = new User();
    	
    	Cursor fcursor = MainActivity.usersDB.getData(id);
    	fcursor.moveToFirst();
    	fuser.setName(fcursor.getString(fcursor.getColumnIndex(UsersDatabaseHelper.COLUMN_NAME)));
    	Event dEvent = new Event();
    	dEvent.importFromString(fcursor.getString(fcursor.getColumnIndex(UsersDatabaseHelper.COLUMN_DEFAULTEVENT)));
    	fuser.setDefaultEvent(dEvent);
    	fuser.setID(id);
    	String[] friendIDs = fcursor.getString(fcursor.getColumnIndex(UsersDatabaseHelper.COLUMN_FRIENDS)).split("@@");
        for (String f : friendIDs) fuser.addFriend(f);
        fcursor.close();
    	return fuser;
    }
}