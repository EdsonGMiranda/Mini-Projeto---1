// Estrutura de Repetição While

import java.util.Scanner;

class EstruturaRepeticaoWhile {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("Tabuada de qual número? ");
        int num = reader.nextInt();
        reader.close();

        int i = 1;
        while(i <= 10) {
            System.out.println(num + " * " + i + " = " + num * i);
            i++;
        }

        /* 
           System.out.println(num + " * 1 = " + num * 1);
           System.out.println(num + " * 2 = " + num * 2);
           System.out.println(num + " * 3 = " + num * 3);
           System.out.println(num + " * 4 = " + num * 4);
           System.out.println(num + " * 5 = " + num * 5); 
        */
    }
}
