# BiodataLink

## Descrição do Projeto
O BiodataLink é um projeto inovador que utiliza a tecnologia Java e a API REST do Google Firebase para gerenciar e disponibilizar informações sobre espécies em extinção. Utilizando uma interface gráfica criada com Java Swing, o projeto visa facilitar o acesso a dados ambientais atualizados, promover a conscientização ambiental e apoiar ações de conservação.

## Objetivos
- **Facilitar o Acesso à Informação:** Prover uma interface amigável para acessar dados sobre espécies em extinção.
- **Promover Conscientização Ambiental:** Usar a tecnologia para informar e sensibilizar o público sobre a importância da conservação.
- **Gerenciamento Eficiente de Dados:** Converter dados de CSV para JSON e armazená-los no Firebase, permitindo operações eficientes de busca e atualização.

## Tecnologias Utilizadas
- **Java**: Linguagem de programação para toda a lógica de back-end e interface gráfica.
- **Java Swing**: Biblioteca gráfica para a criação da interface do usuário.
- **Google Firebase**: Plataforma de banco de dados usada para armazenar e gerenciar dados JSON.
- **API REST**: Integração com a API do Google Firebase para operações de dados.
- **NetBeans/Eclipse**: IDEs recomendados para desenvolvimento e teste do código.
- **Insomnia**: Software para testar a API REST.

## Funcionalidades
1. **Conversão de Dados**: Converte dados do formato CSV para JSON.
2. **Sincronização com Firebase**: Armazena e atualiza dados no Firebase em tempo real.
3. **Busca Filtrada**: Permite que usuários façam buscas detalhadas no banco de dados.
4. **Atualização de Dados**: Interface administrativa para atualizar as informações sobre as espécies.

## Estrutura do Código
- `Main.java`: Inicializa a aplicação e a interface gráfica.
- `Conexao.java`: Gerencia a conexão com o Firebase.
- `Operacoes.java`: Contém métodos para inserir e ler dados.
- `DocumentoEspecie.java`: Define a estrutura de dados para as espécies.
- `InserirDocumento.java`: Interface para inserção de novos dados de espécies.

## Configuração do Projeto
1. **Clonar o Repositório**
git clone https://github.com/seuusuario/biodatalink.git

2. **Configuração do Firebase**
- Crie um projeto no Google Firebase.
- Obtenha as credenciais e atualize `Conexao.java` com essas informações.
3. **Importar o Projeto no IDE**
- Abra o projeto no NetBeans ou Eclipse.
4. **Instalar Dependências**
- Certifique-se de que todas as bibliotecas Java necessárias estão incluídas no projeto.

## Como Usar
- Execute `Main.java` para iniciar a aplicação.
- Utilize a interface gráfica para explorar, inserir ou atualizar dados sobre as espécies.

## Licença
Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE.md para mais detalhes.

## Contribuições
Contribuições são sempre bem-vindas! Por favor, leia o arquivo CONTRIBUTING.md para saber como contribuir com o projeto.

## Autores
- **Samuel Santos**
