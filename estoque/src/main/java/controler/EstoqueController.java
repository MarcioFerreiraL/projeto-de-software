package controler;

import view.EstoqueView;

public class EstoqueController {
	private EstoqueView view;
	
	public void iniciar() {
		do {
			String[] login = view.exibirTelaLogin();
			// validarLogin(login);
		} while (true);
	}
}
