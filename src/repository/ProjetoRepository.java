package repository;

import model.Projeto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoRepository {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/locadora";
        Connection connection = DriverManager.getConnection(url, "folha_owner", "");

        return connection;
    }
    public void insere(Projeto projeto) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into " +
                "projeto values(null, ?, ?, ?, ?, ?,?)");

        stmt.setString(2, projeto.getNome());
        stmt.setString(3, projeto.getDescricao());
        stmt.setString(4, projeto.getCoordenacao());
        stmt.setInt(5, projeto.getInstituicao().getCodigo().intValue());
        stmt.setInt(6, projeto.getTipo().ordinal());
        stmt.setInt(7, projeto.getCategoria().ordinal());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }
    public List<Projeto> buscaPorId(Integer codigo) throws SQLException, ClassNotFoundException {
        List<Projeto> projetos = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from projeto WHERE id = ?");
        stmt.setInt(1, codigo);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            Projeto projeto = new Projeto();
            projeto.setCodigo(resultSet.getInt(1));
            projeto.setNome(resultSet.getString(2));
            projeto.setDescricao(resultSet.getString(3));
            projeto.setCoordenacao(resultSet.getString(4));

            //FK
            InstituicaoRepository instituicaoRepository = new InstituicaoRepository();
            projeto.setInstituicao(instituicaoRepository.buscaPorId(resultSet.getLong(5)).get(0));
            projeto.setTipo(TipoProjeto.getTipoById(resultSet.getInt(6)));
            projeto.setCategoria(Categoria.getCategoriaById(resultSet.getInt(7)));


            projetos.add(projeto);
        }
        connection.close();
        return projetos;
    }
    public List<Projeto> busca() throws SQLException, ClassNotFoundException {
        List<Projeto> projetos = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from projeto");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            Projeto projeto = new Projeto();
            projeto.setCodigo(resultSet.getInt(1));
            projeto.setNome(resultSet.getString(2));
            projeto.setDescricao(resultSet.getString(3));
            projeto.setCoordenacao(resultSet.getString(4));

            //FK
            InstituicaoRepository instituicaoRepository = new InstituicaoRepository();
            projeto.setInstituicao(instituicaoRepository.buscaPorId(resultSet.getLong(5)).get(0));
            projeto.setTipo(TipoProjeto.getTipoById(resultSet.getInt(6)));
            projeto.setCategoria(Categoria.getCategoriaById(resultSet.getInt(7)));


            projetos.add(projeto);
        }
        connection.close();
        return projetos;
    }
    public Integer proximoId() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("select max(id) from projeto");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }
    public void update(Projeto projeto) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update seguros " +
                "SET nome = ?, descricao = ?, coordenacao = ?, instituicao = ?, tipo = ?, categoria = ? WHERE id = ?");
        stmt.setString(1, projeto.getNome());
        stmt.setString(2, projeto.getDescricao());
        stmt.setString(3, projeto.getCoordenacao());
        stmt.setInt(4, projeto.getInstituicao().getCodigo().intValue());
        stmt.setInt(5, projeto.getTipo().ordinal());
        stmt.setInt(6, projeto.getCategoria().ordinal());
        stmt.setInt(7, projeto.getCodigo().intValue());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas");
        connection.close();
    }
    public void delete(Projeto projeto) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM projeto" +
                " WHERE id = ?");
        stmt.setInt(1, projeto.getCodigo().intValue());
        stmt.executeUpdate();
        connection.close();
    }



}

