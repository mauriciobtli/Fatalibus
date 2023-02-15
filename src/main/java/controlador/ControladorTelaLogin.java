/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.AdvogadoDAO;
import dao.ClienteDAO;
import dao.EstagiarioDAO;
import dao.SecretarioDAO;
import javax.swing.JOptionPane;
import modelo.Advogado;
import modelo.Autenticavel;
import modelo.Cliente;
import modelo.Estagiario;
import modelo.Secretario;
import repositorio.AdvogadoRepositorio;
import repositorio.ClienteRepositorio;
import repositorio.EstagiarioRepositorio;
import repositorio.SecretarioRepositorio;
import visao.TelaAdvogado;
import visao.TelaEstagiario;
import visao.TelaLogin;
import visao.TelaPrincipal;

/**
 *
 * @author Maurício
 */
public class ControladorTelaLogin {
    
    private TelaLogin telaLogin;
    private static Autenticavel usuarioLogado;
    
    public ControladorTelaLogin(TelaLogin telaLogin) {
        this.telaLogin = telaLogin;
        
        inicializarBotoes();
        inicializarDados();
    }
    
    public void inicializarBotoes() {
        
        telaLogin.adicionarAcaoBotaoTelaLoginLogar(e -> {
            
            String senha = telaLogin.getLogin();
            String login = telaLogin.getSenha();

        if (realizarLogin(login, senha)) {
            JOptionPane.showMessageDialog(null, "Login realizado com sucesso");
            abrirTela();
            telaLogin.fecharJanela();
        } else {
            JOptionPane.showMessageDialog(null, "Falha no login");
        }
        });

        telaLogin.adicionarAcaoBotaoTelaLoginCancelar(e -> {
            telaLogin.fecharJanela();
        });
        
    }
    
    public static Autenticavel getUsuarioLogado() {
        return usuarioLogado;
    }
    
    public boolean realizarLogin(String login, String senha) {
        
        login = telaLogin.getLogin();
        senha = telaLogin.getSenha();

        SecretarioRepositorio secretarioRepositorio = new SecretarioDAO();
        Secretario s = secretarioRepositorio.recuperarSecretario(login);

        //Secretário encontrado
        if (s != null) {
            if (s.realizarLogin(login, senha)) {
                usuarioLogado = s;
                return true;
            }
        }

        AdvogadoRepositorio advogadoRepositorio = new AdvogadoDAO();
        Advogado a = advogadoRepositorio.recuperarAdvogado(login);
        //Advogado encontrado
        if (a != null) {
            if (a.realizarLogin(login, senha)) {
                usuarioLogado = a;
                return true;
            }
        }

        EstagiarioRepositorio estagiarioRepositorio = new EstagiarioDAO();
        Estagiario e = estagiarioRepositorio.recuperarEstagiario(login);

        //Estagiário encontrado
        if (e != null) {
            if (e.realizarLogin(login, senha)) {
                usuarioLogado = e;
                return true;
            }

        }

        return false;
    }

    
    public void abrirTela() {
        if (usuarioLogado instanceof Secretario) {
            ControladorTelaPrincipal controladorTelaPrincipal = new ControladorTelaPrincipal(new TelaPrincipal());
            controladorTelaPrincipal.exibirTela();
        }
        
        if (usuarioLogado instanceof Advogado) {
            ControladorTelaAdvogado controladorTelaAdvogado = new ControladorTelaAdvogado(new TelaAdvogado(), null);
            controladorTelaAdvogado.exibirTela();
        }
        
        if (usuarioLogado instanceof Estagiario) {
            ControladorTelaEstagiario controladorTelaEstagiario = new ControladorTelaEstagiario(new TelaEstagiario(), null);
            controladorTelaEstagiario.exibirTela();
        }
        telaLogin.fecharJanela();
    }
    
    public void exibirTela() {
        telaLogin.exibirTela();
    }
    
    public void inicializarDados() {
        
        SecretarioRepositorio secretarioRepositorio = new SecretarioDAO();
        secretarioRepositorio.salvarSecretario(new Secretario("Secretario", "admin", "(00) 0 00000000", "admin"));
        

        ClienteRepositorio clienteRepositorio = new ClienteDAO();
        clienteRepositorio.salvarCliente(new Cliente("Bruno", "08456532698", "(049)999930439"));
        clienteRepositorio.salvarCliente(new Cliente("Eduardo", "08459532909", "(047) 9 88776655"));
        
        EstagiarioRepositorio estagiarioRepositorio = new EstagiarioDAO();
        estagiarioRepositorio.salvarEstagiario(new Estagiario("UDESC", "Maurício", "33333333333", "", "123"));
        estagiarioRepositorio.salvarEstagiario(new Estagiario("UNOESC", "Eduardo", "44444444444", "", "123"));
        estagiarioRepositorio.salvarEstagiario(new Estagiario("UFSC", "Fabio", "55555555555", "", "123"));
    
        AdvogadoRepositorio advogadoRepositorio = new AdvogadoDAO();
        advogadoRepositorio.salvarAdvogado(new Advogado("12.345/SC", "Josué", "00000000000", "", "123"));
        advogadoRepositorio.salvarAdvogado(new Advogado("13.456/SC", "Maria", "11111111111", "", "123"));
        advogadoRepositorio.salvarAdvogado(new Advogado("14.789/SC", "Pedro", "22222222222", "", "123"));
    }
        
    public void abrirTelaPrincipal() {
        ControladorTelaPrincipal controladorTelaPrincipal = new ControladorTelaPrincipal(new TelaPrincipal());
        controladorTelaPrincipal.exibirTela();
    }
    
}
