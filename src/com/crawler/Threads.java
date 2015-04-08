package com.crawler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Threads extends Thread{
	
	File path;		//store root path
	long timer;		//store scan delay
	GUI gfx;		//store GUI component
	
	//constructor
	public Threads(long timer, GUI gfx, File def_path){
		//initialize data members
		this.timer = timer;
		this.gfx = gfx;
		this.path = def_path;
	}
	
	//run method
	public void run(){
		//create spider object, initialize map
		Spider obj = new Spider();
		obj.file_list = new HashMap<String, ArrayList<String>>();		
		
		//main scan loop
		while(true){
			
			//if path not yet entered, use default path, else use entered path
			if(gfx.txtEnterPath.getText().equalsIgnoreCase("")){}
			else
				path = new File(gfx.txtEnterPath.getText());
			
			scanner(obj);		//populate map
			try {
				Thread.sleep(timer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//scan method
	public void scanner(Spider obj){
		obj.discard();		//clear map
		obj.search(path);	//start search and fill map
		gfx.print_files(obj);	//print files on GUI
		
	}
	

}
