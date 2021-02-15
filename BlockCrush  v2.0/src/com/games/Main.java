package com.games;

import javax.swing.*;
class Main{
	public static void main(String[] args){
		JFrame object=new JFrame();
		GameLogic gamePlay=new GameLogic();
		int WindowWidth=600;
		int WindowHeight=600;
		object.setTitle("Block Crush");
		object.setBounds(10,10,WindowWidth,WindowHeight);
		object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		object.setVisible(true);
		object.setFocusable(false);
		object.setResizable(false);
		//have to import the jpanel to add gamePlay
		//to JFrame object
		object.add(gamePlay);


	}
}