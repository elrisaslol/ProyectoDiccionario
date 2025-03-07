package org.chatta.movieproyect.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 1000)
    @NotNull
    @Column(name = "titulo", nullable = false, length = 1000)
    private String titulo;

    @Size(max = 1000)
    @NotNull
    @Column(name = "`descripción`", nullable = false, length = 1000)
    private String descripción;

    @NotNull
    @Column(name = "`año`", nullable = false)
    private Integer año;

    @NotNull
    @Column(name = "voto", nullable = false)
    private Integer voto;

    @Column(name = "`valoración`")
    private Double valoración;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }

    public Double getValoración() {
        return valoración;
    }

    public void setValoración(Double valoración) {
        this.valoración = valoración;
    }

}