package models.parsers;

import models.Filme;

public class MetadataParser<T> {
    private CSVParser<T> parser;
    private SetterFunction<Filme, T> attributeSetter;

    public MetadataParser(CSVParser<T> parser, SetterFunction<Filme, T> attributeSetter) {
        this.parser = parser;
        this.attributeSetter = attributeSetter;
    }
    
    public void setDataTo(Filme filme, String metadata) {
        attributeSetter.set(filme, parser.parse(metadata));
    }
}
