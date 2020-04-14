package kwic.parnas;
// -*- Java -*-
/*
 * <copyright>
 * 
 *  Copyright (c) 2002
 *  Institute for Information Processing and Computer Supported New Media (IICM),
 *  Graz University of Technology, Austria.
 * 
 * </copyright>
 * 
 * <file>
 * 
 *  Name:    Output.java
 * 
 *  Purpose: Output prints sorted lines in a nice format
 * 
 *  Created: 24 Sep 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    Output prints sorted lines in a nice format
 * </file>
*/

/*
 * $Log$
*/

/**
 *  An instance of the Output class prints sorted lines in nice format.
 *  @author  dhelic
 *  @version $Id$
*/

public class Output{

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Constructors
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Methods
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Prints the lines at the standard output.
 * @param alphabetizer source of the sorted lines
 */

  public void print(Alphabetizer alphabetizer, CircularShift cs){
    for(int i = 0; i < cs.getLineCount(); i++)
      System.out.println(cs.getLineAsString(alphabetizer.ith(i)));
  }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
