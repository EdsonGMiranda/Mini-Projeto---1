// Mini-Projeto 5 - Exercício de Filtragem de Entidades Nomeadas com Linguagem Scala e Apache Spark

// ATENÇÃO: Inicialize o spark-shell com a instrução descrita no manual em pdf deste mini-projeto
// Remova a pasta "resultado" antes de executar o script mais de uma vez

// 1- Imports
import com.johnsnowlabs.nlp.pretrained.PretrainedPipeline
import com.johnsnowlabs.nlp.SparkNLP
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types._

// 2- Carrega o pipeline de extração de entidades pré-treinado fornecido pelo johnsnowlabs
val pipeline = PretrainedPipeline("recognize_entities_dl", lang="en")

// 3- Carrega os dados
val input = sc.textFile("allforlove.txt")

// 4- Separa as palavras por pontuação
val words = input.flatMap(line => line.split("""\W+"""))
 
// 5- Cria um dataframe de arrays de palavras chamado workable
val workable = words.toDF("text")

// 6- Aplica o pipeline de extração de entidades nomeadas
val results = pipeline.transform(workable)

// 7- Extrai somente o nome das entidades
val interest = results.select("entities.result")

// 8- Transforma o dataframe de arrays em um dataframe de strings e filtra valores vazios
val df = interest.map(f => {
  val result = f.getList(0).toArray.mkString(",")
  (result)
}).filter("value != ''")

// 9- Contagem das entidades e ordenação através de MapReduce
val result = df
  .rdd.map(x => (x,1)) // Transforma cada linha em uma chave
  .reduceByKey((count1, count2) => (count1+count2)) // Faz a agregação da contagem por chave (reduceByKey)
  .sortBy(-_._2) // Ordena por ordem decrescente de contagem
  .toDF("Entidade", "Contagem") // Converte em um dataframe de 2 colunas

// 10- Filtragem para remover valores nulos que ainda restarem
val df = interest.map(f => {
  val result = f.getList(0).toArray.mkString(",")
  (result)
}).filter("value != ''")

// 11- Novo mapeamento e redução para o resultado final
val result = df.rdd.map(x => (x,1)).reduceByKey((count1, count2) => (count1+count2)).sortBy(-_._2).toDF("Entidade", "Contagem")

// 12- Converte para dataframe
val df = result.toDF()
df.printSchema()
df.show(10)

// 13- Salva o resultado em disco no formato csv
df.write.format("csv").save("resultado")

