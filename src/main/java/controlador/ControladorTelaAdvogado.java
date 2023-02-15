/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ProcessoJudicialDAO;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ProcessoJudicial;
import repositorio.ProcessoJudicialRepositorio;
import visao.TelaAdvogado;
import visao.TelaLogin;

/**
 *
 * @author Maurício
 */
public class ControladorTelaAdvogado {
    
    private TelaAdvogado telaAdvogado;
    private List<ProcessoJudicial> processosJudiciais;
    
    
    
    public ControladorTelaAdvogado (TelaAdvogado telaAdvogado, List<ProcessoJudicial> processosJudicials) {
        this.telaAdvogado = telaAdvogado;
        this.processosJudiciais = processosJudiciais;
        
        inicializarBotoes();
        preencherProcessosJudiciais();
    }
    
    public void inicializarBotoes() {
    
        telaAdvogado.adicionarAcaoBotaoSair(e -> {
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
        telaAdvogado.fecharJanela();
    }
    
   public void preencherProcessosJudiciais() {
       ProcessoJudicialRepositorio processoJudicialRepositorio = new ProcessoJudicialDAO();
        processosJudiciais = processoJudicialRepositorio.recuperarTodosProcessosJudiciais();
        telaAdvogado.preencherProcessosJudiciais(processosJudiciais);
   }
   
   public void exibirTela() {
       telaAdvogado.exibirTela();
   }
    
}
