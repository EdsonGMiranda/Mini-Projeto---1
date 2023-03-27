// Exemplo 4

import dsa.ContaBancaria;
import dsa.Cliente;

class ProgObj4 {

    public static void main(String[] args) {

        Cliente cliente = new Cliente(null, null);
        
        cliente.nome = "Bob";
        cliente.sobrenome = "Silva";

        System.out.println("\nCliente " + cliente.getNomeCompleto() + " Cadastrado com Sucesso!");

        ContaBancaria conta = new ContaBancaria();

        System.out.println("\nSaldo Atual do Cliente " + cliente.getNomeCompleto() + ": " + conta.getSaldo());
        
        conta.titular = cliente.nome;

        conta.deposita(780);
        
        System.out.println("\nSaldo Atual do Cliente " + cliente.getNomeCompleto() + ": " + conta.getSaldo());
    }

}
