package com.swapasya.core;

import java.util.*;
import java.io.*;

import org.springframework.data.mongodb.core.MongoOperations;

import com.swapasya.domains.*;
import com.swapasya.model.DBConnect;
import com.swapasya.repo.BookTitleRepositoryMongoDB;

public class MyClass {

	public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException {
		
		MongoOperations op=DBConnect.getConnection();
		BookTitleRepositoryMongoDB mdb=new BookTitleRepositoryMongoDB(op);

		MyClass myClass=new MyClass();
		File file=myClass.getFile("BookCatalogue.csv");


		BufferedReader bir=new BufferedReader(new FileReader(file));
		String s=null;
		ArrayList<String> getLine=new ArrayList<>();
		int noOfPages=0;
		int i=0;
		while((s=bir.readLine())!=null)
		{
			System.out.println("Inside while");
			getLine.add(s);
			String line=getLine.get(i);
			String splitted[]=line.split(",");
			BookTitle bookTitle;
		
			String tagsFromFile[]=splitted[6].split(":");
			ArrayList<String> tags=new ArrayList<>();
			
			for(String tag:tagsFromFile)
			{
				tags.add(tag);
			}
			if(splitted[7].equals("") || splitted[7]==null){
				noOfPages=0;
			}
			else
				noOfPages=Integer.parseInt(splitted[7]);
			
				bookTitle=new BookTitle(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4], splitted[5], tags,noOfPages , splitted[8], splitted[9], splitted[10]);
				mdb.insertOne(bookTitle);
				System.out.println("Inserted Successfully");
				i++;
		}

	}
	File getFile(String fileName){

		ClassLoader classLoader = getClass().getClassLoader();
		System.out.println(classLoader);
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	}
			

}
