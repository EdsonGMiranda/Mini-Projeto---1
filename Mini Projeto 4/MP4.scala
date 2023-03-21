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
rdd.toDF.show()

// Define o schema dos dados
def dfSchema(columnNames: List[String]): StructType =
  StructType(
    Seq(
      StructField(name = columnNames(0), dataType = IntegerType, nullable = false),
      StructField(name = columnNames(1), dataType = StringType, nullable = false),
      StructField(name = columnNames(2), dataType = StringType, nullable = false),
      StructField(name = columnNames(3), dataType = StringType, nullable = false),
      StructField(name = columnNames(4), dataType = IntegerType, nullable = false)
    )
  )

// Extrai o schema dos dados
val schema = dfSchema(List("ID", "Nome", "Cidade", "Estado", "CEP"))

// Aplica o schema ao RDD e converte em dataframe
val rowRDD = rdd.map(_.split(", ")).map(p => Row(p(0).toInt, p(1), p(2), p(3), p(4).toInt))
val df = spark.createDataFrame(rowRDD, schema)

// Mostra os nomes dos clientes
df.select("Nome").show()

// Adiciona uma coluna ao dataframe com um valor padrão
val df2 = df.withColumn("Status", lit("Ativo"))

// Visualiza o resultado final
df2.show()


