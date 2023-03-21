// Mini-Projeto 4 - Exercício de Estruturação de Dados com Linguagem Scala e Apache Spark

// Imports
import spark.implicits._
import org.apache.spark.sql.types.{StructType, StructField, StringType, IntegerType}
import org.apache.spark.sql.Row

// Função para carregar o arquivo
val readFile = (loc: String) => { val rdd = sc.textFile(loc) 
  | rdd 
  | }

// Executa a função e carrega o arquivo
val rdd = readFile("clientes.txt")

// Mostra os dados carregados

// Define o schema dos dados

// Extrai o schema dos dados

// Aplica o schema ao RDD e converte em dataframe

// Mostra os nomes dos clientes

// Adiciona uma coluna ao dataframe com um valor padrão

// Visualiza o resultado final


