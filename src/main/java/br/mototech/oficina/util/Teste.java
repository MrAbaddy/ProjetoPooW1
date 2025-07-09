package br.mototech.oficina.util;

import br.mototech.oficina.model.Usuario;
import br.mototech.oficina.service.LoginService;
import br.mototech.oficina.dao.UsuarioDAO;

public class Teste {
}
/*
    public static void main(String[] args) {

        FuncionarioService service = new FuncionarioService();

        //Criar novo funcionário
        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome("João da Silva");
        novoFuncionario.setCnh("123456789");
        novoFuncionario.setCargo("Mecânico");

        String resultadoInsercao = service.inserir(novoFuncionario);
        System.out.println("Resultado da inserção: " + resultadoInsercao);

        //Buscar funcionário
        int idFuncionario = 1;
        Funcionario funcionarioBuscado = service.buscar(idFuncionario);

        if (funcionarioBuscado != null) {
            System.out.println("Funcionário encontrado:");
            System.out.println("ID: " + funcionarioBuscado.getId());
            System.out.println("Nome: " + funcionarioBuscado.getNome());
            System.out.println("CNH: " + funcionarioBuscado.getCnh());
            System.out.println("Cargo: " + funcionarioBuscado.getCargo());
        } else {
            System.out.println("Funcionário não encontrado!");
        }
    }
}

     */

    //alterar Funcionário
    /*
    public static void main(String[] args) {


        Funcionario funcionario = new Funcionario();
        funcionario.setId(1); // Id
        funcionario.setNome("Carlos Souza");
        funcionario.setCnh("DEF1234567");
        funcionario.setCargo("Supervisor");

        FuncionarioDao dao = new FuncionarioDao();

        String resultado = dao.alterar(funcionario);

        System.out.println(resultado);
    }

     */

    //Excluir funcionário
    /*
    public static void main(String[] args) {
        FuncionarioService service = new FuncionarioService();

        int idFuncionario = 1; // id

        String resultado = service.excluir(idFuncionario);
        System.out.println("Resultado da exclusão: " + resultado);
    }
    */

    //inserir moto
    /*
    public static void main(String[] args) {

        MotocicletaDao dao = new MotocicletaDao();

        // Criando uma motocicleta de teste
        Motocicleta moto = new Motocicleta();
        moto.setNome("CB 500F");
        moto.setMarca("Honda");
        moto.setPlaca("ABC1D23");
        moto.setAnoModelo(2020);
        moto.setCilindradas(500);

        // Inserindo no banco
        String resultado = dao.inserir(moto);

        // Exibindo o resultado
        System.out.println(resultado);
    }
}

     */

    //alterar moto
    /*
    public static void main(String[] args) {

        // Criando o objeto motocicleta com valores definidos
        Motocicleta moto = new Motocicleta();
        moto.setId(1); // ID da moto que deseja alterar no banco
        moto.setNome("CB 650R");
        moto.setMarca("Honda");
        moto.setPlaca("XYZ-9876");
        moto.setAnoModelo(2023);
        moto.setCilindradas(650);

        // Instanciando o DAO
        MotocicletaDao dao = new MotocicletaDao();

        // Chamando o método alterar e capturando a resposta
        String resultado = dao.alterar(moto);

        // Imprimindo o resultado
        System.out.println(resultado);
    }

     */

    //Excluir moto
    /*
    public static void main(String[] args) {
        MotocicletaService service = new MotocicletaService();

        int idMotocicleta = 1; // id

        String resultado = service.excluir(idMotocicleta);
        System.out.println("Resultado da exclusão: " + resultado);
    }

     */

    //inserir user
    /*
    public static void main(String[] args) {

        UsuarioService service = new UsuarioService();

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail("teste@example.com");
        novoUsuario.setSenha("123456");

        String resultadoInsercao = service.inserir(novoUsuario);
        System.out.println("Resultado da inserção: " + resultadoInsercao);
    }
}

     */

    // Alterar Usuário
    /*public static void main(String[] args) {

        Usuario usuario = new Usuario();
        usuario.setId(2); // ID
        usuario.setEmail("teste@example.com");
        usuario.setSenha("novasenha123");

        UsuarioDao dao = new UsuarioDao();

        String resultado = dao.alterar(usuario);
        System.out.println(resultado);
    }

     */

    //exclui usuario
    /*
    public static void main(String[] args) {
        UsuarioDao dao = new UsuarioDao();

        int idParaExcluir = 2; // ID a excluir

        boolean sucesso = dao.excluir(idParaExcluir);
        if (sucesso) {
            System.out.println("Usuário excluído com sucesso!");
        } else {
            System.out.println("Falha ao excluir usuário ou usuário não encontrado.");
        }
    }

     */

    //inserir nota
    /*
    public static void main(String[] args) {
        NotaServicoDao dao = new NotaServicoDao();

        NotaServico nota = new NotaServico();
        nota.setDescricao("Revisão ");
        nota.setMaterial("Óleo, ");
        nota.setSituacao("Aberto");
        nota.setDataEntrada(LocalDate.now());
        nota.setDataSaida(null);

        Motocicleta moto = new Motocicleta();
        moto.setId(1); // id válido da motocicleta no banco
        nota.setMotocicleta(moto);

        Funcionario func = new Funcionario();
        func.setId(1); // id válido do funcionário no banco
        nota.setFuncionario(func);

        String resultado = dao.inserir(nota);
        System.out.println(resultado);
    }
}

     */

    //alterar nota
    /*
    public static void main(String[] args) {
        NotaServicoDao dao = new NotaServicoDao();

        // Criar objeto NotaServico para alterar
        NotaServico nota = new NotaServico();
        nota.setId(1); // ID da nota já existente no banco que será alterada
        nota.setDescricao("Revisão completa");
        nota.setMaterial("Óleo, filtro, vela");
        nota.setSituacao("Concluído");
        nota.setDataEntrada(LocalDate.of(2025, 5, 20));
        nota.setDataSaida(LocalDate.of(2025, 5, 25));

        Motocicleta moto = new Motocicleta();
        moto.setId(1); // id válido da motocicleta no banco
        nota.setMotocicleta(moto);

        Funcionario func = new Funcionario();
        func.setId(1); // id válido do funcionário no banco
        nota.setFuncionario(func);

        String resultado = dao.alterar(nota);
        System.out.println(resultado);
    }
}

     */

    //excluir nota
    /*
    public static void main(String[] args) {
        NotaServicoDao dao = new NotaServicoDao();

        int idParaExcluir = 1;

        String resultado = dao.excluir(idParaExcluir);

        System.out.println(resultado);
    }
}
     */







