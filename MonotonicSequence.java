
import java.util.LinkedList;

public class MonotonicSequence {
	
	// A matrix point class representing a location in (x,y) coordinate space 
	// for a matrix , specified with integer precision.
	private static class MatrixPoint{
		private int x;  
		private int y;

		//Matrix class constructor
		public MatrixPoint( int x, int y ){
			this.x = x;
			this.y = y;
		}
		
		// get the x coordinate i.e the column 
		public int get_x() { return x; }
		
		// get the y coordinate i.e the row
		public int get_y() { return y; }

		public void set_x( int setx ) { x = setx; }
		public void set_y( int sety ) { y = sety; }
	}
	
	/**
     * This is a helper method for creating  longest increasing sequence 
     * it does so but identifying an appropriate matrix element and then 
     * checking its neighbors
     * 
     * @param input_array the 2d array  
     * @param row integer representing the row value of the matrix element
     * @param col integer representing the column value of the matrix element 
     * 
     * @return indices_point matrix point of the element from which we can 
     *         extract the row and column
     *
     * 		  
     */
	private static MatrixPoint create_sequence_helper ( int[][] input_array, 
			int row, int col ){

		MonotonicSequence.MatrixPoint indices_point = 
				new MonotonicSequence.MatrixPoint( row, col);
		
		int array_length = input_array.length;
		
		int prev_col = col - 1;
		int next_col = col + 1;
		int prev_row = row - 1;
		int next_row = row + 1;
			
		// otherwise check the previous value in row
		if( prev_col >= 0 && 
				input_array[row][col] == input_array[row][prev_col] - 1  ){

			indices_point.set_x( row );
			indices_point.set_y( col - 1 );
		}
		// check the next value in the row 
		else if( next_col < array_length  &&				
				input_array[row][col] == input_array[row][next_col] - 1 ) {
			indices_point.set_x( row );
			indices_point.set_y( next_col );
		}
		
		// check the previous value in the col 
		else if( prev_row >= 0 && 
				input_array[row][col] == input_array[ prev_row ][ col ] - 1 ){
			indices_point.set_x( prev_row );
			indices_point.set_y( col );
		}

		// check the next value in the col
		else if( next_row < array_length && 
				input_array[row][col] == input_array[next_row][col] - 1 ){
			indices_point.set_x( next_row );
			indices_point.set_y( col );
		} else {
			indices_point.set_x( -1 );
		}

		return indices_point;
	}
	
	/**
     * This method will create the longest longest sequence increasing sequence 
     * of students
     * 
     * @param input_matrix 
     * @param longest_seq_list the list with the longest sequence  
     * 		  
     */
	
	private static void create_sequence_list( int[][] input_matrix,
								  LinkedList<Integer> longest_seq_list ){
		
		LinkedList<Integer> store_matrix_values = new LinkedList<Integer>();
		int matrix_length = input_matrix.length;
		for (int i = 0; i < matrix_length; i++){
			for (int j = 0; j < matrix_length; j++){
				MatrixPoint indices = create_sequence_helper( input_matrix, i, j );
				store_matrix_values.add( input_matrix[i][j] );

				while ( indices.get_x() != -1 ){
					store_matrix_values.add( 
							input_matrix[indices.get_x()] [indices.get_y()]); 
					indices = create_sequence_helper( input_matrix, 
							indices.get_x(), indices.get_y() );
				}

				if ( store_matrix_values.size() > longest_seq_list.size()){
					longest_seq_list.clear();
					longest_seq_list.addAll( store_matrix_values );
				}
				store_matrix_values.clear();
			}
		}
	}
	
	/**
	 * Main method for testing purposes 
	 * @param args
	 */
	public static void main ( String []args ){
		
		/**
		 	This is a test matrix, 
		 	
		 */

		int[][] test_matrix = { { 4,  3, 4,  5  }, 
							    { 5,  6, 7,  12 }, 
							    { 3,  4, 8,  9 }, 
							    { 7,  8, 9,  10 } };

		LinkedList<Integer> Monotonic_sequence_list = new LinkedList<Integer>();
		
		create_sequence_list( test_matrix, Monotonic_sequence_list );

		System.out.println( Monotonic_sequence_list.toString() );
	}


}

