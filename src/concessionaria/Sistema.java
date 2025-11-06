package concessionaria;

import java.util.ArrayList;

public class Sistema {
    private ArrayList<Veiculo> veiculos;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Gerente> gerentes;
    private ArrayList<Cliente> clientes;

    public Sistema() {
        this.veiculos = new ArrayList<>();
        this.vendedores = new ArrayList<>();
        this.gerentes = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public void adicionar(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public void adicionar(Vendedor vendedor) {
        this.vendedores.add(vendedor);
    }

    public void adicionar(Gerente gerente) {
        this.gerentes.add(gerente);
    }

    public void adicionar(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void listarClientes() {
        System.out.println("Clientes cadastrados:");
        for (Cliente c : this.clientes) {
            System.out.println(c.toString());
        }
    }

    public void listarVendedores() {
        System.out.println("Vendedores cadastrados:");
        for (Vendedor v : this.vendedores) {
            System.out.println(v.toString());
        }
    }

    public void listarGerentes() {
        System.out.println("Gerentes cadastrados:");
        for (Gerente g : this.gerentes) {
            System.out.println(g.toString());
        }
    }

    public void listarVeiculos() {
        System.out.println("Veiculos cadastrados:");
        int i = 1;
        for (Veiculo v : this.veiculos) {
            System.out.println(i + ") " + v.toString());
            i++;
        }
    }

    public Cliente localizarCliente(String cpf) {
        for (Cliente c : this.clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    public Vendedor localizarVendedor(String cpf) {
        for (Vendedor v : this.vendedores) {
            if (v.getCpf().equals(cpf)) {
                return v;
            }
        }
        return null;
    }

    public Gerente localizarGerente(String cpf) {
        for (Gerente g : this.gerentes) {
            if (g.getCpf().equals(cpf)) {
                return g;
            }
        }
        return null;
    }

    public Veiculo localizarVeiculo(int indice) {
        if (indice > 0 && indice <= this.veiculos.size()) {
            return this.veiculos.get(indice - 1);
        }
        return null;
    }

    public void atribuirVendaVendedor(Venda venda, Vendedor vendedor) {
        vendedor.addVenda(venda);
    }

    public void relatorio(int mes, int ano) {
        System.out.println();
        System.out.println("RELATÓRIO DE VENDAS MENSAL DE " + mes + "/" + ano + ":");
        double totalMes = 0;

        for (Vendedor v : this.vendedores) {
            double salarioMes = v.getSalario(mes, ano);
            ArrayList<Venda> vendasVendedor = v.getVendidos();

            for (Venda venda : vendasVendedor ) {
                if (venda.getData().getMes() == mes && venda.getData().getAno() == ano) {
                    System.out.println("Vendedor: " + v.getNome() + " (Salário neste mês: R$" + salarioMes + ")");
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

        for (Vendedor v : this.vendedores) {
            ArrayList<Venda> vendasVendedor = v.getVendidos();

            for (Venda venda : vendasVendedor) {
                if (venda.getData().getAno() == ano) {
                    System.out.println("Vendedor: " + v.getNome());
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

        ArrayList<Venda> vendasVendedor = vendedor.getVendidos();

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
