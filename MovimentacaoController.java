package Controller;
import View.ClienteView;
import Exceptions.ObrigatorioException;
import Model.*;
import Util.LoggerService;
import Service.Distribuidora;

public class ClienteController {
    private ClienteView view;
    private int proximoId;
    private Distribuidora distribuidora;

    public ClienteController(Distribuidora distribuidora) {
        proximoId = 1;
        this.distribuidora = distribuidora;
    }
    public void setView(ClienteView view) {
        this.view = view;
    }

    public void cad_Cliente(String nome, String cpf, String cep) {
        try{
            sincronizarProximoId();
            Cliente c = new Cliente(proximoId, nome, cpf, new Endereco(cep));
            distribuidora.adicionarCliente(c, proximoId);
            view.mensagem("Sucesso! Cliente cadastrado com ID: " + proximoId);
            LoggerService.log("INFO", "Cliente cadastrado com ID: " + proximoId);
            proximoId++;
        }catch (ObrigatorioException e) {
            view.mensagem("Erro ao cadastrar: " + e.getMessage());
            LoggerService.log("ERROR", "Erro ao cadastrar cliente ID: " + proximoId);
        }
    }

    public void buscar_cliente(int id) {
        try {
            if(distribuidora.getClientes().containsKey(id)) {
                view.mensagem(distribuidora.getClientes().get(id).toString());
                LoggerService.log("INFO", "Cliente ID: " + id + " buscado");
            }else {
                view.mensagem("Cliente inexistente!");
                LoggerService.log("INFO", "Cliente inexistente buscado. Id de busca: " + id);
            }
        } catch (Exception e) { 
            view.mensagem("Erro ao buscar cliente: " + e.getMessage());
            LoggerService.log("ERROR", "Erro ao buscar cliente ID: " + id);
        }   
    }

    public Cliente buscar_cliente(int idCliente, String nome) {
        try{
                if(distribuidora.getClientes().containsKey(idCliente)) {
                    Cliente c = distribuidora.getClientes().get(idCliente);
                    LoggerService.log("INFO", "Cliente " + idCliente + " buscado");
                    return c;
                } else {
                    return null;
                }
        } catch (Exception e) {
            LoggerService.log("ERROR", "Erro ao buscar cliente");
            return null;
        }
    }

    public void remover_cliente(int id) {
        try {
            if(!distribuidora.getClientes().containsKey(id)) {
                view.mensagem("Cliente inexistente!");
                LoggerService.log("WARNING", "Tentativa de remoção de cliente inexistente ID: " + id);
            } else {
                distribuidora.getClientes().remove(id);
                view.mensagem("Cliente removido!");
                LoggerService.log("INFO", "Cliente removido ID: " + id);
            }
        } catch (Exception e) {
            view.mensagem("Erro ao remover cliente: " + e.getMessage());
            LoggerService.log("ERROR", "Erro ao remover cliente ID: " + id);
        }
    }

    public String alterarNome(int idCliente, String novoNome) {
        try {
            if(novoNome.isEmpty()) {
                LoggerService.log("WARNING", "Campo nome não preenchido. Alteração não autorizada");
                return "Atualização cancelada! O nome não pode ser vazio";
            }else {
                if(distribuidora.getClientes().containsKey(idCliente)) {
                    distribuidora.getClientes().get(idCliente).setNome(novoNome);
                    LoggerService.log("INFO", "Nome do cliente " + idCliente + " alterado para: " + novoNome);
                    return "Nome alterado com sucesso!";
                }else {
                    LoggerService.log("WARNING", "Id " + idCliente + " inexistente. Não é possível alterar nome");
                    return "ID informado inexistente!";
                }
            }
        }catch (ObrigatorioException e) {
            LoggerService.log("ERROR", "Campos obrigatórios não foram preenchidos na alteração de nome de cliente");
            return "Preencha todos os campos!";
        }
    
    }

    public String alterarCpf(int idCliente, String novoCpf) {
        try {
            if(novoCpf.isEmpty()) {
                LoggerService.log("WARNING", "Campo CPF não preenchido. Alteração não autorizada");
                return "Atualização cancelada! O CPF não pode ser vazio";
            } else {
                if(distribuidora.getClientes().containsKey(idCliente)) {
                    distribuidora.getClientes().get(idCliente).setCPF(novoCpf);
                    LoggerService.log("INFO", "CPF do cliente " + idCliente + " alterado para: " + novoCpf);
                    return "CPF alterado com sucesso!";
                } else {
                    LoggerService.log("WARNING", "Id " + idCliente + " inexistente. Não é possível alterar CPF");
                    return "ID informado inexistente!";
                }
            }
        }catch (ObrigatorioException e) {
            LoggerService.log("ERROR", "Campos obrigatórios não foram preenchidos na alteração de CPF de cliente");
            return "Preencha todos os campos!";
        }
    }

    public String alterarCep(int idCliente, String novoCep) {
        try {
            if(novoCep.isEmpty()) {
                LoggerService.log("WARNING", "Campo CEP não preenchido. Alteração não autorizada");
                return "Atualização cancelada! O CEP não pode ser vazio";
            } else {
                if(distribuidora.getClientes().containsKey(idCliente)) {
                    distribuidora.getClientes().get(idCliente).setEndereco(new Endereco(novoCep));
                    LoggerService.log("INFO", "CEP do cliente " + idCliente + " alterado para: " + novoCep);
                    return "CEP alterado com sucesso!";
                } else {
                    LoggerService.log("WARNING", "Id " + idCliente + " inexistente. Não é possível alterar CEP");
                    return "ID informado inexistente!";
                }
            }
        }catch (ObrigatorioException e) {
            LoggerService.log("ERROR", "Campos obrigatórios não foram preenchidos na alteração de CEP de cliente");
            return "Preencha todos os campos!";
        }
    }

    public void listarClientes() {
        try {
            for(Cliente c : distribuidora.getClientes().values()) {
                view.mensagem(c.toString());
            }
            LoggerService.log("INFO", "Clientes listados");
        } catch (Exception e) {
            view.mensagem("Erro ao listar clientes");
            LoggerService.log("ERROR", "Erro ao listar clientes");
        }
        
    }

    public Cliente buscar_cliente(String cpf) {
        for(Cliente c : distribuidora.getClientes().values()) {
            if(c.getCpf().equalsIgnoreCase(cpf)) {
                LoggerService.log("INFO", "Cliente CPF: " + cpf + " buscado");
                return c;
            }
        }
        LoggerService.log("ERROR", "Erro ao buscar cliente CPF: " + cpf);
        return null;
    }

    public void sincronizarProximoId() {
        if (!distribuidora.getClientes().isEmpty()) {
            int maiorId = 0;
            for (Integer id : distribuidora.getClientes().keySet()) {
                if (id > maiorId) {
                    maiorId = id;
                }
            }
            this.proximoId = maiorId + 1;
        } else {
            this.proximoId = 1; 
        }
    }
}