package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject{
	private int myInt;
	private long myLong;
	private String myString;
	private boolean myBool;
	
	/**
	  * Empty constructor
	  */
	public MyAllTypesFirst(){
	}
	
	/**
	  * Sets values
	  * @param myIntIn sets int value
	  * @param myLongIn sets long value
	  * @param myStringIn sets String value
	  * @param myBoolIn sets boolean value
	  */	
	public MyAllTypesFirst(int myIntIn, long myLongIn, String myStringIn, boolean myBoolIn){
		myInt = myIntIn;
		myLong = myLongIn;
		myString = myStringIn;
		myBool = myBoolIn;
	}
	
	/**
	  * Returns the int that was set for this object
	  * @return myInt
	  */
	public int getInt(){
		return myInt;
	}
	
	/**
 	  * Sets the int variable to this number
	  * @param num the number to set this int variable to
	  */
	public void setInt(int num){
		myInt = num;
	}
	
	/**
	  * Returns the string that was set for this object
	  * @return myString
	  */
	public String getString(){
		return myString;
	}
	
	/**
 	  * Sets the string variable to this string
	  * @param s the string to set this string variable to
	  */
	public void setString(String s){
		myString = s;
	}
	
	/**
	  * Returns the long value that was set for this object
	  * @return myLong
	  */
	public long getLong(){
		return myLong;
	}
	
	/**
	* Sets the long variable to this number
	* @param num the number to set this long variable to
	*/
	public void setLong(long num){
		myLong = num;
	}
	
	/**
	  * Returns the boolean value that was set for this object
	  * @return myBool
	  */
	public boolean getBool(){
		return myBool;
	}
	
	/**
 	  * Sets the bool variable to this flag
	  * @param flag the value to set this boolean variable to
	  */
	public void setBool(boolean flag){
		myBool = flag;
	}
	
	/**
	  * Override the equals operator to compare the actual contents
	  * of each object instead of the vector
	  * @param obj Object to be compared with
	  * @return true or false
	  */
	@Override
	public boolean equals(Object obj){
		if(myBool == ((MyAllTypesFirst) obj).getBool() && 
			myLong == ((MyAllTypesFirst) obj).getLong() &&
			 myString.equals(((MyAllTypesFirst) obj).getString()) && 
				myInt == ((MyAllTypesFirst) obj).getInt()){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	  * Override toString() method for debugging
	  * @return Debug Statement
	  */
	@Override
	public String toString(){
		return String.format("MyAllTypesFirst -> Int: " + getInt() + ", Long: " + getLong() + ", Bool: " + getBool() + ", String: " + getString());
	}
}
