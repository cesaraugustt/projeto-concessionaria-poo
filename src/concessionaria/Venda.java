package concessionaria;

public class Venda {
    private Veiculo veiculo;
    private Cliente cliente;
    private double desconto;
    private Data data;
    private String chassi;

    public Venda(Veiculo veiculo, Cliente cliente, double desconto, Data data, String chassi) {
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.desconto = desconto;
        this.data = data;
        this.chassi = chassi;
    }

    public double valor() {
        return this.veiculo.getValor() - this.desconto;
    }

    public void setDesconto(double novoDesconto, Gerente gerente, String senha) {
        if (gerente.validarAcesso(senha)) {
            this.desconto = novoDesconto;
        } else {
            System.out.println("Acesso negado. Desconto não pode ser alterado.");
        }
    }

    public Data getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public double getDesconto() {
        return desconto;
    }

    public String getChassi() {
        return chassi;
    }


    public String toString() {
        String veiculoInfo = "Veiculo: " + this.veiculo.toString();

        String clienteInfo = "Cliente: " + this.cliente.toString().replace("\n", " ");

        String valorInfo = "Valor da venda: R$" + this.valor();

        String dataInfo = "Data: " + this.data.toString();

        return veiculoInfo + "\n" + clienteInfo + "\n" + valorInfo + "\n" + dataInfo;
    }
}
