/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ProcessoExtrajudicialDAO;
import dao.ProcessoJudicialDAO;
import exception.ProcessoException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Processo;
import modelo.ProcessoExtrajudicial;
import modelo.ProcessoJudicial;
import repositorio.ProcessoExtrajudicialRepositorio;
import repositorio.ProcessoJudicialRepositorio;
import visao.TelaCadastroPrazo;

/**
 *
 * @author Maurício
 */
public class ControladorCadastroPrazo {
    
    private TelaCadastroPrazo telaCadastroPrazo;
    
    
    public ControladorCadastroPrazo(TelaCadastroPrazo telaCadastroPrazo) {
        this.telaCadastroPrazo = telaCadastroPrazo;
        
        inicializarDadosTela();
        inicializarBotoes();
    }
    
    public void inicializarDadosTela(){
        //Popular dados Processos Judiciais
        ProcessoJudicialRepositorio processoJudicialRepositorio = new ProcessoJudicialDAO();
        List<ProcessoJudicial> processosJudiciais = processoJudicialRepositorio.recuperarTodosProcessosJudiciais();
        telaCadastroPrazo.popularProcessosJudiciais(processosJudiciais);
        
        //Popular dados Processos Extrajudiciais
        ProcessoExtrajudicialRepositorio processoExtrajudicialRepositorio = new ProcessoExtrajudicialDAO();
        List<ProcessoExtrajudicial> processosExtrajudiciais = processoExtrajudicialRepositorio.recuperarTodosProcessosExtrajudiciais();
        telaCadastroPrazo.popularProcessosExtrajudiciais(processosExtrajudiciais);
    }
    
    
    public void inicializarBotoes(){
        
        telaCadastroPrazo.adicionarAcaoBotaoSalvarPrazo(e -> {
            try {
            salvarNovoPrazo();
        } catch (ProcessoException ex) {
            apresentarMensagem(ex.getMessage());
        }
        });
        
        telaCadastroPrazo.adicionarAcaoBotaoRemoverPrazo(e -> {
            removerPrazoConfirmacao();
        try {
            removerPrazo();
        } catch (ProcessoException ex) {
            apresentarMensagem(ex.getMessage());
        }

        });
        
        telaCadastroPrazo.adicionarAcaoBotaoLimparTela(e -> {
            telaCadastroPrazo.limparTela();
        });
        
        telaCadastroPrazo.adicionarAcaoBotaoCancelarCadastro(e -> { 
            telaCadastroPrazo.fecharJanela();
        });
    }
    
    public void exbirTela(){
        telaCadastroPrazo.exibirTela();
    }

    public void salvarNovoPrazo() throws ProcessoException {

        try {
            Processo processo = telaCadastroPrazo.getProcessoSelecionado();
            processo.setPrazo(recuperarPrazo());
            apresentarMensagem("Novo prazo caadastrado com sucesso!!");
            telaCadastroPrazo.limparTela();
        } catch (ProcessoException ex) {
            apresentarMensagem(ex.getMessage());
        }
    }

    public void removerPrazo() throws ProcessoException {

        Processo processo = telaCadastroPrazo.getProcessoSelecionado();
        processo.setPrazo(null);
        apresentarMensagem("Prazo removido com sucesso!!");
        telaCadastroPrazo.limparTela();
    }
    
    public LocalDate recuperarPrazo() throws ProcessoException {
        LocalDate novoPrazo = getData();
        return novoPrazo;
    }

    public LocalDate getData() throws ProcessoException {
        String novaData = telaCadastroPrazo.getNovoPrazo();
        try {
            DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataPrazoAgendamento = LocalDate.parse(novaData, formatoData);
            return dataPrazoAgendamento;
        } catch (DateTimeParseException e) {
            throw new ProcessoException("Data inválida!");
        }
    }
    
    public void removerPrazoConfirmacao() {
        Object[] options = {"Confirmar", "Cancelar"};
        JOptionPane.showOptionDialog(null, "Deseja remover o prazo?", "Remover prazo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, "Confirmar");
    }
    
    public void apresentarMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
    
}
