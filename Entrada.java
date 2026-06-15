import Controller.*;
import Model.Estoque;
import Service.Distribuidora;
import Service.PersistenciaArquivo;
import View.*;
import java.util.Scanner;

public class Main {
    private static final String CAMINHO_ARQUIVO = "dados_sistema.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersistenciaArquivo ferramentoPersistencia = new PersistenciaArquivo();

        Distribuidora distribuidora = (Distribuidora) ferramentoPersistencia.carregar(CAMINHO_ARQUIVO);
     
        if (distribuidora == null) {
            distribuidora = new Distribuidora();
            System.out.println("[Sistema] Nova base de dados criada.");
        }

        Estoque estoque = distribuidora.getEstoque();

        BebidaController bebidaController = new BebidaController(distribuidora);
        ConsultaBebidaController consultaBebidaController = new ConsultaBebidaController(distribuidora);
        EstoqueController estoqueController = new EstoqueController(estoque, distribuidora);
        MovimentacaoController movimentacaoController = new MovimentacaoController(estoque, estoqueController);
        
        ClienteController clienteController = new ClienteController(distribuidora); 
        FornecedorController fornecedorController = new FornecedorController(null, distribuidora);
        VendaController vendaController = new VendaController(distribuidora, clienteController, movimentacaoController);

        RelatorioController relatorioController = new RelatorioController(distribuidora);

        BebidaView bebidaView = new BebidaView(bebidaController);
        ConsultaBebidaView consultaBebidaView = new ConsultaBebidaView(consultaBebidaController);
        EstoqueView estoqueView = new EstoqueView(estoqueController);
        MovimentacaoView movimentacaoView = new MovimentacaoView(movimentacaoController);
        ClienteView clienteView = new ClienteView(clienteController);
        FornecedorView fornecedorView = new FornecedorView(fornecedorController);
        VendaView vendaView = new VendaView(vendaController);
 
        RelatorioView relatorioView = new RelatorioView(relatorioController);

        clienteController.setView(clienteView);
        fornecedorController.setView(fornecedorView);

        int opcao = -1;
        do {
            System.out.println("\n=======================================");
            System.out.println("    DISTRIBUUIDORA DE BEBIDAS  ");
            System.out.println("=======================================");
            System.out.println("1. Cadastro de Bebidas");
            System.out.println("2. Consulta de Bebidas");
            System.out.println("3. Gerenciamento de Clientes");
            System.out.println("4. Gerenciamento de Fornecedores");
            System.out.println("5. Controle de Estoque");
            System.out.println("6. Movimentação de Estoque");
            System.out.println("7. Vendas");
            System.out.println("8. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha o módulo desejado: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1: 
                        bebidaView.exibirMenu(); 
                        break;
                    case 2: 
                        consultaBebidaView.exibirMenu();
                        break;
                    case 3: 
                        clienteView.exibirMenu(); 
                        break;
                    case 4: 
                        fornecedorView.menu_atulizar_fornecedor();
                        break;
                    case 5: 
                        estoqueView.exibirMenu(); 
                        break;
                    case 6: 
                        movimentacaoView.exibirMenu(); 
                        break;
                    case 7: 
                        vendaView.exibirMenu(); 
                        break;
                    case 8: 
                        relatorioView.exibirMenu(); 
                        break;
                    case 0:
                        System.out.println("\nSaindo e salvando...");
                        
                        ferramentoPersistencia.salvar(distribuidora, CAMINHO_ARQUIVO);
                        System.out.println("Sistema finalizado!");
                        break;
                    default:
                        System.out.println("\nOpção inválida!");
                }
            } catch (Exception e) {
                System.out.println("\nErro detectado no Menu: " + e.getMessage());
                scanner.nextLine();
            }
        } while (opcao != 0);

        scanner.close();
    }
}
