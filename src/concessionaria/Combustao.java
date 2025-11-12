package concessionaria;

public class Combustao extends Veiculo{
    private double autonomiaComb;
    private double capacidadeComb;

    public Combustao(String marca, String modelo, int anoFab, int mesFab, int anoMod, double valor,
                     double autonomiaComb, double capacidadeComb) {
        super(marca, modelo, anoFab, mesFab, anoMod, valor);
        this.autonomiaComb = autonomiaComb;
        this.capacidadeComb = capacidadeComb;
    }

    @Override
    public double getAutonomia() {
        return this.autonomiaComb;
    }

    public double getCapacidadeComb() {
        return capacidadeComb;
    }

    @Override
    public String toString() {
        return super.toString() + " (Combustão)";
    }
}
