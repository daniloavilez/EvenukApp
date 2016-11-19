package com.evenuk.business;

/**
 * Created by Danilo on 15/11/2016.
 */

public class Comentario {

    public Comentario() {

    }

    private String Descricao;

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    private String IdUsuario;

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    private String DataComentario;

    public String getDataComentario() {
        return DataComentario;
    }

    public void setDataComentario(String dataComentario) {
        DataComentario = dataComentario;
    }
}
