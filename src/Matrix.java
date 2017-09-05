
public class Matrix {
	private int numCols; //greater than 0
	private int numRows; //greater than 0
	private double[][] matrix; //representation of matrix, numRows x numCols
	
	/** Constructor: a matrix with @numRows rows and @numCols columns */
	public Matrix (int numRows, int numCols) {
		this.numCols = numCols;
		this.numRows = numRows;
		this.matrix = new double[numRows][numCols];
	}
	
	/** Get this Matrix's matrix */
	public double[][] getMatrix() {
		return matrix;
	}
	
	/**Get the number of columns in this matrix */
	public int numberCols() {
		return numCols;
	}
	
	/**Get the number of rows in this matrix */
	public int numberRows() {
		return numRows;
	}
	
	/** Swap two rows @r1 and @r2 with matrix @matrix */
	public void swapRows(int r1, int r2) {
		double[] storedRow  = matrix[r1];
		matrix[r1] = matrix[r2];
		matrix[r2] = storedRow;
	}
	
	/** Multiply row @r by a scalar @c in matrix @matrix and 
	 * return row r*/ 
	public double[] scaleRow(int r, double c) {
		double[] scaled = new double[numCols];
		for (int j = 0; j < numCols; j++) {
			scaled[j] = c*matrix[r][j];
		}
		return scaled;
	}
	
	/** Add matrix @m1 and matrix @m2 together. 
	 * Precondition: @m1 and @m2 must have the same number of
	 * rows and the same number of columns 
	 * @throws ImproperMatrixDimensionException */
	public static Matrix add(Matrix m1, Matrix m2) throws ImproperMatrixDimensionException {
		if (m1.numCols != m2.numCols || m1.numRows != m2.numRows) {
			throw new ImproperMatrixDimensionException();
		}
		Matrix added = new Matrix(m1.numRows, m1.numCols);
		for (int i = 0; i < m1.numRows; i++) {
			for (int j = 0; j < m2.numCols; j++) {
				added.matrix[i][j] = m1.matrix[i][j] + m2.matrix[i][j];
			}
		}
		return added;
	}
	
	/** Add row @r1 and row @r2 together. 
	 * Precondition: @r1 and @r2 must have the same number of
	 * same number of columns 
	 * @throws ImproperMatrixDimensionException */
	public static double[] add(double[] r1, double[] r2) throws ImproperMatrixDimensionException {
		if (r1.length != r2.length) {
			throw new ImproperMatrixDimensionException();
		}
		double[] added = new double[r1.length];
		for (int i = 0; i < r1.length; i++) {
			added[i] = r1[i] + r2[i];
		}
		return added;
	}
	
	/** Multiply matrix @m1 and matrix @m2 together. 
	 * Precondition: the number of rows of @m2 must be
	 * the same as the number of columns as @m1
	 * @throws ImproperMatrixDimensionException */
	public static Matrix multiply(Matrix m1, Matrix m2) throws ImproperMatrixDimensionException {
		if (m2.numRows != m1.numCols) {
			throw new ImproperMatrixDimensionException();
		}
		Matrix multiplied = new Matrix(m1.numRows, m2.numCols);
		for (int i = 0; i < m1.numRows; i++) {
			for (int k = 0; k < m2.numCols; k++) {
				double sum = 0;
				for (int j = 0; j < m1.numCols; j++) {
						sum = sum + m1.matrix[i][j] * m2.matrix[j][k];
				}
				multiplied.matrix[i][k] = sum;
			}
		}
		return multiplied;
	}
	
	/** Helper method for reduce(), which reduces a matrix into ecehlon form
	 * @j is the column of matrix being worked on, @j is the row of the matrix being worked on*/
	public void reduceElim(int j, int i) throws ImproperMatrixDimensionException {
		for (int k = i+1; k < numRows;k++) {
			double valToElim = matrix[k][j];
			if (valToElim != 0.0) {
				matrix[k] = add(matrix[k], scaleRow(i,-1*valToElim));
			}
		}
	}
	
	/** Helper method for rowReduce(), which reduces a matrix into row echelon form
	 * @j is the column of matrix being worked on, @j is the row of the matrix being worked on*/
	public void rowReduceElim(int j, int i) throws ImproperMatrixDimensionException {
		for (int k = 0; k < numRows;k++) {
			if (k != i) {
				double valToElim = matrix[k][j];
				if (valToElim != 0.0) {
					matrix[k] = add(matrix[k], scaleRow(i,-1*valToElim));
				}
			}
		}
	}
	
	/** Manipulate this matrix into Echelon form
	 * @throws ImproperMatrixDimensionException */
	public void reduce() throws ImproperMatrixDimensionException {
		for (int j = 0; j < numCols; j++) {
			boolean foundNonZero = false;
			for (int i = j; i < numRows; i++) {
				double val = matrix[i][j];
				if (val != 0.0 && !foundNonZero) {
					foundNonZero = true;
					swapRows(i, j);
					matrix[j] = scaleRow(j, 1/val);
					reduceElim(j,i);
				}
			}
		}
	}
	
	/** Manipulate this matrix into row reduced Echelon form 
	 * @throws ImproperMatrixDimensionException */
	public void rowReduce() throws ImproperMatrixDimensionException {
		for (int j = 0; j < numCols; j++) {
			boolean foundNonZero = false;
			for (int i = j; i < numRows; i++) {
				double val = matrix[i][j];
				if (val != 0.0 && !foundNonZero) {
					foundNonZero = true;
					swapRows(i, j);
					matrix[j] = scaleRow(j, 1/val);
					rowReduceElim(j,i);
				}
			}
		}
		
	}
	
	/** Return true if this matrix is equal to the
	 * matrix @m1 Two matrices are equal if they have 
	 * the same dimensions and the corresponding values at 
	 * every index in the matrices are equal. */
	public boolean equals(Matrix m) {
		if (numCols != m.numCols || numRows != m.numRows) {
			return false;
		}
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (matrix[i][j] != m.matrix[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	/** Fill this matrix with the values in matrix @matrix */
	public void fill(double[][] matrix) {
		if (matrix.length != numRows || matrix[0].length != numCols) {
			return;
		}
		this.matrix = matrix;
	}
	
	/** Update the value of this matrix at row @row and column @column 
	 * with value @value */
	public void update(int row, int column, double value) {
		matrix[row][column] = value;
	}
	
	/** Print this matrix to the console. Displays most cleanly when
	 * each entry of the matrix has the same number of non-zero chars, including
	 * the decimal point
	 */
	public void print() {
		int numDashes = numCols*(this.longestValue()+2) + numCols + 1;
		for (int i = 0; i < numRows; i++) {
			for (int k = 0; k < numDashes; k++) {System.out.print("-");}
			System.out.print("\n");
			for (int j = 0; j < numCols; j++) {
				System.out.print("| " + matrix[i][j] + " ");
				if (j == numCols-1) {
					System.out.println("|");
				}
			}
			if (i == numRows-1) {
				for (int k = 0; k < numDashes; k++) {System.out.print("-");}
			}
		}
	}
	
	/** Return the length (including the decimal char) of the longest double value
	 *  in this matrix. Example: if M is a 1x2 matrix with values 0.55 and 0.666, 5 will be returned. */
	private int longestValue() {
		int max = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				Double val = matrix[i][j];
				String valString = val.toString();
				int length = valString.length();
				if (length > max) {
					max = length;
				}
			}
		}
		return max;
	}
	
	/** Round the values of this matrix to n decimal places */
	public void round(int n) {
		for (int i = 0; i<numRows; i++) {
			for (int j = 0; j<numCols; j++) {
				matrix[i][j] = Math.round((matrix[i][j] * Math.pow(10, n))/Math.pow(10, n));			}
		}
	}
	
	/** Main: used for debugging matrix methods 
	 * @throws ImproperMatrixDimensionException */
	public static void main(String args[]) throws ImproperMatrixDimensionException {
		int numC = 4;
		int numR = 2;
		Matrix mat = new Matrix(numR, numC);
		mat.fill(new double[][] {
			{0.0, 0.1, 0.2, 0.3},
			{0.4, 0.5, 0.6, 0.7},
		});
	}
}