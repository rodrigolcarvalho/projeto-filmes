package com.filmes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import models.Filme;
import models.FilmeBuilder;
import models.Genero;


public class App {

    private static final String REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    public static void main(String[] args) throws IOException {
        List<Filme> filmes = CarregarFilmes("resources/movies-csv/movies3.csv", false);
        filmes.addAll(CarregarFilmes("resources/movies-csv/movies2.csv", false));
        filmes.addAll(CarregarFilmes("resources/movies-csv/movies1.csv", true));

        getTop20Horror(filmes);

    }

    private static void getTop20Horror(List<Filme> filmes) {
        List<Filme> top20 = filmes.stream()
                        .filter(filme -> filme.getGeneros().contains(Genero.HORROR))
                        .sorted(Comparator.comparing(filme -> filme.getRank()))
                        .limit(20)
                        .collect(Collectors.toList());
        System.out.println("Top 20: ");

        try {
            File top20Horror = new File("top20Horror.txt");
            top20Horror.setWritable(true);
            FileOutputStream out = new FileOutputStream(top20Horror);
            Writer osw = new OutputStreamWriter(out);
            BufferedWriter bw = new BufferedWriter(osw);
           
            top20.forEach(filme -> {
                try {
                    bw.write(filme.toString() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bw.close();

        } catch (Exception e) {
        }
        top20.forEach(System.out::println);
    }

    private static List<Filme> CarregarFilmes(String path, Boolean skipHeader) {
        Path arquivoFilmes = Paths.get(path);
        List<Filme> filmes = new ArrayList<>();

        if(!Files.exists(arquivoFilmes))
            return null;

        try(Stream<String> linhas = Files.lines(arquivoFilmes))
        {
            linhas.skip(skipHeader? 1:0)
            .forEach(linha -> {
                Filme filme = FilmeBuilder.builder(linha.split(REGEX));
                filmes.add(filme);
            });
        } catch (IOException e) {
        }
        return filmes; 

    }
}
