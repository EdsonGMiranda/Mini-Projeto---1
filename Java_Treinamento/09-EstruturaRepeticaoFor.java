// Estrutura de Repetição For

import java.util.Scanner;

class EstruturaRepeticaoFor {

     public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("Tabuada de qual número? ");
        int num = reader.nextInt();
        reader.close();

        /*
            Para o comando for, especificar:
            1) variável de controle
            2) critério de continuação
            3) incremento (ou decremento) da variável de controle
        */
        for(int i = 1; i <= 10; i++) {

            // Condicional ternário
            String cria_tabuada = (num <= 10) ? "S" : "N";

            // Condicional convencional
            if (cria_tabuada == "S")
                System.out.println(num + " * " + i + " = " + num * i);
            else 
                break;
        }
    }
}
