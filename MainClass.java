package com.metacube;

import java.util.HashMap;
import java.util.Map;

public class MainClass {

	public static void main(String[] args) {
		Map<Integer, Map<Integer, Integer>> values1 = new HashMap<>();
		//values1.put(index of row where non zero number is located , Map.of(index of column where non zero number is located, actual non zero number))
		values1.put(0, Map.of(4,9));
		values1.put(1, Map.of(1,8));
		values1.put(2, Map.of(0,4,3,2));
		values1.put(3, Map.of(5,5));
		values1.put(4, Map.of(2,2));
		
		SparseMatrix sparseMatrix = new SparseMatrix(5, 6, values1);
		System.out.println("Original Matrix : "+ sparseMatrix);
		
		SparseMatrix transposeMatrix = sparseMatrix.transposeMatrix();
		System.out.println("Transpose : " + transposeMatrix);
		
		System.out.println("Is Matrix Symmetrical : " + sparseMatrix.isMatrixSymmetrical());
		
		
		Map<Integer, Map<Integer, Integer>> values2 = new HashMap<>();
		values2.put(0, Map.of(4,1));
		values2.put(3, Map.of(5,2));
		SparseMatrix matrix2 = new SparseMatrix(5, 6, values2);
		SparseMatrix sumMatrix = sparseMatrix.additionMatrix(matrix2);
		System.out.println("Adition Matrix result : " + sumMatrix);
		
		
		
	}

}
