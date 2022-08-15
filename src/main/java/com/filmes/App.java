package com.filmes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import models.Filme;
import models.FilmeBuilder;


public class App {

    private static final String REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    public static void main(String[] args) throws IOException {
        CarregarFilmes("resources/movies-csv/movies3.csv", false);
    }

    private static void CarregarFilmes(String path, Boolean skipHeader) {
        Path arquivoFilmes = Paths.get(path);
        
        if(!Files.exists(arquivoFilmes))
            return;

        try(Stream<String> linhas = Files.lines(arquivoFilmes))
        {
            linhas.skip(skipHeader? 1:0)
            .forEach(linha -> {
                Filme filme = FilmeBuilder.builder(linha.split(REGEX));
                System.out.println(filme);
            });
        } catch (IOException e) {
        } 
    }
}
