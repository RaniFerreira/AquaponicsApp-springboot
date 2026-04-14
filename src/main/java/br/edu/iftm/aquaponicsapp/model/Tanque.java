package br.edu.iftm.aquaponicsapp.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tanque")
public class Tanque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @Size(min = 3, max = 50, message = "Nome deve conter entre 3 e 50 caracteres")
    @NotBlank(message = "Nome é obrigatório")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    @Column(nullable = false)
    private String descricao;

    @NotNull(message = "Informe o tamanho dos peixes")
    @Min(value = 1, message = "O tamanho deve ser maior que 0")
    @Column(nullable = false)
    private Double tamanhoPeixes;

    @NotNull(message = "Informe a quantidade de peixes")
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    @Column(nullable = false)
    private Integer quantidadePeixes;

    @NotBlank(message = "Espécie é obrigatória")
    @Column(nullable = false)
    private String especie;

    @NotNull(message = "Informe o peso médio")
    @DecimalMin(value = "0.1", message = "O peso deve ser maior que 0")
    @Column(nullable = false)
    private Double peso;

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