package Service;

public interface IPersistencia {
    void salvar(Object dados, String caminho);
    Object carregar(String caminho);
}