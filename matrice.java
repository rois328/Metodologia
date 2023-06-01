public class Matrix {
 
  public static void main(String[] args) {
    int[][] matrix1 = {{1, 2, 3},{4, 5, 6},{7, 8, 9}};
    int[][] matrix2 = {{1, 2, 3},{4, 5, 6},{7, 8, 9}};
    int[][] matrix3 = new int[3][3];
 
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        matrix3[i][j] = matrix1[i][j] * matrix2[i][j];
 
    System.out.println("Resultado de la multiplicación de las matrices: ");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++)
        System.out.print(matrix3[i][j] + " ");
      System.out.println();
    }
  }
}






