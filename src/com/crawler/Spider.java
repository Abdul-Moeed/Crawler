package com.crawler;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Spider {
	
	//file storage data structure
	HashMap<String, ArrayList<String>> file_list;
	
	//search for files
	public void search(File path){
		if(!path.isDirectory()){
			ArrayList<String> temp = new ArrayList<String>();
			//if file name already exists
			if(file_list.containsKey(path.getName())){
				temp = file_list.get(path.getName());
				temp.add(path.getAbsolutePath());
				file_list.put(path.getName(), temp);
			}
			else{
				temp.add(path.getAbsolutePath());
				file_list.put(path.getName(), temp);	
			}
		}
		else{
			File[] children = path.listFiles();
			if(children == null){}
			else{
				for(int i=0; i<children.length; i++){
					search(children[i]);
				}
			}
		}
	}
	
	//dump file list
	public void dump(){
		System.out.println(file_list);
	}
}
