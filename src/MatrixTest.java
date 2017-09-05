import static org.junit.Assert.*;

import org.junit.Test;

public class MatrixTest {
	
	@Test
	public void testSwap() {
		Matrix matOneByOne = new Matrix(1,1);
		matOneByOne.fill(new double[][] {
			{1}
		});
		matOneByOne.print();
		matOneByOne.swapRows(0, 0);
		matOneByOne.print();
		Matrix mat = new Matrix(3,2);
		mat.fill(new double[][] {
			{7.0, 8.0},
			{9.0, 10.0},
			{11.0, 12.0}
		});
		mat.swapRows(1,1);
		mat.print();
		mat.swapRows(0, 1);
		mat.print();
		mat.swapRows(0, 2);
		mat.print();
	}
	
	@Test
	public void testScale() {
		Matrix mat = new Matrix(3,7);
		mat.fill(new double[][] {
			{0.987, 0.111, 0.222, 0.333, 0.555, 0.246, 0.235},
			{0.423, 0.544, 0.612, 0.722, 0.234, 0.212, 0.321},
			{0.412, 0.544, 0.654, 0.787, 0.234, 0.212, 0.321}
		});
		mat.print();
		mat.getMatrix()[0] = mat.scaleRow(0, 1);
		mat.print();
		mat.getMatrix()[1] = mat.scaleRow(1, 2);
		mat.print();
		mat.getMatrix()[2] = mat.scaleRow(2, 0);
		mat.print();
	}
	
	@Test
	public void testAdd() throws ImproperMatrixDimensionException {
		Matrix mat0 = new Matrix(3,7);
		mat0.fill(new double[][] {
			{0.987, 0.111, 0.222, 0.333, 0.555, 0.246, 0.235},
			{0.423, 0.544, 0.612, 0.722, 0.234, 0.212, 0.321},
			{0.412, 0.544, 0.654, 0.787, 0.234, 0.212, 0.321}
		});
		mat0.print();
		Matrix mat1 = new Matrix(3,7);
		mat1.fill(new double[][] {
			{0.987, 0.111, 0.222, 0.333, 0.555, 0.246, 0.235},
			{0.423, 0.544, 0.612, 0.722, 0.234, 0.212, 0.321},
			{0.412, 0.544, 0.654, 0.787, 0.234, 0.212, 0.321}
		});
		mat1.print();
		(Matrix.add(mat0, mat1)).print();
		Matrix mat2 = new Matrix(1,1);
		mat2.fill(new double[][] {
			{7.0}
		});
		try {
			(Matrix.add(mat0,mat2)).print();
		} catch (ImproperMatrixDimensionException e) {
			System.out.println("Can't add matrices of different dimensions");
		}
	}
	
	@Test
	public void testMultiply() throws ImproperMatrixDimensionException {
		Matrix mat0 = new Matrix(2,3);
		mat0.fill(new double[][] {
			{1.0, 2.0, 3.0},
			{4.0, 5.0, 6.0}
		});
		Matrix mat1 = new Matrix(3,2);
		mat1.fill(new double[][] {
			{7.0, 8.0},
			{9.0, 10.0},
			{11.0, 12.0}
		});
		(Matrix.multiply(mat0,mat1)).print();
		Matrix mat2 = new Matrix(1,1);
		mat2.fill(new double[][] {
			{9.0}
		});
		Matrix mat3 = new Matrix(1,1);
		mat3.fill(new double[][] {
			{7.0}
		});
		(Matrix.multiply(mat2, mat3)).print();
	}

	@Test
	public void testReduction() throws ImproperMatrixDimensionException {
		//tests made by comparing result of program
		//with result from wolfram alpha
		Matrix mat0 = new Matrix(3,3);
		mat0.fill(new double[][] {
			{0.0, 1.0, 1.0},
			{2.0, 0.0, -1.0},
			{0.0, -1.0, 0.0}
		});
		mat0.rowReduce();
		Matrix mat0Wolf = new Matrix(3,3);
		mat0Wolf.fill(new double[][] {
			{1.0, 0.0,0.0},
			{0.0,1.0,0.0},
			{0.0,0.0, 1.0}
		});
		assertEquals(true, mat0Wolf.equals(mat0));
		Matrix mat1 = new Matrix(3,4);
		mat1.fill(new double[][] {
			{5.0, 6.0, -4.0, -4.0},
			{4.0, -3.0, -5.0, 22.0},
			{7.0, -1.0, 6.0, 11.0}
		});
		mat1.rowReduce();
		mat1.round(2);
		mat1.print();
		Matrix mat1Wolf = new Matrix (3,4);
		mat1Wolf.fill(new double[][] {
			{1.0, 0.0, 0.0, 2.0},
			{0.0, 1.0, 0.0, -3.0},
			{0.0, 0.0, 1.0, -1.0}
		});
		assertEquals(true, mat1Wolf.equals(mat1));
	}
}
