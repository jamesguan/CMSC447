/***
 * MongoDB.java
 * The original java backend for the project.
 * The functions had basic functionality. It would
 * change and update the database. However, since
 * we switched our model due to compatibility issues, this
 * program is now out dated. We have since then changed our
 * AWS instance and have a new ip address as well as credentials
 * for the database. 
 * 
 */

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.DeleteOneModel;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.WriteModel;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.gridfs.*;
import com.mongodb.client.gridfs.model.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.io.*;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import com.mongodb.ServerAddress;
import java.util.Scanner;

public class MongoDB {

	// Uploads a file to the database
	// along with user inputed meta data
	public static void upload(GridFSBucket gridFSBucket){
	
		try {

		    
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Enter the exact file name (with file extension): ");
		    String name1 = scan.next();
			
		    InputStream streamToUploadFrom = new FileInputStream(new File("/Uploads/"+name1));
		    // Create some custom options
		    
			// User is prompted for values.
			System.out.println("Enter the malware name: ");
		    String name = scan.next();
			System.out.println("Enter the malware type: ");
			String type = scan.next();
			System.out.println("Enter comments: ");
			String comment = scan.nextLine();
			   
			// Attributes are put into meta data
			
			Document doc = new Document("Type", type)
					.append("Comments", comment);
			
		    GridFSUploadOptions options = new GridFSUploadOptions()
		                                        .chunkSizeBytes(358400)
		                                        .metadata(doc);
		    

		    // Name is set to the inputed name
		    ObjectId fileId = gridFSBucket.uploadFromStream(name, streamToUploadFrom, options);
		    
		} catch (FileNotFoundException e){
		   
			// handle exception (for no file)
			System.out.println("No file found.");
		}
	}
	
	// Downloads a file from the database
	public static void download(GridFSBucket gridFSFilesBucket){

		try {
		
			Scanner scan = new Scanner(System.in);
		
			// User is prompted for values.
			System.out.println("Enter the malware name: ");
			String name = scan.next();
	    	
			FileOutputStream streamToDownloadTo = new FileOutputStream("/Download/downloadedFile.txt");
		    GridFSDownloadOptions downloadOptions = new GridFSDownloadOptions().revision(0);
		    gridFSFilesBucket.downloadToStream(name, streamToDownloadTo, downloadOptions);
		    streamToDownloadTo.close();
		    
		} catch (IOException e) {
		    // handle exception
		}		
		
	}

	public static void insert(MongoCollection<Document> col){
	  
	   Scanner scan = new Scanner(System.in);
	   
	   System.out.println("Enter the malware name: ");
	   String name = scan.next();
	   System.out.println("Enter the malware type: ");
	   String type = scan.next();
	   System.out.println("Enter comments: ");
	   String comment = scan.next();
	   
	   Document doc = new Document("name", name)
               .append("type", type)
               .append("comment", comment);
	   
	   col.insertOne(doc);
	   
	   BasicDBObject exactQuery = new BasicDBObject();       
       exactQuery.put("name", name);
       exactQuery.put("type", type);
       exactQuery.put("comment", comment);
          
       MongoCursor<Document> cursor = col.find(exactQuery).iterator();
       
       while (cursor.hasNext()){
    	   System.out.println(cursor.next());
       }
	   
	}
	
	public static void delete(MongoCollection<Document> col){
	   
	   Scanner scan = new Scanner(System.in);
	   System.out.println("Enter the name of the malware: ");
	   String name = scan.next();
	   
	   BasicDBObject exactQuery = new BasicDBObject();       
       exactQuery.put("name", name);
          
       MongoCursor<Document> cursor = col.find(exactQuery).iterator();
       
       while (cursor.hasNext()){
    	   System.out.println(cursor.next());
       }
       
       col.findOneAndDelete(exactQuery);
	   
	}
   
	public static void showDatabase(MongoCollection<Document> col){
	   
       MongoCursor<Document> cursor = col.find().iterator();
       
       while (cursor.hasNext()){
    	   System.out.println(cursor.next());
       }
	   
	}
   
	public static void main( String args[] ) {
	
      try{
		
         // To connect to mongodb server
    	 // Old address for AWS (defunct)
         MongoClient mongoClient = new MongoClient("52.44.22.65" , 27017 );
			
         // Now connect to your databases       
         MongoDatabase database = mongoClient.getDatabase("mydb");
         System.out.println("Connect to database successfully");
         
         // Creates a GridFSBucket object to upload files
         GridFSBucket gridFSBucket = GridFSBuckets.create(database);
         
         
         
         // Get data collection 
         MongoCollection<Document> collection = database.getCollection("fs.files");
 
         // Variables:
         // Name is unique

         
         Scanner scan = new Scanner(System.in);
         int option;
         boolean quit = false;
         
         // Menu for basic functions
         while(!quit){
        	 
        	 System.out.println("");	
        	 System.out.println("Select the options below:");
        	 System.out.println("1 for viewing the database.");
        	 System.out.println("2 for inserting data into the database.");
        	 System.out.println("3 for deleting data from the database.");
        	 System.out.println("4 for uploading a data file from computer.");
        	 System.out.println("5 for downloading a data file from the database.");
        	 System.out.println("6 to quit.");	 
        	 System.out.println("");	
        	 
        	 option = scan.nextInt();
        	 if (option == 1){

        		 showDatabase(collection);

        	 } else if (option == 2){
        		 
        		 insert(collection);
        		 
        	 } else if (option == 3){
        		 
        		 delete(collection);
        	
        	 } else if (option == 4){
        		 
        		 upload(gridFSBucket);
        		
        	 } else if (option == 5){
        		 
        		 download(gridFSBucket);	 
        		 
        	 } else if (option == 6){
        		 
        		 quit = true;
        		 
        	 }
        	 
         }
         
			
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
   }
}