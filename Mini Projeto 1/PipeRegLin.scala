// Pipeline de Regressão Linear com Scala e Spark

// Prever o valor de uma casa com base em seus atributos

// Em Regressão Linear queremos resolver a fórmula: y = xa + b

// Onde:
// y é a variável target
// x representa um ou mais atributos
// a e b representam os coeficientes, aquilo que o modelo aprende no treinamento

// Módulos
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.tuning.{ParamGridBuilder, TrainValidationSplit}

// Definindo o nível do log
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Inicializando a Spark Session
val spark = SparkSession.builder().getOrCreate()

// Carregando os dados
val data = spark.read.option("header", "true").option("inferSchema", "true").format("csv").load("dataset1.csv")

// Verificando os dados
data.printSchema()

// Separando as colunas e a primeira linha
val colnames = data.columns
val firstrow = data.head(1)(0)

// Imprimindo a primeira linha do dataset
println("\n")
println("Linha do dataset")
for(ind <- Range(1,colnames.length)){
  println(colnames(ind))
  println(firstrow(ind))
  println("\n")
}

// Configurando um Dataframe 

// Precisamos definir o dataset na forma de duas colunas ("label", "features").
// Isso nos permitirá juntar várias colunas de recursos em uma única coluna de uma matriz de valores.

// Imports
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

// Criando o dataframe
val df = data.select(data("Valor").as("label"),$"Media_Salarial_Vizinhanca",$"Media_Idade_Casa",$"Media_Numero_Comodos",$"Media_Numero_Quartos",$"Populacao_Vizinhanca")

// Um assembler converte os valores de entrada em um vetor
// Um vetor é o que o algoritmo ML lê para treinar um modelo

// Define as colunas de entrada das quais devemos ler os valores
// Define o nome da coluna onde o vetor será armazenado
val assembler = new VectorAssembler().setInputCols(Array("Media_Salarial_Vizinhanca", "Media_Idade_Casa", "Media_Numero_Comodos", "Media_Numero_Quartos", "Populacao_Vizinhanca")).setOutputCol("features")

// Transformamos o dataset em um objeto de duas colunas, no formato esperado pelo modelo
val output = assembler.transform(df).select($"label",$"features")

//Imprimindo a versão final do dataframe que vai alimentar o modelo de regressão
output.show()

// Configurando o modelo de regressão

// Criar um objeto de Regressão Linear
val lr = new LinearRegression()

// Grid de hiperparâmetros
val paramGrid = new ParamGridBuilder().addGrid(lr.regParam, Array(0.1, 0.01)).addGrid(lr.fitIntercept).addGrid(lr.elasticNetParam, Array(0.0, 0.5, 1.0)).build()

// Divide em dados de treino e teste
val trainValidationSplit = new TrainValidationSplit().setEstimator(lr).setEvaluator(new RegressionEvaluator).setEstimatorParamMaps(paramGrid).setTrainRatio(0.8)

// Fit do modelo nos dados
val lrModel = lr.fit(output)

// Imprimir os coeficientes aprendidos no treinamento do modelo de regressão linear
println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")

// Avaliação

// Resumindo o Modelo
val trainingSummary = lrModel.summary

// Resíduos e Previsões
trainingSummary.residuals.show()
trainingSummary.predictions.show()

// Métricas
println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
println(s"R2: ${trainingSummary.r2}")




