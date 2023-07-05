import model.Cidade;
import model.Instituicao;
import model.Projeto;
import repository.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        exibirMensagemBoasVindas();
        chamaMenuPrincipal();
    }

    private static void exibirMensagemBoasVindas() {
        JOptionPane.showOptionDialog(null, "Seja bem-vindo ao Inova Brasil!",
                "Mensagem de boas-vindas", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Entrar"}, "Entrar");

    }

    public static void chamaMenuPrincipal() throws SQLException, ClassNotFoundException {
        String[] opcoesMenu = {"Cidades", "Projetos", "Instituições", "Sair"};
        int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);

        switch (opcao) {
            case 0: //Cidades
                chamaMenuCidade();
                break;

            case 1: //Projetos
                chamaMenuProjetos();
                break;

            case 2: //Instituições
                chamaMenuInstituicoes();
                break;

            case 3: //SAIR
                int opcaoSair = JOptionPane.showOptionDialog(null, " Deseja realmente sair ? ",
                        "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (opcaoSair == JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                } else {
                    chamaMenuPrincipal();
                }
        }
    }

    /////////////////////////////////////

    public static void chamaMenuCidade() throws SQLException, ClassNotFoundException {
        String[] opcoesMenuCidade = {"Cadastrar", "Alterar", "Excluir", "Gerar relatório", "Voltar"};
        int menuCidade = JOptionPane.showOptionDialog(null, "Escolha o processo que deseja realizar",
                "CIDADES",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCidade, opcoesMenuCidade[0]);

        switch (menuCidade) {
            case 0:
                Cidade cidadeCriada = chamaCadastroCidade();
                JOptionPane.showMessageDialog(null, "Cidade " + cidadeCriada + " alterada com sucesso!");
                chamaMenuPrincipal();
                break;
            case 1:
                Cidade cidadeAlterada = chamaAlterarCidade();
                JOptionPane.showMessageDialog(null, "Cidade " + cidadeAlterada + " alterada com sucesso!");
                chamaMenuPrincipal();
                break;
            case 2:
                Cidade cidadeExcluida = chamaExcluirCidade();
                JOptionPane.showMessageDialog(null, "Cidade " + cidadeExcluida + " alterada com sucesso!");
                chamaMenuPrincipal();
                break;
            case 3:
                chamaRelatorioCidade();
                break;
        }
    }

    private static Cidade chamaCadastroCidade() {

        String nome = JOptionPane.showInputDialog(null, "Informe o nome da cidade");
        if (nome == null) {
            chamaCadastroCidade();
        }

        String uf = JOptionPane.showInputDialog(null, "Informe a UF da cidade");
        if (uf == null) {
            chamaCadastroCidade();
        }

        Cidade cidade = new Cidade();
        cidade.setNome(nome);
        cidade.setUf(uf);
        return cidade;

    }

    private static Cidade chamaAlterarCidade() throws SQLException, ClassNotFoundException {
        Object[] selectionValuesCidades = getCidadeDAO().findCidadesInArray();
        String initialSelectionCidade = (String) selectionValuesCidades[0];
        Object selectionCidade = JOptionPane.showInputDialog(null, "Selecione a cidade que deseja alterar",
                "Cidades", JOptionPane.QUESTION_MESSAGE, null, selectionValuesCidades, selectionValuesCidades);
        List<Cidade> cidades = getCidadeDAO().buscarPorNome((String) selectionCidade);
        Cidade cidade = cidades.get(0);

        String nome = JOptionPane.showInputDialog(null, "Informe o nome da cidade", cidade.getNome());
        if (nome == null) {
            chamaCadastroCidade();
        }

        String uf = JOptionPane.showInputDialog(null, "Informe a UF da cidade", cidade.getUf());
        if (uf == null) {
            chamaCadastroCidade();
        }

        cidade.setNome(nome);
        cidade.setUf(uf);

        return cidade;

    }

    private static Cidade chamaExcluirCidade() throws SQLException, ClassNotFoundException {
        Object[] selectionValuesCidades = getCidadeDAO().buscarTodos().toArray(new Cidade[0]);
        Object selectionCidades = JOptionPane.showInputDialog(null, "Selecione a cidade que deseja remover:",
                "Remover Cidade", JOptionPane.DEFAULT_OPTION, null, selectionValuesCidades, null);

        if (selectionCidades != null) {
            Cidade cidadeSelecionada = (Cidade) selectionCidades;
            getCidadeDAO().remover(cidadeSelecionada);

            JOptionPane.showMessageDialog(null, "Cidade removida com sucesso!");
            chamaMenuPrincipal();
        }
        return null;
    }

    private static void chamaRelatorioCidade() {
        CidadeDAO cidadeDAO = new CidadeDAO();
        List<Cidade> cidades = cidadeDAO.buscarTodos();
        RelatorioCidadeForm.emitirRelatorio(cidades);
    }

    ////////////////////////////////

    public static void chamaMenuProjetos() throws SQLException, ClassNotFoundException {
        String[] opcoesMenuProjetos = {"Cadastrar", "Alterar", "Excluir", "Gerar relatório", "Voltar"};
        int menuProjeto = JOptionPane.showOptionDialog(null, "Escolha o processo que deseja realizar",
                "PROJETOS",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuProjetos, opcoesMenuProjetos[0]);

        switch (menuProjeto) {
            case 0:
                chamaCadastroProjeto();
                break;
            case 1:
                chamaAlterarProjeto();
                break;
            case 2:
                chamaExcluirProjeto();
                break;
            case 3:
                chamaRelatorioProjeto();
                break;
        }
    }

    private static void chamaCadastroProjeto() throws SQLException, ClassNotFoundException {

        String nome = JOptionPane.showInputDialog(null, "Informe o nome do projeto");
        if (nome == null) {
            chamaCadastroProjeto();
        }

        String descricao = JOptionPane.showInputDialog(null, "Informe a descrição do projeto");
        if (descricao == null) {
            chamaCadastroProjeto();
        }

        String coordenacao = JOptionPane.showInputDialog(null, "Informe o nome do responsável pelo projeto");
        if (coordenacao == null) {
            chamaCadastroProjeto();
        }

        TipoProjeto[] tipoProjetos = TipoProjeto.values();
        String[] tiposNomes = new String[tipoProjetos.length];
        for (int i = 0; i < tipoProjetos.length; i++) {
            tiposNomes[i] = tipoProjetos[i].getDescricao();
        }

        int option = JOptionPane.showOptionDialog(null, "Selecione o tipo do projeto", "Tipos de projeto",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, tiposNomes, tiposNomes[0]);

        TipoProjeto tipoSelecionado = null;
        if (option != JOptionPane.CLOSED_OPTION) {
            tipoSelecionado = tipoProjetos[option];
        } else {
            chamaCadastroProjeto();
        }

        Categoria[] categorias = Categoria.values();
        String[] categoriasNomes = new String[categorias.length];
        for (int i = 0; i < categorias.length; i++) {
            categoriasNomes[i] = categorias[i].getDescricaoCategoria();
        }

        int optionC = JOptionPane.showOptionDialog(null, "Selecione a categoria do projeto", "Categorias",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, categoriasNomes, categoriasNomes[0]);

        Categoria categoriaSelecionada = null;
        if (optionC != JOptionPane.CLOSED_OPTION) {
            categoriaSelecionada = categorias[optionC];
        } else {
            chamaCadastroProjeto();
        }

        List<Instituicao> instituicaos = getInstituicaoDAO().buscarTodos();

        if (instituicaos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma instituição cadastrada!");
            chamaMenuPrincipal();
        }

        Object[] selectionInstituicao = instituicaos.stream().map(Instituicao::getNome).toArray();
        String initialSelectionInstituicao = (String) selectionInstituicao[0];

        Object selecInstituicao = JOptionPane.showInputDialog(null, "Selecione a instituição",
                "Instituições", JOptionPane.QUESTION_MESSAGE, null, selectionInstituicao, initialSelectionInstituicao);

        if (selecInstituicao == null) {
            JOptionPane.showMessageDialog(null, "Cadastro de projeto cancelado.");
            chamaMenuPrincipal();
        }

        Projeto projeto = new Projeto();
        projeto.setNome(nome);
        projeto.setTipo(tipoSelecionado);
        projeto.setDescricao(descricao);
        projeto.setCoordenacao(coordenacao);
        projeto.setCategoria(categoriaSelecionada);
        projeto.setInstituicao(instituicaos.get(0));

        JOptionPane.showMessageDialog(null, "Projeto cadastrado com sucesso!");
        chamaMenuPrincipal();
    }

    private static void chamaAlterarProjeto() throws SQLException, ClassNotFoundException {
        Object[] selectionValuesProjetos = getProjetoDAO().findProjetosInArray();
        String initialSelectionProjeto = (String) selectionValuesProjetos[0];
        Object selectionProjeto = JOptionPane.showInputDialog(null, "Selecione o projeto que deseja alterar",
                "Projetos", JOptionPane.QUESTION_MESSAGE, null, selectionValuesProjetos, selectionValuesProjetos);

        List<Projeto> projetos = getProjetoDAO().buscarPorNome((String) selectionProjeto);
        Projeto projeto = projetos.get(0);

        String nome = JOptionPane.showInputDialog(null, "Informe o nome do projeto", projeto.getNome());
        if (nome == null) {
            chamaCadastroProjeto();
        }

        String descricao = JOptionPane.showInputDialog(null, "Informe a descrição do projeto", projeto.getDescricao());
        if (descricao == null) {
            chamaCadastroProjeto();
        }

        String coordenacao = JOptionPane.showInputDialog(null, "Informe o nome do responsável pelo projeto", projeto.getCoordenacao());
        if (coordenacao == null) {
            chamaCadastroProjeto();
        }

        TipoProjeto[] tipoProjetos = TipoProjeto.values();
        String[] tiposNomes = new String[tipoProjetos.length];
        for (int i = 0; i < tipoProjetos.length; i++) {
            tiposNomes[i] = tipoProjetos[i].getDescricao();
        }

        int option = JOptionPane.showOptionDialog(null, "Selecione o tipo do projeto", "Tipos de projeto",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, tiposNomes, tiposNomes[0]);

        TipoProjeto tipoSelecionado = null;
        if (option != JOptionPane.CLOSED_OPTION) {
            tipoSelecionado = tipoProjetos[option];
        } else {
            chamaCadastroProjeto();
        }

        Categoria[] categorias = Categoria.values();
        String[] categoriasNomes = new String[categorias.length];
        for (int i = 0; i < categorias.length; i++) {
            categoriasNomes[i] = categorias[i].getDescricaoCategoria();
        }

        int optionC = JOptionPane.showOptionDialog(null, "Selecione a categoria do projeto", "Categorias",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, categoriasNomes, categoriasNomes[0]);

        Categoria categoriaSelecionada = null;
        if (optionC != JOptionPane.CLOSED_OPTION) {
            categoriaSelecionada = categorias[optionC];
        } else {
            chamaCadastroProjeto();
        }

        List<Instituicao> instituicaos = getInstituicaoDAO().buscarTodos();

        if (instituicaos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma instituição cadastrada!");
            chamaMenuPrincipal();
        }

        Object[] selectionInstituicao = instituicaos.stream().map(Instituicao::getNome).toArray();
        String initialSelectionInstituicao = (String) selectionInstituicao[0];

        Object selecInstituicao = JOptionPane.showInputDialog(null, "Selecione a instituição",
                "Instituições", JOptionPane.QUESTION_MESSAGE, null, selectionInstituicao, initialSelectionInstituicao);

        if (selecInstituicao == null) {
            JOptionPane.showMessageDialog(null, "Alteração de projeto cancelada.");
            chamaMenuPrincipal();
        }

        projeto.setNome(nome);
        projeto.setDescricao(descricao);
        projeto.setCoordenacao(coordenacao);
        projeto.setInstituicao(instituicaos.get(0));
        projeto.setTipo(tipoSelecionado);
        projeto.setCategoria(categoriaSelecionada);

        JOptionPane.showMessageDialog(null, "Cadastro de projeto alterado com sucesso!");
        chamaMenuPrincipal();
    }

    private static void chamaExcluirProjeto() throws SQLException, ClassNotFoundException {
        Object[] selectionValuesProjetos = getProjetoDAO().buscarTodos().toArray(new Projeto[0]);
        Object selectionProjetos = JOptionPane.showInputDialog(null, "Selecione o projeto que deseja remover:",
                "Remover Projeto", JOptionPane.DEFAULT_OPTION, null, selectionValuesProjetos, null);

        if (selectionProjetos != null) {
            Projeto projetoSelecionado = (Projeto) selectionProjetos;
            getProjetoDAO().remover(projetoSelecionado);

            JOptionPane.showMessageDialog(null, "Projeto removida com sucesso!");
            chamaMenuPrincipal();
        }
    }

    private static void chamaRelatorioProjeto() {
        List<Projeto> projetos = getProjetoDAO().buscarTodos();
        RelatorioProjetoForm.emitirRelatorio(projetos);
    }

    ////////////////////////////////

    public static void chamaMenuInstituicoes() throws SQLException, ClassNotFoundException {
        String[] opcoesMenuInstituicoes = {"Cadastrar", "Alterar", "Excluir", "Gerar relatório", "Voltar"};
        int menuInstituicoes = JOptionPane.showOptionDialog(null, "Escolha o processo que deseja realizar",
                "INSTITUIÇÕES",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuInstituicoes, opcoesMenuInstituicoes[0]);

        switch (menuInstituicoes) {
            case 0:
                chamaCadastroInstituicao();
                break;
            case 1:
//                chamaAlterarInstituicao();
                break;
            case 2:
//                chamaExcluirInstituicao();
                break;
            case 3:
                chamaRelatorioInstituicao();
                break;
        }
    }


    private static void chamaCadastroInstituicao() throws SQLException, ClassNotFoundException {

        String nome = JOptionPane.showInputDialog(null, "Informe o nome do instituicao");
        if (nome == null) {
            chamaCadastroInstituicao();
        }

        String rua = JOptionPane.showInputDialog(null, "Informe a rua da instituição");
        if (rua == null) {
            chamaCadastroInstituicao();
        }

        String bairro = JOptionPane.showInputDialog(null, "Informe o nome do bairro da instituição");
        if (bairro == null) {
            chamaCadastroInstituicao();
        }
        String numero = JOptionPane.showInputDialog(null, "Informe o número da instituição");
        if (numero == null) {
            chamaCadastroInstituicao();
        }
        String telefone = JOptionPane.showInputDialog(null, "Informe o telefone da instituição");
        if (telefone == null) {
            chamaCadastroInstituicao();
        }
        String cidade = JOptionPane.showInputDialog(null, "Informe a cidade da instituição");
        if (cidade == null) {
            chamaCadastroInstituicao();
        }

//        Object[] selectionInstituicao = instituicaos.stream().map(Instituicao::getNome).toArray();
//        String initialSelectionInstituicao = (String) selectionInstituicao[0];
//
//        Object selecInstituicao = JOptionPane.showInputDialog(null, "Selecione a instituição",
//                "Instituições", JOptionPane.QUESTION_MESSAGE, null, selectionInstituicao, initialSelectionInstituicao);
//
//        if (selecInstituicao == null) {
//            JOptionPane.showMessageDialog(null, "Cadastro de instituição cancelado.");
//            chamaMenuPrincipal();
//        }

        Instituicao instituicao = new Instituicao();
        instituicao.setNome(nome);
        instituicao.setRua(rua);
        instituicao.setBairro(bairro);
        instituicao.setNumero(numero);
        instituicao.setTelefone(telefone);
//        instituicao.setCidade(cidade);

        JOptionPane.showMessageDialog(null, "Projeto cadastrado com sucesso!");
        chamaMenuPrincipal();
    }


    private static void chamaExcluirInstituicao() throws SQLException, ClassNotFoundException {

        Object[] selectionValuesInstituicao = getInstituicaoDAO().buscarTodos().toArray(new Instituicao[0]);
        Object selectionInstituicao = JOptionPane.showInputDialog(null, "Selecione a Instituição que deseja remover:",
                "Remover Instituição", JOptionPane.DEFAULT_OPTION, null, selectionValuesInstituicao, null);

        if (selectionInstituicao != null) {
            Instituicao instituicaoSelecionada = (Instituicao) selectionInstituicao;
            getInstituicaoDAO().remover(instituicaoSelecionada);

            JOptionPane.showMessageDialog(null, "Instituição removida com sucesso!");
            chamaMenuPrincipal();
        }
    }

    public static void chamaRelatorioInstituicao() {
        List<Instituicao> instituicaos = getInstituicaoDAO().buscarTodos();
        RelatorioInstituicaoForm.emitirRelatorio(instituicaos);
    }


    public static ProjetoDAO getProjetoDAO() {
        ProjetoDAO projetoDAO = new ProjetoDAO();
        return projetoDAO;
    }

    public static InstituicaoDAO getInstituicaoDAO() {
        InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
        return instituicaoDAO;
    }

    public static CidadeDAO getCidadeDAO() {
        CidadeDAO cidadeDAO = new CidadeDAO();
        return cidadeDAO;
    }

}