// Mini-Projeto 3 - Pipeline ETL Para Processamento de Linguagem Natural com Linguagem Scala e Apache Spark

// Imports
import org.apache.spark.ml.Pipeline
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.types.{IntegerType, LongType, StringType, StructField, StructType}
import org.apache.spark.sql.functions._
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.feature._

// Cria a sessão spark
val ss = SparkSession.builder().master("local").appName("MP3").getOrCreate()

// Para conversão implícita de RDD para DataFrame
import ss.implicits._  

// Carregando os arquivos individuais

// Diretório corrente
val currentDir = System.getProperty("user.dir")  

// Arquivos de entrada
val inputFile = "./attributes.csv + ./product_description.csv + ./train.csv"

println("Carregando os arquivos: " + inputFile)
println

// Dataframe com os dados de treino
val trainDF = ss.read.format("csv").option("header", "true").load("train.csv")
trainDF.printSchema()
    
// Dataframe com a descrição dos produtos
val descriptionDF = ss.read.format("csv").option("header", "true").load("product_descriptions.csv")
descriptionDF.printSchema()
    
// Dataframe com os atributos dos produtos
val attributesDF = ss.read.format("csv").option("header", "true").load("attributes.csv")
attributesDF.printSchema()

// Filtra os atributos por uma das brands
val newAttributesDF = attributesDF.filter(attributesDF("name")==="MFG Brand Name")
val newNewAttributesDF = newAttributesDF.select("product_uid","value")

// Consolida os dataframes
val consolidated = trainDF.join(descriptionDF, "product_uid").join(newNewAttributesDF, "product_uid")
      .select(trainDF("product_uid"), trainDF("product_title"), trainDF("search_term"),
      trainDF("relevance"), descriptionDF("product_description"), newNewAttributesDF("value"))
      .withColumn("product_description",lower(col("product_description"))).withColumn("product_title", lower(col("product_title")))
      .withColumn("search_term", lower(col("search_term"))).withColumn("value", lower(col("value")))

// Limpa os dataframes intermediários para liberar memória
trainDF.unpersist()
descriptionDF.unpersist()
attributesDF.unpersist()
newAttributesDF.unpersist()
newNewAttributesDF.unpersist()

// Visualiza
consolidated.show(10)

// Pré-Processamento

// Tokenização da variável product_title

// Cria o tokenizador
val tokenizerTitle = new Tokenizer().setInputCol("product_title").setOutputCol("product_title_words")

// Aplica o tokenizador
val tokenizedTitle = tokenizerTitle.transform(consolidated)
 
// Libera o dataframe intermediário
consolidated.unpersist()

// Seleciona as colunas
tokenizedTitle.select("product_title", "product_title_words")

// Função para remover stopwords
val removerTitle = new StopWordsRemover()
      .setInputCol("product_title_words")
      .setOutputCol("filtered_title_words")

// Une as sequências de palavras em um array de strings
val joinSeq = udf { (words: Seq[String]) => words.mkString(" ") }

// Remove as stopwords
val removedStopwordsTitle = removerTitle.transform(tokenizedTitle)

// Libera o dataframe intermediário
tokenizedTitle.unpersist()

// Junção das sequências como array de strings
val removedStopwordsTitleJoinedSeq = removedStopwordsTitle.withColumn("filtered_title_words", joinSeq($"filtered_title_words"))

// Libera o dataframe intermediário
removedStopwordsTitle.unpersist()

// Tokenização da variável product_description

val tokenizerDesc = new Tokenizer().setInputCol("product_description").setOutputCol("product_description_words")

val tokenizedDesc = tokenizerDesc.transform(removedStopwordsTitleJoinedSeq)

tokenizedDesc.select("product_description", "product_description_words")

val removerDesc = new StopWordsRemover()
      .setInputCol("product_description_words")
      .setOutputCol("filtered_description_words")

val removedStopwordsDesc = removerDesc.transform(tokenizedDesc)

tokenizedDesc.unpersist()

val removedStopwordsDescJoinedSeq = removedStopwordsDesc.withColumn("filtered_description_words", joinSeq($"filtered_description_words"))

removedStopwordsDesc.unpersist()

// Tokenização da variável search_term

val tokenizerSearch = new Tokenizer().setInputCol("search_term").setOutputCol("search_term_words")

val tokenizedSearch = tokenizerSearch.transform(removedStopwordsDescJoinedSeq)

removedStopwordsDescJoinedSeq.unpersist()

tokenizedSearch.select("search_term", "search_term_words")

val removerSearch = new StopWordsRemover()
      .setInputCol("search_term_words")
      .setOutputCol("filtered_search_words")


val removedStopwordsSearch = removerSearch.transform(tokenizedSearch)

tokenizedSearch.unpersist()

val removedStopwordsSearchJoinedSeq = removedStopwordsSearch.withColumn("filtered_search_words", joinSeq($"filtered_search_words"))

removedStopwordsSearch.unpersist()

// Dataframe final após a tokenização
removedStopwordsSearchJoinedSeq.show(10)
removedStopwordsSearchJoinedSeq.printSchema()

// Verificamos se o título contém alguma palavra que foi usada nos termos de busca
val commonterms_SearchVsTitle = udf((filtered_search_words: String, filtered_title_words:String) =>
      if (filtered_search_words.isEmpty || filtered_title_words.isEmpty){
        0
      }
      else{
        var tmp1 = filtered_search_words.split(" ")
        var tmp2 = filtered_title_words.split(" ")
        tmp1.intersect(tmp2).length
      })

// Verificamos se a descrição contém alguma palavra que foi usada nos termos de busca
val commonterms_SearchVsDescription = udf((filtered_search_words: String, filtered_description_words:String) =>
      if (filtered_search_words.isEmpty || filtered_description_words.isEmpty){
        0
      }
      else{
        var tmp1 = filtered_search_words.split(" ")
        var tmp2 = filtered_description_words.split(" ")
        tmp1.intersect(tmp2).length
      })

// Contamos se as descrições e títulos contém os termos usados para busca
val countTimesSearchWordsUsed = udf((filtered_search_words: String, filtered_title_words:String, filtered_description_words:String) =>
      if (filtered_search_words.isEmpty || filtered_title_words.isEmpty){
        0
      }
      else{
        var tmp1 = filtered_search_words
        var count = 0
        if (filtered_title_words.contains(filtered_search_words)){
          count += 1
        }
        if (filtered_description_words.contains(filtered_search_words)){
          count += 1
        }
        count
      })

// Concatenamos os resultados das variáveis após o pré-processamento

// Palavras comuns entre filtered_search_words e filtered_title_words
val results = removedStopwordsSearchJoinedSeq.withColumn("common_words_ST", commonterms_SearchVsTitle($"filtered_search_words", $"filtered_title_words"))
    results.select("common_words_ST").show()
    results.printSchema()

// Palavras comuns entre filtered_search_words e filtered_description_words
val results2 = removedStopwordsSearchJoinedSeq.withColumn("common_words_SD", commonterms_SearchVsDescription($"filtered_search_words", $"filtered_description_words"))
    results2.select("common_words_SD").show()
    results2.printSchema()

// Concatenamos os resultados
val results1and2 = results.withColumn("common_words_SD", commonterms_SearchVsDescription($"filtered_search_words", $"filtered_description_words"))
    results1and2.printSchema()
    results.unpersist()
    results2.unpersist()

// Removemos caracteres especiais e stopwords
val newConsolidated = results1and2
      .withColumn("search_term_len", size(split('filtered_search_words, " ")))
      .withColumn("product_description_len", size(split('filtered_description_words, " ")))
      .withColumn("ratio_desc_len_search_len", size(split('filtered_description_words, " "))/size(split('filtered_search_words, " ")))
      .withColumn("ratio_title_len_search_len", size(split('filtered_title_words, " "))/size(split('filtered_search_words, " ")))
      .withColumn("common_words_ST", $"common_words_ST")
      .withColumn("common_words_SD", $"common_words_SD")
    results.unpersist()

newConsolidated.show(10)

// Converte para dataframe
val df = newConsolidated.toDF()
df.printSchema()
df.show(10)

// Salva o resultado em disco
df.write.format("parquet").save("novo_dataset")



