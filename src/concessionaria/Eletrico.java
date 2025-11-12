package concessionaria;

public class Eletrico extends Veiculo{
    private double autonomiaBat;
    private double capacidadeBat;

    public Eletrico(String marca, String modelo, int anoFab, int mesFab, int anoMod, double valor, double autonomiaBat,
                    double capacidadeBat) {
        super(marca, modelo, anoFab, mesFab, anoMod, valor);
        this.autonomiaBat = autonomiaBat;
        this.capacidadeBat = capacidadeBat;
    }

    @Override
    public double getAutonomia() {
        return this.autonomiaBat;
    }

    public double getCapacidadeBat() {
        return capacidadeBat;
    }

    @Override
    public String toString() {
        return super.toString() + " (Elétrico)";
    }
}
