// Pipeline de Classificação com Regressão Logística

// Prevendo se um passageiro vai sobreviver ou não ao naufrágio de um navio

// Módulos
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession

// Definindo o nível de informação no log (nesse casom, ERRO)
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Spark Session
val spark = SparkSession.builder().getOrCreate()

// Carregando o dataset
val data = spark.read.option("header","true").option("inferSchema","true").format("csv").load("dataset2.csv")

// Print do Schema do dataframe
data.printSchema()

// Visualizando os dados
val colnames = data.columns
val firstrow = data.head(1)(0)
println("\n")
println("Linha de exemplo do dataframe")
for(ind <- Range(1,colnames.length)){
  println(colnames(ind))
  println(firstrow(ind))
  println("\n")
}

// Feature Engineering
// Data Wrangling - Manipulando o dataset para o modelo preditivo

// Obtendo apenas as colunas necessárias para o modelo
val logregdataall = data.select(data("Survived").as("label"), $"Pclass", $"Sex", $"Age", $"SibSp", $"Parch", $"Fare", $"Embarked")

// Removendo linhas com valores NA
val logregdata = logregdataall.na.drop()

// Precisamos lidar com as colunas categóricas!

// Import dos módulos VectorAssembler e Vectors
import org.apache.spark.ml.feature.{VectorAssembler,StringIndexer,VectorIndexer,OneHotEncoder}
import org.apache.spark.ml.linalg.Vectors

// Convertendo strings em valores numéricos (label encoding)
val genderIndexer = new StringIndexer().setInputCol("Sex").setOutputCol("SexIndex")
val embarkIndexer = new StringIndexer().setInputCol("Embarked").setOutputCol("EmbarkIndex")

// Convertendo valores numéricos em One-Hot Encoding 0 ou 1
val genderEncoder = new OneHotEncoder().setInputCol("SexIndex").setOutputCol("SexVec")
val embarkEncoder = new OneHotEncoder().setInputCol("EmbarkIndex").setOutputCol("EmbarkVec")

// Montando o dataset para o formato ("label","features") 
val assembler = (new VectorAssembler().setInputCols(Array("Pclass", "SexVec", "Age","SibSp","Parch","Fare","EmbarkVec")).setOutputCol("features") )

// Dataset de treino e de teste
val Array(training, test) = logregdata.randomSplit(Array(0.7, 0.3), seed = 12345)

// Import do módulo
import org.apache.spark.ml.Pipeline

// Criando o objeto
val lr = new LogisticRegression()

// Criando o Pipeline
val pipeline = new Pipeline().setStages(Array(genderIndexer, embarkIndexer, genderEncoder, embarkEncoder, assembler, lr))

// Construindo o Modelo, Métricas de Avaliação e Confusion Matrix 

// Fit do pipeline nos dados de treino
val model = pipeline.fit(training)

// Obtendo resultados no dataset de Teste
val results = model.transform(test)

// Módulo para métricas e avaliação
import org.apache.spark.mllib.evaluation.MulticlassMetrics

// Precisamos converter para um RDD
val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd

// Instanciando as métricas do objeto
val metrics = new MulticlassMetrics(predictionAndLabels)

// Confusion matrix
println("Confusion Matrix:")
println(metrics.confusionMatrix)
println("Acurácia:")
println(metrics.accuracy)




