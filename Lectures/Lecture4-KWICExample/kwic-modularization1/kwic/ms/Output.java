package kwic.ms;

public class Output {
//	----------------------------------------------------------------------
  /**
   * This function prints the sorted shifts at the standard output.
   */

	public static void execute(){

	  int[][] alphabetized = Alphabetizing.getAlphabetized();	
	  int[] line_index = Input.getLineIndex();
	  char[] chars = Input.getChars();
	  for(int i = 0; i < alphabetized[0].length; i++){
		int line_count = alphabetized[0][i];
		int shift_begin = alphabetized[1][i];
		int line_begin = line_index[line_count];
		int line_end = 0;
		if(line_count == (line_index.length - 1))
		  line_end = chars.length;
		else
		  line_end = line_index[line_count + 1];
      
		if(line_begin != shift_begin){
		  for(int j = shift_begin; j < line_end; j++)
			System.out.print(chars[j]);
		  System.out.print(' ');
		  for(int j = line_begin; j < (shift_begin - 1); j++)
			System.out.print(chars[j]);
		}else
		  for(int j = line_begin; j < line_end; j++)
			System.out.print(chars[j]);
		System.out.print('\n');
	  }    
	}

}
