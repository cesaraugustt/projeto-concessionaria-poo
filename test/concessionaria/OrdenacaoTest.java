package concessionaria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrdenacaoTest {

    @Test
    public void testPessoaNaturalOrdering() {
        Pessoa ana = new Cliente("Ana", "123", 1, 1, 2000, "ana@test.com");
        Pessoa zack = new Vendedor("Zack", "456", 1, 1, 2000, 3000, 5);

        assertTrue(ana.compareTo(zack) < 0, "Ana should come before Zack");
        assertTrue(zack.compareTo(ana) > 0, "Zack should come after Ana");
    }

    @Test
    public void testVeiculoCustomOrdering() {
        Veiculo carroBarato = new Combustao("MarcaA", "ModeloA", 2020, 1, 2020, 30000, 10, 50);
        Veiculo carroCaro = new Eletrico("MarcaB", "ModeloB", 2021, 1, 2021, 80000, 400, 75);

        List<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(carroCaro);
        veiculos.add(carroBarato);

        veiculos.sort(Comparator.comparingDouble(Veiculo::getValor));

        assertEquals(carroBarato, veiculos.get(0), "The cheaper car should be first after sorting");
        assertEquals(carroCaro, veiculos.get(1), "The more expensive car should be second after sorting");
    }

    @Test
    public void testSistemaVendorSorting() {
        Sistema sistema = new Sistema();
        Vendedor zack = new Vendedor("Zack", "456", 1, 1, 2000, 3000, 5);
        Vendedor ana = new Vendedor("Ana", "123", 1, 1, 2000, 3000, 5);
        Vendedor charles = new Vendedor("Charles", "789", 1, 1, 2000, 3000, 5);

        sistema.adicionar(zack);
        sistema.adicionar(ana);
        sistema.adicionar(charles);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        sistema.listarVendedores();

        System.setOut(originalOut);

        String output = outContent.toString();
        assertTrue(output.indexOf("Ana") < output.indexOf("Charles"), "Ana should be listed before Charles");
        assertTrue(output.indexOf("Charles") < output.indexOf("Zack"), "Charles should be listed before Zack");
    }

    @Test
    public void testSistemaVeiculoSorting() {
        Sistema sistema = new Sistema();
        Veiculo carroCaro = new Eletrico("Caro", "Model Y", 2023, 1, 2023, 300000, 500, 80);
        Veiculo carroBarato = new Combustao("Barato", "Model 3", 2022, 1, 2022, 150000, 400, 60);

        sistema.adicionar(carroCaro);
        sistema.adicionar(carroBarato);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        sistema.listarVeiculos();

        System.setOut(originalOut);

        String output = outContent.toString();
        assertTrue(output.indexOf("Model 3") < output.indexOf("Model Y"), "The cheaper vehicle (Model 3) should be listed before the expensive one (Model Y)");
    }
}
