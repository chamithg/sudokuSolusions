package sudoku;

import java.util.stream.IntStream;

public class VerifySudokuSolution 
{ 
   public static void main(String[] args) 
  { 
    // Sudoku example from Wikipedia 
    int[][] arr = {  
        {5, 3, 4, 6, 7, 8, 9, 1, 2},  
        {6, 7, 2, 1, 9, 5, 3, 4, 8}, 
        {1, 9, 8, 3, 4, 2, 5, 6, 7},        
        {8, 5, 9, 7, 6, 1, 4, 2, 3}, 
        {4, 2, 6, 8, 5, 3, 7, 9, 1}, 
        {7, 1, 3, 9, 2, 4, 8, 5, 6},        
        {9, 6, 1, 5, 3, 7, 2, 8, 4}, 
        {2, 8, 7, 4, 1, 9, 6, 3, 5}, 
        {3, 4, 5, 2, 8, 6, 1, 7, 9} 
    }; 
  
    // Solution should have 3 x 3 subsquares containing no duplicates 
    System.out.println(checkSudokuSolution(arr, 3)); 
    
    // Swap a couple of values to mess something up, check it again 
    int temp = arr[2][3]; 
    arr[2][3] = arr[5][6]; 
    arr[5][6] = temp; 
    System.out.println(checkSudokuSolution(arr, 3)); 
  } 





/* 
 * Check whether the given 2D array is a valid Sudoku solution. 
 *  
 * grid: the 2D array to check 
 * subSquareSize: size of the subsquares in the solution 
 * return true if the given array is a valid solution, false otherwise 
 */ 
public static boolean checkSudokuSolution(int[][] grid, int subSquareSize) { 
	  final int size = grid.length; 
	  
	  // First make sure all the values are in the right range. 
	  if (!checkValues(grid, 1, size)) 
	  { 
		  return false; 
	  } 
	
	  // Check that the rows contain no duplicate values 
	  for (int row = 0; row < size; ++row) 
	  { 
	    if (!checkRow(grid, row)) 
	    { 
	      return false; 
	    } 
	  } 
	  // Check that the columns contain no duplicate values 
	  for (int col = 0; col < size; ++col) 
	  { 
	    if (!checkColumn(grid, col)) 
	    { 
	      return false; 
	    } 
	  } 
	
	  // Check that the subsquares contain no duplicate values 
	  for (int baseRow = 0; baseRow < size; baseRow += subSquareSize) 
	  { 
	    for (int baseCol = 0; baseCol < size; baseCol += subSquareSize) 
	    { 
	      if (!checkSquare(grid, baseRow, baseCol, subSquareSize)) 
	      { 
	        return false; 
	      } 
	    } 
	  } 
	 
	  // If we made it to this point, everything is correct! 
	  return true; 
	}

	/*    
	 * * grid: the 2D array to check   
	 * * min: the smallest allowable value    
	 * * max: the largest allowable value    
	 * * return true if all values in grid are between min and max    
	 * * false if some value in the array is less than min or     
	 * * greater than max   
	 * */   
	private static boolean checkValues(int[][] grid, int min, int max)   
	
	
	{
		// Iterate over the whole grid (nested for loop )and check if all values are in given range
		for ( int row = 0; row < max; row++ ) 
		{
			for (int col = 0; col<max ;col++) 
				
			{
				// if there is any number outsid the given range, return false	
				if(grid[row][col] > max || grid[row][col] < min ) 
				{
					return false;
				}
			}
		} 
				return true;
	}
	/*    
	 * * grid: the 2D array to check    
	 * * whichRow: the row to check    
	 * * return true if the row contains no duplicate values, false otherwise    
	 */ 
			
			private static boolean checkRow(int[][] grid, int whichRow) {     
				final int size = grid.length; 
				
				// define a boolean  array with col.length number of elements. 
				// by default all items in array will be false.
				boolean[] found = new boolean[size];
				
				     
				// Iterate over each column in given row 
				for (int col = 0; col < size; ++col)     
				{    
					// set found[x - 1] to be true if we find x in the row 
					int index = grid[whichRow][col] - 1;  
   
					if (!found[index])      
					{         
						found[index] = true;       
					}  
					// found it twice, so return false
					else       
					{            
						return false;       
					}     
	        
				}
				 // didn't find any number twice, so return true
				return true; 
			}
		
	/*    
	 * * grid: the 2D array to check    
	 * * whichCol: the column to check    
	 * * return true if the column contains no duplicate values, false otherwise    
	 * */   
	
	private static boolean checkColumn(int[][] grid, int whichCol)   {      
		final int size = grid.length;  
		
		// define a boolean  array with row.length number of elements. 
		// by default all items in array will be false.
		boolean[] found = new boolean[size];
		
		// Iterate over each row in given column.
		for (int row = 0; row <size; ++row) {
			
			// set found[x - 1] to be true if we find x in the col
			int index = grid[row][whichCol] -1;
			
			if (!found[index])       {         
				found[index] = true;       
				
			}
			// found it twice, so return false
			else
			{      
				return false;      
				}       
				
			};
			 // didn't find any number twice, so return true
		return true;
	} 
	
	/* * grid: the 2D array to check    
	 * * baseRow: topmost row of the square    
	 * * baseCol: leftmost column of the square    
	 * * subSquareSize: the size of the subsquare to be checked    
	 * * return true if the square contains no duplicates, false otherwise    
	 * */   
	private static boolean checkSquare(int[][] grid, int baseRow,int baseCol, int subSquareSize)   {    
		
		// define a boolean  array with subSquareSize * subSquareSize elements. 
		// by default all items in array will be false.
		boolean[] found = new boolean[subSquareSize * subSquareSize];
		
		// Iterate over the whole sub square  (nested for loop ).
		for ( int row = baseRow; row< baseRow + subSquareSize; row++) {
			for( int col = baseCol; col < baseCol + subSquareSize; col++) {
				
				// set found[x - 1] to be true if we find x sub square.
				int index = grid[row][col] -1;
				if (!found[index])       {         
					found[index] = true;       
				}
				// found it twice, so return false
				else{      
					return false;      
					}       
					
				};
			};
			
			// didn't find any number twice, so return true
		return true;
		
		
		
	} 
};


