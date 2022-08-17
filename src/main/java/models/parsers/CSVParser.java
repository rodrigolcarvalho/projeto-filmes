package models.parsers;

import java.time.Duration;
import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import models.Ator;
import models.Diretor;
import models.Genero;

public interface CSVParser<T> {
    public static final CSVParser<Integer> INTEGER = Integer::parseInt;
    public static final CSVParser<Double> DOUBLE = metadado -> {
        if(metadado.isBlank())
            return 0D;

        return Double.parseDouble(metadado);
    };

    public static final CSVParser<String> STRING = metadado -> metadado;
    public static final CSVParser<List<Genero>> GENERO_LISTA = metadado -> {
        metadado = metadado.replaceAll("\"", " ");
        metadado = metadado.replaceAll("-", "_");
        metadado = metadado.toUpperCase().trim();
        Stream<String> generos = Arrays.stream(metadado.split(","));

        return generos.map(genero -> Enum.valueOf(Genero.class, genero))
                    .collect(Collectors.toList());
    };
    public static final CSVParser<Diretor> DIRETOR = Diretor::new; 
    public static final CSVParser<Year> YEAR = metadado -> Year.of(INTEGER.parse(metadado)); 
    public static final CSVParser<Duration> DURATION = metadado -> Duration.ofMinutes(INTEGER.parse(metadado));  
    public static final CSVParser<List<Ator>> ATOR_LISTA = metadado -> {
        metadado = metadado.replaceAll("\"", " ");
        metadado = metadado.replaceAll("-", "_");
        metadado = metadado.toUpperCase().trim();
        Stream<String> atores = Arrays.stream(metadado.split(","));

        return atores.map(Ator::new).collect(Collectors.toList());
    };

    T parse(String metadado);
}
