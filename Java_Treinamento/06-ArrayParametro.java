// Passagem de Array como Parâmetro

class ArrayParametro {

    private static int[] concatenaArrays(int[] a, int[] b) {

        int[] resultado = new int[a.length + b.length];

        int i = 0;

        for(int num : a) {
            resultado[i] = num;
            i++;
        }

        for(int num : b) {
            resultado[i] = num;
            i++;
        }

        return resultado;
    }

    public static void main(String[] args) {

        int[] salariosDiretores = { 10000, 20000, 30000 };
        int[] salariosExecutivos = { 40000, 50000, 60000 };

        int[] tabelaSalariosTodos = concatenaArrays(salariosDiretores, salariosExecutivos);

        for(int salario : tabelaSalariosTodos)
            System.out.println(salario);
    }
}
