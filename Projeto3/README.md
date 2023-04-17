# Pipeline de Carga e Análise de Dados do Data Warehouse com apache Airflow e Metabase

![image](https://user-images.githubusercontent.com/112988565/232615483-99a76a32-d0a8-45bf-9bdd-85b3de4c1268.png)

pipeline  de  dados  para  ajudar  a  área  de negócio  a  analisar  dados com classificações e avaliações de hotéis do site Tripadvisor 
O portal TripAdvisor fornece diversos dados sobre hotéistais como:número de estrelas, classificação, avaliação de clientes, entre outros dados. Usaremos um dataset no formato JSON extraído do TripAdvisor. 

fonte: https://www.tripadvisor.com/


Nosso pipeline criado no Apache Airflow vai fazer a leitura do arquivo JSON e transformar os dados para que sejam carregados em um DW.

Com os dados carregados no DW usaremos o Metabase para conectar no banco de dados e extrair insights e análises através de gráficos e dashboards.Data Science Academy bulovaed@gmail.com 5b4b62645e4cde2e688b456c
