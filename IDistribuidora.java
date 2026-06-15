package Model;
import java.io.Serializable;
import java.util.Map;
import Exceptions.ObrigatorioException;



public class Fornecedor implements Serializable {
    private int id;
    private String nome;
    private String cnpj;
    private Map<String, Bebida> bebidasFornecedor;
    private Endereco endereco;

    public Fornecedor(int id, String nome, String cnpj, Map<String, Bebida> bebidasFornecedor, Endereco endereco) throws ObrigatorioException {
        this.id = id;
        setNome(nome);
        setCnpj(cnpj);
        this.bebidasFornecedor = bebidasFornecedor;
        setEndereco(endereco);

    }
    public void setNome(String nome) throws ObrigatorioException {
        if(!nome.isEmpty()) this.nome = nome;
        else throw new ObrigatorioException("O nome é obrigatório!");
    }
    public void setCnpj(String cnpj) throws ObrigatorioException{
        if(cnpj == null) throw new ObrigatorioException("O CNPJ é obrigatório");
        else this.cnpj = cnpj;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public String getNome() {
        return nome;
    }
    public String getCnpj() {
        return cnpj;
    }
    public Map<String, Bebida> getBebidasFornecedor() {
        return bebidasFornecedor;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public int getId() {
        return id;
    }
    public String toString(){
        return "\n| Fornecedor ID: " + getId() + "\n"
            + "| Nome: " + getNome() + "\n"
            + "| CNPJ: " + getCnpj() + "\n" 
            + endereco.toString();
    }

}
