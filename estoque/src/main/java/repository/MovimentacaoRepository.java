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
import model.Movimentacao;

public class MovimentacaoRepository implements Repository{
	// Constante que define o nome do arquivo de dados.
    private static final String NOME_ARQUIVO = "movimentacao.json";
    // Define o caminho para a pasta 'data'
    private static final Path PASTA_DE_DADOS = Path.of("data");
    // Combina a pasta de dados com o nome do arquivo para obter o caminho completo.
    private static final Path CAMINHO_ARQUIVO_DE_DADOS = PASTA_DE_DADOS.resolve(NOME_ARQUIVO);
    // Instância da biblioteca Gson, usada para converter objetos Java para JSON e vice-versa.
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    

    
	public boolean registrar(Movimentacao movimentacao) throws IOException {
			try {
				inicializadorArquivo();
				ArrayList<Movimentacao> movimentacoes = ler(); // pega todos os dados
				movimentacoes.add(movimentacao); // adiciona novo registro aos dados
				salvarLista(movimentacoes); // salva no arquivo de dados
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}

	}

	// retorna todos os dados de um arquivo
	public ArrayList<Movimentacao> ler() throws IOException {
		inicializadorArquivo();
		try (FileReader leitor = new FileReader(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            Type tipoLista = new TypeToken<ArrayList<Movimentacao>>() {}.getType();
            ArrayList<Movimentacao> movimentacoes = GSON.fromJson(leitor, tipoLista);
            return movimentacoes;
		} 
	}

	
	public void atualizar(Movimentacao movimentacaoNova) throws IOException {
		ArrayList<Movimentacao> movimentacao = ler();
			for (int i = 0; i < movimentacao.size(); i++)
				if (movimentacao.get(i).getId() == (movimentacaoNova.getId())) {
					movimentacao.set(i, movimentacaoNova);
					salvarLista(movimentacao);
					System.out.println("Movimentação atualizada com sucesso!");
				} else {
					System.out.println("Movimentação não atualizada");
				}
		}
	

	public void excluir(Movimentacao movimentacaoExcluir) throws IOException {
		ArrayList<Movimentacao> movimentacao = ler();
		for (int i = 0; i < movimentacao.size(); i++)
			if (movimentacao.get(i).getId() == (movimentacaoExcluir.getId())) {
				movimentacao.remove(i);
				salvarLista(movimentacao);
				System.out.println("Movimentação removida com sucesso!");
			} else {
				System.out.println("Movimentação não removida");
			}
	}

	// salva o arquivo no sistema
	private void salvarLista(ArrayList<Movimentacao> movimentacoes) throws IOException {
		inicializadorArquivo();
		try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
			GSON.toJson(movimentacoes, writer);
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
