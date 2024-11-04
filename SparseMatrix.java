package com.metacube;

import java.util.HashMap;
import java.util.Map;

public final class SparseMatrix {
	public final int rows;
	public final int columns;
	public final Map<Integer, Map<Integer, Integer>> values;
	
	
	//constructor
	public SparseMatrix(int rows, int columns, Map<Integer, Map<Integer, Integer>> values) {
		this.rows= rows;
		this.columns = columns;
		this.values = new HashMap<>();
		
		for(Map.Entry<Integer, Map<Integer, Integer>> rowEntry :  values.entrySet()) {
			Map<Integer, Integer> columnMap = new HashMap<>();
			for(Map.Entry<Integer, Integer> columEntry : rowEntry.getValue().entrySet()) {
				if(columEntry.getValue()!=0) {
					columnMap.put(columEntry.getKey(), columEntry.getValue());
				}
			}
			this.values.put(rowEntry.getKey(), columnMap);
		}
	}
	
	//to Return the transpose of a matrix 
	public SparseMatrix transposeMatrix() {
		Map<Integer, Map<Integer, Integer>> transposedValuesMap = new HashMap<>();
		for(int row : values.keySet()) {
			for(int column : values.get(row).keySet()) {
				int value = values.get(row).get(column);
				
				transposedValuesMap.computeIfAbsent(column, k -> new HashMap<>()).put(row, value);
			}
		}
		return new SparseMatrix(columns, rows, transposedValuesMap);
	}
	
	//To check is matrix symmetrical
	public boolean isMatrixSymmetrical() {
	    if (rows != columns) {
	        return false; // A non-square matrix cant be symmetrical
	    }

	    for (int i : values.keySet()) {
	        for (int j : values.get(i).keySet()) {
	            // Check if the value at (i, j) is equal to the value at (j, i)
	            int valueAtIJ = values.get(i).getOrDefault(j, 0);
	            int valueAtJI = values.get(j).getOrDefault(i, 0);
	            if (valueAtIJ != valueAtJI) {
	                return false; // Found a mismatch
	            }
	        }
	    }
	    return true; // All corresponding values matched
	}
	
	//To perform addition of two matrices
	public SparseMatrix additionMatrix(SparseMatrix otherMatrix) {
		if(this.rows != otherMatrix.rows || this.columns != otherMatrix.columns) {
			throw new IllegalArgumentException("Matrix Dimensions must be same for addition.");
		}
		
		Map<Integer, Map<Integer, Integer>> resultMap = new HashMap<>();
		for(int row : this.values.keySet()) {
			for(int column : this.values.get(row).keySet()) {
				int value = this.values.get(row).get(column);
				resultMap.computeIfAbsent(row, k-> new HashMap<>()).put(column, value);
			}
		}
		
		for(int row: otherMatrix.values.keySet()) {
			for(int column : otherMatrix.values.get(row).keySet()) {
				int value = otherMatrix.values.get(row).get(column);
				
				resultMap.computeIfAbsent(row,  k-> new HashMap<>()).merge(column, value, Integer::sum);
				
			}
		}
		return new SparseMatrix(rows, columns, resultMap);
	}
	
	
	//to perform matrix multiplication
	public SparseMatrix multiplicationMatrix(SparseMatrix otherMatrix) {
	    if (this.columns != otherMatrix.rows) {
	        throw new IllegalArgumentException("Matrix dimensions must be compatible for multiplication.");
	    }

	    Map<Integer, Map<Integer, Integer>> resultMap = new HashMap<>();

	    // Iterate through non-zero entries of the first matrix
	    for (int i : this.values.keySet()) {
	        for (int j : this.values.get(i).keySet()) {
	            int valueA = this.values.get(i).get(j);

	         // For each non-zero entry in the first matrix, find corresponding entries in the second matrix
	            if (otherMatrix.values.containsKey(j)) {
	                for (int k : otherMatrix.values.get(j).keySet()) {
	                    int valueB = otherMatrix.values.get(j).get(k);

	                    // Calculate the product and add it to the result
	                    int product = valueA * valueB;
	                    resultMap.computeIfAbsent(i, k1 -> new HashMap<>())
	                             .merge(k, product, Integer::sum);
	                }
	            }
	        }
	    }

	    return new SparseMatrix(this.rows, otherMatrix.columns, resultMap);
	}

	//to print the matrix in correct way
	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SparseMatrix (").append(rows).append("x").append(columns).append("):\n");
		for(int i=0;i<rows;i++) {
			for(int j =0;j<columns;j++) {
				int value = values.getOrDefault(i, new HashMap<>()).getOrDefault(j, 0);
				sBuilder.append(value).append(" ");
			}
			sBuilder.append("\n");
		}
		return sBuilder.toString();
	}
}
