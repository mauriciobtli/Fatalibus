/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ProcessoExtrajudicialDAO;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import modelo.ProcessoExtrajudicial;
import repositorio.ProcessoExtrajudicialRepositorio;
import visao.TelaVisualizarProcessosExtraudicais;

/**
 *
 * @author Maurício
 */
public class ControladorVisualizarProcessosExtrajudiciais {
    
    private TelaVisualizarProcessosExtraudicais telaVisualizarProcessosExtrajudiciais;
    private List<ProcessoExtrajudicial> processosExtrajudiciais;
    
    public ControladorVisualizarProcessosExtrajudiciais (TelaVisualizarProcessosExtraudicais telaVisualizarProcessosExtrajudiciais, List<ProcessoExtrajudicial> processosExtrajudiciais) {
        this.telaVisualizarProcessosExtrajudiciais = telaVisualizarProcessosExtrajudiciais;
        this.processosExtrajudiciais = this.processosExtrajudiciais;
        
        inicializarBotoes();
        preencherProcessosExtrajudiciais();
    }
    
    public void inicializarBotoes() {
        
        telaVisualizarProcessosExtrajudiciais.adicionarAcaoBotaoCrescente(e -> {
            ordenarProcessosExtrajudiciaisPrazoCrescente();        
        });
        
        telaVisualizarProcessosExtrajudiciais.adicionarAcaoBotaoDecrescente(e -> {
            ordenarProcessosExtrajudiciaisPrazoDecrescente();
        });
        
        
        telaVisualizarProcessosExtrajudiciais.adicionarAcaoBotaoSair(e -> {
            telaVisualizarProcessosExtrajudiciais.fecharJanela();
        });
        
    }
    
    public void preencherProcessosExtrajudiciais() {
        ProcessoExtrajudicialRepositorio processoExtrajudicialRepositorio = new ProcessoExtrajudicialDAO();
        processosExtrajudiciais = processoExtrajudicialRepositorio.recuperarTodosProcessosExtrajudiciais();
        telaVisualizarProcessosExtrajudiciais.preencherProcessosExtrajudiciais(processosExtrajudiciais);
    }
        
    public List<ProcessoExtrajudicial> getProcessos() {
        ProcessoExtrajudicialRepositorio processoExtrajudicialRepositorio = new ProcessoExtrajudicialDAO();
        List<ProcessoExtrajudicial> processosExtrajudiciais = processoExtrajudicialRepositorio.recuperarTodosProcessosExtrajudiciais();
        return processosExtrajudiciais;
    }

    public void ordenarProcessosExtrajudiciaisPrazoCrescente() {
        List<ProcessoExtrajudicial> processosExtrajudiciais = getProcessos();
        Collections.sort(processosExtrajudiciais);
        
        telaVisualizarProcessosExtrajudiciais.preencherProcessosExtrajudiciais(processosExtrajudiciais);
    }

    public void ordenarProcessosExtrajudiciaisPrazoDecrescente() {
        List<ProcessoExtrajudicial> processosExtrajudiciais = getProcessos();
        Collections.sort(processosExtrajudiciais, new Comparator<ProcessoExtrajudicial>() {
            @Override
            public int compare(ProcessoExtrajudicial pe1, ProcessoExtrajudicial pe2) {
                if (pe1.getPrazo().isAfter(pe2.getPrazo())) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        telaVisualizarProcessosExtrajudiciais.preencherProcessosExtrajudiciais(processosExtrajudiciais);
    }
    
    public void exibirTela() {
        telaVisualizarProcessosExtrajudiciais.exibirTela();
    }
  
}
