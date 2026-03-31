package br.edu.iftm.aquaponicsapp.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tanque")
public class Tanque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome; // nome do tanque

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Double tamanhoPeixes; // centímetros

    @Column(nullable = false)
    private Integer quantidadePeixes;

    @Column(nullable = false)
    private String especie;

    @Column(nullable = false)
    private Double peso; // peso médio dos peixes (kg ou g)

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getTamanhoPeixes() {
        return tamanhoPeixes;
    }

    public void setTamanhoPeixes(Double tamanhoPeixes) {
        this.tamanhoPeixes = tamanhoPeixes;
    }

    public Integer getQuantidadePeixes() {
        return quantidadePeixes;
    }

    public void setQuantidadePeixes(Integer quantidadePeixes) {
        this.quantidadePeixes = quantidadePeixes;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}