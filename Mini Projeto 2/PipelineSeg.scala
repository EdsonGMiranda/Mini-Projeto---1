// Mini-Projeto 2 - Pipeline de Machine Learning Para Segmentação de Clientes

// Detalhes sobre o projeto no manual em pdf no Capítulo 3 do curso.

// https://spark.apache.org/docs/latest/api/scala/org/apache/spark/ml/clustering/KMeans.html

// Import SparkSession
import org.apache.spark.sql.SparkSession

// Definindo o log de erro
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Criando sessão Spark
val spark = SparkSession.builder().getOrCreate()

// Importando o algoritmo K-Means
import org.apache.spark.ml.clustering.KMeans

// Carregando o dataset
val dataset = spark.read.option("header","true").option("inferSchema","true").csv("dados.csv")

// Selecionamos as seguintes colunas para o conjunto de treinamento:
// Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicatessen
val feature_data = dataset.select($"Fresh", $"Milk", $"Grocery", $"Frozen", $"Detergents_Paper", $"Delicatessen")
println(dataset.schema)

// Import VectorAssembler e Vectors
import org.apache.spark.ml.feature.{VectorAssembler,StringIndexer,VectorIndexer,OneHotEncoder}
import org.apache.spark.ml.linalg.Vectors

// Criamos um novo objeto VectorAssembler chamado assembler para a coluna de atributos
// Lembre-se de que não existe uma coluna de labels em problemas de aprendizado não supervisionado
val assembler = new VectorAssembler().setInputCols(Array("Fresh", "Milk", "Grocery", "Frozen", "Detergents_Paper", "Delicatessen")).setOutputCol("features")

// Usamos o objeto assembler para transformar o feature_data
// Chamamos este novo objeto de dataset
val dataset = assembler.transform(feature_data).select("features")

// Criando o modelo Kmeans com K = 3
val kmeans = new KMeans().setK(3).setSeed(1L)

// Fit do modelo
val model = kmeans.fit(dataset)

// Previsões
val previsoes = model.transform(dataset)

// Criamos o avaliador de cluster
import org.apache.spark.ml.evaluation.ClusteringEvaluator
val evaluator = new ClusteringEvaluator()

// Avaliaremos o modelo usando o Silhouette Score.
// O Silhouette Score é um coeficiente dentro do intervalo [-1, 1]. 
// Valor próximo de 1 significa que os clusters são muito densos e bem separados. 
// Valor próximo de 0 significa que os clusters estão sobrepostos. 
// Valor inferior a 0 significa que os dados pertencentes aos clusters podem estar errados/incorretos.
val silhouette = evaluator.evaluate(previsoes)
println(s"Silhouette Score = $silhouette")

// Mostra os resultados
println("Segmentos de Clientes (Clusters): ")
previsoes.collect().foreach(println)

// Salva o resultado em disco
import java.io._
val writer = new BufferedWriter(new FileWriter("previsoes.txt"))
writer.write("Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicatessen, Grupo\n")
previsoes.collect().foreach(x=>{writer.write(x.toString())})
writer.close()





