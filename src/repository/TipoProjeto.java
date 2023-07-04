package repository;//ISTO SERÁ UMA ENUM

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

    public static String getEnumByString(String code) {
        for (TipoProjeto e : TipoProjeto.values()) {
            if (e.descricaoTipo.equals(code)) {
                return e.name();
            }
        }
        return null;
    }
    public static void add(TipoProjeto escolheTipo) {

    }

    public String getDescricao() {
        return descricaoTipo;
    }
}
