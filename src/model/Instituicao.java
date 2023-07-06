package model;

import java.util.ArrayList;

public class Instituicao {

    private Integer codigo;
    private String nome;
    private String rua;
    private String bairro;
    private String numero;
    private String telefone;
    private Cidade cidade;
    private RedeSocial redeSocial ;

    public Instituicao(){

    }

    public Instituicao(String nome, String rua, String bairro, String numero, String telefone,
                       Cidade cidade, RedeSocial redeSocial) {
        this.nome = nome;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.telefone = telefone;
        this.cidade = cidade;
        this.redeSocial = redeSocial;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public RedeSocial getRedeSocial() {
        return redeSocial;
    }


    public void setRedeSocial(RedeSocial redeSocial) {
        this.redeSocial = redeSocial;

    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "model.Instituicao{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", numero='" + numero + '\'' +
                ", telefone='" + telefone + '\'' +
                ", redesSociais=" + redeSocial +
                '}';
    }
}
