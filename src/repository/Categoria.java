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

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }
}