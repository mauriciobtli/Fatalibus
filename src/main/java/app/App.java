package app;

import controlador.ControladorTelaLogin;
import controlador.ControladorTelaPrincipal;
import visao.TelaLogin;
import visao.TelaPrincipal;

/**
 *
 * @author Maurício
 */
public class App {

    public static void main(String[] args) {

    //    TelaLogin tela = new TelaLogin();
       // tela.setVisible(true);
       
  //     ControladorTelaPrincipal controladorTelaPrincipal = new ControladorTelaPrincipal(new TelaPrincipal());
  //     controladorTelaPrincipal.exibirTela();
       
       ControladorTelaLogin controladorTelaLogin = new ControladorTelaLogin(new TelaLogin());
       controladorTelaLogin.exibirTela();

    }

}
