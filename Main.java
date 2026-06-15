package Controller;

import Model.*;
import Service.Distribuidora;
import Util.LoggerService;

import java.util.List;

import Exceptions.*;

public class VendaController {
    private Venda vendaAtual;
    private Distribuidora distribuidora;
    private int proximaVenda = 1;
    private ClienteController clienteController;
    private MovimentacaoController movimentacaoController;

    public VendaController(Distribuidora distribuidora, ClienteController clienteController, MovimentacaoController movimentacaoController){
        this.distribuidora = distribuidora;
        this.vendaAtual = null;
        this.clienteController = clienteController;
        this.movimentacaoController = movimentacaoController;
    }

    public String iniciarNovaVenda(int idCliente, String nome) throws ObrigatorioException {
        sincronizarProximaVenda();
        if (vendaAtual != null){
            return "Erro: Existe uma compra ainda em andamento.";
        }
        Cliente clienteEncontrado = clienteController.buscar_cliente(idCliente, nome);
        if (clienteEncontrado == null){
            return "Erro: Cliente não encontrado.";
        }

        clienteEncontrado.setNome(nome); 
        this.vendaAtual = new Venda(this.proximaVenda, clienteEncontrado);
        this.proximaVenda++;
        return "Sucesso: Compra n° " + vendaAtual.getCodigoVenda() + " iniciada para o cliente " + clienteEncontrado.getNome();
    }

    public String finalizarVenda() {
        try {
            if (vendaAtual == null) {
                return "Erro: Não há nenhuma compra em andamento para finalizar.";
            }
            if (vendaAtual.getItens().isEmpty()) {
                return "Erro: Não é possível comprar sem produtos no carrinho!";
            }

            for (ItemVenda item : vendaAtual.getItens()) {
                String codigoBebida = item.getBebidaItem().getCodigo();
                int qtdVendida = item.getQuantidade();
                movimentacaoController.registrarSaida(codigoBebida, qtdVendida);
            }

            double total = vendaAtual.calculoTotalVenda();
            vendaAtual.setFinalizada(true);        
            distribuidora.cadastrarVenda(vendaAtual);
            this.vendaAtual = null; 

            LoggerService.log("INFO", "Venda finalizada com sucesso! Total: R$ " + String.format("%.2f", total));
            return "Venda finalizada com sucesso! Total: R$ " + String.format("%.2f", total);

        } catch (DistribuidoraException e) {
            LoggerService.log("ERROR", "Erro ao finalizar venda");
            return "Erro ao finalizar venda";
        }
        
    }

    public String addItemVenda(String codigoBebida, int quantidade){
        try {
            if (vendaAtual == null){
                return "Erro: Não há compra aberta.";
            }
            if (quantidade <= 0) {
                return "Erro: A quantidade deve ser maior que zero.";
            }
            Bebida bebidaEncontrada = distribuidora.buscarBebidaPorCodigo(codigoBebida);
            if(bebidaEncontrada == null){
                return "Erro: Bebida com o código " + codigoBebida + " não foi encontrada.";
            }

            double precoUnt = bebidaEncontrada.getPreco();
            ItemVenda novoItem = new ItemVenda(bebidaEncontrada, quantidade, precoUnt);
            this.vendaAtual.addItem(novoItem);

            LoggerService.log("INFO", "Item " + codigoBebida + " adicionado a venda");
            return "Sucesso: " + quantidade + "x " + bebidaEncontrada.getNome() + " adicionado(s) à venda!";

        } catch (Exception  e) {
            LoggerService.log("ERROR", "Erro ao adicionar item na venda");
            return "Erro ao adicionar item";
        }
        
    }

    public String cancelarCompra() {
        try {
            if (vendaAtual == null) {
                return "Não há nenhuma compra ativa para cancelar.";
            }
            vendaAtual = null;

            LoggerService.log("INFO", "Venda cancelada");
            return "Compra cancelada com sucesso.";

        } catch (Exception e) {
            LoggerService.log("ERROR", "Erro ao cancelar compra");
            return "Erro ao cancelar compra";
        }
        
    }

    public void listarVendas() {
        try {
            if (distribuidora.getVendas().isEmpty()) {
                System.out.println("Nenhuma venda realizada no histórico.");
                return;
            }
            System.out.println("\n--- HISTÓRICO DE VENDAS FINALIZADAS ---");
            for (Venda v : distribuidora.getVendas()) {
                System.out.println("\nVenda Cód: " + v.getCodigoVenda() + " | Cliente: " + v.getCliente().getNome());
                System.out.println("Itens da Venda:");
                for (ItemVenda i : v.getItens()) { 
                    System.out.println("  - " + i.getBebidaItem().getNome() + " | Qtd: " + i.getQuantidade() + " | Subtotal: R$" + String.format("%.2f", i.calculoSubTotal()));
                }
                System.out.println("Total da Venda: R$ " + String.format("%.2f", v.calculoTotalVenda()));
                System.out.println("-----------------------------------");
            }
            LoggerService.log("INFO", "Vendas listadas");
        } catch (Exception e) {
            LoggerService.log("ERROR", "Erro ao listar vendas");
            System.out.println("Erro ao listar vendas");
        }
        
    }

    public void sincronizarProximaVenda() {
        List<Venda> listaVendas = distribuidora.getVendas();
        
        if (listaVendas != null && !listaVendas.isEmpty()) {
            int maiorCodigo = 0;
            for (Venda v : listaVendas) {
                if (v.getCodigoVenda() > maiorCodigo) {
                    maiorCodigo = v.getCodigoVenda();
                }
            }
            this.proximaVenda = maiorCodigo + 1;
        } else {
            this.proximaVenda = 1; 
        }
    }

}