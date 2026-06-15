package Service;

import Model.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Distribuidora implements IDistribuidora, Serializable {
    private static final long serialVersionUID = 1L;

    private Estoque estoque;
    private List<Bebida> bancoDeDadosBebidas;
    private Map<Integer, Cliente> clientes;
    private Map<Integer, Fornecedor> fornecedores;
    private List<Venda> vendas;
    private List<Movimentacao> movimentacoes;

    public Distribuidora() {
        this.estoque = new Estoque();
        this.bancoDeDadosBebidas = new ArrayList<>();
        this.clientes = new HashMap<>();
        this.fornecedores = new HashMap<>();
        this.vendas = new ArrayList<>();
        this.movimentacoes = new ArrayList<>();
    }

    @Override
    public void cadastrarBebida(Bebida bebida) {
        this.bancoDeDadosBebidas.add(bebida);
        this.estoque.adicionarProduto(bebida); 
    }

    @Override
    public List<Bebida> listarBebidas() {
        return new ArrayList<>(this.bancoDeDadosBebidas);
    }

    @Override
    public Bebida buscarBebidaPorCodigo(String codigo) {
        for (Bebida b : this.bancoDeDadosBebidas) {
            if (b.getCodigo().equalsIgnoreCase(codigo)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public List<Bebida> buscarBebidasPorNome(String nome) {
        List<Bebida> resultado = new ArrayList<>();
        for (Bebida b : this.bancoDeDadosBebidas) {
            if (b.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(b);
            }
        }
        return resultado;
    }

    @Override
    public List<Bebida> buscarBebidasPorTipo(boolean apenasAlcoolicas) {
        List<Bebida> resultado = new ArrayList<>();
        for (Bebida b : this.bancoDeDadosBebidas) {
            if (b.isAlcoolica() == apenasAlcoolicas) {
                resultado.add(b);
            }
        }
        return resultado;
    }

    @Override
    public boolean removerBebida(String codigo) {
        Bebida b = buscarBebidaPorCodigo(codigo);
        if (b != null) {
            this.bancoDeDadosBebidas.remove(b);
            return true;
        }
        return false;
    }

    @Override
    public boolean atualizarPrecoBebida(String codigo, double novoPreco) {
        Bebida b = buscarBebidaPorCodigo(codigo);
        if (b != null && novoPreco >= 0) {
            b.setPreco(novoPreco);
            return true;
        }
        return false;
    }



    public void adicionarCliente(Cliente cliente, int id) {
        this.clientes.put(id, cliente);
    }

    public void adicionarFornecedor(int id, Fornecedor fornecedor) {
        this.fornecedores.put(id, fornecedor);
    }

    public void cadastrarVenda(Venda venda) {
        this.vendas.add(venda);
    }

    public void registrarMovimentacaoHistorico(Movimentacao m) {
        this.movimentacoes.add(m);
    }

    public Estoque getEstoque() { 
        return this.estoque; 
    }
    
    public Map<Integer, Cliente> getClientes() { 
        return this.clientes; 
    }
    
    public Map<Integer, Fornecedor> getFornecedores() { 
        return this.fornecedores; 
    }
    
    public List<Venda> getVendas() { 
        return this.vendas; 
    }
    
    public List<Movimentacao> getMovimentacoes() { 
        return this.movimentacoes; 
    }
}