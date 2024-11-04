package com.metacube;

import java.util.HashMap;
import java.util.Map;

public class MainClass {

	public static void main(String[] args) {
		try {			
			Map<Integer, Map<Integer, Integer>> values1 = new HashMap<>();
			//values1.put(index of row where non zero number is located , Map.of(index of column where non zero number is located, actual non zero number))
			values1.put(0, Map.of(4,9));
			values1.put(1, Map.of(1,8));
			values1.put(2, Map.of(0,4,3,2));
			values1.put(3, Map.of(5,5));
			values1.put(4, Map.of(2,2));
			
			
			Map<Integer, Map<Integer, Integer>> values3 = new HashMap<>();
			values3.put(0, Map.of(0, 1, 1, 2));
			values3.put(1, Map.of(0, 2, 1, 1));
			SparseMatrix sparseMatrix1 = new SparseMatrix(2, 2, values3);
			
			SparseMatrix sparseMatrix = new SparseMatrix(5, 6, values1);
			System.out.println("Original Matrix : "+ sparseMatrix);
			
			SparseMatrix transposeMatrix = sparseMatrix.transposeMatrix();
			System.out.println("Transpose : " + transposeMatrix);
			
			System.out.println("Is Matrix Symmetrical : " + sparseMatrix1.isMatrixSymmetrical());
			
			//Addition matrix
			Map<Integer, Map<Integer, Integer>> values2 = new HashMap<>();
			values2.put(0, Map.of(4,1));
			values2.put(3, Map.of(5,2));
			SparseMatrix matrix2 = new SparseMatrix(5, 6, values2);
			SparseMatrix sumMatrix = sparseMatrix.additionMatrix(matrix2);
			System.out.println("Adition Matrix result : " + sumMatrix);
			
			//Multiplication matrix
			// Define the second sparse matrix
			Map<Integer, Map<Integer, Integer>> values4 = new HashMap<>();
			values4.put(0, Map.of(1, 3));
			values4.put(1, Map.of(0, 2, 2, 1));
			values4.put(2, Map.of(3, 4));
			values4.put(3, Map.of(4, 1));
			values4.put(4, Map.of(5, 6));
			
			SparseMatrix matrix3 = new SparseMatrix(5, 6, values4); // 6 rows, 6 columns
			System.out.println(sparseMatrix);
			System.out.println(matrix3);
			SparseMatrix multiplicationMatrix = sparseMatrix.multiplicationMatrix(matrix3);
			System.out.println("Multiplication Matrix result : " + multiplicationMatrix);
			
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
