package com.evenuk.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Feed {

    public Feed() {

    }

    public Feed(Integer key, String autor, String TituloEvento, Date DataEvento, String DescricaoEvento, List<Comentario> Comentarios,
                List<String> Gostar) {
        this.setKey(key);
        this.setAutor(autor);
        this.setTituloEvento(TituloEvento);
        this.setDataEvento(DataEvento);
        this.setDescricaoEvento(DescricaoEvento);
        this.setComentarios(Comentarios);
        this.setGostar(Gostar);
    }

    private Integer Key;

    public Integer getKey() {
        return Key;
    }

    public void setKey(Integer key) {
        Key = key;
    }

    private String Autor;

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    private String TituloEvento;

    public String getTituloEvento() {
        return TituloEvento;
    }

    public void setTituloEvento(String tituloEvento) {
        TituloEvento = tituloEvento;
    }

    private Date DataEvento;

    public Date getDataEvento() {
        return DataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        DataEvento = dataEvento;
    }

    private String DescricaoEvento;

    public String getDescricaoEvento() {
        return DescricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        DescricaoEvento = descricaoEvento;
    }

    private List<Comentario> Comentarios;

    public List<Comentario> getComentarios() {
        return Comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        Comentarios = comentarios;
    }

    private List<String> Gostar;

    public List<String> getGostar() {
        return Gostar;
    }

    public void setGostar(List<String> gostar) {
        Gostar = gostar;
    }
}
