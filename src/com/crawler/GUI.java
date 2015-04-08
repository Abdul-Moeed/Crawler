package com.crawler;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame{

	//data members
	private static final long serialVersionUID = 1L;
	JTextField txtEnterPath;
	JScrollPane scrollPane;
	JTextArea textArea;
	
	//constructor
	public GUI(String title, final Spider obj, File default_path, long timer){
		super(title);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//search button
		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(Color.ORANGE);
		btnSearch.setBounds(217, 22, 87, 23);
		panel.add(btnSearch);
		
		//scroll-able text area to show paths
		scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 65, 563, 353);
		scrollPane.setBorder(new TitledBorder(null, "File Dump", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("DengXian", Font.PLAIN, 13));
		textArea.setForeground(Color.BLACK);
		scrollPane.setViewportView(textArea);
		
		//input path field
		txtEnterPath = new JTextField();
		txtEnterPath.setBounds(39, 11, 145, 39);
		panel.add(txtEnterPath);
		txtEnterPath.setToolTipText("Enter root search path");
		txtEnterPath.setFont(new Font("DengXian", Font.ITALIC, 11));
		txtEnterPath.setBackground(Color.WHITE);
		//txtEnterPath.setText("Enter Path");
		txtEnterPath.setColumns(10);
		txtEnterPath.setBorder(new TitledBorder(null, "Enter Path", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		//launch background thread
		Threads w_thread = new Threads(timer, this, default_path);
		w_thread.start();
		
		//add action listener to search button
		btnSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				File path = new File(txtEnterPath.getText());
				obj.discard();
				obj.search(path);
				print_files(obj);
			}
		});
	}
	
	//print updated file list of GUI
	public void print_files(Spider obj){
		
		textArea.setText("");	//clear text area
		Collection<ArrayList<String>> dump_list = obj.file_list.values();
		//if no such path exists
		if(dump_list.isEmpty()){
			textArea.append("Path does not exist.");
			return;
		}
		//dump paths
		for(Iterator<ArrayList<String>> itr = dump_list.iterator(); itr.hasNext();){
			ArrayList<String> temp = (ArrayList<String>)itr.next();
			for(int i=0; i<temp.size(); i++){
				textArea.append((String) temp.get(i));
			}
			textArea.append("\n");
		}
	}
	
}
