package models;

import java.util.Objects;

public class Diretor {
    private String nome;

    public Diretor() {
    }

    public Diretor(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Diretor nome(String nome) {
        setNome(nome);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Diretor)) {
            return false;
        }
        Diretor diretor = (Diretor) o;
        return Objects.equals(nome, diretor.nome);
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
