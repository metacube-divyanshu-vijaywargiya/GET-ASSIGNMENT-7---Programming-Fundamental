package com.metacube;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestClass {
	private SparseMatrix matrix1;
    private SparseMatrix matrix2;
    private SparseMatrix matrix3;
    private SparseMatrix matrix4;

    @BeforeEach
    public void setUp() {
        // Initializing matrices initially for all tests
        Map<Integer, Map<Integer, Integer>> values1 = new HashMap<>();
        values1.put(0, Map.of(1, 2));
        values1.put(1, Map.of(0, 3));
        matrix1 = new SparseMatrix(2, 2, values1); // Matrix= [[0, 2], [3, 0]]

        Map<Integer, Map<Integer, Integer>> values2 = new HashMap<>();
        values2.put(0, Map.of(0, 1, 1, 1));
        values2.put(1, Map.of(0, 1));
        matrix2 = new SparseMatrix(2, 2, values2); // Matrix= [[1, 1], [1, 0]]

        Map<Integer, Map<Integer, Integer>> values3 = new HashMap<>();
        values3.put(0, Map.of(0, 1, 1, 2));
        values3.put(1, Map.of(0, 2, 1, 1));
        matrix3 = new SparseMatrix(2, 2, values3); // this is Symmetrical matrix= [[1, 2], [2, 1]]

        Map<Integer, Map<Integer, Integer>> values4 = new HashMap<>();
        values4.put(0, Map.of(0, 1, 1, 2));
        values4.put(1, Map.of(0, 3, 1, 4));
        matrix4 = new SparseMatrix(2, 2, values4); // this is nonSymmetrical matrix= [[1, 2], [3, 4]]
    }
    
    @Test
    public void testTransposeMatrix() {
        SparseMatrix transposedMatrix = matrix1.transposeMatrix();
        assertEquals(2, transposedMatrix.rows);
        assertEquals(2, transposedMatrix.columns);
        //assertEquals(3, transposedMatrix.values.get(1).get(0));
        //assertEquals(2, transposedMatrix.values.get(0).get(1));
    }
    
    @Test
    public void testIsMatrixSymmetrical() {
        assertTrue(matrix3.isMatrixSymmetrical());
        assertFalse(matrix4.isMatrixSymmetrical());
    }

}
