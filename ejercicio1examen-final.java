import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MatrizSuma {

    private int[][] matrizA;
    private int[][] matrizC;
    private int[][] matrizB;

    public MatrizSuma(String archivoMatrizA, String archivoMatrizC) {
        matrizA = cargarMatrizDesdeArchivo(archivoMatrizA);
        matrizC = cargarMatrizDesdeArchivo(archivoMatrizC);
    }

    public void calcularMatrizB() {     
        int filas = matrizA.length;
        int columnas = matrizA[0].length;
        matrizB = new int[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizB[i][j] = matrizC[i][j] - matrizA[i][j];
            }
        }
    }

    public void imprimirMatrizB() {     
        int filas = matrizB.length;
        int columnas = matrizB[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matrizB[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int[][] cargarMatrizDesdeArchivo(String archivo) {
        int[][] matriz = null;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int fila = 0;

            while ((linea = br.readLine()) != null) {
                String[] elementos = linea.split(" ");
                int columnas = elementos.length;

                if (matriz == null) {
                    matriz = new int[columnas][columnas];
                }

                for (int j = 0; j < columnas; j++) {
                    matriz[fila][j] = Integer.parseInt(elementos[j]);
                }

                fila++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matriz;
    }

    public static void main(String[] args) {
        String archivoMatrizA = "matrizA.txt";
        String archivoMatrizC = "matrizC.txt";

        MatrizSuma matrizSuma = new MatrizSuma(archivoMatrizA, archivoMatrizC);
        matrizSuma.calcularMatrizB();
        matrizSuma.imprimirMatrizB();
    }
}
