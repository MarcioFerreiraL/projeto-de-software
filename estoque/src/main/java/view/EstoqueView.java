package view;

import java.util.List;
import java.util.Scanner;
import model.Produto;

public class EstoqueView {

    private Scanner scanner;

    public EstoqueView() {
        this.scanner = new Scanner(System.in);
    }

    // --- Tela de Login ---
    public String[] exibirTelaLogin() {
        String[] credenciais = new String[2];
        System.out.println("====================================");
        System.out.println("   SISTEMA DE CONTROLE DE ESTOQUE   ");
        System.out.println("====================================");
        System.out.println("Por favor, faça login.");
        System.out.print("Usuario: ");
        credenciais[0] = scanner.nextLine();
        System.out.print("Senha: ");
        credenciais[1] = scanner.nextLine();
        System.out.println("====================================");
        return credenciais;
    }

    // --- Menus ---
    public int exibirMenuAdministrador(String nomeAdministrador) {
        System.out.println("\n====================================");
        System.out.println("      PAINEL DO ADMINISTRADOR      ");
        System.out.println("====================================");
        System.out.println("Bem-vindo(a)!, " + nomeAdministrador);
        System.out.println("\nO que deseja fazer?");
        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Atualizar Produto");
        System.out.println("3 - Gerar Relatório do Estoque");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }

    public int exibirMenuFuncionario(String nomeFuncionario) {
        System.out.println("\n====================================");
        System.out.println("       PAINEL DO FUNCIONÁRIO       ");
        System.out.println("====================================");
        System.out.println("Bem-vindo(a)!, " + nomeFuncionario);
        System.out.println("\nO que deseja fazer?");
        System.out.println("1 - Consultar Produto");
        System.out.println("2 - Registrar Entrada no Estoque");
        System.out.println("3 - Registrar Saída no Estoque");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }

    // --- Telas de Operação ---
    public Produto exibirTelaCadastroProduto() {
        System.out.println("\n====================================");
        System.out.println("        CADASTRAR PRODUTO        ");
        System.out.println("====================================");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        System.out.print("Preço: ");
        float preco = scanner.nextFloat();
        scanner.nextLine(); // Consumir nova linha

        return new Produto(0, nome, categoria, quantidade, preco); //refatorar o id
    }

    public int exibirTelaDeSelecaoDeProduto(List<Produto> produtos) {
        System.out.println("\n====================================");
        System.out.println("        ATUALIZAR PRODUTO        ");
        System.out.println("====================================");
        System.out.println("Qual Produto você deseja atualizar?");
        System.out.printf("%-5s %-20s %-15s %-5s %-10s\n", "ID", "NOME", "CATEGORIA", "QTD", "PREÇO");
        for (Produto produto : produtos) {
            System.out.printf("%-5d %-20s %-15s %-5d %-10.2f\n",
                produto.getId(), produto.getNome(), produto.getCategoria(),
                produto.getQuantidade(), produto.getPreco());
        }
        System.out.print("\nDigite o ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }
    
    public Produto exibirTelaAtualizarProduto(Produto produto) {
        System.out.println("\nATUALIZAR PRODUTO: " + produto.getNome());
        System.out.print("Novo Nome (" + produto.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.trim().isEmpty()) {
            produto.setNome(nome);
        }

        System.out.print("Nova Categoria (" + produto.getCategoria() + "): ");
        String categoria = scanner.nextLine();
        if (!categoria.trim().isEmpty()) {
            produto.setCategoria(categoria);
        }

        System.out.print("Nova Quantidade (" + produto.getQuantidade() + "): ");
        String quantidadeStr = scanner.nextLine();
        if (!quantidadeStr.trim().isEmpty()) {
            produto.setQuantidade(Integer.parseInt(quantidadeStr));
        }

        System.out.print("Novo Preço (" + produto.getPreco() + "): ");
        String precoStr = scanner.nextLine();
        if (!precoStr.trim().isEmpty()) {
            produto.setPreco(Float.parseFloat(precoStr));
        }
        
        return produto;
    }

    public void exibirRelatorio(List<Produto> produtos) {
        System.out.println("\n====================================");
        System.out.println("        RELATÓRIO DO ESTOQUE        ");
        System.out.println("====================================");
        System.out.printf("%-5s %-20s %-15s %-5s %-10s\n", "ID", "NOME", "CATEGORIA", "QTD", "PREÇO");
        for (Produto produto : produtos) {
            System.out.printf("%-5d %-20s %-15s %-5d %-10.2f\n",
                produto.getId(), produto.getNome(), produto.getCategoria(),
                produto.getQuantidade(), produto.getPreco());
        }
        System.out.println("====================================");
    }

    public String exibirTelaConsultarProduto() {
        System.out.println("\n====================================");
        System.out.println("        CONSULTAR PRODUTO        ");
        System.out.println("====================================");
        System.out.println("DIGITE O NOME DO PRODUTO QUE DESEJA CONSULTAR:");
        System.out.print("Nome: ");
        return scanner.nextLine();
    }

    public void exibirProdutoConsultado(Produto produto) {
        if (produto != null) {
            System.out.println("\n--- RESULTADO DA CONSULTA ---");
            System.out.printf("%-5s %-20s %-15s %-5s %-10s\n", "ID", "NOME", "CATEGORIA", "QTD", "PREÇO");
            System.out.printf("%-5d %-20s %-15s %-5d %-10.2f\n",
                produto.getId(), produto.getNome(), produto.getCategoria(),
                produto.getQuantidade(), produto.getPreco());
        } else {
            System.out.println("\nProduto não encontrado.");
        }
        System.out.println("-----------------------------");
    }

    public int[] exibirTelaRegistrarEntrada() {
        int[] dados = new int[2];
        System.out.println("\n====================================");
        System.out.println("    REGISTRAR ENTRADA NO ESTOQUE    ");
        System.out.println("====================================");
        System.out.print("ID do Produto: ");
        dados[0] = scanner.nextInt();
        System.out.print("Quantidade: ");
        dados[1] = scanner.nextInt();
        scanner.nextLine(); 
        return dados;
    }

    public int[] exibirTelaRegistrarSaida() {
        int[] dados = new int[2];
        System.out.println("\n====================================");
        System.out.println("     REGISTRAR SAÍDA NO ESTOQUE     ");
        System.out.println("====================================");
        System.out.print("ID do Produto: ");
        dados[0] = scanner.nextInt();
        System.out.print("Quantidade: ");
        dados[1] = scanner.nextInt();
        scanner.nextLine();
        return dados;
    }
}