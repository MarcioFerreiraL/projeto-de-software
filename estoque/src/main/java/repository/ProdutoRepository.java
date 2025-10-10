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
import model.Produto;

public class ProdutoRepository implements Repository{
	// Constante que define o nome do arquivo de dados.
    private static final String NOME_ARQUIVO = "usuario.json";
    // Define o caminho para a pasta 'data'
    private static final Path PASTA_DE_DADOS = Path.of("data");
    // Combina a pasta de dados com o nome do arquivo para obter o caminho completo.
    private static final Path CAMINHO_ARQUIVO_DE_DADOS = PASTA_DE_DADOS.resolve(NOME_ARQUIVO);
    // Instância da biblioteca Gson, usada para converter objetos Java para JSON e vice-versa.
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    

    
	public boolean registrar(Produto produto) throws IOException {
			try {
				inicializadorArquivo();
				ArrayList<Produto> produtos = ler(); // pega todos os dados
				produtos.add(produto); // adiciona novo registro aos dados
				salvarLista(produtos); // salva no arquivo de dados
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}

	}

	// retorna todos os dados de um arquivo
	public ArrayList<Produto> ler() throws IOException {
		inicializadorArquivo();
		try (FileReader leitor = new FileReader(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            Type tipoLista = new TypeToken<ArrayList<Produto>>() {}.getType();
            ArrayList<Produto> produtos = GSON.fromJson(leitor, tipoLista);
            return produtos;
		} 
	}

	
	public void atualizar(Produto produtoNovo) throws IOException {
		ArrayList<Produto> produto = ler();
			for (int i = 0; i < produto.size(); i++)
				if (produto.get(i).getId() == (produtoNovo.getId())) {
					produto.set(i, produtoNovo);
					salvarLista(produto);
					System.out.println("Produto atualizado com sucesso!");
				} else {
					System.out.println("Produto não atualizado");
				}
		}
	

	public void excluir(Produto produtoExcluir) throws IOException {
		ArrayList<Produto> produto = ler();
		for (int i = 0; i < produto.size(); i++)
			if (produto.get(i).getId() == (produtoExcluir.getId())) {
				produto.remove(i);
				salvarLista(produto);
				System.out.println("Produto removido com sucesso!");
			} else {
				System.out.println("Produto não removido");
			}
	}

	// salva o arquivo no sistema
	private void salvarLista(ArrayList<Produto> produtos) throws IOException {
		inicializadorArquivo();
		try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
			GSON.toJson(produtos, writer);
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
