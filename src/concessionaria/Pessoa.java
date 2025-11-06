package concessionaria;

public class Pessoa {
    protected String nome;
    protected String cpf;
    protected Data nasc;

    public Pessoa(String nome, String cpf, int dia, int mes, int ano) {
        this.nome = nome;
        this.cpf = cpf;
        this.nasc = new Data(dia,mes,ano);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Data getNasc() {
        return nasc;
    }

    public int getIdade(Data hoje) {
        int idade = hoje.getAno() - this.nasc.getAno();

        if (hoje.getMes() < this.nasc.getMes() ||
                (hoje.getMes() == this.nasc.getMes() && hoje.getDia() < this.nasc.getDia())) {
            idade--;
        }
        return idade;
    }

    public String toString() {
        return this.nome + " - CPF: " + this.cpf;
    }

}

