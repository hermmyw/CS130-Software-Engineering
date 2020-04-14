package kwic.ms;

public class MasterControl {

//	----------------------------------------------------------------------
  /**
   * This function controls all other functions in the system. It implements
   * the sequence of calls to other functions to obtain the desired functionality
   * of the system. Before any other function is called, main function checks the 
   * command line arguments. The program expects exactly one command line argument
   * specifying the name of the file that contains the data. If the program have
   * not been started with proper command line arguments, main function exits
   * with an error message. Otherwise, the input function is called first to read the 
   * data from the file. After that the circularShift and alphabetizing 
   * functions are called to produce and sort the shifts respectivelly. Finally, the output
   * function prints the sorted shifts at the standard output.
   * @param args command line argumnets
   */

	public static void main(String[] args){
	  if(args.length != 1){
		System.err.println("KWIC Usage: java kwic.ms.MasterControl file_name");
		System.exit(1);
	  }
	  Input.execute(args[0]);
	  CircularShift.execute();
	  Alphabetizing.execute();
	  Output.execute();
	}

}
