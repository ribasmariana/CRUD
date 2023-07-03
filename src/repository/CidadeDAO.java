package repository;

import model.Cidade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class CidadeDAO implements IGenericDAO<Cidade>{

    static List<Cidade> cidades = new ArrayList<>();


    public CidadeDAO() {
        super();
    }

    @Override
    public void salvar(Cidade cidade) {
        CidadeRepository cidadeRepository = new CidadeRepository();
        try {
            if (cidade.getId() != null) {
                cidadeRepository.update(cidade);
            } else {
                cidade.setId(cidadeRepository.proximoId().longValue());
                cidadeRepository.insere(cidade);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        cidades.add(cidade);

    }

    @Override
    public void remover(Cidade objeto) throws SQLException, ClassNotFoundException {

    }

    @Override
    public List<Cidade> buscarTodos() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Cidade> buscarPorNome(String nome) {
        return null;
    }
}
