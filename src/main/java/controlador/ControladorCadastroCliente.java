/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ClienteDAO;
import exception.ClienteException;
import javax.swing.JOptionPane;
import modelo.Cliente;
import repositorio.ClienteRepositorio;
import visao.TelaCadastroCliente;

/**
 *
 * @author Maurício
 */
public class ControladorCadastroCliente {
    
    private TelaCadastroCliente telaCadastroCliente;
    private Cliente cliente;
    
    public ControladorCadastroCliente(TelaCadastroCliente telaCadastroCliente, Cliente cliente) {
        this.telaCadastroCliente = telaCadastroCliente;
        this.cliente = cliente;
        inicializarBotoes();
    }
    
    public void inicializarBotoes(){
        
        telaCadastroCliente.adicionarAcaoBotaoSalvarCliente(e -> {
            try {
            salvarCliente();
        } catch (ClienteException ex) {
            apresentarMensagem(ex.getMessage());
        }
        });
        
        telaCadastroCliente.adicionarAcaoBotaoLimparTela(e -> {
            telaCadastroCliente.limparTela();
        });
        
        telaCadastroCliente.adicionarAcaoBotaoCancelarCadastro(e -> { 
            telaCadastroCliente.fecharJanela();
        });
        
    }
    
    public void exibirTela() {
        telaCadastroCliente.exibirTela();
    }

    public void apresentarMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
    
    public void salvarCliente() throws ClienteException {

        String nome = telaCadastroCliente.getNomeCliente();
        if(nome.equals("")) {
            throw new ClienteException("O nome do cliente está em branco!!");
        }
        String CPF = telaCadastroCliente.getCPFCliente();
        String telefone = telaCadastroCliente.getClienteTelefone();
        cliente = new Cliente(nome, CPF, telefone);
        criarCliente(cliente);
        apresentarMensagem("Cadastro de cliente realizado com sucesso!!");
        telaCadastroCliente.limparTela();
    }

    public void criarCliente(Cliente cliente) {
        ClienteRepositorio clienteRepositorio = new ClienteDAO();
        clienteRepositorio.salvarCliente(cliente);
    }


    
}
