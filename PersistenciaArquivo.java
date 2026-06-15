package Model;

import Exceptions.DistribuidoraException;
import java.io.Serializable;

public abstract class Movimentacao implements Serializable {
    protected String codigoBebida;
    protected int quantidade;

    public Movimentacao(String codigoBebida, int quantidade) {
        this.codigoBebida = codigoBebida;
        this.quantidade = quantidade;
    }

    public String getCodigoBebida() { 
        return codigoBebida;
    }

    public int getQuantidade() { 
        return quantidade; 
    }


    public abstract void processar(Estoque estoque) throws DistribuidoraException;
}