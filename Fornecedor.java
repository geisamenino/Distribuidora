package Model;
import java.io.Serializable;

public abstract class Bebida implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigo;
    private String nome;
    private double preco;

    public Bebida(String codigo, String nome, double preco) {
        setCodigo(codigo);
        setNome(nome);
        setPreco(preco);
    }

    public String getCodigo() { 
        return codigo;
    }

    public void setCodigo(String codigo) { 
        if(!codigo.isEmpty()) this.codigo = codigo;
    }

    public String getNome() {
        return nome; 
    }

    public void setNome(String nome) { 
        if(!nome.isEmpty()) this.nome = nome;
    }

    public double getPreco() { 
        return preco; 
    }

    public void setPreco(double preco) {
        if(preco >= 0) this.preco = preco; 
    }

    public abstract String getDescricaoBebida();

    public abstract boolean isAlcoolica();
}