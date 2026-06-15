package Controller;

import Model.Bebida;
import Model.Estoque;
import Exceptions.DistribuidoraException;
import Service.Distribuidora;

public class EstoqueController {
    private Estoque estoque;
    private Distribuidora distribuidora;

    public EstoqueController(Estoque estoque, Distribuidora distribuidora) {
        this.estoque = estoque;
        this.distribuidora = distribuidora;
    }

    public void sincronizarProdutos() {
        for (Bebida b : distribuidora.listarBebidas()) {
            estoque.adicionarProduto(b);
        }
    }

    public int consultarDisponibilidade(String codigo) {
        sincronizarProdutos();
        return estoque.getQuantidade(codigo);
    }

    public Bebida obterBebida(String codigo) {
        return distribuidora.buscarBebidaPorCodigo(codigo);
    }

    public void ajustarEstoqueManual(String codigo, int novaQuantidade) throws DistribuidoraException {
        sincronizarProdutos();
        estoque.ajustarQuantidade(codigo, novaQuantidade);
    }

    public Estoque getEstoque() {
        sincronizarProdutos();
        return estoque;
    }
}