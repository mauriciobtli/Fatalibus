/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JOptionPane;
import visao.TelaCadastroAdvogado;
import visao.TelaCadastroCliente;
import visao.TelaCadastroEstagiario;
import visao.TelaCadastroPrazo;
import visao.TelaCadastroProcessoExtrajudicial;
import visao.TelaCadastroProcessoJudicial;
import visao.TelaLogin;
import visao.TelaPrincipal;
import visao.TelaVisualizarClientes;
import visao.TelaVisualizarColaboradores;
import visao.TelaVisualizarProcessosExtraudicais;
import visao.TelaVisualizarProcessosJudiciais;

/**
 *
 * @author Maurício
 */
public class ControladorTelaPrincipal {

    private TelaPrincipal telaPrincipal;

    public ControladorTelaPrincipal(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
        inicializarBotoes();
    }

    public void inicializarBotoes() {

        telaPrincipal.adicionarAcaoBotaoTelaCadastroAdvogado(e -> {
            abrirTelaCadastroAdvogado();
        });

        telaPrincipal.adicionarAcaoBotaoTelaCadastroEstagiario(e -> {
            abrirTelaCadastroEstagiario();
        });

        telaPrincipal.adicionarAcaoBotaoTelaCadastroCliente(e -> {
            abrirTelaCadastroCliente();
        });

        telaPrincipal.adicionarAcaoBotaoTelaCadastroPrazo(e -> {
            abrirTelaCadastroPrazo();
        });

        telaPrincipal.adicionarAcaoBotaoTelaCadastroProcessoExtrajudicial(e -> {
            abrirTelaCadastroProcessoExtrajudicial();
        });

        telaPrincipal.adicionarAcaoBotaoTelaCadastroProcessoJudicial(e -> {
            abrirTelaCadastroProcessoJudicial();
        });
        
        telaPrincipal.adicionarAcaoBotaoTelaVisualizarClientes(e -> {
            abrirTelaVisualizarClientes();
        });
        
        telaPrincipal.adicionarAcaoBotaoTelaVisualizarColaboradores(e -> {
            abrirTelaVisualizarColaboradores();
        });
        
        telaPrincipal.adicionarAcaoBotaoTelaVisualizarProcessosExtrajudiciais(e -> {
            abrirTelaVisualizarProcessosExtrajudiciais();
        });
        
        telaPrincipal.adicionarAcaoBotaoTelaVisualizarProcessosJudiciais(e -> {
            abrirTelaVisualizarProcessosJudiciais();
        });

        telaPrincipal.adicionarAcaoBotaoSair(e -> {
            logOff();
        });

    }

    public void abrirTelaCadastroAdvogado() {
        ControladorCadastroAdvogado controladorCadastroAdvogado = new ControladorCadastroAdvogado(new TelaCadastroAdvogado(), null);
        controladorCadastroAdvogado.exibirTela();
    }

    public void abrirTelaCadastroEstagiario() {
        ControladorCadastroEstagiario controladorCadastroEstagiario = new ControladorCadastroEstagiario(new TelaCadastroEstagiario(), null);
        controladorCadastroEstagiario.exibirTela();
    }

    public void abrirTelaCadastroCliente() {
        ControladorCadastroCliente controladorCadastroCliente = new ControladorCadastroCliente(new TelaCadastroCliente(), null);
        controladorCadastroCliente.exibirTela();
    }

    public void abrirTelaCadastroProcessoJudicial() {
        ControladorCadastroProcessoJudicial controladorCadastroProcessoJudicial = new ControladorCadastroProcessoJudicial(new TelaCadastroProcessoJudicial(), null);
        controladorCadastroProcessoJudicial.exibirTela();
    }

    public void abrirTelaCadastroProcessoExtrajudicial() {
       ControladorCadastroProcessoExtrajudicial controladorCadastroProcessoExtrajudicial = new ControladorCadastroProcessoExtrajudicial(new TelaCadastroProcessoExtrajudicial(), null);
       controladorCadastroProcessoExtrajudicial.exibirTela();
    }

    public void abrirTelaCadastroPrazo() {
       ControladorCadastroPrazo controladorCadastroPrazo = new ControladorCadastroPrazo(new TelaCadastroPrazo());
       controladorCadastroPrazo.exbirTela();
    }

    public void abrirTelaVisualizarProcessosJudiciais() {
        ControladorVisualizarProcessosJudiciais controladorVisualizarProcessosJudiciais = new ControladorVisualizarProcessosJudiciais(new TelaVisualizarProcessosJudiciais(), null);
        controladorVisualizarProcessosJudiciais.exibirTela();
    }

    public void abrirTelaVisualizarProcessosExtrajudiciais() {
        ControladorVisualizarProcessosExtrajudiciais controladorVisualizarProcessosExtrajudiciais = new ControladorVisualizarProcessosExtrajudiciais(new TelaVisualizarProcessosExtraudicais(), null);
        controladorVisualizarProcessosExtrajudiciais.exibirTela();
    }

    public void abrirTelaVisualizarColaboradores() {
        ControladorVisualizarColaboradores controladorVisualizarColaboradores = new ControladorVisualizarColaboradores(new TelaVisualizarColaboradores(), null, null);
        controladorVisualizarColaboradores.exibirTela();
    }

    public void abrirTelaVisualizarClientes() {
        ControladorVisualizarClientes controladorVisualizarClientes = new ControladorVisualizarClientes(new TelaVisualizarClientes(), null);
        controladorVisualizarClientes.exbirTela();
    }

    public void exibirTela() {
        telaPrincipal.exibirTela();
    }
    
    public void abrirTelaLogin() {
       ControladorTelaLogin controladorTelaLogin = new ControladorTelaLogin(new TelaLogin());
       controladorTelaLogin.exibirTela();
    }

    public void logOff() {
        Object[] options = {"Confirmar", "Cancelar"};
        JOptionPane.showOptionDialog(null, "Deseja sair?", "LogOff", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, "Confirmar");
        abrirTelaLogin();
        telaPrincipal.dispose();
    }

}
