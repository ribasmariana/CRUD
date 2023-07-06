package repository;//ISTO SERÁ UMA ENUM

public enum TipoProjeto {
    PESQUISA(1,"Pesquisa"),
    EXTENSAO(2,"Extensão"),
    GRADUACAO(3,"Graduação"),
    POS_GRADUACAO(4,"Pós graduação"),
    MESTRADO(5,"Mestrado"),
    DOUTORADO(6,"Doutorado"),
    STARTUP(7,"Startup");

    private String descricaoTipo;
    private Integer codigo;

    TipoProjeto(Integer codigo ,String descricaoTipo) {
        this.codigo = codigo;
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
            } else if (opcao == 7){
            return STARTUP;
        }else {
            return null;
        }
    }

    public String getDescricao() {
        return descricaoTipo;
    }
}
