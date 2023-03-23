// Precedência de Operadores

class Precedencia {
    public static void main(String[] args) {

    System.out.println("Trabalhando com Precedência de Operadores");

    // Ordem de precedência:
    // () primeiro
    // Multiplicação e divisão
    // Resto
    // Adição e subtração
    // Atribuição

    System.out.println(1 + 2 * 3);

    System.out.println((1 + 2) * 3);

    System.out.println(3 + 5 - 2);

    System.out.println(3 - 3 + 1 * 10); // = 1 * 10
                                        // = 3 - 3 + 10
                                        // = 0 + 10
                                        // = 10
    }
}