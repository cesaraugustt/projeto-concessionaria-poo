package concessionaria;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Entrada entrada = new Entrada();
        int opcao;

        try {
            do {
                opcao = entrada.menu(sistema);

                switch (opcao) {
                    case 1:
                        entrada.cadCliente(sistema);
                        break;
                    case 2:
                        entrada.cadVendedor(sistema);
                        break;
                    case 3:
                        entrada.cadGerente(sistema);
                        break;
                    case 4:
                        entrada.cadVeiculo(sistema);
                        break;
                    case 5:
                        entrada.cadVenda(sistema);
                        break;
                    case 6:
                        entrada.relatorioMensal(sistema);
                        break;
                    case 7:
                        entrada.relatorioAnual(sistema);
                        break;
                    case 8:
                        entrada.relatorioVendedor(sistema);
                        break;
                    case 0:
                        // System.out.println("Saindo do sistema... Obrigado por utilizar!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }

            } while (opcao != 0);
        } finally {
            entrada.close();
        }
    }
}
