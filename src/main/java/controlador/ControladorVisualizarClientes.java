/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ClienteDAO;
import java.util.List;
import java.util.Set;
import modelo.Cliente;
import repositorio.ClienteRepositorio;
import visao.TelaVisualizarClientes;

/**
 *
 * @author Maurício
 */
public class ControladorVisualizarClientes {
 
    private TelaVisualizarClientes telaVisualizarClientes;
    private Set<Cliente> clientes;
    
    public ControladorVisualizarClientes(TelaVisualizarClientes telaVisualizarClientes, Set<Cliente> clientes) {
        this.telaVisualizarClientes = telaVisualizarClientes;
        this.clientes = clientes;
        
        inicializarBotoes();
        preencherClientes();
    }
    
    public void inicializarBotoes() {
        
        telaVisualizarClientes.adicionarAcaoBotaoSair(e -> {
            telaVisualizarClientes.fecharJanela();
        });
        
    }
    
    public void preencherClientes() {
        ClienteRepositorio clienteRepositorio = new ClienteDAO();
        clientes = clienteRepositorio.recuperarTodosClientes();
        telaVisualizarClientes.popularClientes(clientes);
    }
    
    public void exbirTela(){
        telaVisualizarClientes.exbirTela();
    } 
    
}
