package kwic.ms;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Input {


	/**
	 * Input characters
	 *
	 */

	 private static char[] chars_;

	/**
	 * Array that keeps line indices (line index is the index of the first character of a line)
	 *
	 */

	 private static int[] line_index_;

	 public static char[] getChars() {return chars_;}
	 public static int[] getLineIndex() { return line_index_;}

//	----------------------------------------------------------------------
  /**
   * Input function reads the raw data from the specified file and stores it in the core storage.
   * If some system I/O error occurs the program exits with an error message.
   * The format of raw data is as follows. Lines are separated by the line separator
   * character(s) (on Unix '\n', on Windows '\r\n'). Each line consists of a number of
   * words. Words are delimited by any number and combination of the space chracter (' ')
   * and the horizontal tabulation chracter ('\t'). The entered data is parsed in the 
   * following way. All line separators are removed from the data, all horizontal tabulation
   * word delimiters are replaced by a single space character, and all multiple word
   * delimiters are replaced by a single space character. Then the parsed data is represented 
   * in the core as two arrays: chars_ array and line_index_ array.
   * @param file Name of input file
   */

	public static void execute(String file) {
	  try{
		CharArrayWriter writer = new CharArrayWriter();
		ArrayList line_indices = new ArrayList();
		BufferedReader reader = new BufferedReader(new FileReader(file));

		line_indices.add(new Integer(0));
		String line = reader.readLine();      
		while(line != null){
		  StringTokenizer tokenizer = new StringTokenizer(line); //default delimiters:" \t\n\r\f"
		  while(tokenizer.hasMoreTokens()){
			writer.write(tokenizer.nextToken());
			if(tokenizer.hasMoreTokens())
			  writer.write(" ");
		  }
		  line_indices.add(new Integer(writer.size()));
		  line = reader.readLine();
		}

		chars_ = writer.toCharArray();
		line_indices.remove(line_indices.size() - 1);
		line_index_ = new int[line_indices.size()];
		for(int i = 0; i < line_indices.size(); i++)
		  line_index_[i] = ((Integer) line_indices.get(i)).intValue();
	  }catch(FileNotFoundException exc){
		exc.printStackTrace();
		System.err.println("KWIC Error: Could not open " + file + "file.");
		System.exit(1);
	  }catch(IOException exc){
		exc.printStackTrace();
		System.err.println("KWIC Error: Could not read " + file + "file.");
		System.exit(1);
	  }
	}


}
