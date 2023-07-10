public class Matrix {
    private double[][] data;
    private int rows;
    private int cols;
    
    public Matrix(double[][] data) {
        this.data = data;
        this.rows = data.length;
        this.cols = data[0].length;
    }
    
    public Matrix multiply(Matrix other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("El número de columnas de la matriz A no coincide con el número de filas de la matriz B.");
        }
        
        double[][] resultData = new double[this.rows][other.cols];
        
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                for (int k = 0; k < this.cols; k++) {
                    resultData[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
        }
        
        return new Matrix(resultData);
    }
    
    public Matrix inverse() {
        if (this.rows != this.cols) {
            throw new IllegalArgumentException("La matriz no es cuadrada y no tiene inversa.");
        }
        
        double[][] augmentedMatrix = augmentMatrix(this.data, getIdentityMatrix(this.rows));
        double[][] reducedRowEchelonForm = gaussianElimination(augmentedMatrix);
        double[][] inverseData = extractInverseMatrix(reducedRowEchelonForm, this.rows);
        
        return new Matrix(inverseData);
    }
    
    private double[][] getIdentityMatrix(int n) {
        double[][] identityMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            identityMatrix[i][i] = 1;
        }
        return identityMatrix;
    }
    
    private double[][] augmentMatrix(double[][] matrixA, double[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int rowsB = matrixB.length;
        int colsB = matrixB[0].length;
        
        if (rowsA != rowsB) {
            throw new IllegalArgumentException("Las matrices no tienen el mismo número de filas.");
        }
        
        double[][] augmentedMatrix = new double[rowsA][colsA + colsB];
        
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                augmentedMatrix[i][j] = matrixA[i][j];
            }
            for (int j = 0; j < colsB; j++) {
                augmentedMatrix[i][j + colsA] = matrixB[i][j];
            }
        }
        
        return augmentedMatrix;
    }
    
    private double[][] gaussianElimination(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        for (int i = 0; i < rows; i++) {
            if (matrix[i][i] == 0) {
                int nonzeroRow = findNonzeroRow(matrix, i);
                if (nonzeroRow != -1) {
                    swapRows(matrix, i, nonzeroRow);
                } else {
                    throw new IllegalArgumentException("La matriz no tiene inversa.");
                }
            }
            
            double pivot = matrix[i][i];
            
            for (int j = i; j < cols; j++) {
                matrix[i][j] /= pivot;
            }
            
            for (int k = 0; k < rows; k++) {
                if (k != i) {
                    double factor = matrix[k][i];
                    for (int j = i; j < cols; j++) {
                        matrix[k][j] -= factor * matrix[i][j];
                    }
                }
            }
        }
        
        return matrix;
    }
    
    private int findNonzeroRow(double[][] matrix, int col) {
        int rows = matrix.length;
        
        for (int i = col; i < rows; i++) {
            if (matrix[i][col] != 0) {
                return i;
            }
        }
        
        return -1;
    }
    
    private void swapRows(double[][] matrix, int row1, int row2) {
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }
    
    private double[][] extractInverseMatrix(double[][] matrix, int n) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        double[][] inverseMatrix = new double[rows][n];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < n; j++) {
                inverseMatrix[i][j] = matrix[i][j + n];
            }
        }
        
        return inverseMatrix;
    }
    
    public void printMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.data[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        double[][] matrixData = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        
        Matrix matrixA = new Matrix(matrixData);
        Matrix matrixInverse = matrixA.inverse();
        Matrix result = matrixA.multiply(matrixInverse);
        
        System.out.println("A * A':");
        result.printMatrix();
    }
}
