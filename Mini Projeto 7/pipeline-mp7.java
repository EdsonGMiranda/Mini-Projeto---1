/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */

// https://beam.apache.org/

// Mini-Projeto 7 - Pipeline de Dados em Java com Apache Beam e Google Cloud Platform

// Aqui usamos o Direct Runner (processamento local) do Apache Beam para ler o arquivo de entrada, separar as palavras, contar a ocorrência de cada palavra
// e gravar o resultado. Entrada e saída ficarão no GCP.

// Este código é uma customização do exemplo disponível no Apache Beam Playground.

// Pacote
package org.apache.beam.examples;

// Imports
import org.apache.beam.examples.common.ExampleUtils;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.metrics.Counter;
import org.apache.beam.sdk.metrics.Distribution;
import org.apache.beam.sdk.metrics.Metrics;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.Validation.Required;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;

// Classe do programa
public class WordCount {

  // Esta classe (DoFn) tokeniza linhas de texto em palavras individuais, que passamos como parâmetro para um ParDo no pipeline.
  static class ExtractWordsFn extends DoFn<String, String> {

    // Atributos
    private final Counter emptyLines = Metrics.counter(ExtractWordsFn.class, "emptyLines");
    private final Distribution lineLenDist = Metrics.distribution(ExtractWordsFn.class, "lineLenDistro");

    // Método de processamento
    @ProcessElement
    public void processElement(@Element String element, OutputReceiver<String> receiver) {

      // Recebe a linha
      lineLenDist.update(element.length());

      // Verifica se a linha está vazia
      if (element.trim().isEmpty()) {
        emptyLines.inc();
      }

      // Split da linha em palavras
      String[] words = element.split(ExampleUtils.TOKENIZER_PATTERN, -1);

      // Output de cada palavra encontrada na PCollection de saída.
      for (String word : words) {
        if (!word.isEmpty()) {
          receiver.output(word);
        }
      }
    }
  }

  // Uma classe do tipo SimpleFunction que converte palavra e contagem em uma string que pode ser impressa.
  public static class FormatAsTextFn extends SimpleFunction<KV<String, Long>, String> {

    // Método apply
    @Override
    public String apply(KV<String, Long> input) {
      return input.getKey() + ": " + input.getValue();
    }
  }

  /*
   * Classe de contagem de palavras
   *
   * Um PTransform que converte uma PCollection contendo linhas de texto em uma PCollection de contagens de palavras formatadas.
   * Essa é uma transformação composta personalizada que agrupa duas transformações (ParDo e Count) como uma subclasse PTransform reutilizável. 
   * O uso de transformações compostas permite uma fácil reutilização, testes modulares e uma experiência de monitoramento aprimorada.
   *
   */
  public static class CountWords extends PTransform<PCollection<String>, PCollection<KV<String, Long>>> {

    // Método PCollection
    @Override
    public PCollection<KV<String, Long>> expand(PCollection<String> lines) {

      // Converte linhas de texto em palavras individuais.
      PCollection<String> words = lines.apply(ParDo.of(new ExtractWordsFn()));

      // Conta o número de vezes que cada palavra ocorre.
      PCollection<KV<String, Long>> wordCounts = words.apply(Count.perElement());

      return wordCounts;
    }
  }

  // Esse método é a interface para os arquivos de entrada e saída
  public interface WordCountOptions extends PipelineOptions {

    // Por padrão, este exemplo lê um conjunto de dados contendo o texto de arquivo de entrada. 
    @Description("Caminho para o arquivo de entrada no GCP")
    @Default.String("gs://lab-dsa-mp7/input/pg67816.txt")
    String getInputFile();

    void setInputFile(String value);

    // O método getOutput() lê o que você configurar no Pipeline Options no Beam Playground. Isso será usado como saída do pipeline.
    @Description("Caminho para o arquivo de saída")
    @Required
    String getOutput();

    void setOutput(String value);
  }

  // Método que executa o pipeline
  static void runWordCount(WordCountOptions options) {

    // Cria o objeto de pipeline
    Pipeline p = Pipeline.create(options);

    // Nosso pipeline aplica a transformação composta CountWords e a estática FormatAsTextFn().
    p.apply("ReadLines", TextIO.read().from(options.getInputFile()))
        .apply(new CountWords())
        .apply(MapElements.via(new FormatAsTextFn()))
        .apply("WriteCounts", TextIO.write().to(options.getOutput()));

    p.run().waitUntilFinish();
  }

  // Método main
  public static void main(String[] args) {

    // Opções do pipeline
    WordCountOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().as(WordCountOptions.class);

    // Executa o pipeline
    runWordCount(options);
  }
}







