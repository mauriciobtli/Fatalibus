/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ClienteDAO;
import dao.ProcessoJudicialDAO;
import exception.ProcessoException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ProcessoJudicial;
import repositorio.ClienteRepositorio;
import repositorio.ProcessoJudicialRepositorio;
import visao.TelaCadastroProcessoJudicial;

/**
 *
 * @author Maurício
 */
public class ControladorCadastroProcessoJudicial {
    
    private TelaCadastroProcessoJudicial telaCadastroProcessoJudicial;
    private ProcessoJudicial processoJudicial;
    
    public ControladorCadastroProcessoJudicial (TelaCadastroProcessoJudicial telaCadastroProcessoJudicial, ProcessoJudicial processoJudicial) {
        this.telaCadastroProcessoJudicial = telaCadastroProcessoJudicial;
        this.processoJudicial = processoJudicial;
        inicializarBotoes();
        inicializarDadosTela();
    }
    
    public void inicializarDadosTela() {
        ClienteRepositorio clienteRepositorio = new ClienteDAO();
        Set<Cliente> clientes = clienteRepositorio.recuperarTodosClientes();
        telaCadastroProcessoJudicial.popularClientes(clientes);
        }
    
    public void inicializarBotoes() {
        
        telaCadastroProcessoJudicial.adicionarAcaoBotaoSalvarProcessoJudicial(e -> {
            try {
            salvarProcessoJudicial();
        } catch (ProcessoException ex) {
            apresentarMensagem(ex.getMessage());
        }
            
        });
        
        telaCadastroProcessoJudicial.adicionarAcaoBotaoLimparTela(e -> {
            telaCadastroProcessoJudicial.limparTela();
    });
        
        telaCadastroProcessoJudicial.adicionarAcaoBotaoCancelarCadastro(e -> {
            telaCadastroProcessoJudicial.fecharJanela();
        });
        
    }
    
    public ProcessoJudicial recuperarProcessoJudicial() throws ProcessoException {

        String numero = telaCadastroProcessoJudicial.getNumero();
        if (numero.equals("")) {
            throw new ProcessoException("Número do processo está em branco!!");
        }
        String tipo = telaCadastroProcessoJudicial.getTipo();
        Cliente cliente = (Cliente) telaCadastroProcessoJudicial.getClienteSelecionado();
        if (cliente == null) {
            throw new ProcessoException("Nenhum cliente selecionado!!");
        }
        LocalDate dataPrazo = getData();
        double preco;
        try {
        preco = Double.parseDouble(telaCadastroProcessoJudicial.getPreco());
        }catch (NumberFormatException ex) {
                       throw new ProcessoException("Preencha o preço!");
        } 
        String descricao = telaCadastroProcessoJudicial.getDescricao();
        processoJudicial = ProcessoJudicial.criarProcessoJudicial(numero, tipo, cliente, descricao, dataPrazo, preco);
        return processoJudicial;

    }
    
    public void salvarProcessoJudicial() throws ProcessoException {
        
        try {
            processoJudicial = recuperarProcessoJudicial();
            ProcessoJudicialRepositorio processoJudicialRepositorio = new ProcessoJudicialDAO();
            processoJudicialRepositorio.salvarProcessoJudicial(processoJudicial);
            apresentarMensagem("Processo Judicial criado com sucesso!!");
            telaCadastroProcessoJudicial.limparTela();
        }
        catch (ProcessoException ex) {
            apresentarMensagem(ex.getMessage());
        }
        
    }

    public LocalDate getData() throws ProcessoException {
        String dataPrazo = telaCadastroProcessoJudicial.getPrazo();
        try {
            DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataPrazoAgendamento = LocalDate.parse(dataPrazo, formatoData);
            return dataPrazoAgendamento;
        } catch (DateTimeParseException e) {
            throw new ProcessoException("Data inválida");
        }
    }
    
    public void exibirTela() {
        telaCadastroProcessoJudicial.exibirTela();
    }

    public void apresentarMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
    
}
