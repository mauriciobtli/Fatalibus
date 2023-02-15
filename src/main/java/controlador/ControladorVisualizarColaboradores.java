/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.AdvogadoDAO;
import dao.EstagiarioDAO;
import java.util.List;
import java.util.Map;
import modelo.Advogado;
import modelo.Estagiario;
import repositorio.AdvogadoRepositorio;
import repositorio.EstagiarioRepositorio;
import visao.TelaVisualizarColaboradores;

/**
 *
 * @author Maurício
 */
public class ControladorVisualizarColaboradores {
    
    private TelaVisualizarColaboradores telaVisualizarColaboradores;
    private Map<String, Advogado> advogados;
    private List<Estagiario> estagiarios;
    
    public ControladorVisualizarColaboradores(TelaVisualizarColaboradores telaVisualizarColaboradores, Map<String, Advogado> advogados, List<Estagiario> estagiarios) {
        this.telaVisualizarColaboradores = telaVisualizarColaboradores;
        this.advogados = advogados;
        this.estagiarios = estagiarios;
        
        inicializarBotoes();
        preencherAdvogados();
        preencherEstagiarios();
    }
    
    public void inicializarBotoes() {
        
        telaVisualizarColaboradores.adicionarAcaoBotaoSair(e -> {
            telaVisualizarColaboradores.fecharJanela();
        });
        
    }
    
    public void preencherAdvogados() {
        AdvogadoRepositorio advogadoRepositorio = new AdvogadoDAO();
        advogados = advogadoRepositorio.recuperarTodosAdvogados();
        telaVisualizarColaboradores.popularAdvogados(advogados);
    }
    
    public void preencherEstagiarios() {
        EstagiarioRepositorio estagiarioRepositorio = new EstagiarioDAO();
        estagiarios = estagiarioRepositorio.recuperarTodosEstagiarios();
        telaVisualizarColaboradores.popularEstagiarios(estagiarios);
    }
    
    public void exibirTela() {
        telaVisualizarColaboradores.exibirTela();
    }
    
    
}
