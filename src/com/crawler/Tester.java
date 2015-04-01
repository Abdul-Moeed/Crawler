package com.crawler;

import java.io.File;

public class Tester {

	public static void main(String[] args) {
		//create a root for search
		File path = new File("C:/Box/Movies");
		//pass root path and scan time delay
		Thread d_thread = new Threads(path, 2000);
		//start spider
		d_thread.start();
	}

}
