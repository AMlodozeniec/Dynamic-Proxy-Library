package genericCheckpointing.util;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;
import java.util.NoSuchElementException;

/**
 * File operations. Opens a file for reading or writing.
 * Either reads and returns a word from a file (ignoring whitespace),
 * or, given a string, writes to a file.
 */
public class FileProcessor {

    private String filename = null;
    private String word     = null;
	private String line		= null;
    private Scanner reader  = null;
    private FileWriter writer = null;

    /**
     *
     * @args fileIn   Name of file to be opened.
     */
    public FileProcessor(String fileIn) {
        filename = fileIn;
    }

    /**
     * Opens file for reading.
     */
    public void openR() {
        try { 
            reader = new Scanner(new File(filename));
        //File stream failed.
        } catch (FileNotFoundException e) {
            System.err.println("File stream could not be created.");
            e.printStackTrace();
            System.exit(1);
        } finally {

        }

    }

    /**
     * Opens file for writing.
     * File is created if it does not already exist.
     */
    public void openW() {
        try { 
            writer = new FileWriter(filename, false);
        //File stream failed.
        } catch (IOException e) {
            System.err.println("File stream could not be created.");
            e.printStackTrace();
            System.exit(1);
        } finally {

        }

    }
	
	public String readLineFromFile() {
        try {
            line = reader.nextLine();
			if(line != null){
            	return line;
			}
			else{
				return null;
			}
        } catch (NoSuchElementException | IllegalStateException e) {
            System.err.println("Error reading file. Exiting");
            e.printStackTrace();
            System.exit(1);
            return null;
        } finally {

        }
    }
	
    /**
     * Reads and returns one word from a file. Ignores whitespace.
     *
     * @return  One word from a file.
     */
    public String readWordFromFile() {
        try {
            word = reader.next();
            return word;
        } catch (NoSuchElementException | IllegalStateException e) {
            System.err.println("Error reading file. Exiting");
            e.printStackTrace();
            System.exit(1);
            return null;
        } finally {

        }
    }

    /**
     * Checks if there are more words to read from the file. i.e. the 
     * end of the file has not been reached.
     *
     * @return  T/F if there are more words to read from the file.
     */
    public boolean hasMoreWords() {
        return reader.hasNext();
    }

    /**
     * Writes to a file.
     *
     * @args lineIn     String to be written to file.
     */
    public void writeToFile(String lineIn) {
        try {
            writer.write(lineIn + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to file. Exiting");
            e.printStackTrace();
            System.exit(1);
        } finally {

        }
    }

    /**
     * Closes file that was opened for reading.
     */
    public void closeFileR() {
        if (reader != null) reader.close();
    }

    /**
     * Closes file that was opened for writing.
     */
    public void closeFileW() {
        try {
            writer.close();
        } catch (IOException | NullPointerException e) {
            System.err.println("Error closing file stream.");
            e.printStackTrace();
            System.exit(1);
        } finally {

        }
    }

    /**
     * Override toString() method for debugging.
     *
     * @return  Debug statement w/ filename.
     */
    @Override
    public String toString() {
        return String.format("wordCount.util.FileProcessor. Filename: " + filename);
    }

}
