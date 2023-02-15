/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.AdvogadoDAO;
import exception.AdvogadoException;
import javax.swing.JOptionPane;
import modelo.Advogado;
import repositorio.AdvogadoRepositorio;
import visao.TelaCadastroAdvogado;

/**
 *
 * @author Maurício
 */
public class ControladorCadastroAdvogado {

    private TelaCadastroAdvogado telaCadastrarAdvogado;
    private Advogado advogado;

    public ControladorCadastroAdvogado(TelaCadastroAdvogado telaCadastrarAdvogado, Advogado advogado) {
        this.telaCadastrarAdvogado = telaCadastrarAdvogado;
        this.advogado = advogado;
        inicializarBotoes();
    }

    public void inicializarBotoes() {

        telaCadastrarAdvogado.adicionarAcaoBotaoSalvarAdvogado(e -> {
            try {
                cadastrarAdvogado();
            } catch (AdvogadoException ex) {
                apresentarMensagem(ex.getMessage());
            }
        });
        
        telaCadastrarAdvogado.adicionarAcaoBotaoLimparTela(e -> {
            telaCadastrarAdvogado.limparTela();
        });
        
        telaCadastrarAdvogado.adicionarAcaoBotaoCancelarCadastro(e -> { 
            telaCadastrarAdvogado.fecharJanela();
        });

    }

    public void exibirTela() {
        telaCadastrarAdvogado.exibirTela();
    }

    public void apresentarMensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public void cadastrarAdvogado() throws AdvogadoException {

        String nome = telaCadastrarAdvogado.getNomeAdvogado();
        if (nome.equals("")) {
            throw new AdvogadoException("Nome está em branco!!");
        }
            String CPF = telaCadastrarAdvogado.getCPFAdvogado();
            String telefone = telaCadastrarAdvogado.getTelefoneAdvogado();
            String OAB = telaCadastrarAdvogado.getOABAdvogado();
            String senha = telaCadastrarAdvogado.getSenhaAdvogado();
            advogado = new Advogado(OAB, nome, CPF, telefone, senha);
            criarAdvogado(advogado);
            apresentarMensagem("Cadastro de Advogado realizado com sucesso!!");
            telaCadastrarAdvogado.limparTela();
        }
    

    public void criarAdvogado(Advogado advogado) {
        AdvogadoRepositorio advogadoRepositorio = new AdvogadoDAO();
        advogadoRepositorio.salvarAdvogado(advogado);
    }

}


