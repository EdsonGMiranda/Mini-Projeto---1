// Trabalhando com Arrays

import java.util.Arrays;
import java.util.Random;

class ParallelOpsArrays {

    public static void main(String[] args) {

        // Cria um objeto do tipo Random
        Random rn = new Random();

        // Declara 2 arrays
        int[] array1 = new int[100000000];
        int[] array2 = new int[100000000];

        // Alimenta cada posição de cada array com um valor entre 0 e 99
        for(int i = 0; i < array1.length; i++) {
            array1[i] = rn.nextInt(100);
            array2[i] = array1[i];
        }

        // Ordenação do array 1 (sem paralelização)
        long inicioTarefa = System.currentTimeMillis();
        Arrays.sort(array1);
        long fimTarefa = System.currentTimeMillis();
        System.out.println("Tempo de ordenação do array 1 (ms): " + (fimTarefa - inicioTarefa));

        // Ordenação do array 2 (com paralelização)
        inicioTarefa = System.currentTimeMillis();
        Arrays.parallelSort(array2);
        fimTarefa = System.currentTimeMillis();
        System.out.println("Tempo de ordenação do array 2 (ms): " + (fimTarefa - inicioTarefa));
    }
}
