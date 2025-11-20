package concessionaria;

public class Data implements Comparable<Data> {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }
    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public String toString() {
        return this.dia + "/" + this.mes + "/" + this.ano;
    }

    @Override
    public int compareTo(Data outra) {
        if (this.ano != outra.ano) {
            return Integer.compare(this.ano, outra.ano);
        }
        if (this.mes != outra.mes) {
            return Integer.compare(this.mes, outra.mes);
        }
        return Integer.compare(this.dia, outra.dia);
    }

}
