package com.filmes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import models.Filme;
import models.FilmeBuilder;
import models.Genero;

public class App {

    private static final String REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
    private static final String DIRETORIO_ARQUIVO = "arquivosDeSaida";
    private static File diretorio = new File(DIRETORIO_ARQUIVO);

    public static void main(String[] args) throws IOException {
        Set<Filme> filmes = CarregarFilmes("resources/movies-csv/movies3.csv", false);
        filmes.addAll(CarregarFilmes("resources/movies-csv/movies2.csv", false));
        filmes.addAll(CarregarFilmes("resources/movies-csv/movies1.csv", true));
        diretorio.mkdir();
        final DateTimeFormatter formatoCustomizado = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");
        LocalDateTime inicioProcessamento = LocalDateTime.now();
        
        System.out.println("Início do processamento: " + inicioProcessamento.format(formatoCustomizado));
        
        System.out.println("Gerando arquivo top20Horror...");
        getTop20Horror(filmes);
        
        System.out.println("Gerando arquivos de filmes por ano...");
        getTop50porAno(filmes);
        
        LocalDateTime fimProcessamento = LocalDateTime.now();
        System.out.println("Fim do processamento: " + fimProcessamento.format(formatoCustomizado));

        long duracaoMillis = inicioProcessamento.until(fimProcessamento, ChronoUnit.MILLIS);
        // long duracaoSec= inicioProcessamento.until(fimProcessamento, ChronoUnit.SECONDS);
        System.out.println("Tempo em milisegundos: " + duracaoMillis + " milisegundos");
        System.out.println("Tempo em segundos: " + (duracaoMillis/1000.0) + " segundo");
        tempoProcessamento(inicioProcessamento,fimProcessamento,duracaoMillis);
    }

    private static void getTop50porAno(Set<Filme> filmes) {
        filmes.stream().collect(Collectors.groupingBy(Filme::getAno)).forEach((ano, listaFilmes) -> {
            listaFilmes.sort((Comparator.comparing(Filme::getNota).reversed()));

            List<String> filmesAnot = new ArrayList<>();

            listaFilmes.stream()
                    .limit(50)
                    .forEach(filme -> filmesAnot.add(filme.toString()));

            try {
                //Path anoPath = Path.of(ano + ".txt");
                Path anoPath = Path.of(DIRETORIO_ARQUIVO+"/"+ano + ".txt");
                Files.write(anoPath, filmesAnot, StandardCharsets.UTF_8);
            } catch (Exception e) {
            }
        });
    }

    private static void getTop20Horror(Set<Filme> filmes) {
        List<String> top20teste = new ArrayList<>();

        filmes.stream()
                .filter(filme -> filme.getGeneros().contains(Genero.HORROR))
                .sorted(Comparator.comparing(Filme::getNota).reversed())
                .limit(20)
                .forEach(filme -> top20teste.add(filme.toString()));

        try {
            //Path top20Horror = Path.of("top20Horror.txt");
            Path top20Horror = Path.of(DIRETORIO_ARQUIVO+"/"+"top20Horror.txt");
            Files.write(top20Horror, top20teste, StandardCharsets.UTF_8);
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

    private static void tempoProcessamento(LocalDateTime inicio, LocalDateTime fim,long duracaoMillis) {
    	final DateTimeFormatter formatoCustomizado = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");
    	
    	 try {
             //Path tempoProcessamento = Path.of("tempoDeProcessamento.txt");
             Path tempoProcessamento = Path.of(DIRETORIO_ARQUIVO+"/"+"tempoDeProcessamento.txt");
             Files.writeString(tempoProcessamento, "Incio processamento: "+inicio.format(formatoCustomizado)+"\n", StandardCharsets.UTF_8);
             Files.writeString(tempoProcessamento, "Fim processamento: "+fim.format(formatoCustomizado)+"\n", StandardCharsets.UTF_8,StandardOpenOption.APPEND);
             Files.writeString(tempoProcessamento, "Tempo em milisegundos: "+duracaoMillis +" milissegundos\n", StandardCharsets.UTF_8,StandardOpenOption.APPEND);
             Files.writeString(tempoProcessamento, "Tempo em segundos: "+ (duracaoMillis/1000.00) + " segundos", StandardCharsets.UTF_8,StandardOpenOption.APPEND);
    	 } catch (Exception e) {
         }
    
    }
}
