import model.Cidade;
import model.Instituicao;
import model.Projeto;
import repository.*;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        exibirMensagemBoasVindas();
    }

    private static void exibirMensagemBoasVindas() {
        JOptionPane.showOptionDialog(null, "Seja bem-vindo ao Inova Brasil!",
                "Mensagem de boas-vindas", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Entrar"}, "Entrar");
    }

    public static void chamaMenuPrincipal() {
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

    public static void chamaMenuCidade() {
            String[] opcoesMenuCidade = {"Cadastrar", "Alterar", "Excluir", "Gerar relatório", "Voltar"};
            int menuCidade = JOptionPane.showOptionDialog(null, "Escolha o processo que deseja realizar",
                    "CIDADES",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCidade, opcoesMenuCidade[0]);

            switch (menuCidade) {
                case 0:
                    chamaCadastroCidade();
                    break;
                case 1:
                    chamaAlterarCidade();
                    break;
                case 2:
                    chamaExcluirCidade();
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

    private static Cidade chamaAlterarCidade() {
        Object[] selectionValuesCidades = CidadeDAO.findCidadesInArray();
        String initialSelectionCidade = (String) selectionValuesCidades[0];
        Object selectionCidade = JOptionPane.showInputDialog(null, "Selecione a cidade que deseja alterar",
                "Cidades", JOptionPane.QUESTION_MESSAGE, null, selectionValuesCidades, selectionValuesCidades);
        List<Cidade> cidades = CidadeDAO.buscarPorNome((String) selectionCidade);
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

        JOptionPane.showMessageDialog(null, "Cadastro de cidade alterado com sucesso!");
        chamaMenuPrincipal();
    }

    private static void chamaExcluirCidade() {
        Object[] selectionValuesCidades = CidadeDAO.buscarTodos().toArray(new Cidade[0]);
        Object selectionCidades = JOptionPane.showInputDialog(null, "Selecione a cidade que deseja remover:",
                "Remover Cidade", JOptionPane.DEFAULT_OPTION, null, selectionValuesCidades, null);

        if (selectionCidades != null) {
            Cidade cidadeSelecionada = (Cidade) selectionCidades;
            CidadeDAO.remover(cidadeSelecionada);

            JOptionPane.showMessageDialog(null, "Cidade removida com sucesso!");
            chamaMenuPrincipal();
        }
    }

    private static void chamaRelatorioCidade() {
        List<Cidade> cidades = CidadeDAO.buscarTodos();
        RelatorioCidadeForm.emitirRelatorio(cidades);
    }

    ////////////////////////////////

    public static void chamaMenuProjetos() {
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

    private static void chamaCadastroProjeto() {

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

        List<Instituicao> instituicaos = InstituicaoDAO.buscarTodos();

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

    private static void chamaAlterarProjeto() {
        Object[] selectionValuesProjetos = ProjetoDAO.findProjetosInArray();
        String initialSelectionProjeto = (String) selectionValuesProjetos[0];
        Object selectionProjeto = JOptionPane.showInputDialog(null, "Selecione o projeto que deseja alterar",
                "Projetos", JOptionPane.QUESTION_MESSAGE, null, selectionValuesProjetos, selectionValuesProjetos);

        List<Projeto> projetos = ProjetoDAO.buscarPorNome((String) selectionProjeto);
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

        List<Instituicao> instituicaos = InstituicaoDAO.buscarTodos();

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

    private static void chamaExcluirProjeto(){

        Object[] selectionValuesProjetos = ProjetoDAO.buscarTodos().toArray(new Projeto[0]);
        Object selectionProjetos = JOptionPane.showInputDialog(null, "Selecione o projeto que deseja remover:",
                "Remover Projeto", JOptionPane.DEFAULT_OPTION, null, selectionValuesProjetos, null);

        if (selectionProjetos != null) {
            Projeto projetoSelecionado = (Projeto) selectionProjetos;
            ProjetoDAO.remover(projetoSelecionado);

            JOptionPane.showMessageDialog(null, "Projeto removida com sucesso!");
            chamaMenuPrincipal();
        }
    }

    private static void chamaRelatorioProjeto() {
        List<Projeto> projetos = ProjetoDAO.buscarTodos();
        RelatorioProjetoForm.emitirRelatorio(projetos);
    }

    ////////////////////////////////

    public static void chamaMenuInstituicoes() {
        String[] opcoesMenuInstituicoes = {"Cadastrar", "Alterar", "Excluir", "Gerar relatório", "Voltar"};
        int menuInstituicoes = JOptionPane.showOptionDialog(null, "Escolha o processo que deseja realizar",
                "INSTITUIÇÕES",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuInstituicoes, opcoesMenuInstituicoes[0]);

        switch (menuInstituicoes) {
            case 0:
                chamaCadastroInstituicao();
                break;
            case 1:
                chamaAlterarInstituicao();
                break;
            case 2:
                chamaExcluirInstituicao();
                break;
            case 3:
                chamaRelatorioInstituicao();
                break;
        }
    }



}