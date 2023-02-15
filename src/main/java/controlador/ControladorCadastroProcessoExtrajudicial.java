/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ClienteDAO;
import dao.ProcessoExtrajudicialDAO;
import exception.ProcessoException;
import static java.awt.image.ImageObserver.WIDTH;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ProcessoExtrajudicial;
import repositorio.ClienteRepositorio;
import repositorio.ProcessoExtrajudicialRepositorio;
import visao.TelaCadastroProcessoExtrajudicial;

/**
 *
 * @author Maurício
 */
public class ControladorCadastroProcessoExtrajudicial {

    private TelaCadastroProcessoExtrajudicial telaCadastroProcessoExtrajudicial;
    private ProcessoExtrajudicial processoExtrajudicial;

    public ControladorCadastroProcessoExtrajudicial(TelaCadastroProcessoExtrajudicial telaCadastroProcessoExtrajudicial, ProcessoExtrajudicial processoExtrajudicial) {
        this.telaCadastroProcessoExtrajudicial = telaCadastroProcessoExtrajudicial;
        this.processoExtrajudicial = processoExtrajudicial;
        inicializarBotoes();
        inicializarDadosTela();

    }

    public void inicializarBotoes() {

        telaCadastroProcessoExtrajudicial.adicionarAcaoBotaoSalvarProcessoExtrajudicial(e -> {
            try {
                salvarProcessoExtrajudicial();
            } catch (ProcessoException ex) {
                apresentarMensagem(ex.getMessage());
            }
        });
     
        telaCadastroProcessoExtrajudicial.adicionarAcaoBotaoLimparTela(e -> {
            telaCadastroProcessoExtrajudicial.limparTela();
        });
        
        telaCadastroProcessoExtrajudicial.adicionarAcaoBotaoCancelarCadastro(e -> { 
            telaCadastroProcessoExtrajudicial.fecharJanela();
        });
        
    }

    public void inicializarDadosTela() {
        ClienteRepositorio clienteRepositorio = new ClienteDAO();
        Set<Cliente> clientes = clienteRepositorio.recuperarTodosClientes();
        telaCadastroProcessoExtrajudicial.popularClientes(clientes);
        }
    

    public void exibirTela() {
        telaCadastroProcessoExtrajudicial.exibirTela();
    }

    public void apresentarMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public ProcessoExtrajudicial recuperarProcessoExtrajudicial() throws ProcessoException {

        String nome = telaCadastroProcessoExtrajudicial.getNomeProcesso();
        if(nome.equals("")) {
            throw new ProcessoException("Nome do processo está em branco!!");
        }
        Cliente cliente = (Cliente) telaCadastroProcessoExtrajudicial.getClienteSelecionado();
        if (cliente == null) {
            throw new ProcessoException("Nenhum cliente selecionado!!");
        }
        LocalDate dataPrazo = getData();
        double preco;
        try {
        preco = Double.parseDouble(telaCadastroProcessoExtrajudicial.getPreco());
        }catch (NumberFormatException ex) {
                       throw new ProcessoException("Preencha o preço");
        }
        String descricao = telaCadastroProcessoExtrajudicial.getDescricao();
        processoExtrajudicial = ProcessoExtrajudicial.criarProcessoExtrajudicial(nome, WIDTH, cliente, descricao, dataPrazo, preco);
        return processoExtrajudicial;
        
    }

    public void salvarProcessoExtrajudicial() throws ProcessoException {

        try {
            processoExtrajudicial = recuperarProcessoExtrajudicial();
            ProcessoExtrajudicialRepositorio processoExtrajudicialRepositorio = new ProcessoExtrajudicialDAO();
            processoExtrajudicialRepositorio.salvarProcessoExtrajudicial(processoExtrajudicial);
            apresentarMensagem("Processo Extrajudicial criado com sucesso!!");
            telaCadastroProcessoExtrajudicial.limparTela();
        } catch (ProcessoException ex) {
            apresentarMensagem(ex.getMessage());
        }
    }

    public LocalDate getData() throws ProcessoException {
        String dataPrazo = telaCadastroProcessoExtrajudicial.getPrazo();
        try {
            DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataPrazoAgendamento = LocalDate.parse(dataPrazo, formatoData);
            return dataPrazoAgendamento;
        } catch (DateTimeParseException e) {
            throw new ProcessoException("Data inválida");
        }
    }

}
