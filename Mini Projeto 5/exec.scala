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

 
// 5- Cria um dataframe de arrays de palavras chamado workable


// 6- Aplica o pipeline de extração de entidades
val results = pipeline.transform(workable)

// 7- Extrai somente o nome das entidades
val interest = results.select("entities.result")

// 8- Transforma o dataframe de arrays em um dataframe de strings e filtra valores vazios


// 9- Contagem das entidades e ordenação através de MapReduce
val result = df
  . // Transforma cada linha em uma chave
  . // Faz a agregação da contagem por chave (reduceByKey)
  . // Ordena por ordem decrescente de contagem
  . // Converte em um dataframe de 2 colunas

// 10- Filtragem para remover valores nulos que ainda restarem


// 11- Novo mapeamento e redução para o resultado final


// 12- Converte para dataframe


// 13- Salva o resultado em disco no formato csv


