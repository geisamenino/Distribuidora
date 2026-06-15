package Controller;
import Model.ItemVenda;
import Service.Distribuidora;
import Util.LoggerService;
import Model.Bebida;

public class ItemVendaController {
    private Distribuidora distribuidora;

    public ItemVendaController(Distribuidora distribuidora){
        this.distribuidora=distribuidora;
    }

    public ItemVenda criarItem(String codigo, int quantidade){
        Bebida bebidaEncontrada = distribuidora.buscarBebidaPorCodigo(codigo);
        if (bebidaEncontrada != null) {
            double preco = bebidaEncontrada.getPreco();
            LoggerService.log("INFO", "Item venda criado");
            return new ItemVenda(bebidaEncontrada, quantidade, preco);
        }
        return null;
    }

}