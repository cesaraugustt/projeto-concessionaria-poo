package concessionaria;

import concessionaria.exceptions.ClienteNaoEncontradoException;
import concessionaria.exceptions.GerenteNaoEncontradoException;
import concessionaria.exceptions.VeiculoNaoEncontradoException;
import concessionaria.exceptions.VendedorNaoEncontradoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sistema {
    private List<Veiculo> veiculos;
    private List<Vendedor> vendedores;
    private List<Gerente> gerentes;
    private List<Cliente> clientes;

    public Sistema() {
        this.veiculos = new ArrayList<>();
        this.vendedores = new ArrayList<>();
        this.gerentes = new ArrayList<>();
        this.clientes = new ArrayList<>();
        carregarDados();
    }

    private void salvarVeiculos() {
        try (PrintWriter out = new PrintWriter(new FileWriter("veiculos.txt"))) {
            for (Veiculo v : veiculos) {
                String commonPart = v.getMarca() + ";" + v.getModelo() + ";" + v.getAnoFab() + ";" + v.getMesFab() + ";"
                        + v.getAnoMod() + ";" + v.getValor();
                switch (v) {
                    case Combustao c ->
                            out.println("COMB;" + commonPart + ";" + c.getAutonomia() + ";" + c.getCapacidadeComb());
                    case Eletrico e ->
                            out.println("ELET;" + commonPart + ";" + e.getAutonomia() + ";" + e.getCapacidadeBat());
                    case Hibrido h ->
                            out.println("HIBR;" + commonPart + ";" + h.getAutonomiaComb() + ";" + h.getCapacidadeComb() + ";"
                                    + h.getAutonomiaBat() + ";" + h.getCapacidadeBat());
                    default -> {
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar veículos: " + e.getMessage());
        }
    }

    private void salvarClientes() {
        try (PrintWriter out = new PrintWriter(new FileWriter("clientes.txt"))) {
            for (Cliente c : clientes) {
                out.println(c.getNome() + ";" + c.getCpf() + ";" + c.getNasc().getDia() + ";" + c.getNasc().getMes()
                        + ";" + c.getNasc().getAno() + ";" + c.getEmail());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    private void salvarVendedores() {
        try (PrintWriter out = new PrintWriter(new FileWriter("vendedores.txt"))) {
            for (Vendedor v : vendedores) {
                out.println(v.getNome() + ";" + v.getCpf() + ";" + v.getNasc().getDia() + ";" + v.getNasc().getMes()
                        + ";" + v.getNasc().getAno() + ";" + v.getSalarioBase() + ";" + v.getComissao());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar vendedores: " + e.getMessage());
        }
    }

    private void salvarGerentes() {
        try (PrintWriter out = new PrintWriter(new FileWriter("gerentes.txt"))) {
            for (Gerente g : gerentes) {
                out.println(g.getNome() + ";" + g.getCpf() + ";" + g.getNasc().getDia() + ";" + g.getNasc().getMes()
                        + ";" + g.getNasc().getAno() + ";" + g.getSalario(0, 0) + ";" + g.getSenha());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar gerentes: " + e.getMessage());
        }
    }

    private void salvarVendas() {
        try (PrintWriter out = new PrintWriter(new FileWriter("vendas.txt"))) {
            for (Vendedor v : vendedores) {
                for (Venda venda : v.getVendidos()) {
                    out.println(v.getCpf() + ";" + venda.getCliente().getCpf() + ";"
                            + veiculos.indexOf(venda.getVeiculo()) + ";" + venda.getDesconto() + ";"
                            + venda.getData().getDia() + ";" + venda.getData().getMes() + ";"
                            + venda.getData().getAno() + ";" + venda.getChassi());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar vendas: " + e.getMessage());
        }
    }

    private void salvarDados() {
        salvarVeiculos();
        salvarClientes();
        salvarVendedores();
        salvarGerentes();
        salvarVendas();
    }

    private void carregarVeiculos() {
        try (Scanner scanner = new Scanner(new File("veiculos.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                String marca = data[1];
                String modelo = data[2];
                int anoFab = Integer.parseInt(data[3]);
                int mesFab = Integer.parseInt(data[4]);
                int anoMod = Integer.parseInt(data[5]);
                double valor = Double.parseDouble(data[6]);

                switch (data[0]) {
                    case "COMB":
                        double autonomiaComb = Double.parseDouble(data[7]);
                        double capacidadeComb = Double.parseDouble(data[8]);
                        this.veiculos.add(new Combustao(marca, modelo, anoFab, mesFab, anoMod, valor, autonomiaComb, capacidadeComb));
                        break;
                    case "ELET":
                        double autonomiaBat = Double.parseDouble(data[7]);
                        double capacidadeBat = Double.parseDouble(data[8]);
                        this.veiculos.add(new Eletrico(marca, modelo, anoFab, mesFab, anoMod, valor, autonomiaBat, capacidadeBat));
                        break;
                    case "HIBR":
                        autonomiaComb = Double.parseDouble(data[7]);
                        capacidadeComb = Double.parseDouble(data[8]);
                        autonomiaBat = Double.parseDouble(data[9]);
                        capacidadeBat = Double.parseDouble(data[10]);
                        this.veiculos.add(new Hibrido(marca, modelo, anoFab, mesFab, anoMod, valor, autonomiaComb, capacidadeComb, autonomiaBat, capacidadeBat));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de veículos não encontrado. Começando com lista vazia.");
        }
    }

    private void carregarClientes() {
        try (Scanner scanner = new Scanner(new File("clientes.txt"))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(";");
                this.clientes.add(new Cliente(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]), data[5]));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de clientes não encontrado. Começando com lista vazia.");
        }
    }

    private void carregarVendedores() {
        try (Scanner scanner = new Scanner(new File("vendedores.txt"))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(";");
                this.vendedores.add(new Vendedor(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6])));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de vendedores não encontrado. Começando com lista vazia.");
        }
    }

    private void carregarGerentes() {
        try (Scanner scanner = new Scanner(new File("gerentes.txt"))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(";");
                this.gerentes.add(new Gerente(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]), Double.parseDouble(data[5]), data[6]));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de gerentes não encontrado. Começando com lista vazia.");
        }
    }

    private void carregarVendas() {
        try (Scanner scanner = new Scanner(new File("vendas.txt"))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(";");
                Vendedor vendedor = localizarVendedor(data[0]);
                Cliente cliente = localizarCliente(data[1]);
                Veiculo veiculo = veiculos.get(Integer.parseInt(data[2]));
                double desconto = Double.parseDouble(data[3]);
                Data vendaData = new Data(Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
                String chassi = data[7];
                Venda venda = new Venda(veiculo, cliente, desconto, vendaData, chassi);
                vendedor.addVenda(venda);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de vendas não encontrado. Começando sem histórico de vendas.");
        } catch (ClienteNaoEncontradoException | VendedorNaoEncontradoException e) {
            System.err.println("Erro ao carregar vendas: " + e.getMessage());
        }
    }

    private void carregarDados() {
        carregarVeiculos();
        carregarClientes();
        carregarVendedores();
        carregarGerentes();
        carregarVendas();
    }

    public void adicionar(Veiculo veiculo) {
        this.veiculos.add(veiculo);
        salvarDados();
    }

    public void adicionar(Vendedor vendedor) {
        this.vendedores.add(vendedor);
        salvarDados();
    }

    public void adicionar(Gerente gerente) {
        this.gerentes.add(gerente);
        salvarDados();
    }

    public void adicionar(Cliente cliente) {
        this.clientes.add(cliente);
        salvarDados();
    }

    public void listarClientes() {
        System.out.println("Clientes cadastrados:");
        Collections.sort(this.clientes);
        for (Cliente c : this.clientes) {
            System.out.println(c.toString());
        }
    }

    public void listarVendedores() {
        System.out.println("Vendedores cadastrados:");
        Collections.sort(this.vendedores);
        for (Vendedor v : this.vendedores) {
            System.out.println(v.toString());
        }
    }

    public void listarGerentes() {
        System.out.println("Gerentes cadastrados:");
        Collections.sort(this.gerentes);
        for (Gerente g : this.gerentes) {
            System.out.println(g.toString());
        }
    }

    public void listarVeiculos() {
        System.out.println("Veiculos cadastrados:");
        this.veiculos.sort(Comparator.comparingDouble(Veiculo::getValor));
        int i = 1;
        for (Veiculo v : this.veiculos) {
            System.out.println(i + ") " + v.toString());
            i++;
        }
    }

    public Cliente localizarCliente(String cpf) throws ClienteNaoEncontradoException {
        for (Cliente c : this.clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        throw new ClienteNaoEncontradoException("Cliente com CPF " + cpf + " não encontrado.");
    }

    public Vendedor localizarVendedor(String cpf) throws VendedorNaoEncontradoException {
        for (Vendedor v : this.vendedores) {
            if (v.getCpf().equals(cpf)) {
                return v;
            }
        }
        throw new VendedorNaoEncontradoException("Vendedor com CPF " + cpf + " não encontrado.");
    }

    public Gerente localizarGerente(String cpf) throws GerenteNaoEncontradoException {
        for (Gerente g : this.gerentes) {
            if (g.getCpf().equals(cpf)) {
                return g;
            }
        }
        throw new GerenteNaoEncontradoException("Gerente com CPF " + cpf + " não encontrado.");
    }

    public Veiculo localizarVeiculo(int indice) throws VeiculoNaoEncontradoException {
        if (indice > 0 && indice <= this.veiculos.size()) {
            return this.veiculos.get(indice - 1);
        }
        throw new VeiculoNaoEncontradoException("Veículo com índice " + indice + " não encontrado.");
    }


    public void atribuirVendaVendedor(Venda venda, Vendedor vendedor) {
        vendedor.addVenda(venda);
        salvarDados();
    }

    public void relatorio(int mes, int ano) {
        System.out.println();
        System.out.println("RELATÓRIO DE VENDAS MENSAL DE " + mes + "/" + ano + ":");
        double totalMes = 0;

        Collections.sort(this.vendedores);

        for (Vendedor v : this.vendedores) {
            double salarioMes = v.getSalario(mes, ano);
            List<Venda> vendasVendedor = v.getVendidos().stream()
                .filter(venda -> venda.getData().getMes() == mes && venda.getData().getAno() == ano)
                .sorted(Comparator.comparingDouble(Venda::valor).reversed())
                .toList();

            if (!vendasVendedor.isEmpty()) {
                System.out.println("Vendedor: " + v.getNome() + " (Salário neste mês: R$" + salarioMes + ")");
                for (Venda venda : vendasVendedor) {
                    System.out.println("Veiculo: " + venda.getVeiculo().toString());
                    System.out.println("Cliente: " + venda.getCliente().toString());
                    System.out.println("Valor da venda: R$" + venda.valor());
                    System.out.println("Data: " + venda.getData().toString());
                    System.out.println("***************************************");

                    totalMes += venda.valor();
                }
            }
        }
        System.out.printf("Total: R$" + totalMes);
        System.out.println();
    }

    public void relatorio (int ano) {
        System.out.println();
        System.out.println("RELATÓRIO DE VENDAS ANUAL DE " + ano + ":");
        double totalAno = 0;

        Collections.sort(this.vendedores);

        for (Vendedor v : this.vendedores) {
            List<Venda> vendasVendedor = v.getVendidos().stream()
                .filter(venda -> venda.getData().getAno() == ano)
                .sorted(Comparator.comparingDouble(Venda::valor).reversed())
                .toList();

            if (!vendasVendedor.isEmpty()) {
                System.out.println("Vendedor: " + v.getNome());
                for (Venda venda : vendasVendedor) {
                    System.out.println("Veiculo: " + venda.getVeiculo().toString());
                    System.out.println("Cliente: " + venda.getCliente().toString());
                    System.out.println("Valor da venda: R$" + venda.valor());
                    System.out.println("Data: " + venda.getData().toString());
                    System.out.println("***************************************");

                    totalAno += venda.valor();
                }
            }
        }
        System.out.println("Total: R$" + totalAno);
        System.out.println();
    }

    public void relatorio(Vendedor vendedor) {
        System.out.println();
        System.out.println("RELATÓRIO DE VENDAS DO VENDEDOR:");
        System.out.println("Vendas do vendedor " + vendedor.getNome() + ":");
        double totalVendedor = 0;

        List<Venda> vendasVendedor = vendedor.getVendidos().stream()
            .sorted(Comparator.comparingDouble(Venda::valor).reversed())
            .toList();

        if (vendasVendedor.isEmpty()) {
            System.out.println("Este vendedor ainda não realizou nenhuma venda.");
        } else {
            for (Venda venda : vendasVendedor) {
                System.out.println("Veiculo: " + venda.getVeiculo().toString());
                System.out.println("Cliente: " + venda.getCliente().toString());
                System.out.println("Valor da venda: R$" + venda.valor());
                System.out.println("Data: " + venda.getData().toString());
                System.out.println("***************************************");

                totalVendedor += venda.valor();
            }
        }
        System.out.println("Total: R$" + totalVendedor);
        System.out.println();
    }
}
