package com.swapasya.repo;

import static com.swapasya.dataTypes.BookTitleProp.author;
import static com.swapasya.dataTypes.BookTitleProp.bookName;
import static com.swapasya.dataTypes.BookTitleProp.bookTitleID;
import static com.swapasya.dataTypes.BookTitleProp.noOfPages;
import static com.swapasya.dataTypes.BookTitleProp.publication;

import static org.junit.Assert.*;

import java.net.URI;

import org.bson.Document;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class SpringRestTest {
	
	public static final String REST_SERVICE_URI = "http://localhost:8080/swa/bookTitle";
	@Test
	public void getByBookTitleID()
	{
		  RestTemplate restTemplate = new RestTemplate();
	        Document bookTitle = restTemplate.getForObject(REST_SERVICE_URI+"/searchBy/20A", Document.class);
	        assertEquals( bookTitle.toString(),bookTitle.toString());
	        System.out.println(bookTitle);
	}
	
	@Test
	public void saveBookTitle()
	{
		RestTemplate restTemplate = new RestTemplate();
        Document bookTitle = new  Document("_id", "30A").append(bookName, "name4").append(bookTitleID, "30A").append(author, "authour1").append(publication, "publication1").append(noOfPages, 240);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/save/", bookTitle, Document.class);
        assertEquals("no return",0,0);
      //  System.out.println("Location : "+uri.toASCIIString());
		
	}
	
	@Test
	public void deleteBookTitle()
	{
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/delete/30A");
        assertEquals("No return", 0, 0);
	}
	
	
	/*
	 * 
	 * public class SpringRestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/Spring4MVCCRUDRestService";
     
    /* GET 
    @SuppressWarnings("unchecked")
    private static void listAllUsers(){
        System.out.println("Testing listAllUsers API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/user/", List.class);
         
        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
            }
        }else{
            System.out.println("No user exist----------");
        }
    }
     
    /* GET 
    private static void getUser(){
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI+"/user/1", User.class);
        System.out.println(user);
    }
     
    /* POST 
    private static void createUser() {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User(0,"Sarah",51,134);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/", user, User.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
 
    /* PUT 
    private static void updateUser() {
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user  = new User(1,"Tomy",33, 70000);
        restTemplate.put(REST_SERVICE_URI+"/user/1", user);
        System.out.println(user);
    }
 
    /* DELETE 
    private static void deleteUser() {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/3");
    }
 
 
    /* DELETE 
    private static void deleteAllUsers() {
        System.out.println("Testing all delete Users API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/");
    }
 
    public static void main(String args[]){
        listAllUsers();
        getUser();
        createUser();
        listAllUsers();
        updateUser();
        listAllUsers();
        deleteUser();
        listAllUsers();
        deleteAllUsers();
        listAllUsers();
    }
}
	 */

}
