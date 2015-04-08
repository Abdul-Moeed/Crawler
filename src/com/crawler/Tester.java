package com.crawler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Tester {
	/*
	 * Main user class having main().
	 * To test the local crawler, do the following:
	 * 1. Give a default root path, from where the search should initially start
	 *	  in the 'default_path' object.	
	 * 2. Give a delay value, which periodically calls the thread, in 'timer' variable.
	 * 3. Run the code. GUI should appear with the files in root folder.
	 * 4. Enter any path or file in the upper field
	 * */

	public static void main(String[] args) {
		
		//create spider object, init map
		final Spider obj = new Spider();
		obj.file_list = new HashMap<String, ArrayList<String>>();

		final File default_path = new File("C:/Box/Movies/Productions");
		final long timer = 5000;
		
		SwingUtilities.invokeLater(new Runnable(){
			
			public void run() {
				JFrame window;
				
				try{
					window = new GUI("Local crawler", obj, default_path, timer);
					window.setSize(640,  480);
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}	
			}	
		});
		
	}

}
