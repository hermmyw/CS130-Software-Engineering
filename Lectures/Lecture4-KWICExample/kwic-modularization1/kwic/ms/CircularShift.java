package kwic.ms;
import java.util.ArrayList;

public class CircularShift {

	/**
	 * 2D array that keeps circular shift indices (each circular shift index is a column
	 * in this 2D array, the first index is the index of the original line from line_index_ 
	 * array, the second index is the index of the starting word from chars_ array of 
	 * that circular shift)
	 *
	 */

	  private static int[][] circular_shifts_;
	  public static int[][] getCircularShifts() { return circular_shifts_; }



//	----------------------------------------------------------------------
  /**
   * This function processes arrays prepared by the input
   * function and produces circular shifts of the stored lines. A circular
   * shift is a line where the first word is removed from the begin of a line
   * and appended at the end of the line. To obtain all circular shifts of a line
   * we repeat this process until we can't obtain any new lines. Circular shifts 
   * are represented as a 2D array that keeps circular shift indices (each circular 
   * shift index is a column in this 2D array, the first index is the index of 
   * the original line from line_index_ array, the second index is the index of 
   * the starting word from chars_ array of that circular shift)
   */

	public static void execute(){
	  ArrayList word_indices = new ArrayList();
	  ArrayList line_indices = new ArrayList();

	  int[] line_index = Input.getLineIndex();
	  char[] chars = Input.getChars();

	  for(int i = 0; i < line_index.length; i++){
		word_indices.add(new Integer(line_index[i]));
		line_indices.add(new Integer(i));
		int last_index = 0;
		if(i != (line_index.length - 1))
		  last_index = line_index[i + 1];
		else
		  last_index = chars.length;
		for(int j = line_index[i]; j < last_index; j++){
		  if(chars[j] == ' '){
			word_indices.add(new Integer(j + 1));
			line_indices.add(new Integer(i));
		  }
		}
	  }
    
	  circular_shifts_ = new int[2][word_indices.size()];
	  for(int i = 0; i < word_indices.size(); i++){
		circular_shifts_[0][i] = ((Integer) line_indices.get(i)).intValue();
		circular_shifts_[1][i] = ((Integer) word_indices.get(i)).intValue();
	  }
	}


}
