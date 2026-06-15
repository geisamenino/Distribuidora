package Model;

public abstract class BebidaNaoAlcoolica extends Bebida {
    private boolean contemAcucar;

    public BebidaNaoAlcoolica(String codigo, String nome, double preco, boolean contemAcucar) {
        super(codigo, nome, preco);
        setContemAcucar(contemAcucar);
    }

    public boolean isContemAcucar() { 
        return contemAcucar;
    }

    public void setContemAcucar(boolean contemAcucar) {
         this.contemAcucar = contemAcucar; 
    }

    @Override
    public boolean isAlcoolica() { 
        return false; 
    }
}
