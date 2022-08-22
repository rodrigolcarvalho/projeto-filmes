package models;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Duration;
import java.time.Year;
import java.util.List;
import java.util.Objects;

// @Getters
@Entity
@Table(name="filmes")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rank;
    private String titulo;
    // @JoinTable(name = "filmes",
    // joinColumns = @JoinColumn(name = "id"))
    @ElementCollection(targetClass = Genero.class)
    @Enumerated(EnumType.STRING)
    private List<Genero> generos;
    private String descricao;
    @OneToOne
    private Diretor diretor;
    @OneToMany(mappedBy = "filmeEstrelado", cascade = CascadeType.ALL)
    private List<Ator> atores;
    private Year ano;
    private Duration duracao;
    private Double nota;
    private Integer votos;
    private Double redimento;
    private Integer metascore;


    public Filme() {
    }

    public Filme(Integer rank, String titulo, List<Genero> generos, String descricao, Diretor diretor, List<Ator> atores, Year ano, Duration duracao, Double nota, Integer votos, Double redimento, Integer metascore) {
        this.rank = rank;
        this.titulo = titulo;
        this.generos = generos;
        this.descricao = descricao;
        this.diretor = diretor;
        this.atores = atores;
        this.ano = ano;
        this.duracao = duracao;
        this.nota = nota;
        this.votos = votos;
        this.redimento = redimento;
        this.metascore = metascore;
    }

    public Integer getRank() {
        return this.rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Genero> getGeneros() {
        return this.generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Diretor getDiretor() {
        return this.diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public List<Ator> getAtores() {
        return this.atores;
    }

    public void setAtores(List<Ator> atores) {
        this.atores = atores;
    }

    public Year getAno() {
        return this.ano;
    }

    public void setAno(Year ano) {
        this.ano = ano;
    }

    public Duration getDuracao() {
        return this.duracao;
    }

    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

    public Double getNota() {
        return this.nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Integer getVotos() {
        return this.votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    public Double getRedimento() {
        return this.redimento;
    }

    public void setRedimento(Double redimento) {
        this.redimento = redimento;
    }

    public Integer getMetascore() {
        return this.metascore;
    }

    public void setMetascore(Integer metascore) {
        this.metascore = metascore;
    }

    public Filme rank(Integer rank) {
        setRank(rank);
        return this;
    }

    public Filme titulo(String titulo) {
        setTitulo(titulo);
        return this;
    }

    public Filme generos(List<Genero> generos) {
        setGeneros(generos);
        return this;
    }

    public Filme descricao(String descricao) {
        setDescricao(descricao);
        return this;
    }

    public Filme diretor(Diretor diretor) {
        setDiretor(diretor);
        return this;
    }

    public Filme atores(List<Ator> atores) {
        setAtores(atores);
        return this;
    }

    public Filme ano(Year ano) {
        setAno(ano);
        return this;
    }

    public Filme duracao(Duration duracao) {
        setDuracao(duracao);
        return this;
    }

    public Filme nota(Double nota) {
        setNota(nota);
        return this;
    }

    public Filme votos(Integer votos) {
        setVotos(votos);
        return this;
    }

    public Filme redimento(Double redimento) {
        setRedimento(redimento);
        return this;
    }

    public Filme metascore(Integer metascore) {
        setMetascore(metascore);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Filme)) {
            return false;
        }
        Filme filme = (Filme) o;
        return Objects.equals(rank, filme.rank) && Objects.equals(titulo, filme.titulo) && Objects.equals(generos, filme.generos) && Objects.equals(descricao, filme.descricao) && Objects.equals(diretor, filme.diretor) && Objects.equals(atores, filme.atores) && Objects.equals(ano, filme.ano) && Objects.equals(duracao, filme.duracao) && Objects.equals(nota, filme.nota) && Objects.equals(votos, filme.votos) && Objects.equals(redimento, filme.redimento) && Objects.equals(metascore, filme.metascore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, titulo, generos, descricao, diretor, atores, ano, duracao, nota, votos, redimento, metascore);
    }

    @Override
    public String toString() {
        return "{" +
            " rank='" + getRank() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", generos='" + getGeneros() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", diretor='" + getDiretor() + "'" +
            ", itemAtores='" + getAtores() + "'" +
            ", ano='" + getAno() + "'" +
            ", duracao='" + getDuracao().toMinutes() + "'" +
            ", nota='" + getNota() + "'" +
            ", votos='" + getVotos() + "'" +
            ", redimento='" + getRedimento() + "'" +
            ", metascore='" + getMetascore() + "'" +
            "}";
    }
}
