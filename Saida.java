package Model;
import Exceptions.ObrigatorioException;
import java.io.Serializable;

public class Cliente implements Serializable {
    private int id;
    private String nome;
    private String cpf;
    private Endereco endereco;


    public Cliente(int id, String nome, String CPF, Endereco endereco) throws ObrigatorioException {
        this.id = id;
        setNome(nome);
        setCPF(CPF);
        setEndereco(endereco);
    }

    public void setNome(String nome) throws ObrigatorioException {
        if(!nome.isEmpty()) this.nome = nome;
        else throw new ObrigatorioException("O nome é obrigatório!");
    }

    public void setCPF(String cpf) throws ObrigatorioException {
        if(cpf == null) throw new ObrigatorioException("O CPF é obrigatório");
        else this.cpf = cpf;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public int getId() {
        return id;
    }
    public String toString(){
        return "\n| Cliente ID: " + getId() + "\n"
            + "| Nome: " + getNome() + "\n"
            + "| CPF: " + getCpf() + "\n" 
            + endereco.toString();
    }

}