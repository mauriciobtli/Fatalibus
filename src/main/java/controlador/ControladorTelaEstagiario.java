/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ProcessoExtrajudicialDAO;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ProcessoExtrajudicial;
import repositorio.ProcessoExtrajudicialRepositorio;
import visao.TelaEstagiario;
import visao.TelaLogin;

/**
 *
 * @author Maurício
 */
public class ControladorTelaEstagiario {
    
    private TelaEstagiario telaEstagiario;
    private List<ProcessoExtrajudicial> processosExtrajudiciais;
    
    
    
    public ControladorTelaEstagiario (TelaEstagiario telaEstagiario, List<ProcessoExtrajudicial> processosExtrajudiciais) {
        this.telaEstagiario = telaEstagiario;
        this.processosExtrajudiciais = processosExtrajudiciais;
        
        inicializarBotoes();
        preencherProcessosExtrajudiciais();
    }
    
    public void inicializarBotoes() {
    
        telaEstagiario.adicionarAcaoBotaoSair(e -> {
             logOff();
        });
        
    }
    
    public void abrirTelaLogin() {
       ControladorTelaLogin controladorTelaLogin = new ControladorTelaLogin(new TelaLogin());
       controladorTelaLogin.exibirTela();
    }

    public void logOff() {
        Object[] options = {"Confirmar", "Cancelar"};
        JOptionPane.showOptionDialog(null, "Deseja sair?", "LogOff", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, "Confirmar");
        abrirTelaLogin();
        telaEstagiario.fecharJanela();
    }
    
   public void preencherProcessosExtrajudiciais() {
       ProcessoExtrajudicialRepositorio processoExtrajudicialRepositorio = new ProcessoExtrajudicialDAO();
        processosExtrajudiciais = processoExtrajudicialRepositorio.recuperarTodosProcessosExtrajudiciais();
        telaEstagiario.preencherProcessosExtrajudiciais(processosExtrajudiciais);
   }
   
   public void exibirTela() {
       telaEstagiario.exibirTela();
   }
    
}
    

