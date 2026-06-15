# 🍾 Sistema Gerencial de Distribuidora de Bebidas

Este projeto consiste em um sistema de gestão e controle de fluxo de caixa para uma distribuidora de bebidas, desenvolvido em **Java** utilizando Programação Orientada a Objetos (POO). Em seu desenvolvimento, adotamos a arquitetura **MVC (Model-View-Controller)**, o padrão estrutural **Facade** e boas práticas de desenvolvimento (*Clean Code*).

---

## 📌 Informações Gerais sobre o Projeto

### 📄 Descrição
O sistema simula a operação interna e a frente de caixa de uma distribuidora de bebidas. Ele resolve problemas típicos do negócio, como:
- Cadastro centralizado de bebidas, clientes e fornecedores;
- Associação dinâmica de bebidas a fornecedores;
- Consulta e ajuste de estoque, possibilitando o registro de Entradas e Saídas, além de ajustes manuais;
- Realização de vendas com baixa automática;
- Relatórios de produtos com alerta de estoque mínimo, faturamento bruto das vendas e valor total de bebidas em estoque;
- Persistência de dados local;
- Registro de auditoria em arquivo físico através de logs estruturados.

### 🎯 Objetivos
* **Unificação de Módulos:** Integrar de forma coesa as implementações independentes de múltiplos desenvolvedores.
* **Consistência Estatística e Financeira:** Gerar relatórios confiáveis sobre o patrimônio imobilizado e o faturamento histórico bruto.
* **Robustez de Dados:** Garantir que nenhuma transação seja perdida ao encerrar o sistema utilizando persistência de dados em disco.

### ⚙️ Funcionalidades Principais
1. **Gestão de Portfólio (Bebidas):** Cadastro, remoção, busca avançada e atualização de preços de bebidas (alcoólicas e não alcoólicas).
2. **Controle de Estoque Inteligente:** Atualização automática de quantidades, auditoria de entradas e saídas físicas e registro histórico de movimentações.
3. **Frente de Caixa:** Abertura de ordens de venda, vinculação de clientes cadastrados, validação de itens em estoque e fechamento com atualização de saldo em tempo real.
4. **Persistência de Dados:** Salvamento e carregamento automático do estado completo da aplicação através de serialização de objetos Java.
5. **Painel de Relatórios:** Cálculo do valor financeiro total imobilizado em estoque, detecção automática de quebra de estoque (alerta de mínimo) e faturamento acumulado.
6. **Sistema de Logs:** Registro em arquivo de texto plano (`logs/log.txt`) de todas as principais operações do sistema para fins de auditoria.

---

## 🏛️ Classes e Relações Arquiteturais

A arquitetura do sistema é estruturada sobre o padrão **Facade**, onde a classe `Distribuidora` atua como o ponto focal de armazenamento e inteligência de negócio.

### 🧠 Análise de Acoplamento e Relações:

* **Distribuidora & Estoque (Composição):** A `Distribuidora` possui uma relação de composição com a classe `Estoque`. O ciclo de vida do estoque está atado ao da distribuidora; se ela deixa de existir, a estrutura do estoque é destruída.
* **Distribuidora & Bebida/Cliente/Fornecedor (Agregação):** O gerenciamento de entidades usa agregação em estruturas de dicionário (`Map<Integer, T>`). Os objetos existem de forma independente fora do sistema (ex: um Cliente pode ser instanciado em escopos isolados). A distribuidora apenas agrupa suas referências usando identificadores únicos (ID e Código) para otimização de busca.
* **Distribuidora & Venda (Composição):** O histórico de vendas é uma coleção estritamente criada e controlada de dentro do sistema após a finalização pelo fluxo de caixa, configurando uma composição lógica para fins de persistência.
* **Camada Controller & View (Associação Bidirecional/Cíclica):** Resolvida no `Main` através da injeção de dependência via método (`setView`). A View associa-se ao Controller para disparar gatilhos de comando, e o Controller associa-se de volta à View para atualizar dados na interface de terminal de maneira desacoplada.

---

## 📂 Estrutura de Pastas do Projeto

O código-fonte está organizado em pacotes lógicos para garantir a separação de responsabilidades do padrão MVC:

📁 src/
│
├── 📁 Controller/       # Intermediadores de regras e fluxos de dados
├── 📁 Exceptions/       # Exceções personalizadas do sistema
├── 📁 Model/            # Classes de entidade (Bebida, Cliente, Venda, etc.)
├── 📁 Service/          # Facade (Distribuidora) e interfaces de contrato
├── 📁 Util/             # Serviços utilitários (LoggerService)
├── 📁 View/             # Telas de terminal e menus de interação com o usuário
└── 📄 Main.java         # Ponto de entrada e inicialização do sistema

## 🚀 Como Executar o Projeto

### Pré Requisitos
* **Java Development Kit (JDK):** Versão 11 ou superior instalada e configurada nas variáveis de ambiente.
* **IDE/Terminal:** Prompt de Comando, VS Code, NetBeans ou IntelliJ IDEA.

### Passo a Passo via Terminal

1.  **Clone o Repositório:** 
git clone [https://github.com/PedroMorais2408/distribuidora-java.git](https://github.com/PedroMorais2408/distribuidora-java.git)
cd distribuidora-java

2.  **Compilação do Código-Fonte:**
    Certifique-se de estar na pasta raiz que contém o diretório de pacotes (`Controller`, `Model`, `Service`, `View`):
    javac Controller/*.java Model/*.java Service/*.java View/*.java Exceptions/*.java Util/*.java Main.java

3.  **Execução da Aplicação:**
    java Main


> 💾 **Nota sobre Persistência:** Ao iniciar pela primeira vez, o sistema identificará a ausência do arquivo local e exibirá [Sistema] Nova base de dados criada.. Ao realizar cadastros e selecionar a opção 0. Sair do Sistema, o arquivo de fluxo binário serializado dados_sistema.dat será gerado automaticamente no diretório raiz, preservando os dados para a próxima inicialização.

---

## 🤖 Abordagem e Uso do Gemini no Desenvolvimento

O **Gemini** foi adotado ativamente durante o ciclo de desenvolvimento do software como uma ferramenta consultiva de engenharia de software e design pattern, fundamentando as decisões de código pelos seguintes pilares:

1. **Idealização e Estruturação:** Auxílio na modelagem inicial do escopo, sugerindo assinaturas de métodos e a distribuição de responsabilidades entre as classes.
2.  **Refatoração Arquitetural (Orquestração do Padrão Facade):** O assistente auxiliou a modelar a transição de coleções locais para uma Distribuidora real e centralizada, eliminando listas isoladas internas nos Controllers que causavam perda de sincronia de dados.
3.  **Corretor de Tipagem e Sintaxe Crítica:** Solução de conflitos de escopo e amarração de dependências circulares no método principal (Main).
4.  **Estratégia de Persistência:** Auxílio na estruturação de fluxos de entrada e saída usando padrões modernos do Java como Try-with-resources e no rastreio da exceção NotSerializableException em tempo de serialização em cascata.

---

## 📚 Referências e Recursos Adicionais

* **Java Collections Framework:** Documentação oficial da Oracle sobre o uso de `HashMap`, `ArrayList` e polimorfismo de interfaces de coleção.
* **Padrões de Projeto (Design Patterns):** *Gamma, Erich; Helm, Richard; Johnson, Ralph; Vlissides, John (Design Patterns: Elements of Reusable Object-Oriented Software)* - Estudos aplicados sobre o padrão **Facade**.
* **Serialização em Java:** Guias arquiteturais de entrada/saída binária (`java.io.ObjectOutputStream` e `java.io.Serializable`).