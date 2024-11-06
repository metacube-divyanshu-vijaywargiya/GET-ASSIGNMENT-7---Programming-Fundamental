package com.metacube;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Input for the first sparse matrix
            System.out.print("Enter the number of rows for the first sparse matrix: ");
            int rows1 = scanner.nextInt();
            System.out.print("Enter the number of columns for the first sparse matrix: ");
            int cols1 = scanner.nextInt();
            Map<Integer, Map<Integer, Integer>> values1 = new HashMap<>();
            System.out.print("Enter the number of non-zero elements for the first sparse matrix: ");
            int nonZeroCount1 = scanner.nextInt();
            for (int i = 0; i < nonZeroCount1; i++) {
                System.out.print("Enter row index, column index, and value (space-separated): ");
                int row = scanner.nextInt();
                if(row >= rows1) {
                	throw new IllegalArgumentException("Please Enter Valid Row Index.");
                }
                int col = scanner.nextInt();
                if(col >= cols1) {
                	throw new IllegalArgumentException("Please Enter Valid Column Index.");                	
                }
                int value = scanner.nextInt();
                values1.computeIfAbsent(row, k -> new HashMap<>()).put(col, value);
            }
            SparseMatrix sparseMatrix = new SparseMatrix(rows1, cols1, values1);
            System.out.println("Original Matrix: " + sparseMatrix);
            
            SparseMatrix transposeMatrix = sparseMatrix.transposeMatrix();
            System.out.println("Transpose: " + transposeMatrix);
            
            // Check if the first sparse matrix is symmetrical
            System.out.println("Is Matrix Symmetrical: " + sparseMatrix.isMatrixSymmetrical() + "\n");
            
            // Input for the second sparse matrix for addition
            System.out.print("Enter the number of rows for the first sparse matrix: ");
            int rows2 = scanner.nextInt();
            System.out.print("Enter the number of columns for the first sparse matrix: ");
            int cols2 = scanner.nextInt();
            if(rows1 != rows2 || cols1 != cols2) {
            	throw new IllegalArgumentException("Matrix must be compatible for Addition.");
            }
            
            Map<Integer, Map<Integer, Integer>> values2 = new HashMap<>();
            System.out.print("Enter the number of non-zero elements for the second sparse matrix (for addition): ");            
            int nonZeroCount2 = scanner.nextInt();
            
            for (int i = 0; i < nonZeroCount2; i++) {
                System.out.print("Enter row index, column index, and value (space-separated): ");
                int row = scanner.nextInt();
                if(row >= rows2) {
                	throw new IllegalArgumentException("Please Enter Valid Row Index.");
                }
                int col = scanner.nextInt();
                if(col >= cols2) {
                	throw new IllegalArgumentException("Please Enter Valid Column Index.");                	
                }
                int value = scanner.nextInt();
                values2.computeIfAbsent(row, k -> new HashMap<>()).put(col, value);
            }
            SparseMatrix matrix2 = new SparseMatrix(rows2, cols2, values2);
            SparseMatrix sumMatrix = sparseMatrix.additionMatrix(matrix2);
            System.out.println("Addition Matrix result: " + sumMatrix);
            
            // Input for the third sparse matrix for multiplication
            System.out.print("Enter the number of rows for the second sparse matrix (for multiplication): ");
            int rows3 = scanner.nextInt();
            System.out.print("Enter the number of columns for the second sparse matrix (for multiplication): ");
            int cols3 = scanner.nextInt();
            Map<Integer, Map<Integer, Integer>> values3 = new HashMap<>();
            System.out.print("Enter the number of non-zero elements for the multiplication matrix: ");
            int nonZeroCount3 = scanner.nextInt();
            for (int i = 0; i < nonZeroCount3; i++) {
                System.out.print("Enter row index, column index, and value (space-separated): ");
                int row = scanner.nextInt();
                if(row >= rows3) {
                	throw new IllegalArgumentException("Please Enter Valid Row Index.");
                }
                int col = scanner.nextInt();
                if(col >= cols3) {
                	throw new IllegalArgumentException("Please Enter Valid Column Index.");                	
                }
                int value = scanner.nextInt();
                values3.computeIfAbsent(row, k -> new HashMap<>()).put(col, value);
            }
            SparseMatrix matrix3 = new SparseMatrix(rows3, cols3, values3);
            SparseMatrix multiplicationMatrix = sparseMatrix.multiplicationMatrix(matrix3);
            System.out.println("Multiplication Matrix result: " + multiplicationMatrix);
            
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
