package Model;
import Exceptions.ObrigatorioException;
import java.io.Serializable;

public class Endereco implements Serializable {
    private String cep;

    public Endereco(String cep) throws ObrigatorioException {
        setCep(cep);
    }
    public void setCep(String cep) throws ObrigatorioException {
        if(cep == null) throw new ObrigatorioException("O CEP é obrigatório");
        else this.cep = cep;
    }
    public String getCep() {
        return cep;
    }
    public String toString() {
        return "| CEP: " + getCep();
    }
}
