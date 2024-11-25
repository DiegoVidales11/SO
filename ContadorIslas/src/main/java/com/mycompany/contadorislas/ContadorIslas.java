package com.mycompany.contadorislas;

public class ContadorIslas {
    
    static final int f = 6;
    static final int c = 8;

    public static void main(String[] args) {
        int[][] matriz = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 1},
            {0, 1, 1, 0, 0, 1, 0, 1},
            {0, 1, 1, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        
        System.out.println("Número de islas: "+contarIslas(matriz));
    }
    
    public static int contarIslas(int[][] matriz) {
        boolean[][] verifica = new boolean[f][c];
        int numeroDeIslas = 0;

        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                if (matriz[i][j] == 1 && !verifica[i][j]) {
                    alrededores(matriz, verifica, i, j);
                    numeroDeIslas++;
                }
            }
        }
        return numeroDeIslas;
    }
    
    public static void alrededores(int[][] matriz, boolean[][] visitado, int fila, int columna) {
        if (fila < 0 || fila >= f || columna < 0 || columna >= c || 
            matriz[fila][columna] == 0 || visitado[fila][columna]) {
            return;
        }
        visitado[fila][columna] = true;
        alrededores(matriz, visitado, fila - 1, columna);
        alrededores(matriz, visitado, fila + 1, columna);
        alrededores(matriz, visitado, fila, columna - 1);
        alrededores(matriz, visitado, fila, columna + 1); 
    }
}
