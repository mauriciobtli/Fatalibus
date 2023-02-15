/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import dao.ProcessoJudicialDAO;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import modelo.ProcessoJudicial;
import repositorio.ProcessoJudicialRepositorio;
import visao.TelaVisualizarProcessosJudiciais;

/**
 *
 * @author Maurício
 */
public class ControladorVisualizarProcessosJudiciais {
    
    private TelaVisualizarProcessosJudiciais telaVisualizarProcessosJudiciais;
    private List<ProcessoJudicial> processosJudiciais;
    
    public ControladorVisualizarProcessosJudiciais (TelaVisualizarProcessosJudiciais telaVisualizarProcessosJudiciais, List<ProcessoJudicial> processosJudiciais) {
        this.telaVisualizarProcessosJudiciais = telaVisualizarProcessosJudiciais;
        this.processosJudiciais = this.processosJudiciais;
        
        inicializarBotoes();
        preencherProcessosJudiciais();
    }
    
    public void inicializarBotoes() {
        
        telaVisualizarProcessosJudiciais.adicionarAcaoBotaoCrescente(e -> {
            ordenarProcessosJudiciaisPrazoCrescente();        
        });
        
        telaVisualizarProcessosJudiciais.adicionarAcaoBotaoDecrescente(e -> {
            ordenarProcessosJudiciaisPrazoDecrescente();
        });
        
        
        telaVisualizarProcessosJudiciais.adicionarAcaoBotaoSair(e -> {
            telaVisualizarProcessosJudiciais.fecharJanela();
        });
        
    }
    
    public void preencherProcessosJudiciais() {
        ProcessoJudicialRepositorio processoJudicialRepositorio = new ProcessoJudicialDAO();
        processosJudiciais = processoJudicialRepositorio.recuperarTodosProcessosJudiciais();
        telaVisualizarProcessosJudiciais.preencherProcessosJudiciais(processosJudiciais);
    }
        
    public List<ProcessoJudicial> getProcessos() {
        ProcessoJudicialRepositorio processoJudicialRepositorio = new ProcessoJudicialDAO();
        List<ProcessoJudicial> processosJudiciais = processoJudicialRepositorio.recuperarTodosProcessosJudiciais();
        return processosJudiciais;
    }

    public void ordenarProcessosJudiciaisPrazoCrescente() {
        List<ProcessoJudicial> processosJudiciais = getProcessos();
        Collections.sort(processosJudiciais);
        
        telaVisualizarProcessosJudiciais.preencherProcessosJudiciais(processosJudiciais);
    }

    public void ordenarProcessosJudiciaisPrazoDecrescente() {
        List<ProcessoJudicial> processosJudiciais = getProcessos();
        Collections.sort(processosJudiciais, new Comparator<ProcessoJudicial>() {
            @Override
            public int compare(ProcessoJudicial pj1, ProcessoJudicial pj2) {
                if (pj1.getPrazo().isAfter(pj2.getPrazo())) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        telaVisualizarProcessosJudiciais.preencherProcessosJudiciais(processosJudiciais);
    }
    
    public void exibirTela() {
        telaVisualizarProcessosJudiciais.exibirTela();
    }
    
}
