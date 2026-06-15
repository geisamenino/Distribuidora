package Model;

public class Refrigerante extends BebidaNaoAlcoolica {
    private String sabor;

    public Refrigerante(String codigo, String nome, double preco, boolean contemAcucar, String sabor) {
        super(codigo, nome, preco, contemAcucar);
        setSabor(sabor);
    }

    public String getSabor() {
         return sabor; 
    }

    public void setSabor(String sabor) {
        if(!sabor.isEmpty()) this.sabor = sabor; 
    }

    @Override
    public String getDescricaoBebida() {
        String acucar = isContemAcucar() ? "Com Açúcar" : "Zero Açúcar";
        return String.format("[Refrigerante de %s] %s | Cod: %s | Preço: R$%.2f | %s",
                sabor, getNome(), getCodigo(), getPreco(), acucar);
    }
}