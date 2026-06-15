package Model;
import java.util.ArrayList;
import java.io.Serializable;

public class Venda implements Serializable {
    private int codigoVenda;
    private ArrayList<ItemVenda> itens;
    private Cliente cliente;
    private boolean finalizada;

    public Venda(int codigoVenda, Cliente cliente) {
        setCodigoVenda(codigoVenda);
        setCliente(cliente);
        this.itens = new ArrayList<ItemVenda>();
        this.finalizada = false;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public void setItens(ArrayList<ItemVenda> itens) {
        this.itens = itens;
    }

    public ArrayList<ItemVenda> getItens() {
        return itens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public boolean addItem(ItemVenda item){
        if(!this.finalizada){
            this.itens.add(item);
            return true;
        }else{
            return false;
        }
    }

    public double calculoTotalVenda(){
        double soma = 0;
        for (ItemVenda item : this.itens){
            soma += item.calculoSubTotal();
        }
        return soma;
    }

    public void removerItens(ItemVenda item){
        if(!this.finalizada){
            this.itens.remove(item);
        }
    }
    public String toString() {
        return "\nVenda n° " + getCodigoVenda() +
               "\nCliente: " + getCliente().getNome();
    }
}