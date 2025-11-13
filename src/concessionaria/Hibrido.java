package concessionaria;

public class Hibrido extends Veiculo{
    private double autonomiaComb;
    private double capacidadeComb;
    private double autonomiaBat;
    private double capacidadeBat;

    public Hibrido(String marca, String modelo, int anoFab, int mesFab, int anoMod, double valor, double autonomiaComb,
                   double capacidadeComb, double autonomiaBat, double capacidadeBat) {
        super(marca, modelo, anoFab, mesFab, anoMod, valor);
        this.autonomiaComb = autonomiaComb;
        this.capacidadeComb = capacidadeComb;
        this.autonomiaBat = autonomiaBat;
        this.capacidadeBat = capacidadeBat;
    }

    @Override
    public double getAutonomia() {
        return (this.autonomiaBat + this.autonomiaComb);
    }

    public double getAutonomiaComb() {
        return autonomiaComb;
    }

    public double getCapacidadeComb() {
        return capacidadeComb;
    }

    public double getAutonomiaBat() {
        return autonomiaBat;
    }

    public double getCapacidadeBat() {
        return capacidadeBat;
    }

    @Override
    public String toString() {
        return super.toString() + " (Híbrido)";
    }
}
