package models;

import java.util.Objects;

public class Ator {
    private String nome;

    public Ator() {
    }

    public Ator(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Ator nome(String nome) {
        setNome(nome);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ator)) {
            return false;
        }
        Ator ator = (Ator) o;
        return Objects.equals(nome, ator.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }

    @Override
    public String toString() {
        return "{" +
                " nome='" + getNome() + "'" +
                "}";
    }

}
