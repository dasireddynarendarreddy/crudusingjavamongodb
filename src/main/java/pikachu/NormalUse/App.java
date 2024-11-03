package pikachu.NormalUse;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoException;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import com.mongodb.client.model.Filters;


import java.util.Scanner;

class UserData{
    static  String name;
    static int age;
    static String adharno;
    static String queryname;
   
    static Scanner sc = new Scanner(System.in);
static void opearions(int option)
{
    
    	
    	
    	
    	switch(option)
    	{
    	case 1:System.out.println("enter your name to insert...");
    	queryname="insert";
    	name=sc.next();
    	System.out.println("enter your age to insert...");
    	age=sc.nextInt();
    	System.out.println("enter your adharnumber to insert...");
    	adharno=sc.next();
    	   break;
    	case 2:
    		System.out.println("enter adharnumber to delete...");
    		queryname="delete";
    		adharno=sc.next();
    		break;
    	case 3:
    		System.out.println("enter your adharno..");
    		adharno=sc.next();
    		
    		queryname="update";
    		
    		
    		break;
    	case 4:System.exit(0);
    		
    	}
    	
    	
    
}


}

public class App {
    public static void main(String[] args) {
        // Connection URI
        String uri = "mongodb+srv://narendar:narendar915@cluster0.1l4c8.mongodb.net/mydb?retryWrites=true&w=majority&appName=Cluster0"; // Ensure to replace with your MongoDB URI

        
        MongoClient mongoClient = null;
        

        try {
            mongoClient = MongoClients.create(uri);
            System.out.println(mongoClient.listDatabases());
            System.out.println("Successfully connected to MongoDB!");

          
            MongoDatabase database = mongoClient.getDatabase("my"); 
                 System.out.println(database);
            
            MongoCollection<Document> collection = database.getCollection("users_data");
           
            while(1==1)
            {
           System.out.println("enter 1 for insert");
           System.out.println("enter 2 for delete");
           System.out.println("enter 3 for update");
           System.out.println("enter 4 for exit");
           int op=UserData.sc.nextInt();
           UserData.opearions(op);
            if(UserData.queryname.toLowerCase().equals("update")) {
            	System.out.println("you want to update your name or age...");
               String choice=UserData.sc.next();
            	if(choice.toLowerCase().equals("name"))
            	{
            		System.out.println("enter your name to update...");
            	      String name=UserData.sc.next();
            	      
            	 
            		collection.updateOne(
            			    Filters.eq("adharno", UserData.adharno), 
            			    Updates.set("name", name) 
            			);
            		System.out.println("name updated...");
            	}
            	else
            	{
            	
            		System.out.println("enter your age to update...");
          	      String age=UserData.sc.next();
            		collection.updateOne(
            			    Filters.eq("adharno", UserData.adharno), 
            			    Updates.set("age", age) 
            			);
            		System.out.println("age updated...");
            	}
            }
            else if(UserData.queryname.toLowerCase().equals("delete"))
            {
            	          collection.deleteOne(Filters.eq("adharno", UserData.adharno));
            	          System.out.println("data deleted...");
            }
            else
            	{
            		Document document = new Document("name",UserData.name)
                            .append("age", UserData.age).append("adharno",UserData.adharno);

            		collection.insertOne(document);
            		 System.out.println("Document inserted successfully: " + document);
            	}
            }
        
            

            
            
           

        } catch (MongoException e) {
            System.out.println("Check the URI once. " + e.getLocalizedMessage());
        }
        finally {
            if (mongoClient != null) {
                mongoClient.close();
                System.out.println("MongoClient closed.");
            }
        }
    }
}

