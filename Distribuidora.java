package Model;
import Exceptions.DistribuidoraException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Estoque implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Bebida> bebidas;
    private Map<String, Integer> quantidades;

    public Estoque() {
        this.bebidas = new HashMap<>();
        this.quantidades = new HashMap<>();
    }

    public void adicionarProduto(Bebida bebida) {
        if (!bebidas.containsKey(bebida.getCodigo())) {
            bebidas.put(bebida.getCodigo(), bebida);
            quantidades.put(bebida.getCodigo(), 0);
        }
    }

    public void ajustarQuantidade(String codigo, int novaQuantidade) throws DistribuidoraException {
        if (!bebidas.containsKey(codigo)) {
            throw new DistribuidoraException("Bebida com código " + codigo + " não encontrada no sistema.");
        }
        if (novaQuantidade < 0) {
            throw new DistribuidoraException("A quantidade não pode ser negativa.");
        }
        quantidades.put(codigo, novaQuantidade);
    }

    public int getQuantidade(String codigo) {
        return quantidades.getOrDefault(codigo, 0);
    }

    public Bebida getBebida(String codigo) {
        return bebidas.get(codigo);
    }

    public Map<String, Bebida> getProdutos() {
        return bebidas;
    }

    public Map<String, Integer> getQuantidades() {
        return quantidades;
    }
}