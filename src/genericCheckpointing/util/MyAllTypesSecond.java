package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject{
	private double myDoubleT;
	private float myFloatT;
	private short myShortT;
	private char myCharT;
	
	/**
	  * Empty constructor
	  */
	public MyAllTypesSecond(){
	}
		
	/**
	  * Data object constructor with fields double, float, short, char
	  * @param myDoubleTIn number to set double var to
	  * @param myFloatTIn number to set float var to
	  * @param myShortTIn number to set short var to
	  * @param myCharTIn character to set char var to
	  */
	public MyAllTypesSecond(double myDoubleTIn, float myFloatTIn, short myShortTIn, char myCharTIn){
		myDoubleT = myDoubleTIn;
		myFloatT = myFloatTIn;
		myShortT = myShortTIn;
		myCharT = myCharTIn;
	}
	
	/**
	  * Returns the double that was set for this object
	  * @return myDoubleT	
	  */
	public double getDoubleT(){
		return myDoubleT;
	}
	
	/**
	  * Sets the myDoubleT variable to this number
	  * @param num the number to set this double variable to
	  */
	public void setDoubleT(double num){
		myDoubleT = num;
	}	
	
	
	/**
	  * Returns the float that was set for this object
	  * @return myFloatT	
	  */
	public float getFloatT(){
		return myFloatT;
	}
	
	/**
	  * Sets the myFloatT variable to this number
	  * @param num the number to set this float variable to
	  */
	public void setFloatT(float num){
		myFloatT= num;
	}
	
	/**
	  * Returns the short that was set for this object
	  * @return myShortT	
	  */
	public short getShortT(){
		return myShortT;
	}
	
	/**
	  * Sets the myShortT variable to this number
	  * @param num the number to set this short variable to
	  */
	public void setShortT(short num){
		myShortT = num;
	}
	
	/**
	  * Returns the char that was set for this object
	  * @return myCharT	
	  */
	public char getCharT(){
		return myCharT;
	}
	
	/**
	  * Sets the myCharT variable to this number
	  * @param c the character to set this char variable to
	  */
	public void setCharT(char c){
		myCharT = c;
	}
	
	/**
	  * Override the equals operator to compare the actual contents
	  * of each object instead of the vector
	  * @param obj Object to be compared with
	  * @return true or false
	  */
	@Override
	public boolean equals(Object obj){
		if(myDoubleT == ((MyAllTypesSecond) obj).getDoubleT() && 
			myFloatT == ((MyAllTypesSecond) obj).getFloatT() &&
			 myShortT == ((MyAllTypesSecond) obj).getShortT() && 
				myCharT == ((MyAllTypesSecond) obj).getCharT()){
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
		return String.format("MyAllTypesSecond -> Double: " + getDoubleT() + ", Float: " + getFloatT() + ", Short: " + getShortT() + ", Char: " + getCharT());
	}
}
