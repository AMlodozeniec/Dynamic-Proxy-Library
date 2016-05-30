package genericCheckpointing.driver;

import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.server.RestoreI;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;
import genericCheckpointing.xmlStoreRestore.StrategyI;
import genericCheckpointing.xmlStoreRestore.XMLSerializerStrategy;
import genericCheckpointing.xmlStoreRestore.XMLDeserializerStrategy;

import java.util.Vector;
import java.lang.reflect.InvocationHandler;
import java.util.UUID;
import java.util.Random;

public class Driver{
	
	/**
	  * Determines whether user asked for serializing and deserializing or just 
	  * deserializing. If both were asked, program will generate two objects with
	  * random values, print them to a file, and store them in a vector using 
	  * java reflection and a dynamic proxy. Then, the program will read that same
	  * file and generically create its own objects based on the output of the file.
	  * It will then check whether the objects created are the same as the ones generated
	  * And it will print how many mismatched objects there were.
	  * <p>
	  * If only deserialization was asked, it will generate the first N objects in the file
	  * and print to stdout. 
	  * @param args[0]: mode
	  * @param args[1]: number of objects to generate for both types
	  * @param args[2]: output file name
      */
	public static void main(String[] args){
		String mode = null;
		String checkpointFile = null;
		int N = 0;
		FileProcessor outputFileProc;

		//Check args
		if(args.length != 3){
			System.out.println("Usage: <mode> <num-of-objects> <output-file-name>");
			System.exit(1);
		}
		mode = args[0];
		if(!mode.equals("serdeser") && !mode.equals("deser")){
			System.out.println("First arg must be either 'serdeser' or 'deser'");
			System.exit(1);
		}
		
		try{
			N = Integer.parseInt(args[1]);
		}
		catch(NumberFormatException e){
			System.out.println(args[1] + " is not a valid arg for the number of objects.");
			e.printStackTrace();
			System.exit(1);	
		}
		finally{
			
		}
		checkpointFile = args[2];

		//Create proxyCreator
		ProxyCreator pc = new ProxyCreator();
		
		// create an instance of StoreRestoreHandler (which implements
		// the InvocationHandler
		StoreRestoreHandler handler = new StoreRestoreHandler(checkpointFile);
		
		// create a proxy
		StoreRestoreI sProxy = (StoreRestoreI) pc.createProxy(
									 new Class[] {
									     StoreI.class, RestoreI.class
									 }, 
									 handler
									 );
			
		//Create and open outputfile
		outputFileProc = new FileProcessor(checkpointFile);
		
		//Create vector to store serialized objects
		Vector<SerializableObject> vector_old = new Vector<SerializableObject>();
		
		//Create StrategyI object
		StrategyI xmlStrategy = null;
		
		//Serialize part
		if(mode.equals("serdeser")){
			xmlStrategy = new XMLSerializerStrategy();
			handler.openFileToWrite();
			
			MyAllTypesFirst myFirst;
			MyAllTypesSecond mySecond;
			boolean boolVal = true; //Used to swap boolean value
			Random r = new Random(); //Used to generate random chars
			for (int i = 0; i < N; i++) {
				char character = (char)(r.nextInt(26) + 'a'); //Generates random char every iteration
				myFirst = new MyAllTypesFirst((int)(Math.random() * 5000 + 1), (long)(Math.random() * 100000 + 8787), "work you buffoon", boolVal);
			    mySecond = new MyAllTypesSecond((double)(Math.random() * 5000 + 1), (float) (Math.random() * 3000 + 78), (short)(Math.random() * 2000 + 100), character);
				
				vector_old.add(myFirst);
				vector_old.add(mySecond);
				((StoreI) sProxy).writeObj(myFirst, xmlStrategy, checkpointFile);	
				((StoreI) sProxy).writeObj(mySecond, xmlStrategy, checkpointFile);
				boolVal = !boolVal; //Swaps boolean value each iteration
			}
			handler.closeFileToWrite();
		}
		
		//Deserialize part
		xmlStrategy = new XMLDeserializerStrategy();
		handler.openFileToRead();
			
		//Create vector for deserialized objects
		Vector<SerializableObject> vector_new = new Vector<SerializableObject>();
		
		SerializableObject myRecordRet = null;
			
		// create a vector to store the returned ojects
		for (int j = 0; j < 2 * N; j++) {
		    myRecordRet = ((RestoreI) sProxy).readObj(xmlStrategy, checkpointFile);
			
		    vector_new.add(myRecordRet);
			if(mode.equals("deser")){
				System.out.println(myRecordRet);
			}
		}
		
		handler.closeFileToRead();
		
		//Checks how many mismatched objects there are
		if(mode.equals("serdeser")){	
			int wrongCounter = 0;
			for(int i = 0; i < 2*N; i++){
				boolean flag = true;
				flag = vector_old.get(i).equals(vector_new.get(i));
				if(!flag){
					wrongCounter++;
					System.out.println("Wrong matches");
					System.out.println("new: " + vector_new.get(i));
					System.out.println("old: " + vector_old.get(i));
				}
			}
			System.out.println(wrongCounter + " mismatched objects");
			
		}
		
	}
	
}
