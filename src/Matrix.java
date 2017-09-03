
public class Matrix {
	private int numCols; //greater than 0
	private int numRows; //greater than 0
	private double[][] matrix; //representation of matrix, numRows x numCols
	public Matrix (int numRows, int numCols) {
		this.numCols = numCols;
		this.numRows = numRows;
		this.matrix = new double[numRows][numCols];
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
	
	/** Print the matrix @matrix to the console. Displays most cleanly when
	 * each entry of the matrix has the same number of non-zero chars, including
	 * the decimal point
	 */
	public static void print(Matrix matrix) {
		int numDashes = matrix.numCols*((longestValue(matrix))+2) + matrix.numCols + 1;
		for (int i = 0; i < matrix.numRows; i++) {
			for (int k = 0; k < numDashes; k++) {System.out.print("-");}
			System.out.print("\n");
			for (int j = 0; j < matrix.numCols; j++) {
				System.out.print("| " + matrix.matrix[i][j] + " ");
				if (j == matrix.numCols-1) {
					System.out.println("|");
				}
			}
			if (i == matrix.numRows-1) {
				for (int k = 0; k < numDashes; k++) {System.out.print("-");}
			}
		}
	}
	
	/** Return the length (including the decimal char) of the longest double value
	 *  in the matrix. Example: if M is a 1x2 matrix with values 0.55 and 0.666, 5 will be returned. */
	private static int longestValue(Matrix matrix) {
		int max = 0;
		for (int i = 0; i < matrix.numRows; i++) {
			for (int j = 0; j < matrix.numCols; j++) {
				Double val = matrix.matrix[i][j];
				String valString = val.toString();
				int length = valString.length();
				if (length > max) {
					max = length;
				}
			}
		}
		return max;
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
	
	/** Main: used for writing and testing matrix methods 
	 * @throws ImproperMatrixDimensionException */
	public static void main(String args[]) throws ImproperMatrixDimensionException {
		int numC = 4;
		int numR = 2;
		Matrix mat = new Matrix(numR, numC);
		mat.fill(new double[][] {
			{0.0, 0.1, 0.2, 0.3},
			{0.4, 0.5, 0.6, 0.7},
		});
		Matrix mat2 = new Matrix(1,1);
		mat2.fill(new double[][] {
			{0.0223},
		});
		Matrix mat3 = new Matrix(3,7);
		mat3.fill(new double[][] {
			{0.987, 0.111, 0.222, 0.333, 0.555, 0.246, 0.235},
			{0.423, 0.544, 0.612, 0.722, 0.234, 0.212, 0.321},
			{0.412, 0.544, 0.654, 0.787, 0.234, 0.212, 0.321}
		});
		Matrix mat4 = new Matrix(3,7);
		mat4.fill(new double[][] {
			{0.987, 0.111, 0.222, 0.333, 0.555, 0.246, 0.235},
			{0.423, 0.544, 0.612, 0.722, 0.234, 0.212, 0.321},
			{0.412, 0.544, 0.654, 0.787, 0.234, 0.212, 0.321}
		});
		/*
		try {
			add(mat2, mat3);
		} catch (ImproperMatrixDimensionException e) {
			System.out.println("caught matrix dimension exception");
		}
		print(add(mat3, mat4));*/
		Matrix mat5 = new Matrix(2,3);
		mat5.fill(new double[][] {
			{1.0, 2.0, 3.0},
			{4.0, 5.0, 6.0}
		});
		Matrix mat6 = new Matrix(3,2);
		mat6.fill(new double[][] {
			{7.0, 8.0},
			{9.0, 10.0},
			{11.0, 12.0}
		});
		//print(multiply(mat5,mat6));
		Matrix mat7 = new Matrix(1,1);
		mat7.fill(new double[][] {
			{9.0}
		});
		Matrix mat8 = new Matrix(1,1);
		mat8.fill(new double[][] {
			{7.0}
		});
		//print(multiply(mat7,mat8));
		Matrix mat9 = new Matrix(9,5);
		mat9.fill(new double[][] {
			{1.0, 2.0, 3.0, 4.0, 5.0},
			{6.0, 7.0, 8.0, 9.0, 10.0},
			{11.0, 12.0, 13.0, 14.0, 15.0},
			{16.0, 17.0, 18.0, 19.0, 20.0},
			{21.0, 22.0, 23.0, 24.0, 25.0},
			{26.0, 27.0, 28.0, 29.0, 30.0},
			{31.0, 32.0, 33.0, 34.0, 35.0},
			{36.0, 37.0, 38.0, 39.0, 40.0},
			{41.0, 42.0, 43.0, 44.0, 45.0},
		});
		Matrix mat10 = new Matrix(5,4);
		mat10.fill(new double[][] {
			{1.0, 2.0, 3.0, 4.0},
			{5.0, 6.0, 7.0, 8.0},
			{9.0, 10.0, 11.0, 12.0},
			{13.0, 14.0, 15.0, 16.0},
			{17.0, 18.0, 19.0, 20.0},
		});
		print(multiply(mat9, mat10));
	}
	
}