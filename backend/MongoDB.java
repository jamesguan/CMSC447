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

	public static void upload(GridFSBucket gridFSFilesBucket){
	
		try {
		    InputStream streamToUploadFrom = new FileInputStream(new File("/Download/example.txt"));
		    // Create some custom options
		    GridFSUploadOptions options = new GridFSUploadOptions()
		                                        .chunkSizeBytes(358400)
		                                        .metadata(new Document("type", "presentation"));

		    ObjectId fileId = gridFSFilesBucket.uploadFromStream("mongodb-tutorial", streamToUploadFrom, options);
	
		} catch (FileNotFoundException e){
		   // handle exception
		}
	}
	
	public static void download(GridFSBucket gridFSFilesBudget, ObjectId fileId){

		try {
		    FileOutputStream streamToDownloadTo = new FileOutputStream("/tmp/mongodb-tutorial.pdf");
		    gridFSFilesBucket.downloadToStream(fileId, streamToDownloadTo);
		    streamToDownloadTo.close();
		    System.out.println(streamToDownloadTo.toString());
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
         MongoClient mongoClient = new MongoClient("52.44.22.65" , 27017 );
			
         // Now connect to your databases       
         MongoDatabase database = mongoClient.getDatabase("mydb");
         System.out.println("Connect to database successfully");
         
         // Creates a GridFSBucket object to upload files
         GridFSBucket gridFSBucket = GridFSBuckets.create(database);
         
         
         
         // Get data collection 
         MongoCollection<Document> collection = database.getCollection("test");

         
         
         // Variables:
         // Name is unique

         
         Scanner scan = new Scanner(System.in);
         int option;
         boolean quit = false;
         
         while(!quit){
        	 
        	 System.out.println("");	
        	 System.out.println("Select the options below:");
        	 System.out.println("1 for viewing the database.");
        	 System.out.println("2 for inserting data into the database.");
        	 System.out.println("3 for deleting data from the database.");
        	 System.out.println("4 to quit.");	 
        	 System.out.println("");	
        	 
        	 option = scan.nextInt();
        	 if (option == 1){

        		 showDatabase(collection);

        	 } else if (option == 2){
        		 
        		 insert(collection);
        		 
        	 } else if (option == 3){
        		 
        		 delete(collection);
        		 
        	 } else if (option == 4){
        		 
        		 quit = true;
        		 
        	 }
        	 
         }
         
         
      
			
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
   }
}