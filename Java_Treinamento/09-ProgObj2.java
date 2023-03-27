// Exemplo 2

import dsa.Cliente;

class ProgObj2 {

    public static void main(String[] args) {

        // Cliente 1
        Cliente c1 = null;
        c1 = new Cliente();
        c1.nome = "Maria";

        if(c1 != null)
            System.out.println("Nome do Cliente c1: " + c1.nome);

        // Cliente 2
        Cliente c2 = new Cliente();
        c2.nome = "Ana Maria Soares da Silva";
        System.out.println("Número de Caracteres do Nome do Cliente c2: " + c2.nome.length());

        // Cliente 3
        Cliente c3 = new Cliente();
        c3.nome = "Bob";
        c3.sobrenome = "Silva";
        System.out.println("Nome Completo do Cliente c3: " + c3.getNomeCompleto());

        // Cliente 4
        Cliente c4 = new Cliente();
        c4.nome = "Ted";
        c4.setIdade(59);
        System.out.println(String.format("%s possui %d anos", c4.nome, c4.getIdade()));

    }

}
