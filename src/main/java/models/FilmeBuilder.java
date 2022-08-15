package models;

import java.time.Duration;
import java.time.Year;
import java.util.List;


import models.parsers.CSVParser;
import models.parsers.MetadataParser;

public class FilmeBuilder {

    private static List<MetadataParser> parsers = List.of(
        new MetadataParser<Integer>(CSVParser.INTEGER, Filme::setRank),
        new MetadataParser<String>(CSVParser.STRING, Filme::setTitulo),
        new MetadataParser<List<Genero>>(CSVParser.GENERO_LISTA, Filme::setGeneros),
        new MetadataParser<String>(CSVParser.STRING, Filme::setDescricao),
        new MetadataParser<Diretor>(CSVParser.DIRETOR, Filme::setDiretor),
        new MetadataParser<List<Ator>>(CSVParser.ATOR_LISTA, Filme::setAtores),
        new MetadataParser<Year>(CSVParser.YEAR, Filme::setAno),
        new MetadataParser<Duration>(CSVParser.DURATION, Filme::setDuracao),
        new MetadataParser<Double>(CSVParser.DOUBLE, Filme::setNota),
        new MetadataParser<Integer>(CSVParser.INTEGER, Filme::setVotos),
        new MetadataParser<Double>(CSVParser.DOUBLE, Filme::setRedimento),
        new MetadataParser<Integer>(CSVParser.INTEGER, Filme::setMetascore)
    );

    public static Filme builder(String[] metadados) {
        Filme filme = new Filme();
        
        for (int i = 0; i < metadados.length; i++) 
            parsers.get(i).setDataTo(filme, metadados[i]);
        
        return filme;
    }
}
