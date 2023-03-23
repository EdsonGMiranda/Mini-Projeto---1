// Representação de Datas e Tratamento de Erro

import java.util.Date;
import java.time.LocalDate; 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class RepDatas {

    public static void main(String[] args) {

    Date dataAtual = new Date();
    String mensagem = String.format("Hoje é %tD", dataAtual);
    System.out.println("\nData com o Pacote Date:");
    System.out.println(mensagem);

    LocalDate myObj = LocalDate.now(); 
    System.out.println("\nData Atual do Sistema com LocalDate:");
    System.out.println(myObj); 

    LocalDateTime myDateObj = LocalDateTime.now();
    System.out.println("\nData Atual do Sistema com LocalDateTime Antes da Formatação:");
    System.out.println(myDateObj);

    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedDate = myDateObj.format(myFormatObj);
    System.out.println("\nData Atual do Sistema com LocalDateTime Depois da Formatação:");
    System.out.println(formattedDate);

    // Calculando o tempo entre duas ações
    System.out.println("\nCalculando a Diferença Entre Datas:");
    try {
        long start = System.currentTimeMillis( );
        System.out.println(new Date( ) + "\n");
        
        Thread.sleep(5*60*10);
        
        System.out.println(new Date( ) + "\n");
        
        long end = System.currentTimeMillis( );
        long diff = end - start;
        System.out.println("A diferença é : " + diff);
     } catch (Exception e) {
        System.out.println("Ocorreu algum problema!");
     }

    }
    
}
