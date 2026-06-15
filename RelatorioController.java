package Controller;

import Model.Bebida;
import Service.Distribuidora;
import java.util.List;

public class ConsultaBebidaController {
    private Distribuidora distribuidora;

    public ConsultaBebidaController(Distribuidora distribuidora) {
        this.distribuidora = distribuidora;
    }

    public Bebida buscarPorCodigo(String codigo) {
        return distribuidora.buscarBebidaPorCodigo(codigo);
    }

    public List<Bebida> buscarPorNome(String nome) {
        return distribuidora.buscarBebidasPorNome(nome);
    }

    public List<Bebida> buscarPorTipo(boolean isAlcoolica) {
        return distribuidora.buscarBebidasPorTipo(isAlcoolica);
    }
}