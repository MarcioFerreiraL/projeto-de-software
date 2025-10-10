package repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Usuario;

public class UsuarioRepository implements Repository{
	// Constante que define o nome do arquivo de dados.
    private static final String NOME_ARQUIVO = "usuario.json";
    // Define o caminho para a pasta 'data'
    private static final Path PASTA_DE_DADOS = Path.of("data");
    // Combina a pasta de dados com o nome do arquivo para obter o caminho completo.
    private static final Path CAMINHO_ARQUIVO_DE_DADOS = PASTA_DE_DADOS.resolve(NOME_ARQUIVO);
    // Instância da biblioteca Gson, usada para converter objetos Java para JSON e vice-versa.
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    

    
	public boolean registrar(Usuario usuario) throws IOException {
			try {
				inicializadorArquivo();
				ArrayList<Usuario> usuarios = ler(); // pega todos os dados
				usuarios.add(usuario); // adiciona novo registro aos dados
				salvarLista(usuarios); // salva no arquivo de dados
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}

	}

	// retorna todos os dados de um arquivo
	public ArrayList<Usuario> ler() throws IOException {
		inicializadorArquivo();
		try (FileReader leitor = new FileReader(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            Type tipoLista = new TypeToken<ArrayList<Usuario>>() {}.getType();
            ArrayList<Usuario> usuarios = GSON.fromJson(leitor, tipoLista);
            return usuarios;
		} 
	}

	
	public void atualizar(Usuario usuarioNovo) throws IOException {
		ArrayList<Usuario> usuario = ler();
			for (int i = 0; i < usuario.size(); i++)
				if (usuario.get(i).getId() == (usuarioNovo.getId())) {
					usuario.set(i, usuarioNovo);
					salvarLista(usuario);
					System.out.println("Usuario atualizado com sucesso!");
				} else {
					System.out.println("Usuario não atualizado");
				}
		}
	

	public void excluir(Usuario usuarioExluir) throws IOException {
		ArrayList<Usuario> usuario = ler();
		for (int i = 0; i < usuario.size(); i++)
			if (usuario.get(i).getId() == (usuarioExluir.getId())) {
				usuario.remove(i);
				salvarLista(usuario);
				System.out.println("Usuario removido com sucesso!");
			} else {
				System.out.println("Usuario não removido");
			}
	}

	// salva o arquivo no sistema
	private void salvarLista(ArrayList<Usuario> usuarios) throws IOException {
		inicializadorArquivo();
		try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
			GSON.toJson(usuarios, writer);
		}
	}
	
    // verifica se a pasta e arquivo existe
    public void inicializadorArquivo() throws IOException{
    	
    	if(!Files.exists(CAMINHO_ARQUIVO_DE_DADOS)) {
    		try(FileWriter writer = new FileWriter(CAMINHO_ARQUIVO_DE_DADOS.toFile())){
    			writer.write("[]");
    			}
    		} 
    	
    	if(!Files.exists(PASTA_DE_DADOS)) {
    		Files.createFile(PASTA_DE_DADOS);
    	}
    	
    }
}
