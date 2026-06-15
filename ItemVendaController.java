package Controller;
import Model.*;
import Service.Distribuidora;

import java.util.List;

public class BebidaController {
    private Distribuidora distribuidora;

    public BebidaController(Distribuidora distribuidora) {
        this.distribuidora = distribuidora;
    }

    public void cadastrarCerveja(String codigo, String nome, double preco, double teor, String tipo) {
        Cerveja cerveja = new Cerveja(codigo, nome, preco, teor, tipo);
        distribuidora.cadastrarBebida(cerveja);
    }

    public void cadastrarRefrigerante(String codigo, String nome, double preco, boolean acucar, String sabor) {
        Refrigerante refrigerante = new Refrigerante(codigo, nome, preco, acucar, sabor);
        distribuidora.cadastrarBebida(refrigerante);
    }

    public boolean excluirBebida(String codigo) {
        return distribuidora.removerBebida(codigo);
    }
 
    public List<Bebida> obterTodasBebidas() {
        return distribuidora.listarBebidas();
    }
    public String atualizarPreco(String codigo, double novoPreco) {
    try {
        if (codigo == null || codigo.trim().isEmpty()) {
            return "Erro: O código da bebida não pode ser vazio!";
        }
        if (novoPreco <= 0) {
            return "Erro: O preço deve ser maior que zero!";
        }

        boolean atualizado = distribuidora.atualizarPrecoBebida(codigo, novoPreco);

        if (atualizado) {
            return "Sucesso! O preço da bebida [" + codigo.toUpperCase() + "] foi atualizado para R$ " + String.format("%.2f", novoPreco);
        } else {
            return "Erro: Nenhuma bebida encontrada com o código [" + codigo.toUpperCase() + "].";
        }
    } catch (Exception e) {
        return "Erro ao atualizar preço: " + e.getMessage();
    }
}
}