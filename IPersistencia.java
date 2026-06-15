package Model;
import java.io.Serializable;

public class ItemVenda implements Serializable {
    private Bebida bebidaItem;
    private int quantidade;
    private double precoUnt;

    public ItemVenda(Bebida bebidaItem,int quantidade, double precoUnt){
        setBebidaItem(bebidaItem);
        setPrecoUnt(precoUnt);
        setQuantidade(quantidade);
    }
    public void setBebidaItem(Bebida bebidaItem) {
        this.bebidaItem = bebidaItem;
    }
    public void setPrecoUnt(double precoUnt) {
        if(precoUnt >= 0) this.precoUnt = precoUnt;
    }
    public void setQuantidade(int quantidade) {
        if(quantidade >= 0) this.quantidade = quantidade;
    }

    public double getPrecoUnt() {
        return precoUnt;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public Bebida getBebidaItem() {
        return bebidaItem;
    }

    public double calculoSubTotal(){
        double somaS = 0;
        somaS=getQuantidade()*getPrecoUnt();
        return somaS;
    }
    
    public String toString() {
        return "\n| Item: " + getBebidaItem().getNome() +
                "\n| Quantidade: " + getQuantidade() +
                "\n| Preço unitário: " + getPrecoUnt() +
                "\n| Total do item: " + calculoSubTotal();

    }

}