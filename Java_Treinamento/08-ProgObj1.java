// Exemplo 1

import dsa.Cliente;

class ProgObj1 {

    public static void main(String[] args) {

       Cliente c1 =  new Cliente();
       c1.nome = "Bob";
       c1.pais = "Brasil";

       System.out.println(c1.nome + " mora no " + c1.pais);

       Cliente c2;
       c2 = c1;

       c2.nome = "Ted";
       System.out.println(c2.nome + " mora no " + c2.pais);

    }

}
