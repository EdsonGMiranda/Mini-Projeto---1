# Mini-Projeto 8 - App Java Para Pipeline de Processamento de Dados com Apache Spark


# Testa o ambiente:

/opt/spark/bin/spark-submit --master local[*] --class org.apache.spark.examples.SparkPi /opt/spark/examples/jars/spark-examples_2.12-3.2.1.jar 80


# Compila a app:

mvn package


# Executa a app:

/opt/spark/bin/spark-submit --class mp8.MP8App --master local[*] target/mp8-1.0.jar
