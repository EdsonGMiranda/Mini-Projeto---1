// Trabalhando com Arrays

class InstanciandoArrays {

    public static void main(String[] args) {

        // Declarando um array de double
        double[] salarios;

        // Instanciando um array com 5 posições
        // Importante: uma vez que o tamanho é definido, ele não poderá ser alterado
        salarios = new double[5];

        // Carregando o array em algumas posições
        salarios[3] = 1000;
        salarios[4] = 2000;

        // Imprimindo todos os elementos do array (por que não podemos usar o código abaixo?)
        //for(int salario : salarios)
        //    System.out.println(salario);

        // Iterando pelo array
        for(int i = 0; i < salarios.length; i++) {
            System.out.println(salarios[i]);
        }

        System.out.println("\n");

        // Iterando para escrever em cada posição do array
        // Primeira posição do array é 0
        for(int i = 0; i < salarios.length; i++) {
            salarios[i] = salarios[i] + 1000;
        }

        // Iterando pelo array
        for(int i = 0; i < salarios.length; i++) {
            System.out.println(salarios[i]);
        }

        System.out.println("\n");

        // Iterando e acumulando o valor de todos os salários
        double totalSalarios = 0;
        for(double salario : salarios) {
            totalSalarios += salario;
        }
        double mediaSalarios = totalSalarios / salarios.length;
        System.out.println(String.format("Média salarial: %f", mediaSalarios));
    }
}
