package Model;
import Exceptions.DistribuidoraException;
import Util.LoggerService;

public class Saida extends Movimentacao {

    public Saida(String codigoBebida, int quantidade) {
        super(codigoBebida, quantidade);
    }

    @Override
    public void processar(Estoque estoque) throws DistribuidoraException {
        if (estoque.getBebida(codigoBebida) == null) {
            LoggerService.log("WARNING", "Tentativa de saída no estoque de uma bebida não cadastrada");
            throw new DistribuidoraException("Não é possível dar saída: Bebida não cadastrada.");
        }
        if (quantidade <= 0) {
            LoggerService.log("WARNING", "Tentativa de incluir estoque negativo");
            throw new DistribuidoraException("A quantidade de saída deve ser maior que zero.");
        }

        int qtdAtual = estoque.getQuantidade(codigoBebida);
        if (qtdAtual < quantidade) {
            LoggerService.log("WARNING", "Tentativa de saída de mais itens do que o contido");
            throw new DistribuidoraException("Estoque insuficiente! Disponível: " + qtdAtual + " | Solicitado: " + quantidade);
        }

        estoque.ajustarQuantidade(codigoBebida, qtdAtual - quantidade);
        LoggerService.log("INFO", "Saida registrada bebida: " + codigoBebida);
    }
}