package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.FileProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class StoreRestoreHandler implements InvocationHandler{
	private FileProcessor fp;
	
	/**
	  * Takes in a string to open the FileProcessor with
	  * @param fileNameIn Filename to work with in the FileProcessor
	  */
	public StoreRestoreHandler(String fileNameIn){
		fp = new FileProcessor(fileNameIn);
	}
	
	/**
	  * Uses Dynamic proxy to determine which method is being invoked
	  * and then implements that method.
	  * @param proxy The dynamic proxy
	  * @param m Method that was invoked
	  * @param args The args that was invoked with the method
	  * @return obj
	  */
	
	public Object invoke(Object proxy, Method m, Object[]args){
		
		String methodName = m.getName();
		
		if(methodName.equals("writeObj")){
			StrategyI strategy = (StrategyI) args[1];
			Object complexType = (SerializableObject) args[0];
			strategy.check(complexType, fp);
			return true;
		}
		else if(methodName.equals("readObj")){
			StrategyI strategy = (StrategyI) args[0];
			Object obj = null;
			Object placeholder = null;
			obj = strategy.check(obj, fp);
			return obj;
		}
		
		return proxy;
	}	
	
	/**
	  * Opens file that should be read and not written to
	  */
	public void openFileToRead(){
		fp.openR();
	}
	
	/**
	  * Closes file that should be read and not written to
	  */

	public void closeFileToRead(){
		fp.closeFileR();
	}
	
	/**
	  * Opens file that should be written to
	  */

	public void openFileToWrite(){
		fp.openW();
	}
	
	/**
	  * Closes file that should be written to
	  */

	public void closeFileToWrite(){
		fp.closeFileW();
	}
	
	/**
	  * Override toString() method for debugging
	  * @return Debug statement
	  */
	@Override
	public String toString(){
		return String.format("genericCheckpointing.xmlStoreRestore.StoreRestoreHandler.");
	}
}
