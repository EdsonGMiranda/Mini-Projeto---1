// Arrays Multidimensionais

class ArraysMultidimensionais {

        public static void main(String[] args) {

            // Matriz (array multidimensional)
            int[][] matriz1;
            matriz1 = new int[3][4];
            
            for(int i = 0; i < matriz1.length; i++) {
                for(int j = 0; j < matriz1[i].length; j++)
                    matriz1[i][j] = i + j;
            }
            
            System.out.println("\nImprimindo a Matriz 1:");
            for(int i = 0; i < matriz1.length; i++) {
                for(int j = 0; j < matriz1[i].length; j++)
                System.out.println(matriz1[i][j]);
            }
    
            // Inicializando uma matriz
            int[][] matriz2 = { {10,20}, {30,40,50} };
            int numeroCinquenta = matriz2[1][2];
            System.out.println("\nImprimindo 1 Elemento da Matriz 2:");
            System.out.println(numeroCinquenta);
        }
}
