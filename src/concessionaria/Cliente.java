package concessionaria;

public class Cliente extends Pessoa{
    private String email;

    public Cliente(String nome, String cpf, int dia, int mes, int ano, String email) {
        super(nome, cpf, dia, mes, ano);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return super.toString() + " - " + this.email;
    }
}
