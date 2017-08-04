package com.swapasya.testData;

import java.util.ArrayList;
import java.util.List;

import com.swapasya.core.ReadTxt;

public class InsertData {
	
	
	
	public void createperson() {
		
		
		
		
		
		createPerson("161117", "Sagar", "Gag", "Teacher", "Shivann col, nagar", "757575757");
		createPerson("161118", "AMit", "Paul", "Teacher", "Jaature , solapur", "655555555");
		createPerson("161119", "Rahul", "Kana", "Student", "bharat nagar, solapur", "77777777777");
		createPerson("161120"	, "Ram", "Sur", "Student", " Ram Nagar , New Delhi", "9908997688");
		createPerson("161121"	, "Shyam", "Pathak", "Student", " Vishal Nagar , New Delhi", "5555997688");
		createPerson("161122"	, "Parshya", "DIsh", "Student", " Zinagt Nagar , solapur", "111111111");
		createPerson("161123"	, "Swati", "Gajare", "Student", " Shivshahi Nagar , Solapur", "2222222222");
		createPerson("161124"	, "Avanti", "Surwase", "Student", " Shivaji Nagar , Mumbai", "6666666666");
	
	}

	
	ArrayList <ArrayList <String>> textDb;
	
	// wehn there is new file add it to path :
	//it has to add to :
		//      GUmeshPro\war\WEB-INF\classes\   your file
	
	public void createBookFromCSV () throws ClassNotFoundException {
		
		textDb = new ReadTxt ().readT ("\\TB.csv");
		textDb.get(0).get(0);
		
		StringBuffer s = new StringBuffer();
		
		for (ArrayList <String> row : textDb) {
			
			
			String bookID = row.get(0);
			if (bookID.equals("BookID")) {
				continue;
			}
			s.append("\"" + bookID + "\", ");
			
			//System.out.println(row);
			String clgName = row.get(1);
			String isbn = row.get(2);
			String bookName = row.get(3);
			String authour = row.get(4);
			String publication = row.get(5);
			String price = row.get(6);
			
			String category = row.get(8);
			String noOfPages = row.get(14);
			String language = row.get(15);
			

			
			
			List<String> tags = new ArrayList <String> ();
			ArrayList <Category> ctags = new ArrayList <Category> ();
			for (int i=17 ; i>10; i++) {
				try {
					ctags.add(new Category(row.get(i)));
				} catch (IndexOutOfBoundsException e) {
					System.out.println("indexxx");
					break;
				}

			}
			
			if (price.isEmpty() || price == "") {
				price = "0";
			}
			
			if (noOfPages.isEmpty() || noOfPages.equals("")) {
				noOfPages = "0";
			}
			
			System.out.println(bookID +" " + category + " " + noOfPages + " " + language );
			
			gen.addBook(bookID, isbn, bookName, authour, category, publication,  Integer.parseInt(price) , null, null, ctags, Integer.parseInt(noOfPages), language, 0, null, null);

		}
		
	//	System.out.println(s.toString());
		
	}
	
	public void setRules () {
		gen.roleDefine("Student", "Study");
		gen.roleDefine("Teacher", "Teach");
		
		gen.categoryWiseRules("Study", "Book",  2, 7, 4, 5);
		gen.categoryWiseRules("Study", "Magazine",  1, 4, 4, 2);
		gen.categoryWiseRules("Study", "CD",  1, 4, 4, 2);
		
		gen.categoryWiseRules("Teach", "Book",  4, 7, 6, 0);
		gen.categoryWiseRules("Teach", "Magazine",  2, 7, 6, 0);
		gen.categoryWiseRules("Teach", "CD",  2, 7, 6, 0);
		
		gen.issueWiseRules("Study", "BookBank",  2, 7, (float) 1.0);
		gen.issueWiseRules("Study", "GATE",  2, 7, (float) 1.0);
	}
		


}
