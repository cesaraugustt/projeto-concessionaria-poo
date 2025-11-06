package concessionaria;

import concessionaria.exceptions.ClienteNaoEncontradoException;
import concessionaria.exceptions.VeiculoNaoEncontradoException;
import concessionaria.exceptions.VendedorNaoEncontradoException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Entrada {
    private Scanner input;

    public Entrada() {
        this.input = new Scanner(System.in);
        this.input.useLocale(Locale.US);
    }

    public void close() {
        if (input != null) {
            input.close();
        }
    }

    private String lerProximaLinha() {
        String linha = "";
        while (input.hasNextLine()) {
            linha = input.nextLine().trim();
            if (!linha.startsWith("#") && !linha.isEmpty()) {
                return linha;
            }
        }
        return linha;
    }

    private String lerLinha(String msg) {
        // System.out.print(msg);
        return lerProximaLinha();
    }

    private int lerInteiro(String msg) {
        while (true) {
            try {
                // System.out.print(msg);
                String linha = lerProximaLinha();
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            }
        }
    }

    private double lerDouble(String msg) {
        while (true) {
            try {
                // System.out.print(msg);
                String linha = lerProximaLinha();
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número decimal.");
            }
        }
    }

    public int menu(Sistema sistema) {
//        System.out.println("\nEscolha uma opção: ");
//        System.out.println("1) Cadastrar Cliente ");
//        System.out.println("2) Cadastrar Vendedor ");
//        System.out.println("3) Cadastrar Gerente ");
//        System.out.println("4) Cadastrar Veículo ");
//        System.out.println("5) Cadastrar Venda ");
//        System.out.println("6) Relatório de Vendas Mensal ");
//        System.out.println("7) Relatório de Vendas Anual ");
//        System.out.println("8) Relatório de Vendas do Vendedor ");
//        System.out.println("0) Sair ");
        return lerInteiro("Opção: ");
    }

    public void cadCliente(Sistema s) {
        // System.out.println("--- Cadastro de Cliente ---");
        String nome = lerLinha("Digite o nome do cliente: ");
        String cpf = lerLinha("Digite o cpf do cliente: ");
        int dia = lerInteiro("Digite o dia do nascimento do cliente: ");
        int mes = lerInteiro("Digite o mês do nascimento do cliente: ");
        int ano = lerInteiro("Digite o ano do nascimento do cliente: ");
        String email = lerLinha("Digite o email do cliente: ");

        Cliente novoCliente = new Cliente(nome, cpf, dia, mes, ano, email);
        s.adicionar(novoCliente);
        // System.out.println("Cliente cadastrado com sucesso!");
    }

    public void cadVendedor(Sistema s) {
        // System.out.println("--- Cadastro de Vendedor ---");
        String nome = lerLinha("Digite o nome do vendedor: ");
        String cpf = lerLinha("Digite o cpf do vendedor: ");
        int dia = lerInteiro("Digite o dia do nascimento do vendedor: ");
        int mes = lerInteiro("Digite o mês do nascimento do vendedor: ");
        int ano = lerInteiro("Digite o ano do nascimento do vendedor: ");
        double salario = lerDouble("Digite o salário mensal fixo do vendedor: ");
        double comissao = lerDouble("Digite o percentual de comissão deste vendedor: ");

        Vendedor novoVendedor = new Vendedor(nome, cpf, dia, mes, ano, salario, comissao);
        s.adicionar(novoVendedor);
        // System.out.println("Vendedor cadastrado com sucesso!");
    }

    public void cadGerente(Sistema s) {
        // System.out.println("--- Cadastro de Gerente ---");
        String nome = lerLinha("Digite o nome do gerente: ");
        String cpf = lerLinha("Digite o cpf do gerente: ");
        int dia = lerInteiro("Digite o dia do nascimento do gerente: ");
        int mes = lerInteiro("Digite o mês do nascimento do gerente: ");
        int ano = lerInteiro("Digite o ano do nascimento do gerente: ");
        double salario = lerDouble("Digite o salário mensal fixo do gerente: ");
        String senha = lerLinha("Digite a senha do gerente: ");

        Gerente novoGerente = new Gerente(nome, cpf, dia, mes, ano, salario, senha);
        s.adicionar(novoGerente);
        // System.out.println("Gerente cadastrado com sucesso!");
    }

    public void cadVeiculo(Sistema s) {
        // System.out.println("--- Cadastro de Veículo ---");
        String marca = lerLinha("Digite a Marca do veículo: ");
        String modelo = lerLinha("Digite o Modelo do veículo: ");
        int anoFab = lerInteiro("Digite o ano de fabricação do veículo: ");
        int mesFab = lerInteiro("Digite o mês de fabricação do veículo: ");
        int anoMod = lerInteiro("Digite o ano do modelo do veículo: ");
        double valor = lerDouble("Digite o valor do veículo: ");

//        System.out.println("Escolha o tipo do veículo:");
//        System.out.println("1) Elétrico");
//        System.out.println("2) Combustão");
//        System.out.println("3) Híbrido");
        int tipo = lerInteiro("Tipo: ");

        Veiculo novoVeiculo = null;

        switch (tipo) {
            case 1:
                double autonomiaBatE = lerDouble("Digite a autonomia da bateria (em km): ");
                double capacidadeBatE = lerDouble("Digite a capacidade da bateria (em kwH): ");
                novoVeiculo = new Eletrico(marca, modelo, anoFab, mesFab, anoMod, valor, autonomiaBatE, capacidadeBatE);
                break;
            case 2:
                double autonomiaCombC = lerDouble("Digite a autonomia do motor (em km): ");
                double capacidadeCombC = lerDouble("Digite a capacidade do motor (em L): ");
                novoVeiculo = new Combustao(marca, modelo, anoFab, mesFab, anoMod, valor, autonomiaCombC,
                        capacidadeCombC);
                break;
            case 3:
                double autonomiaCombH = lerDouble("Digite a autonomia do motor (em km): ");
                double capacidadeCombH = lerDouble("Digite a capacidade do motor (em L): ");
                double autonomiaBatH = lerDouble("Digite a autonomia da bateria (em km): ");
                double capacidadeBatH = lerDouble("Digite a capacidade da bateria (em kwH): ");
                novoVeiculo = new Hibrido(marca, modelo, anoFab, mesFab, anoMod, valor, autonomiaCombH,
                        capacidadeCombH, autonomiaBatH, capacidadeBatH);
                break;
            default:
                System.out.println("Tipo inválido! Cadastro cancelado.");
                return;
        }

        s.adicionar(novoVeiculo);
        // System.out.println("Veículo cadastrado com sucesso!");
    }

    public void cadVenda(Sistema s) {
        // System.out.println("--- Cadastro de Venda ---");
        try {
            // s.listarVendedores();
            String cpfVendedor = lerLinha("Digite o CPF do vendedor: ");
            Vendedor vendedor = s.localizarVendedor(cpfVendedor);

            // s.listarVeiculos();
            int numVeiculo = lerInteiro("Escolha um veículo pelo número: ");
            Veiculo veiculo = s.localizarVeiculo(numVeiculo);

            // s.listarClientes();
            String cpfCliente = lerLinha("Digite o CPF do cliente: ");
            Cliente cliente = s.localizarCliente(cpfCliente);

            double desconto = lerDouble("Digite o desconto (em R$): ");
            int dia = lerInteiro("Digite o dia da venda: ");
            int mes = lerInteiro("Digite o mês da venda: ");
            int ano = lerInteiro("Digite o ano da venda: ");
            Data dataVenda = new Data(dia, mes, ano);
            String chassi = lerLinha("Digite o chassi do veículo: ");

            Venda novaVenda = new Venda(veiculo, cliente, desconto, dataVenda, chassi);
            s.atribuirVendaVendedor(novaVenda, vendedor);

            // System.out.println("Venda cadastrada com sucesso!");
        } catch (VendedorNaoEncontradoException | VeiculoNaoEncontradoException | ClienteNaoEncontradoException e) {
            System.out.println("Erro ao cadastrar venda: " + e.getMessage());
        }
    }

    public void relatorioMensal(Sistema s) {
        // System.out.println("--- Relatório Mensal ---");
        int mes = lerInteiro("Digite o mês: ");
        int ano = lerInteiro("Digite o ano: ");
        s.relatorio(mes, ano);
    }

    public void relatorioAnual(Sistema s) {
        // System.out.println("--- Relatório Anual ---");
        int ano = lerInteiro("Digite o ano: ");
        s.relatorio(ano);
    }

    public void relatorioVendedor(Sistema s) {
        // System.out.println("--- Relatório por Vendedor ---");
        try {
            // s.listarVendedores();
            String cpf = lerLinha("Digite o CPF do vendedor: ");
            Vendedor v = s.localizarVendedor(cpf);
            s.relatorio(v);
        } catch (VendedorNaoEncontradoException e) {
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }
}
