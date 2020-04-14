package kwic.ms;

public class Alphabetizing {

	/**
	 * 2D array that keeps circular shift indices, sorted alphabetically
	 *
	 */

	  private static int[][] alphabetized_;
      public static int[][] getAlphabetized() { return alphabetized_; }
      

//	----------------------------------------------------------------------
  /**
   * This function sorts circular shifts lines alphabetically. The sorted shifts
   * are represented in the same way as the unsorted shifts with the only difference
   * that now they are ordered alphabetically. This function implements binary search
   * to sort the shifts.
   */

	public static void execute(){
	  
	  int[][] circular_shifts = CircularShift.getCircularShifts();
	  int[] line_index = Input.getLineIndex();
	  char[] chars = Input.getChars();	
	  alphabetized_ = new int[2][circular_shifts[0].length];
	  int alphabetized_count = 0;
	  int low = 0; 
	  int high = 0; 
	  int mid = 0; 
    
	  for(int i = 0; i < alphabetized_[0].length; i++){
		int line_count;
		line_count = circular_shifts[0][i];
		int shift_begin = circular_shifts[1][i];
		int line_begin = line_index[line_count];
		int line_end = 0;      
		if(line_count == (line_index.length - 1))
		  line_end = chars.length;
		else
		  line_end = line_index[line_count + 1];

		String shift = "";      
		if(line_begin != shift_begin){
		  shift += new String(chars, shift_begin, line_end - shift_begin);
		  shift += " ";
		  shift += new String(chars, line_begin,  shift_begin - line_begin - 1);
		}else
		  shift += new String(chars, line_begin, line_end - line_begin);

		low = 0; //binary search
		high = alphabetized_count - 1;
		while(low <= high){ 
		  mid = (low + high) / 2;
		  int mid_line_count = alphabetized_[0][mid];
		  int mid_shift_begin = alphabetized_[1][mid];
		  int mid_line_begin = line_index[mid_line_count];
		  int mid_line_end = 0;
		  if(mid_line_count == (line_index.length - 1))
			mid_line_end = chars.length;
		  else
			mid_line_end = line_index[mid_line_count + 1];        
        
		  String mid_line = "";        
		  if(mid_line_begin != mid_shift_begin){
			mid_line += new String(chars, mid_shift_begin, mid_line_end - mid_shift_begin);
			mid_line += " ";
			mid_line += new String(chars, mid_line_begin, mid_shift_begin - mid_line_begin - 1);
		  }else
			mid_line += new String(chars, mid_line_begin, mid_line_end - mid_line_begin);
        
		  int compared = shift.compareTo(mid_line);        
		  if(compared > 0)
			low = mid + 1;
		  else if(compared < 0)
			high = mid - 1;
		  else{
			low = mid;
			high = mid - 1;
		  }
		}

		System.arraycopy(alphabetized_[0], low, alphabetized_[0], low + 1, alphabetized_count - low);
		System.arraycopy(alphabetized_[1], low, alphabetized_[1], low + 1, alphabetized_count - low);
		alphabetized_[0][low] = line_count;
		alphabetized_[1][low] = shift_begin;
		alphabetized_count++;
	  }
	}


}
