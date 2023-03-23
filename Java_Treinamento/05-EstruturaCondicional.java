// Estrutura Condicional

import java.util.Scanner;

class Condicional {

    public static void main(String[] args) {

        // Cria o scanner do terminal
        Scanner reader = new Scanner(System.in);

        // Imprime mensagem no terminal
        System.out.println("Qual a sua idade? ");

        // Leitura do valor digitado
        int idade = reader.nextInt();
        reader.close();

        // Condicional
        if(idade < 0) {
            System.out.println("Idade inválida");
            System.out.println("Tente novamente");
        } else if(idade < 16)
                  System.out.println("Não pode votar");
               else if(idade < 18)
                       System.out.println("Voto opcional");
                    else if(idade < 65)
                            System.out.println("Voto obrigatório");
                         else System.out.println("Voto opcional");
    }
}