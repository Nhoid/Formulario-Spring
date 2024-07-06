# Formulário Spring


Este projeto é um sistema de Formulário de Currículo desenvolvido utilizando Spring Framework. Ele oferece uma interface principal para preenchimento de formulários de currículo, além de uma área administrativa para gerenciamento de candidatos e vagas disponíveis.

**Propósito e Objetivos Principais:**
- Facilitar o preenchimento e armazenamento de informações de currículos.
- Fornecer uma interface intuitiva para candidatos e administradores.
- Permitir o gerenciamento eficiente de candidatos e vagas disponíveis através de uma página de administração.

### Funcionalidades Principais

1. **Formulário de Cadastro de Currículo**
   - Interface para os candidatos preencherem e enviarem seus currículos.
   - Utiliza Thymeleaf para renderização dinâmica da interface do formulário.
   - Validação de entrada de dados usando anotações do Spring.

2. **Página de Administração**
   - Interface administrativa para gerenciar currículos e vagas.
   - Permite filtrar candidatos por critérios como data de envio, status, etc.
   - Funcionalidades para baixar currículos dos candidatos.

3. **Controle de Vagas**
   - Gerenciamento das vagas disponíveis e fechamento quando necessário.
   - Interface para adicionar e remover vagas.
   - Funcionalidades para desqualificar candidatos de vagas específicas.

### Exemplos de Casos de Uso

- **Cadastro de Novo Currículo:**
   - Um candidato acessa a interface do formulário, preenche os campos obrigatórios e envia seu currículo.
   - O sistema valida os dados e os armazena no banco de dados.

- **Administração de Currículos:**
   - Um administrador acessa a página de administração, filtra os currículos por critérios específicos (por exemplo, data de envio).
   - O administrador visualiza os detalhes dos currículos dos candidatos e pode baixá-los conforme necessário.

- **Gestão de Vagas:**
   - Um administrador acessa a interface de administração de vagas, onde pode adicionar ou remover vagas.
   - O administrador controla o status das vagas, abrindo ou fechando de acordo com a necessidade da empresa.



### Tecnologias Utilizadas

Além do Spring Framework, este projeto utiliza as seguintes tecnologias principais:

- **Spring Boot Starter Data JPA**: Para integração com o banco de dados e mapeamento objeto-relacional.
- **Spring Boot Starter Web**: Para desenvolvimento de aplicações web com o Spring MVC.
- **Flyway Core**: Para migrações de banco de dados.
- **Spring Boot DevTools**: Para melhorias no desenvolvimento como reinicialização automática.
- **MySQL Connector/J**: Driver JDBC para MySQL.
- **Lombok**: Para reduzir o código boilerplate.
- **Spring Boot Starter Test**: Para testes na aplicação Spring Boot.
- **Spring Boot Starter Security**: Para segurança de aplicações web.
- **Spring Boot Starter Validation**: Para validação de dados.
- **Spring Boot Starter Thymeleaf**: Para integração do Thymeleaf como mecanismo de template.
- **Thymeleaf Extras Spring Security 6**: Integração do Thymeleaf com Spring Security.
- **Spring Boot Starter Mail**: Para envio de e-mails.
- **Bootstrap**: Para o desenvolvimento do front-end, proporcionando um design responsivo e componentes visuais modernos.

Essas tecnologias foram escolhidas para proporcionar uma base robusta e eficiente para o desenvolvimento do sistema de Formulário de Currículo, abrangendo desde o acesso a dados até a segurança e a interface do usuário.


### Configuração do Ambiente

Para configurar o ambiente de desenvolvimento deste projeto em Spring, siga os passos abaixo:

1. **Requisitos de Software:**
    - JDK (Java Development Kit) versão compatível com o Spring Boot.
    - Maven para gerenciamento de dependências.
    - IDE de sua preferência com suporte a desenvolvimento Java (recomendado: IntelliJ IDEA, Eclipse).

2. **Configuração do Banco de Dados:**
    - Certifique-se de ter um servidor MySQL disponível.
    - Configure as propriedades no arquivo `application.properties` localizado no diretório `src/main/resources`. Exemplo:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/database
      spring.datasource.username=root
      spring.datasource.password=verysecret
      ```

3. **Configuração da Pasta de Salvamento de Currículos:**
    - Defina o diretório onde os currículos serão salvos. Exemplo:
      ```properties
      spring.storage.file-directory=curriculos
      ```

4. **Utilização do Docker (Opcional):**
    - Um arquivo `docker-compose.yml` está disponível na pasta principal do projeto para facilitar a configuração do ambiente de banco de dados via Docker. Certifique-se de ter o Docker instalado e execute `docker-compose up -d` para iniciar o container MySQL conforme configurado no arquivo.

Estes passos garantem que você tenha um ambiente configurado para desenvolver e executar o projeto de Formulário de Currículo usando Spring.


### Organização de Pacotes

- **configuration**: Contém classes de configuração, como configuração do banco de dados, autenticação personalizada, configuração de e-mails, e configurações de segurança.

- **controller**: Agrupa os controladores da aplicação. No subpacote `admin`, há endpoints relacionados à administração do sistema, como login de administrador e manipulação de vagas. No subpacote `form`, há endpoints relacionados ao formulário de currículo.

- **exception**: Classes para tratamento de exceções, como `FormHandler` que trata erros e os apresenta ao usuário de forma adequada.

- **model**: Contém as entidades (`Vaga`, `Curriculo`) e DTOs (Data Transfer Objects) que são usados para transferência de dados entre camadas.

- **repository**: Repositórios para acesso aos dados das entidades (`VagaRepository`, `CurriculoRepository`).

- **services**: Interfaces e implementações dos serviços da aplicação, como serviços de e-mail, vaga, currículo e armazenamento.

- **storage**: Funcionalidades relacionadas ao armazenamento de arquivos, incluindo exceções personalizadas e propriedades.

- **util**: Classes utilitárias, como anotações para validação de arquivos.

- **FormProjectApplication.java**: Classe principal de inicialização do Spring Boot.


































