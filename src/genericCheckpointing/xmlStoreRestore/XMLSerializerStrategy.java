package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.FileProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class XMLSerializerStrategy implements StrategyI{
	
	/**
	  * Override check method in StrategyI to read values of an object
	  * using Java Reflection and write the corresponding values in an
	  * XML file format.
	  * @param complexType Object to get values of and write to file
	  * @param fp Fileprocessor to write to
	  * @return true To make sure that the serializing went through the 
	  * method fine
	  */
	@Override
	public Object check(Object complexType, FileProcessor fp){
		Field[] fieldList = complexType.getClass().getDeclaredFields();
		fp.writeToFile("<DPSerialization>");
		fp.writeToFile(" <complexType xsi:type=" + complexType.getClass() + ">");
		for(int j = 0; j < fieldList.length; j++){
			String fieldType = fieldList[j].getType().toString();
			String fieldName = fieldList[j].getName();
			int startIndex = 2;
			String mSubstringName = fieldName.toString().substring(startIndex, fieldName.toString().length());
			try{
				Method method = complexType.getClass().getMethod("get" + mSubstringName);
				Object result = method.invoke(complexType);
				
				if(mSubstringName.equals("Int")){
					String output = serializeInt((int)result, fieldName, fieldType);
					fp.writeToFile("  " + output);
				}
				
				else if(mSubstringName.equals("Long")){
					String output = serializeLong((long)result, fieldName, fieldType);
					fp.writeToFile("  " + output);
				}
				
				else if(mSubstringName.equals("String")){
					String output = serializeString((String)result, fieldName, fieldType);
					fp.writeToFile("  " + output);
				}
				
				else if(mSubstringName.equals("Bool")){
					String output = serializeBool((boolean)result, fieldName, fieldType);
					fp.writeToFile("  " + output);
				}
				
				else if(mSubstringName.equals("DoubleT")){
					String output = serializeDoubleT((double)result, fieldName, fieldType);
					fp.writeToFile("  " + output);
				}	
				
				else if(mSubstringName.equals("FloatT")){
					String output = serializeFloatT((float)result, fieldName, fieldType);
					fp.writeToFile("  " + output);
				}
				
				else if(mSubstringName.equals("ShortT")){
					String output = serializeShortT((short)result, fieldName, fieldType);
					fp.writeToFile("  " + output);
				}
				
				else if(mSubstringName.equals("CharT")){
					String output = serializeCharT((char)result, fieldName, fieldType);
					fp.writeToFile("  " + output);
				}
					
			}
			catch(NoSuchMethodException e){
				System.err.println("get" + mSubstringName + " does not exist");
				e.printStackTrace();
				System.exit(1);	
			}
			catch(IllegalAccessException e){
				System.err.println("Cannot access this data");
				e.printStackTrace();
				System.exit(1);
			}
			catch(InvocationTargetException e){
				System.err.println("Cannot invoke this target");
				e.printStackTrace();
				System.exit(1);
			
			}
			finally{
			
			}
		}
		fp.writeToFile(" </complexType>");
		fp.writeToFile("</DPSerialization>");
		return null;
	}
	
	/**
	  * Returns the corresponding string for an int value in XML
	  * format
	  * @param value Value of the variable
	  * @param tagName Name of the method
	  * @param fieldType Type of the variable
	  * @return sb 
	  */
	public String serializeInt(int value, String tagName, String fieldType){
		StringBuilder sb = new StringBuilder();
		sb.append("<" + tagName + " xsi:type=\"xsd:" + fieldType + "\">" +
				value + "</" + tagName + ">");
		return sb.toString();
	}
	
	/**
	  * Returns the corresponding string for a long value in XML
	  * format
	  * @param value Value of the variable
	  * @param tagName Name of the method
	  * @param fieldType Type of the variable
	  * @return sb 
	  */
	public String serializeLong(long value, String tagName, String fieldType){
		StringBuilder sb = new StringBuilder();
		sb.append("<" + tagName + " xsi:type=\"xsd:" + fieldType + "\">" +
				value + "</" + tagName + ">");
		return sb.toString();
	}
	
	/**
	  * Returns the corresponding string for a String value in XML
	  * format
	  * @param value Value of the variable
	  * @param tagName Name of the method
	  * @param fieldType Type of the variable
	  * @return sb 
	  */
	public String serializeString(String value, String tagName, String fieldType){
		StringBuilder sb = new StringBuilder();
		sb.append("<" + tagName + " xsi:type=\"xsd:string" + "\">" +
				value + "</" + tagName + ">");
		return sb.toString();
	}
	
	/**
	  * Returns the corresponding string for a boolean value in XML
	  * format
	  * @param value Value of the variable
	  * @param tagName Name of the method
	  * @param fieldType Type of the variable
	  * @return sb 
	  */
	public String serializeBool(boolean value, String tagName, String fieldType){
		StringBuilder sb = new StringBuilder();
		sb.append("<" + tagName + " xsi:type=\"xsd:" + fieldType + "\">" +
				value + "</" + tagName + ">");
		return sb.toString();
	}
	
	/**
	  * Returns the corresponding string for a double value in XML
	  * format
	  * @param value Value of the variable
	  * @param tagName Name of the method
	  * @param fieldType Type of the variable
	  * @return sb 
	  */
	public String serializeDoubleT(double value, String tagName, String fieldType){
		StringBuilder sb = new StringBuilder();
		sb.append("<" + tagName + " xsi:type=\"xsd:" + fieldType + "\">" +
			value + "</" + tagName + ">");
		return sb.toString();
	}
	
	/**
	  * Returns the corresponding string for a float value in XML
	  * format
	  * @param value Value of the variable
	  * @param tagName Name of the method
	  * @param fieldType Type of the variable
	  * @return sb 
	  */
	public String serializeFloatT(float value, String tagName, String fieldType){
		StringBuilder sb = new StringBuilder();
		sb.append("<" + tagName + " xsi:type=\"xsd:" + fieldType + "\">" +
				value + "</" + tagName + ">");
		return sb.toString();
	}
	
	/**
	  * Returns the corresponding string for a short value in XML
	  * format
	  * @param value Value of the variable
	  * @param tagName Name of the method
	  * @param fieldType Type of the variable
	  * @return sb 
	  */
	public String serializeShortT(short value, String tagName, String fieldType){
		StringBuilder sb = new StringBuilder();
		sb.append("<" + tagName + " xsi:type=\"xsd:" + fieldType + "\">" +
				value + "</" + tagName + ">");
		return sb.toString();
	}
	
	/**
	  * Returns the corresponding string for a char value in XML
	  * format
	  * @param value Value of the variable
	  * @param tagName Name of the method
	  * @param fieldType Type of the variable
	  * @return sb 
	  */
	public String serializeCharT(char value, String tagName, String fieldType){
		StringBuilder sb = new StringBuilder();
		sb.append("<" + tagName + " xsi:type=\"xsd:" + fieldType + "\">" +
				value + "</" + tagName + ">");
		return sb.toString();
	}
	
	/**
	  * Override toString() method for debugging
	  * @return Debug statement
	  */
	@Override
	public String toString(){
		return String.format("genericCheckpointing.xmlStoreRestore.XMLSerializerStrategy.");
	}
	
}
