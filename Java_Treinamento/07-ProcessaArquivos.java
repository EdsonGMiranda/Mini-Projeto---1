// Processamento de Arquivos

import java.io.*;

class ProcessaArquivos {

    public static void imprimeArquivo(String nomeArquivo) throws IOException {

        // Cria o stream de entrada a partir do nome do arquivo
        FileInputStream fis = new FileInputStream(nomeArquivo);

        // Carrega o stream de entrada
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        // Inicializa variável de controle
        String line = null;

        // Loop pelo arquivo para imprimir cada linha
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }

    public static void main(String[] args) {

        // Bloco 1
        System.out.println("\nExecutando o Bloco 1 em teste.txt:");
        try {
            imprimeArquivo("teste.txt");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao abrir o arquivo teste.txt");
            e.printStackTrace();
        }

        // Bloco 2
        System.out.println("\nExecutando o Bloco 2 em dados.csv:");
        try {
            imprimeArquivo("dados.csv");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao abrir o arquivo dados.csv");
            e.printStackTrace();
        }
    }
}
