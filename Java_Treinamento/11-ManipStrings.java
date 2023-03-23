// Manipulação de Strings

class ManipStrings {

    public static void main(String[] args) {

        // Declarando e atribuindo valor a variável do tipo String
        String texto = "Quem não sabe para onde vai qualquer caminho serve!";

        // Obtendo o comprimento de uma String
        // Lembrando que o índice inicial (posição do primeiro caractere) é 0
        System.out.println(String.format("%s tem %d letras", texto, texto.length()));

        // Obtendo um caracter de uma String em uma posição específica
        char letra = texto.charAt(9);
        System.out.println(letra);

        // Obtendo uma sub-String de uma String
        String palavra = texto.substring(37, 44);
        System.out.println(palavra);

        // Dividindo (separando) conteúdo de uma String
        String nomes = "Bob,Ted,Maria,Roberto,Silvia";
        String[] arrayNomes = nomes.split(",");
        for(String nome : arrayNomes)
            System.out.println(nome);

        // Convertendo para maíusculo
        System.out.println("dsa é incrível!!!".toUpperCase());

        // Procurando a primeira ocorrência de uma substring em uma String
        int primeiraOcorrencia = texto.indexOf("er");
        System.out.println(primeiraOcorrencia);

        // Procurando a última ocorrência de uma substring em uma String
        int ultimaOcorrencia = texto.lastIndexOf("er");
        System.out.println(ultimaOcorrencia);

        // Verificando se uma String possui uma substring
        // Retorno do método contains é booleano (verdadeiro ou falso)
        System.out.println(texto.contains("sabe"));
        System.out.println(texto.contains("araraquara"));

        // Substituindo caracteres e realizando encadeamento
        String frase = "analytics-visualizacao-relatorios-tomada-decisao"
                .replace('-',' ')
                .replaceAll(" ","  ")
                .replaceFirst("analytics","ANALYTICS");


        System.out.println(frase);
    }
    
}
