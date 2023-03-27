// Trabalhando com Arrays

import java.util.Arrays;

class TrabalhandComArrays {

    public static void main(String[] args) {

        // Inicializando os valores de um array na sua declaração
        int[] idades = { 10, 20, 30 };

        System.out.println("\n");

        // Imprimindo todos os elementos do array
        for(int idade : idades)
            System.out.println(idade);

        // Preenche os valores de um array
        Arrays.fill(idades, 100);

        System.out.println("\n");

        // Imprimindo todos os elementos do array
        for(int idade : idades)
            System.out.println(idade);

        // Atribuindo valores ao array
        idades[0] = 30;
        idades[1] = 10;
        idades[2] = 20;

        System.out.println("\n");

        // Imprimindo todos os elementos do array
        for(int idade : idades)
            System.out.println(idade);

        // Realizando uma busca linear
        int numeroAProcurar = 20;
        boolean achou = false;
        for(int idade : idades) {
            if(idade == numeroAProcurar) {
                achou = true;
                break;
            }
        }

        System.out.println("\n");
        System.out.println("Número " + numeroAProcurar + " está na lista? " + achou);

        // Ordenando um array
        Arrays.sort(idades);

        System.out.println("\n");

        // Imprimindo todos os elementos do array
        for(int idade : idades)
            System.out.println(idade);

        // Fazendo uma busca binária no array
        // Para a busca binária funcionar, o array precisa estar ordenado
        int pontoInsercao = Arrays.binarySearch(idades, 20);
        System.out.println("\n");
        System.out.println("Posição do Número no Array: " + pontoInsercao);

        // Imprimindo todos os elementos do array
        System.out.println("\n");
        for(int idade : idades)
            System.out.println(idade);

    }
}
