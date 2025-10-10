package repository;

import java.io.IOException;
import java.util.ArrayList;
import model.Entidade;

public interface Repository{
	public void inicializadorArquivo()  throws IOException;
	public ArrayList<?> ler()  throws IOException;
	
}
