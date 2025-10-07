# 🏷️ Sistema de Controle de Estoque

## 📘 Contexto
Este projeto tem como objetivo desenvolver um **sistema de controle de estoque** simples, executado pelo terminal, com persistência em banco de dados PostgreSQL.

## 👥 Atores
- **Administrador:** gerencia produtos e usuários.
- **Funcionário:** registra movimentações e consulta produtos.

## ⚙️ Funcionalidades principais
1. Cadastrar produto  
2. Consultar produto  
3. Atualizar produto  
4. Registrar entrada de estoque  
5. Registrar saída de estoque  
6. Gerar relatório de estoque  

## 🧩 Diagramas
### Casos de Uso
```mermaid
flowchart TD
    %% === Atores ===
    A[👤 **Administrador**]
    F[👤 **Funcionário**]

    %% === Casos de uso do Administrador ===
    A --> Cadastrar[(Cadastrar Produto)]
    A --> Atualizar[(Atualizar Produto)]
    A --> Relatorio[(Gerar Relatório)]
    A --> ConsultarA[(Consultar Produto)]

    %% === Casos de uso do Funcionário ===
    F --> ConsultarF[(Consultar Produto)]
    F --> Entrada[(Registrar Entrada de Estoque)]
    F --> Saida[(Registrar Saída de Estoque)]
```

### Classes
```mermaid
classDiagram
class Produto {
  -int id
  -string nome
  -string categoria
  -int quantidade
  -float preco
  +atualizarQuantidade(qtd:int)
  +exibirInfo()
}

class Movimentacao {
  -int id
  -string tipo
  -int quantidade
  -date data
  -Produto produto
  +registrar()
}

class Usuario {
  -int id
  -string nome
  -string tipo
  +autenticar()
}

class Relatorio {
  +gerarRelatorioEstoque()
}

Produto "1" <--> "*" Movimentacao : movimenta
Usuario "1" <--> "*" Movimentacao : realiza
```

## 🧱 Protótipo
O sistema é operado por menu de terminal:

📄 [Visualizar Protótipo em PDF](./desing/Prototipo.pdf)



## 💻 Tecnologias utilizadas
- 
-   
-  

## 🚀 Execução
1. 
2. 
