package com.crawler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Threads extends Thread{
	
	File path;		//store root path
	long timer;		//store scan delay
	
	//constructor
	public Threads(File path, long timer){
		//init data members
		this.path = path;
		this.timer = timer;
	}
	
	//run method
	public void run(){
		//create spider object, init map
		Spider obj = new Spider();
		obj.file_list = new HashMap<String, ArrayList<String>>();
		
		//main scan loop
		while(true){
			discard(obj);		//clear map
			System.out.println("Scanning file system ...");
			scanner(obj);		//populate map
			try {
				Thread.sleep(timer);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//scan method
	public void scanner(Spider obj){
		obj.search(path);
		obj.dump();
	}
	
	//discard method
	public void discard(Spider obj){
		obj.file_list.clear();
	}

}
