package Service;

import Model.Bebida;
import java.util.List;

public interface IDistribuidora {
    
    void cadastrarBebida(Bebida bebida);
    List<Bebida> listarBebidas();
    Bebida buscarBebidaPorCodigo(String codigo);
    List<Bebida> buscarBebidasPorNome(String nome);
    List<Bebida> buscarBebidasPorTipo(boolean apenasAlcoolicas);
    boolean removerBebida(String codigo);
    boolean atualizarPrecoBebida(String codigo, double novoPreco);
    
}