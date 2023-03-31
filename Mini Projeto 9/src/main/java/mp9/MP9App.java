// Classe Principal da App

// Pacote
package mp9;

// Imports
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.io.FileWriter; 
import java.io.IOException;
import org.apache.commons.lang3.text.WordUtils;

// Classe
public class MP9App {

	// Método para imprimir no terminal
	public static <T> void print(T x) {
		System.out.println(x);
	}

	// Método para gravar as saídas em arquivo txt
	public static void grava(String message) {
		try
		{
    		String filename = "resultado.txt";
   			FileWriter fw = new FileWriter(filename, true); 
    		fw.write(message);
    		fw.close();
		}
		catch(IOException ioe)
		{
    		System.err.println("IOException: " + ioe.getMessage());
		}
	}

	// Método main
	public static void main(String[] args) throws IOException {

		// Mensagem
		print("\nIniciando o Processamento...\n");

		// Nome do arquivo de entrada
		String fileName = "dados.csv";

		// Carrega o arquivo associando os dados aos metadados
		List<Car> cars = Files.lines(Path.of(fileName)).skip(1).map(Car::String2Car).collect(Collectors.toList());
		cars = cars.stream().filter(car -> car != null).collect(Collectors.toList());
	
		// Mensagem
		print("\nIniciando a extração...");
		grava("ETL Realizado no Arquivo dados.csv\n");
		
		// 1- Extrai o número total de veículos
		long count = cars.stream().count();
		String str1 = String.valueOf(count);
		grava("Número total de veículos: " + str1);
		print("\nExtraído e Gravado o Item 1!");
		
		// 2- Extrai o número total de veículos fabricados nos EUA
		var uscars = cars.stream().filter(car -> car.getOrigin().equals("usa"));
		String str2 = String.valueOf(uscars.count());
		grava("\nNúmero total de veículos produzidos nos EUA: " + str2);
		print("\nExtraído e Gravado o Item 2!");
		
		// 3- Extrai o maior ano de fabricação entre todos os anos (ou seja, ano mais recente)
		var maxYear = cars.stream().map(car -> car.getModelYear()).reduce((x,y) -> Math.max(x, y)).get();
		String str3 = String.valueOf(maxYear);
		grava("\nMaior ano de fabricação dos veículos: " + str3);
		print("\nExtraído e Gravado o Item 3!");
				
		// 4- Extrai a média de horsepower de veículos produzidos no Japão
		var japanHorsepower = cars.stream().filter(car -> car.getOrigin().equals("japan")).mapToDouble(car -> car.getHorsepower()).average().getAsDouble();
		String str4 = String.valueOf(japanHorsepower);
		grava("\nMédia de horsepower dos veículos produzidos no Japão: " + str4);
		print("\nExtraído e Gravado o Item 4!");
		
		// 5- Extrai a média de horsepower por país de fabricação dos veículos
		var countryHorsePower = cars.stream().collect(Collectors.groupingBy(car -> car.getOrigin(), Collectors.averagingDouble(car -> car.getHorsepower())));
		String str5 = String.valueOf(countryHorsePower);
		grava("\nMédia de horsepower dos veículos produzidos por país/continente: " + str5);
		print("\nExtraído e Gravado o Item 5!");
		
		// 6- Considerando que a primeira palavra da coluna de nome do veículo é a marca, 
		// extraímos as marcas distintas, convertendo para maiúsculas e minúsculas.
		List<String> brands = cars.stream().map(car -> car.getName().split(" ")[0]).distinct().map(name -> WordUtils.capitalize(name)).collect(Collectors.toList());
		String str6 = String.valueOf(brands);
		grava("\nMarcas dos veículos: " + str6);
		print("\nExtraído e Gravado o Item 6!");	
		
		// 7- Extrai a marca com o maior número de veículos
		var res = cars.stream().map(car -> car.getName().split(" ")[0]).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
		var brandWithHighestNumber = res.entrySet().stream().max( (x, y) -> x.getValue().compareTo(y.getValue()) ).get().getKey();
		String str7 = String.valueOf(brandWithHighestNumber);
		grava("\nMarca com maior número de veículos: " + str7);
		print("\nExtraído e Gravado o Item 7!");	
		
		print("\nProcessamento Concluído. Obrigado por usar esta  app! Verifique o arquivo de saída!\n");

	}
}
