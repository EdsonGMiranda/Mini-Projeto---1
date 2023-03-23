// Operações com Datas e Calendário

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

class OpDatas {

    public static void main(String[] args) {

        // Data em Java é representado basicamente como um número (long)
        // que indica quantos milissegundos se passaram deste 01/01/1970

        Date dataAtual = new Date(); 
        System.out.println(dataAtual.getTime());
        System.out.println(dataAtual);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(sdf.format(dataAtual));

        SimpleDateFormat sdf2 = new SimpleDateFormat("D"); // dia no ano
        System.out.println(sdf2.format(dataAtual));

        // Obtendo uma instância de um calendário
        Calendar cal = Calendar.getInstance();
        System.out.println(cal);

        cal.setTime(new Date());

        int dia = cal.get(Calendar.DATE);
        int mes = cal.get(Calendar.MONTH)+1;
        int ano = cal.get(Calendar.YEAR);

        System.out.println(dia + "/" + mes + "/" + ano);

        // Alterar o ano de uma data (da mesma forma outros campos podem ser manipulados)
        cal.set(Calendar.YEAR,2023);
        System.out.println(cal.toInstant().toString());

        // Manipulações de datas
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -7);
        System.out.println(cal.toInstant().toString());

    }
    
}
