package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.FileProcessor;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class XMLDeserializerStrategy implements StrategyI{
	
	/**
	  * Override check method in StrategyI to read a file and parse out
	  * the corresponding values for an object of that type. It will
	  * return an object that has the same values of it using Java
	  * Reflection
	  * @param placeholder An unused object just to appease the args of
	  * the check method
	  * @param fp Fileprocessor to read from
	  * @return object Object that has correct values
	  */
	@Override
	public Object check(Object placeholder, FileProcessor fp){
		Class cls = null;
		Object obj = null;
		String firstLine = fp.readLineFromFile(); //<DPSerialization> useless
		String line = fp.readLineFromFile(); //<complexType xsi:type...
		String[] classTokens = line.split("[\"<> ]+");
		String className = classTokens[3]; //Set equal to genericCeckpointing.util.whatever
		
		try{
			cls = Class.forName(className);
			obj = cls.newInstance();
		}
		catch(ClassNotFoundException e){
				System.err.println(className + " does not exist");
				e.printStackTrace();
				System.exit(1);
		}
		catch(InstantiationException e){
			System.err.println("Cannot instantiate " + cls);
			e.printStackTrace();
			System.exit(1);
		}
		catch(IllegalAccessException e){
			System.err.println("Cannot access " + cls);
			e.printStackTrace();
			System.exit(1);
		}
		finally{
		}

		while(!(line = fp.readLineFromFile()).equals("</DPSerialization>")){
			if(line.equals(" </complexType>")){
				continue;
			}
				
			String[] tokens = line.split("[\"<> ]+");
			String typeMethod = tokens[1];
			int startIndex = 2;
			String smallMethodName = typeMethod.substring(startIndex, typeMethod.length());
			String value  = line.substring(line.indexOf(tokens[4]), line.indexOf("/") - 1); 
			Field field = null;
			Method method = null;
				
			try{
				field = cls.getDeclaredField(typeMethod);
				Class signature = field.getType();//This gets whether it is an int, double etc.
				method = cls.getMethod("set" + smallMethodName, signature);
			}
			catch(NoSuchFieldException e){
				System.err.println(typeMethod + " field does not exist");
				e.printStackTrace();
				System.exit(1);
			}
			catch(NoSuchMethodException e){
				System.err.println(smallMethodName + " method does not exist");
				e.printStackTrace();
				System.exit(1);
			}
			finally{
			}
			
			try{
				if(smallMethodName.equals("Int")){
					int valueInt = deserializeInt(value);
					method.invoke(obj, valueInt);
				}
				else if(smallMethodName.equals("String")){
					String valueString = value;
					method.invoke(obj, valueString);
				}
				else if(smallMethodName.equals("Long")){
					long valueLong = deserializeLong(value);
					method.invoke(obj, valueLong);
				}
				else if(smallMethodName.equals("Bool")){
					boolean valueBool = deserializeBool(value);
					method.invoke(obj, valueBool);
				}
				else if(smallMethodName.equals("ShortT")){
					short valueShort = deserializeShortT(value);
					method.invoke(obj, valueShort);
				}
				else if(smallMethodName.equals("DoubleT")){
					double valueDouble = deserializeDoubleT(value);
					method.invoke(obj, valueDouble);
				}
				else if(smallMethodName.equals("FloatT")){
					float valueFloat = deserializeFloatT(value);
					method.invoke(obj, valueFloat);
				}
				else if(smallMethodName.equals("CharT")){
					char valueChar = deserializeCharT(value);
					method.invoke(obj, valueChar);
				}
			}
			catch(IllegalAccessException e){
				System.err.println("Cannot access " + method);
				e.printStackTrace();
				System.exit(1);
			}
			catch(InvocationTargetException e){
				System.err.println("Cannot invoke " + obj);
				e.printStackTrace();
				System.exit(1);
			}
			finally{
			}
				
		}
		
		return obj;	
		
	}
	
	/**
	  * Converts string to an int and returns it
	  * @param s String to be converted
	  * @return int 
	  */
	
	public int deserializeInt(String s){
		return Integer.parseInt(s);
	}
	
	/**
	  * Converts string to an bool and returns it
	  * @param s String to be converted
	  * @return boolean
	  */

	public boolean deserializeBool(String s){
		return (new Boolean(s));
	}
	
	/**
	  * Converts string to a long and returns it
	  * @param s String to be converted
	  * @return long
	  */

	public long deserializeLong(String s){
		return Long.parseLong(s);
	}
	
	/**
	  * Converts string to an double and returns it
	  * @param s String to be converted
	  * @return double
	  */

	public double deserializeDoubleT(String s){
		return Double.parseDouble(s);
	}
	
	/**
	  * Converts string to an float and returns it
	  * @param s String to be converted
	  * @return float 
	  */

	public float deserializeFloatT(String s){
		return Float.parseFloat(s);
	}
	
	/**
	  * Converts string to a short and returns it
	  * @param s String to be converted
	  * @return short 
	  */

	public short deserializeShortT(String s){
		return Short.parseShort(s);
	}
	
	/**
	  * Converts string to a char and returns it
	  * @param s String to be converted
	  * @return char
	  */

	public char deserializeCharT(String s){
		return s.charAt(0);
	}
	
	/**
	  * Override toString() method for debugging
	  * @return Debug statement
	  */
	@Override
	public String toString(){
		return String.format("genericCheckpointing.xmlStoreRestore.XMLDeserializerStrategy.");
	}
}
