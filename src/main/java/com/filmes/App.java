package com.filmes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import models.Filme;
import models.FilmeBuilder;
import models.Genero;

public class App {

    private static final String REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    public static void main(String[] args) throws IOException {
        Set<Filme> filmes = CarregarFilmes("resources/movies-csv/movies3.csv", false);
        filmes.addAll(CarregarFilmes("resources/movies-csv/movies2.csv", false));
        filmes.addAll(CarregarFilmes("resources/movies-csv/movies1.csv", true));

        getTop20Horror(filmes);

        getTop50porAno(filmes);

    }

    private static void getTop50porAno(Set<Filme> filmes) {
        filmes.stream().collect(Collectors.groupingBy(Filme::getAno)).forEach((ano, listaFilmes) -> {
            listaFilmes.sort((Comparator.comparing(Filme::getNota).reversed()));

            String filmesAno = listaFilmes.stream()
                    .limit(50)
                    .map(Filme::toString)
                    .reduce((f1, f2) -> f1 + "\n" + f2).get();

            try {
                Path anoPath = Path.of(ano + ".txt");
                Files.write(anoPath, filmesAno.getBytes());
            } catch (Exception e) {
            }
        });
    }

    private static void getTop20Horror(Set<Filme> filmes) {
        String top20 = filmes.stream()
                .filter(filme -> filme.getGeneros().contains(Genero.HORROR))
                .sorted(Comparator.comparing(Filme::getNota).reversed())
                .limit(20)
                .map(Filme::toString)
                .reduce((f1, f2) -> f1 + "\n" + f2).get();
        System.out.println("Top 20: ");

        try {
            Path top20Horror = Path.of("top20Horror.txt");
            Files.write(top20Horror, top20.getBytes());
        } catch (Exception e) {
        }

    }

    private static Set<Filme> CarregarFilmes(String path, Boolean skipHeader) {
        Path arquivoFilmes = Paths.get(path);
        Set<Filme> filmes = new HashSet<>();

        if (!Files.exists(arquivoFilmes))
            return null;

        try (Stream<String> linhas = Files.lines(arquivoFilmes)) {
            linhas.skip(skipHeader ? 1 : 0)
                    .forEach(linha -> {
                        Filme filme = FilmeBuilder.builder(linha.split(REGEX));
                        filmes.add(filme);
                    });
        } catch (IOException e) {
        }
        return filmes;

    }
}
