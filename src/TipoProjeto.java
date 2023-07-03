//ISTO SERÁ UMA ENUM

public enum TipoProjeto {
    PESQUISA("Pesquisa"),
    EXTENSAO("Extensão"),
    GRADUACAO("Graduação"),
    POS_GRADUACAO("Pós graduação"),
    MESTRADO("Mestrado"),
    DOUTORADO("Doutorado"),
    STARTUP("Startup");

    private String descricaoTipo;

    TipoProjeto(String descricaoTipo) {
        this.descricaoTipo = descricaoTipo;
    }

    public String getDescricao() {
        return descricaoTipo;
    }
}
