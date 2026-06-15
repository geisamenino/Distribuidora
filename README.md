package Model;
import Exceptions.DistribuidoraException;
import Util.LoggerService;

public class Entrada extends Movimentacao {

    public Entrada(String codigoBebida, int quantidade) {
        super(codigoBebida, quantidade);
    }

    @Override
    public void processar(Estoque estoque) throws DistribuidoraException {
        if (estoque.getBebida(codigoBebida) == null) {
            LoggerService.log("WARNING", "Tentativa de dar entrada no estoque de uma bebida não cadastrada");
            throw new DistribuidoraException("Não é possível dar entrada: Bebida não cadastrada.");
        }
        if (quantidade <= 0) {
            LoggerService.log("WARNING", "Tentativa de incluir estoque negativo");
            throw new DistribuidoraException("A quantidade de entrada deve ser maior que zero.");
        }
        
        int qtdAtual = estoque.getQuantidade(codigoBebida);
        estoque.ajustarQuantidade(codigoBebida, qtdAtual + quantidade);
        LoggerService.log("INFO", "Estoque ajustado bebida: " + codigoBebida);
    }
}