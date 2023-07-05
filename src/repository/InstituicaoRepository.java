package repository;

import model.Instituicao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstituicaoRepository extends Conectora {


    public void insere(Instituicao instituicao) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into " +
                "instituicao values(null, ?, ?, ?, ?, ?,?,?)");
        stmt.setString(2, instituicao.getNome());
        stmt.setString(3, instituicao.getRua());
        stmt.setString(4, instituicao.getBairro());
        stmt.setString(5, instituicao.getNumero());
        stmt.setString(6, instituicao.getTelefone());
        stmt.setObject(7, instituicao.getCidade());
        stmt.setArray(8, (Array) instituicao.getRedesSociais());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }
    public List<Instituicao> buscaPorId(Long id) throws SQLException, ClassNotFoundException {
        List<Instituicao> instituicoes = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from instituicao WHERE id = ?");
        stmt.setLong(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            Instituicao instituicao = new Instituicao();
            instituicao.setCodigo(resultSet.getInt(1));

            instituicao.setNome(resultSet.getString(2));
            instituicao.setRua(resultSet.getString(3));
            instituicao.setBairro(resultSet.getString(4));
            instituicao.setNumero(resultSet.getString(5));
            instituicao.setTelefone(resultSet.getString(6));

            //FK
            CidadeRepository cidadeRepository = new CidadeRepository();
            instituicao.setCidade(cidadeRepository.buscaPorId(resultSet.getLong(7)).get(0));

            //FK
            RedeSocialRepository redeSocialRepository = new RedeSocialRepository();
            instituicao.setRedesSociais((List) redeSocialRepository.buscaPorId(resultSet.getLong(8)).get(0));



            instituicoes.add(instituicao);
        }
        connection.close();
        return instituicoes;
    }
    public List<Instituicao> busca() throws SQLException, ClassNotFoundException {
        List<Instituicao> instituicoes = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from instituicao");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            Instituicao instituicao = new Instituicao();
            instituicao.setCodigo(resultSet.getInt(1));

            instituicao.setNome(resultSet.getString(2));
            instituicao.setRua(resultSet.getString(3));
            instituicao.setBairro(resultSet.getString(4));
            instituicao.setNumero(resultSet.getString(5));
            instituicao.setTelefone(resultSet.getString(6));

            //FK
            CidadeRepository cidadeRepository = new CidadeRepository();
            instituicao.setCidade(cidadeRepository.buscaPorId(resultSet.getLong(7)).get(0));

            //FK
            RedeSocialRepository redeSocialRepository = new RedeSocialRepository();
            instituicao.setRedesSociais((List) redeSocialRepository.buscaPorId(resultSet.getLong(8)).get(0));



            instituicoes.add(instituicao);
        }
        connection.close();
        return instituicoes;
    }
    public Integer proximoId() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("select max(id) from instituicao");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }
    public void update(Instituicao instituicao) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update instituicao " +
                "SET nome = ?, rua = ?, bairro = ?, numero = ?, telefone = ?, cidade = ?,redeSocial = ? WHERE id = ?");
        stmt.setString(1, instituicao.getNome());
        stmt.setString(2, instituicao.getRua());
        stmt.setString(3, instituicao.getBairro());
        stmt.setString(4, instituicao.getNumero());
        stmt.setString(5, instituicao.getTelefone());
        stmt.setObject(6, instituicao.getCidade());
        stmt.setArray(7, (Array) instituicao.getRedesSociais());
        stmt.setInt(8, instituicao.getCodigo());


        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas");
        connection.close();
    }
    public void delete(Instituicao instituicao) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM instituicao" +
                " WHERE id = ?");
        stmt.setInt(1, instituicao.getCodigo());
        stmt.executeUpdate();
        connection.close();
    }


}
