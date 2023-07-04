package repository;//ISTO SERÁ UMA ENUM

public enum Categoria {
    TECNOLOGIA("Tecnologia"),
    ENGENHARIA("Engenharia"),
    AGRONOMIA("Agronomia"),
    SAUDE("Saúde"),
    EDUCACAO("Educação"),
    SOCIAL("Social");

    private String descricaoCategoria;

    Categoria (String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }

    public static String getEnumByString(String code) {
        for (Categoria e : Categoria.values()) {
            if (e.descricaoCategoria.equals(code)) {
                return e.name();
            }
        }
        return null;
    }
    public static void add(Categoria escolheCategoria) {

    }

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }
}