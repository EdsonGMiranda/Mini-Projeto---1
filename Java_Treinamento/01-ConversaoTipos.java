// Conversões entre Tipos de Dados

class ConversaoTipos {

    public static void main(String[] args) {

        // boolean para String
        String flag = String.valueOf(true);
        System.out.println(flag.getClass().getSimpleName());

        // int para String
        String idadeEmString = String.valueOf(10);
        System.out.println(idadeEmString.getClass().getSimpleName());

        // float para String
        String salarioEmString = String.valueOf(1234.56);
        System.out.println(salarioEmString.getClass().getSimpleName());

        // array de caracteres para String
        char[] arrayChar = { 'D','S','A' };
        String nome = String.valueOf(arrayChar);
        System.out.println(nome.getClass().getSimpleName());

        // String para int
        int idade = Integer.parseInt("10");
        System.out.println(idade);

        // String para float
        float salario = Float.parseFloat("123.45");
        System.out.println(salario);

        // String para double
        double salarioMuitoGrande = Double.parseDouble("123456.78");
        System.out.println(salarioMuitoGrande);

        int salarioTruncado = (int) 12345.67F;
        System.out.println(salarioTruncado);

        // Um int pode receber um short? Sim!
        short meuShort = 10;
        int meuInteiro = meuShort;
        System.out.println(meuInteiro);

        // E um short, pode receber um inteiro? Não!
        // meuInteiro = 2000000000;
        // meuShort = meuInteiro;

        // E se quisermos fazer uma conversão explícita?
        meuInteiro = 2000000000;
        meuShort = (short) meuInteiro;
        System.out.println(meuShort);
    }

}
