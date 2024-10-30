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
	
	
	public boolean isMatrixSymmetrical() {
		if(rows != columns) {
			return false;
		}
		
		SparseMatrix transposeMatrix = this.transposeMatrix();
		return this.equals(transposeMatrix);	
	}
	
	  
	
	public boolean isMatrixEquals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof SparseMatrix)) {
			return false;
		}
		
		SparseMatrix otherMatrix = (SparseMatrix) obj;
		
		return this.rows == otherMatrix.rows && this.columns == otherMatrix.columns && this.values.equals(otherMatrix.values);
	}
	
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
