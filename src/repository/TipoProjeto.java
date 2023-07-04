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

    public static TipoProjeto getTipoById(Integer opcao) {
        if (opcao == 1) {
            return PESQUISA;
        } else if (opcao == 2) {
            return EXTENSAO;
        }  else if (opcao == 3) {
            return GRADUACAO;
        }  else if (opcao == 4) {
             return POS_GRADUACAO;
        }  else if (opcao == 5) {
             return MESTRADO;
        }  else if (opcao == 6) {
             return DOUTORADO;
            } else {
            return STARTUP;
        }
    }

    public String getDescricao() {
        return descricaoTipo;
    }
}
