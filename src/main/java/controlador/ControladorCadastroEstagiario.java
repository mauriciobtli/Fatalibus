/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.EstagiarioDAO;
import exception.EstagiarioException;
import javax.swing.JOptionPane;
import modelo.Estagiario;
import repositorio.EstagiarioRepositorio;
import visao.TelaCadastroEstagiario;

/**
 *
 * @author Maurício
 */
public class ControladorCadastroEstagiario {
    
    private TelaCadastroEstagiario telaCadastroEstagiario;
    private Estagiario estagiario;
    
    public ControladorCadastroEstagiario(TelaCadastroEstagiario telaCadastroEstagiario, Estagiario estagiario) {
        this.telaCadastroEstagiario = telaCadastroEstagiario;
        this.estagiario = estagiario;        
        inicializarBotoes();
    }
    
    public void inicializarBotoes(){
        
        telaCadastroEstagiario.adicionarAcaoBotaoSalvarEstagiario(e -> {
            try {
            salvarEstagiario();
        } catch (EstagiarioException ex) {
            apresentarMensagem(ex.getMessage());
        }
        });
        
        telaCadastroEstagiario.adicionarAcaoBotaoLimparTela(e -> {
            telaCadastroEstagiario.limparTela();
        });
        
        telaCadastroEstagiario.adicionarAcaoBotaoCancelarCadastro(e -> { 
            telaCadastroEstagiario.fecharJanela();
        });
        
    }
    
    public void exibirTela() {
        telaCadastroEstagiario.exibirTela();
    }
    
    public void apresentarMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
    
    
    public void salvarEstagiario() throws EstagiarioException {

        String nome = telaCadastroEstagiario.getNomeEstagario();
        if(nome.equals("")) {
            throw new EstagiarioException("O nome está em branco!!");
        }
        String CPF = telaCadastroEstagiario.getCPFEstagiario();
        String telefone = telaCadastroEstagiario.getTelefoneEstagiario();
        String faculdade = telaCadastroEstagiario.getFaculdadeEstagiario();
        String senha = telaCadastroEstagiario.getSenhaEstagiario();
        estagiario = new Estagiario(faculdade, nome, CPF, telefone, senha);
        criarEstagiario(estagiario);
        apresentarMensagem("Cadastro de estagiário realizado com sucesso!!");
        telaCadastroEstagiario.limparTela();
    }

    public void criarEstagiario(Estagiario estagiario) {
        EstagiarioRepositorio estagiarioRepostorio = new EstagiarioDAO();
        estagiarioRepostorio.salvarEstagiario(estagiario);
    }
    
    
}
