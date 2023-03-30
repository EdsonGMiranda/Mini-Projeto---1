// Classe Principal da App

// Pacote
package mp8;

// Imports
import java.util.*;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.*;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.*;
import static org.apache.spark.sql.functions.*;

// Classe
public class MP8App {

	public static void main(String[] args) {

		// Sessão Spark
		SparkSession spark = SparkSession.builder().appName("MP8App").config("spark.master", "local").getOrCreate();

		// Java Spark Context
		JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());

		// Nível de erro
		sc.setLogLevel("ERROR");
		
		// Carrega os dados e cria um dataframe
		var carsDF = spark.read().json("cars.json");

		// Aplica o encoder para ler os dados usando o schema pré-definido na classe Car
		var carsDS = carsDF.as(Encoders.bean(Car.class)); 

		// Imprime o schema e o dataframe
		carsDS.printSchema();
		carsDS.show();
		
		// Função para obter a coluna Name com o nome do fabricante
		MapFunction<Car, String> myFunc = (x -> x.getName().toUpperCase());

		// Aplica o encoder e mostra o resultado
		carsDS.map(myFunc, Encoders.STRING()).show();
		
		// Select para mostrar o resultado
		carsDS.select(functions.upper(carsDS.col("Name"))).show();
		
		// Aplica a lógica de negócio
		carsDS.map((MapFunction<Car, Double>) car -> car.businessLogicMethod(), Encoders.DOUBLE()).show();
		
		// Converte de volta para coleções java
		List<Car> carList = carsDS.collectAsList();

		// Loop pelo stream de dados
		carList.stream().forEach( car -> CarDealership.order(car));
		
		// Seleciona outra coluna
		carsDS.select(functions.mean(carsDS.col("Miles_per_Gallon"))).show();
		
		// Filtrando os dados e retornando não nulos
		var carsWithMpg = carsDS.filter((FilterFunction<Car>) c -> c.getMiles_per_Gallon() != null);

		// Calcula o total com reduce
		var total = carsWithMpg.map((MapFunction<Car, Double>) c -> c.getMiles_per_Gallon(), Encoders.DOUBLE()).reduce((ReduceFunction<Double>) Double::sum); 

		// Print da média de milhas por galão
		System.out.println("Média de MPG: " + total / carsWithMpg.count());

	}

}
