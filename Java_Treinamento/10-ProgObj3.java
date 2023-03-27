// Exemplo 3

import dsa.ContaBancaria;

class ProgObj3 {

    public static void main(String[] args) {

        ContaBancaria conta = new ContaBancaria();

        System.out.println("\nSaldo Atual:" + conta.getSaldo());
        
        conta.titular = "Bob";

        conta.deposita(1000);

        System.out.println("\nSaldo Atual:" + conta.getSaldo());

        conta.saca(200);

        System.out.println("\nSaldo Atual:" + conta.getSaldo());

        conta.saca(600);

        System.out.println("\nSaldo Atual:" + conta.getSaldo());

        // Deposita com um bônus
        conta.deposita(300,15);

        System.out.println("\nSaldo Atual:" + conta.getSaldo());
    }

}
