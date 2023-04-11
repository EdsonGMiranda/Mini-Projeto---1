# Projeto 2 - Pipeline ETL em Python Para Ingestão de Dados no Data Lake

# Imports
import os
import json
from pymongo import MongoClient
from encodings import utf_8

print("\nIniciando o Pipeline ETL..." )

# Conexão ao MongoDB
client = MongoClient('mongodb://localhost:27017')

print("\nConexão ao MongoDB Feita com Sucesso.")

# Deleta o banco de dados (se existir)
client.drop_database('projeto2')

# Define o banco de dados (será criado se não existir)
db = client.projeto2

print("Banco de Dados Criado com Sucesso.")

# Abre o arquivo com os nomes dos capítulos do livro
arquivo_capitulos = open('dados/capitulos.json', 'r', encoding = 'utf-8')

print("Carregando os Títulos de Cada Capítulo.")

# Carrega o dataset como formato JSON em uma variável Python
conteudo_arquivo_capitulos = json.load(arquivo_capitulos)

# Lista para receber os dados (títulos dos capítulos)
lista_titulos_capitulos = []

# Extrai cada título de cada capítulo e coloca em uma lista
for capitulo in conteudo_arquivo_capitulos:

    # Para cada linha do arquivo extrai duas colunas, nome do capítulo e id
    lista_titulos_capitulos.append((capitulo['transliteration'], capitulo['id']))

print("Criando Uma Coleção no MongoDB Para Cada Título.")

# Loop pela lista de capítulos
for capitulo in lista_titulos_capitulos:

    # Para cada título, cria uma coleção no banco de dados do MongoDB
    db.create_collection(capitulo[0])

    # Prepara o documento com os campos da coleção (número do verso e texto do verso)
    documento = {'number' : capitulo[1], 'translation': capitulo[0]}

    # Insere o documento
    db.summary.insert_one(documento)

print("Carregando o Arquivo com os Versos em Francês.")

# Define o arquivo com os versos em Francês
arquivo_versos = open('dados/versos_fr.json', 'r', encoding = 'utf-8')

# Carrega o arquivo no formato JSON
conteudo_arquivo_versos = json.load(arquivo_versos)

print("Para Cada Título (Coleção), Carregando os Versos Como Documento:\n")

# Para cada título (cada coleção), carrega os dados (documentos) dos versos do livro
for versos in conteudo_arquivo_versos:

    # Condicional que verifica se o título do capítulo no arquivo de versos é o mesmo na lista de títulos dos capítulos
    if versos['transliteration'] in [nome[0] for nome in lista_titulos_capitulos]:

        # Define o nome da Coleção
        collection = versos['transliteration']
        print(collection)

        # Extrai os versos daquele capítulo
        verses = versos['verses']

        # Lista de documentos (versos)
        documentos = []

        # Loop para carregar os versos em cada capítulo (coleção)
        for verse in verses:

            # ID do verso
            aya_number = verse['id']

            # Texto do verso
            translation = verse['translation']

            # Documento
            documento = {'aya_number' : aya_number, 'translation': translation}

            # Adiciona à lista de documentos (versos)
            documentos.append(documento)

        # Carrega os versos do capítulo corrente
        db.get_collection(collection).insert_many(documentos)

print("\nPipeline ETL Concluído com Sucesso.\n")











