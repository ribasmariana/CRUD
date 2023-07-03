package model;

public class RedeSocial {

    private Integer id;
    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public RedeSocial(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public RedeSocial(String descricao) {
        this.descricao = descricao;
    }
}
