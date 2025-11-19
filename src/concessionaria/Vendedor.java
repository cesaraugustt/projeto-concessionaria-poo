package concessionaria;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Funcionario{
    private double comissao;
    private List<Venda> vendidos;

    public Vendedor(String nome, String cpf, int dia, int mes, int ano, double salario, double comissao) {
        super(nome, cpf, dia, mes, ano, salario);
        this.vendidos = new ArrayList<>();
        this.comissao = comissao;
    }

    public void addVenda(Venda venda) {
        this.vendidos.add(venda);
    }

    public double comissaoTotal(int mes, int ano) {
        double totalComissao = 0;
        for (Venda venda : this.vendidos) {
            if (venda.getData().getMes() == mes && venda.getData().getAno() == ano) {
                totalComissao += venda.valor() * (this.comissao / 100.0);
            }
        }
        return  totalComissao;
    }

    public double getSalario(int mes, int ano) {
        return super.getSalario(mes, ano) + this.comissaoTotal(mes, ano);
    }

    public double getSalarioBase() {
        return super.getSalario(0, 0);
    }

    public double getComissao() {
        return comissao;
    }

    public List<Venda> getVendidos() {
        return this.vendidos;
    }
}
