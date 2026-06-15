package Model;

public abstract class BebidaAlcoolica extends Bebida {
    private double teorAlcoolico;

    public BebidaAlcoolica(String codigo, String nome, double preco, double teorAlcoolico) {
        super(codigo, nome, preco);
        setTeorAlcoolico(teorAlcoolico);
    }

    public double getTeorAlcoolico() { 
        return teorAlcoolico; 
    }

    public void setTeorAlcoolico(double teorAlcoolico) {
         if(teorAlcoolico > 0) this.teorAlcoolico = teorAlcoolico; 
    }

    @Override
    public boolean isAlcoolica() { 
        return true;
    }
}