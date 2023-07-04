package repository;

import model.RedeSocial;

import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<E> {

    void salvar(E objeto);

    void remover(E objeto) throws SQLException, ClassNotFoundException;

    void salvar(RedeSocial redeSocial);

    void remover(RedeSocial redeSocial) throws SQLException, ClassNotFoundException;

    List<E> buscarTodos() throws SQLException, ClassNotFoundException;

    List<E> buscarPorNome(String nome);

}