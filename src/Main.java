import model.Cidade;
import model.Instituicao;
import model.Projeto;
import model.RedeSocial;
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
        String[] opcoesMenu = {"Cidades", "Projetos", "Instituições","Redes Sociais","Sair"};
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
            case 3: //redesSociais
                chamaMenuRedesSociais();
                break;

                case 4: //SAIR
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
                JOptionPane.showMessageDialog(null, "Cidade " + cidadeCriada.getNome() + " cadastrada com sucesso!");
                chamaMenuPrincipal();
                break;
            case 1:
                Cidade cidadeAlterada = chamaAlterarCidade();
                JOptionPane.showMessageDialog(null, "Cidade " + cidadeAlterada.getNome() + " alterada com sucesso!");
                chamaMenuPrincipal();
                break;
            case 2:
                Cidade cidadeExcluida = chamaExcluirCidade();
                JOptionPane.showMessageDialog(null, "Cidade " + cidadeExcluida.getNome() + " excluída com sucesso!");
                chamaMenuPrincipal();
                break;
            case 3:
                chamaRelatorioCidade();
                chamaMenuPrincipal();
                break;
            case 4:
                chamaMenuPrincipal();
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

        getCidadeDAO().salvar(cidade);
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
        getCidadeDAO().salvar(cidade);
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

    static void chamaRelatorioCidade() {
        List<Cidade> cidades = getCidadeDAO().buscarTodos();
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
                Projeto projetoCriado = chamaCadastroProjeto();
                JOptionPane.showMessageDialog(null, "Projeto " + projetoCriado.getNome() + " cadastrado com sucesso!");
                chamaMenuPrincipal();
                break;
            case 1:
                Projeto projetoAlterado = chamaAlterarProjeto();
                JOptionPane.showMessageDialog(null, "Projeto " + projetoAlterado.getNome() + " alterado com sucesso!");
                chamaMenuPrincipal();
                break;
            case 2:
                Projeto projetoExcluido = chamaExcluirProjeto();
                JOptionPane.showMessageDialog(null, "Projeto " + projetoExcluido.getNome() + " excluído com sucesso!");
                chamaMenuPrincipal();
                break;
            case 3:
                chamaRelatorioProjeto();
                chamaMenuPrincipal();
                break;
            case 4:
                chamaMenuPrincipal();
                break;
        }
    }

    private static Projeto chamaCadastroProjeto() throws SQLException, ClassNotFoundException {

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
        getProjetoDAO().salvar(projeto);
        return projeto;
    }

    private static Projeto chamaAlterarProjeto() throws SQLException, ClassNotFoundException {
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
        getProjetoDAO().salvar(projeto);
        return projeto;
    }

    private static Projeto chamaExcluirProjeto() throws SQLException, ClassNotFoundException {
        Object[] selectionValuesProjetos = getProjetoDAO().buscarTodos().toArray(new Projeto[0]);
        Object selectionProjetos = JOptionPane.showInputDialog(null, "Selecione o projeto que deseja remover:",
                "Remover Projeto", JOptionPane.DEFAULT_OPTION, null, selectionValuesProjetos, null);

        if (selectionProjetos != null) {
            Projeto projetoSelecionado = (Projeto) selectionProjetos;
            getProjetoDAO().remover(projetoSelecionado);
        }
        return null;
    }

    static void chamaRelatorioProjeto() {
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
                Instituicao instituicaoCriada = chamaCadastroInstituicao();
                JOptionPane.showMessageDialog(null, "Instituição " + instituicaoCriada.getNome() + " cadastrada com sucesso!");
                chamaMenuPrincipal();
                break;
            case 1:
                Instituicao instituicaoAlterada = chamaAlterarInstituicao();
                JOptionPane.showMessageDialog(null, "Instituição " + instituicaoAlterada.getNome() + " alterada com sucesso!");
                chamaMenuPrincipal();
                break;
            case 2:
                Instituicao instituicaoExcluida = chamaExcluirInstituicao();
                JOptionPane.showMessageDialog(null, "Instituição " + instituicaoExcluida.getNome() + " excluída com sucesso!");
                chamaMenuPrincipal();
                break;
            case 3:
                chamaRelatorioInstituicao();
                chamaMenuPrincipal();
                break;
            case 4:
                chamaMenuPrincipal();
                break;
        }
    }


    private static Instituicao chamaCadastroInstituicao() throws SQLException, ClassNotFoundException {

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


         List<Cidade> cidades = getCidadeDAO().buscarTodos();

        if (cidades.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma Cidade cadastrada!");

        }
       Object[] selectionCidade = cidades.stream().map(Cidade::getNome).toArray();
       String initialSelectionCidade = (String) selectionCidade[0];
       Object selecCidade = JOptionPane.showInputDialog(null, "Selecione a Cidade",
                "Cidades", JOptionPane.QUESTION_MESSAGE, null, selectionCidade, initialSelectionCidade);

        if (selecCidade == null) {
           JOptionPane.showMessageDialog(null, "Cadastro de Instituição cancelado.");
          chamaMenuPrincipal();
       }

        List<RedeSocial> redeSociais = getRedeSocialDAO().buscarTodos();

        if (redeSociais.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma Rede Social cadastrada!");

        }
        Object[] selectionRedeSocial = redeSociais.stream().map(RedeSocial::getDescricao).toArray();
        String initialSelectionRedeSocial = (String) selectionRedeSocial[0];
        Object selecRede = JOptionPane.showInputDialog(null, "Selecione a Rede Social",
                "Redes Sociais", JOptionPane.QUESTION_MESSAGE, null, selectionRedeSocial, initialSelectionRedeSocial);

        if (selecRede == null) {
            JOptionPane.showMessageDialog(null, "Cadastro de Instituição cancelado.");
            chamaMenuPrincipal();
        }


        Instituicao instituicao = new Instituicao();
        instituicao.setNome(nome);
        instituicao.setRua(rua);
        instituicao.setBairro(bairro);
        instituicao.setNumero(numero);
        instituicao.setTelefone(telefone);

        instituicao.setCidade(cidades.get(0));
        instituicao.setRedeSocial(redeSociais.get(0));
        getInstituicaoDAO().salvar(instituicao);

        return instituicao;
    }

    private static Instituicao chamaAlterarInstituicao() throws SQLException, ClassNotFoundException {
        Object[] selectionValuesInstituicao = getInstituicaoDAO().findInstituicaoInArray();
        String initialSelectionInstituicao = (String) selectionValuesInstituicao[0];
        Object selectionInstituicao = JOptionPane.showInputDialog(null, "Selecione a Instituição que deseja alterar",
                "Instituições", JOptionPane.QUESTION_MESSAGE, null, selectionValuesInstituicao, selectionValuesInstituicao);
        List<Instituicao> instituicaos = getInstituicaoDAO().buscarPorNome((String) selectionInstituicao);
        Instituicao instituicao = instituicaos.get(0);

        String nome = JOptionPane.showInputDialog(null, "Informe o nome da Instituicao", instituicaos.stream().map(Instituicao::getNome).toArray());
        if (nome == null) {
            chamaCadastroInstituicao();
        }

        String rua = JOptionPane.showInputDialog(null, "Informe a rua da instituição", instituicao.getRua());
        if (rua == null) {
        chamaCadastroInstituicao();
        }

        String bairro = JOptionPane.showInputDialog(null, "Informe o nome do bairro da instituição", instituicao.getBairro());
        if (bairro == null) {
            chamaCadastroInstituicao();
        }
        String numero = JOptionPane.showInputDialog(null, "Informe o número da instituição",instituicao.getNumero());
        if (numero == null) {
            chamaCadastroInstituicao();
        }
        String telefone = JOptionPane.showInputDialog(null, "Informe o telefone da instituição",instituicao.getTelefone());
        if (telefone == null) {
            chamaCadastroInstituicao();
        }

        List<Cidade> cidades = getCidadeDAO().buscarTodos();

        if (cidades.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma Cidade cadastrada!");

        }
        Object[] selectionCidade = cidades.stream().map(Cidade::getNome).toArray();
        String initialSelectionCidade = (String) selectionCidade[0];
        Object selecCidade = JOptionPane.showInputDialog(null, "Selecione a Cidade",
                "Cidades", JOptionPane.QUESTION_MESSAGE, null, selectionCidade, initialSelectionCidade);

        if (selecCidade == null) {
            JOptionPane.showMessageDialog(null, "Alteração de Instituição cancelada.");
            chamaMenuPrincipal();
        }
        List<RedeSocial> redeSociais = getRedeSocialDAO().buscarTodos();

        if (redeSociais.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma Rede Social cadastrada!");

        }
        Object[] selectionRedeSocial = redeSociais.stream().map(RedeSocial::getDescricao).toArray();
        String initialSelectionRedeSocial = (String) selectionRedeSocial[0];
        Object selecRede = JOptionPane.showInputDialog(null, "Selecione a Rede Social",
                "Redes Sociais", JOptionPane.QUESTION_MESSAGE, null, selectionRedeSocial, initialSelectionRedeSocial);

        if (selecRede == null) {
            JOptionPane.showMessageDialog(null, "Alteração de Instituição cancelada.");
            chamaMenuPrincipal();
        }



        instituicao.setNome(nome);
        instituicao.setRua(rua);
        instituicao.setBairro(bairro);
        instituicao.setNumero(numero);
        instituicao.setTelefone(telefone);

        instituicao.setCidade(cidades.get(0));
        instituicao.setRedeSocial(redeSociais.get(0));

        JOptionPane.showMessageDialog(null, "instituição alterada com sucesso!");
        chamaMenuPrincipal();
        getInstituicaoDAO().salvar(instituicao);

        return instituicao;

    }

    private static Instituicao chamaExcluirInstituicao() throws SQLException, ClassNotFoundException {

        Object[] selectionValuesInstituicao = getInstituicaoDAO().buscarTodos().toArray(new Instituicao[0]);
        Object selectionInstituicao = JOptionPane.showInputDialog(null, "Selecione a Instituição que deseja remover:",
                "Remover Instituição", JOptionPane.DEFAULT_OPTION, null, selectionValuesInstituicao, null);

        if (selectionInstituicao != null) {
            Instituicao instituicaoSelecionada = (Instituicao) selectionInstituicao;
            getInstituicaoDAO().remover(instituicaoSelecionada);
        }
        return null;
    }

    public static void chamaRelatorioInstituicao() {
        List<Instituicao> instituicaos = getInstituicaoDAO().buscarTodos();
        RelatorioInstituicaoForm.emitirRelatorio(instituicaos);
    }

    public static void chamaMenuRedesSociais() throws SQLException, ClassNotFoundException {
        String[] opcoesMenuRedesSociais = {"Cadastrar", "Alterar", "Excluir", "Gerar relatório", "Voltar"};
        int menuRede = JOptionPane.showOptionDialog(null, "Escolha o processo que deseja realizar",
                "Rede Social",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuRedesSociais, opcoesMenuRedesSociais[0]);

        switch (menuRede) {
            case 0:
                RedeSocial redeSocialCriada = chamaCadastroRedeSocial();
                JOptionPane.showMessageDialog(null, "Rede Social " + redeSocialCriada.getDescricao() + " cadastrada com sucesso!");
                chamaMenuPrincipal();
                break;
            case 1:
                RedeSocial redeSocialAlterada = chamaAlterarRedeSocial();
                JOptionPane.showMessageDialog(null, "Rede Social " + redeSocialAlterada.getDescricao() + " alterada com sucesso!");
                chamaMenuPrincipal();
                break;
            case 2:
                RedeSocial redeSocialExcluida = chamaExcluirRedeSocial();
                JOptionPane.showMessageDialog(null, "Rede Social " + redeSocialExcluida.getDescricao() + " excluída com sucesso!");
                chamaMenuPrincipal();
                break;
            case 3:
                chamaRelatorioRedeSocial();
                chamaMenuPrincipal();
                break;
            case 4:
                chamaMenuPrincipal();
                break;
        }
    }
    private static RedeSocial chamaCadastroRedeSocial() throws SQLException, ClassNotFoundException {

        String nome = JOptionPane.showInputDialog(null, "Informe o nome da RedeSocial");
        if (nome == null) {
            chamaCadastroRedeSocial();
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
            JOptionPane.showMessageDialog(null, "Cadastro de rede social cancelado.");
            chamaMenuPrincipal();
        }

        RedeSocial redeSocial = new RedeSocial();
        redeSocial.setDescricao(nome);
        redeSocial.setCodigoInstituicao(instituicaos.get(0).getCodigo());

        getRedeSocialDAO().salvar(redeSocial);
        return redeSocial;

    }
    private static RedeSocial chamaAlterarRedeSocial() throws SQLException, ClassNotFoundException {
        Object[] selectionValuesRedeSocial = getRedeSocialDAO().findRedesSociaisInArray();
        String initialSelectionRedeSocial = (String) selectionValuesRedeSocial[0];
        Object selectionRede = JOptionPane.showInputDialog(null, "Selecione a Rede Social que deseja alterar",
                "Redes Sociais", JOptionPane.QUESTION_MESSAGE, null, selectionValuesRedeSocial, selectionValuesRedeSocial);
        List<RedeSocial> redesSociais = getRedeSocialDAO().buscarPorNome((String) selectionRede);
        RedeSocial redeSocial = redesSociais.get(0);

        String nome = JOptionPane.showInputDialog(null, "Informe o nome da Rede Social", redeSocial.getDescricao());
        if (nome == null) {
            chamaCadastroCidade();
        }

        redeSocial.setDescricao(nome);

        getRedeSocialDAO().salvar(redeSocial);
        return redeSocial;

    }
    private static RedeSocial chamaExcluirRedeSocial() throws SQLException, ClassNotFoundException {
        Object[] selectionValuesRedesSociais = getRedeSocialDAO().findRedesSociaisInArray();
        Object selectionRedesSociais = JOptionPane.showInputDialog(null, "Selecione a Rede Social que deseja remover:",
                "Remover Rede Social", JOptionPane.DEFAULT_OPTION, null, selectionValuesRedesSociais, null);

        if (selectionRedesSociais != null) {
            RedeSocial redeSocialSelecionada = (RedeSocial) selectionRedesSociais;
            getRedeSocialDAO().remover(redeSocialSelecionada);

            JOptionPane.showMessageDialog(null, "Rede Social removida com sucesso!");
            chamaMenuPrincipal();
        }
        return null;
    }

    static void chamaRelatorioRedeSocial() {
        List<RedeSocial> redeSocials = getRedeSocialDAO().buscarTodos();
        RelatorioRedeSocialForm.emitirRelatorio(redeSocials);
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
        public static RedeSocialDAO getRedeSocialDAO() {
            RedeSocialDAO redeSocialDAO = new RedeSocialDAO();
            return redeSocialDAO;
        }
    }