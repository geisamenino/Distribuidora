package Service;

import java.io.*;

public class PersistenciaArquivo implements IPersistencia {

    @Override
    public void salvar(Object dados, String caminho) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(dados);
            System.out.println("[Persistência] Dados salvos com sucesso em: " + caminho);
        } catch (IOException e) {
            System.out.println("[Erro Persistência] Não foi possível salvar os dados: " + e.getMessage());
        }
    }

    @Override
    public Object carregar(String caminho) {
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object dados = ois.readObject();
            System.out.println("[Persistência] Dados carregados com sucesso de: " + caminho);
            return dados;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[Erro Persistência] Falha ao carregar arquivo. Criando base vazia. Erro: "
             + e.getMessage());
             e.printStackTrace();
            return null;
        }
    }
}