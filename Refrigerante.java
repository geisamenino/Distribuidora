package Model;

public class Cerveja extends BebidaAlcoolica {
    private String tipoCerveja; 

    public Cerveja(String codigo, String nome, double preco, double teorAlcoolico, String tipoCerveja) {
        super(codigo, nome, preco, teorAlcoolico);
        setTipoCerveja(tipoCerveja);
    }

    public String getTipoCerveja() {
        return tipoCerveja; 
    }

    public void setTipoCerveja(String tipoCerveja) {
        if(!tipoCerveja.isEmpty()) this.tipoCerveja = tipoCerveja; 
    }

    @Override
    public String getDescricaoBebida() {
        return String.format("[Cerveja %s] %s | Cod: %s | Preço: R$%.2f | Teor Alcoólico: %.1f%%",
                tipoCerveja, getNome(), getCodigo(), getPreco(), getTeorAlcoolico());
    }
}