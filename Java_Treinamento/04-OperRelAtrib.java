// Operadores Relacionais e de Atribuição

class OperRelAtrib {
    public static void main(String[] args) {

    System.out.println("Trabalhando com Operadores Relacionais e de Atribuição");

    // Operadores relacionais
    int x = 12, y = 6;
    System.out.println("x > y : " + (x > y));
    System.out.println("x < y : " + (x < y));
    System.out.println("x >= y : " + (x >= y));
    System.out.println("x <= y : " + (x <= y));
    System.out.println("x == y : " + (x == y));
    System.out.println("x != y : " + (x != y));

    // Operadores de atribuição
    int a = 0;
    a = a + 10;
    System.out.println(a);

    a += 10;
    a -= 15;
    System.out.println(a);

    // Operador de negação
    boolean esportista = false;
    esportista = !esportista;
    System.out.println(esportista);
    }
}