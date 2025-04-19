<a id="readme-top"></a>

[![Contributors][contributors-shield]][contributors-url]
[![project_license][license-shield]][license-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/P-py/cash-track-api">
    <img src="https://raw.githubusercontent.com/P-py/cash-track-api/refs/heads/main/assets/images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Cash-Track API</h3>

  <p align="center">
    O Cashtrack é uma aplicação web desenvolvida para ajudar usuários a gerenciar suas finanças pessoais de forma intuitiva e eficiente. A plataforma permite o controle de gastos e receitas, fornecendo uma visão clara do saldo e do histórico financeiro do usuário.
    <br />
    <a href="https://github.com/P-py/cash-track-api/tree/main/docs"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://cash-track-puce.vercel.app/">View Demo</a>
    &middot;
    <a href="https://github.com/P-py/cash-track-api/issues/new?labels=bug&template=bug-report---.md">Report Bug</a>
    &middot;
    <a href="https://github.com/P-py/cash-track-api/issues/new?labels=enhancement&template=feature-request---.md">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Conteúdos</summary>
  <ol>
    <li>
      <a href="#sobre-o-projeto">Sobre o projeto</a>
      <ul>
        <li><a href="#stack-de-tecnologias">Stack de tecnologias</a></li>
      </ul>
    </li>
    <li>
      <a href="#configuração-inicial">Configuração inicial</a>
      <ul>
        <li><a href="#pré-requisitos">Pré-requisitos</a></li>
        <li><a href="#instalação">Instalação</a></li>
      </ul>
    </li>
    <li><a href="#usabilidade">Usabilidade</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contribuindo">Contribuindo</a></li>
      <ul>
        <li><a href="#como-contribuir">Como contribuir</a></li>
        <li><a href="#regras-de-contribuição">Regras de contribuição</a></li>
      </ul>
    <li><a href="#licença-do-projeto">Licença do projeto</a></li>
    <li><a href="#contato">Contato</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## Sobre o projeto

O **Cashtrack** é uma aplicação web desenvolvida com foco em controle financeiro pessoal. Criado como parte de um projeto acadêmico na disciplina de Desenvolvimento da [Faculdade de Engenharia de Sorocaba (FACENS)](https://facens.br/), o sistema oferece uma experiência moderna e segura para usuários que desejam registrar suas receitas e despesas, visualizar históricos e gerenciar suas finanças.

Este repositório contém a implementação do **back-end** da aplicação, desenvolvido em **Kotlin** utilizando o framework **Spring Boot**. A API RESTful fornece todos os endpoints necessários para autenticação de usuários, operações financeiras e gerenciamento de conta, além de utilizar um banco de dados **PostgreSQL** para persistência das informações.

O projeto segue boas práticas de arquitetura, segurança com autenticação via **JWT**, controle de acesso por token, validação de dados e estrutura escalável, facilitando futuras expansões.

O sistema está em constante evolução, com novas funcionalidades sendo planejadas e implementadas.


[![Swagger API Demo][product-screenshot]](https://cash-track-puce.vercel.app/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Stack de tecnologias

[![Kotlin Logo][kotlin-shield]]()
[![Spring Boot Logo][spring-shield]]()
[![PostgreSQL Logo][postgresql-shield]]()

O back-end do projeto **Cashtrack** foi desenvolvido com foco em segurança, escalabilidade e integração eficiente com o front-end. As principais tecnologias utilizadas foram:

- **Kotlin** – Linguagem moderna, concisa e segura, utilizada na construção de toda a API.
- **Spring Boot** – Framework robusto para criação de serviços RESTful, com suporte a segurança, validação, injeção de dependência e muito mais.
- **Spring Security** – Gerenciamento de autenticação e autorização utilizando tokens JWT.
- **PostgreSQL** – Banco de dados relacional utilizado para persistência de usuários, transações e dados financeiros.
- **Docker (opcional)** – Para facilitar a execução e orquestração de ambientes locais e produção.
- **Git & GitHub** – Controle de versão e colaboração entre os membros do time.

A arquitetura da API foi pensada para ser RESTful, com separação clara de responsabilidades, e integração direta com o frontend via autenticação segura com tokens JWT. Toda a comunicação é baseada em JSON, com endpoints bem definidos e documentação em progresso.


> **Nota:** Este repositório contém exclusivamente o código do **back-end** da aplicação. Para a interface do usuário, acesse o repositório do [frontend](https://github.com/seu-usuario/cashtrack-frontend).


<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Configuração inicial

Esta seção fornece instruções sobre como obter uma cópia local do projeto e executá-lo para desenvolvimento e testes.

### Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas:

- [Git](https://git-scm.com/)
- [Java JDK 17+](https://adoptium.net/) (ou versão compatível com o Spring Boot utilizado)
- [Gradle](https://gradle.org/) 
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou [VSCode](https://code.visualstudio.com/) com extensões para Kotlin e Spring
- [Docker](https://www.docker.com/) (opcional, se quiser subir o PostgreSQL localmente)
- PostgreSQL instalado ou acesso a um banco externo

Verifique se o Git está instalado corretamente executando:

```bash
git --version
java --version
gradle --version
docker -v
```

### Instalação
1. Clone o repositório:
    ```bash
    git clone https://github.com/P-py/cash-track-api.git
    ```

2. Acesse o diretório do projeto:
    ```bash
    cd cash-track-api
    ```

3. Abra o projeto em sua IDE preferida (recomendado: IntelliJ IDEA)

4. Configure as variáveis de ambiente ou o arquivo .env (caso esteja usando) com os dados de conexão do PostgreSQL e outras configurações necessárias.

5. Execute a aplicação:
    - Com IntelliJ: Clique no arquivo CashtrackApplication.kt e escolha Run
    - Ou pelo terminal:
        ```bash
        ./gradlew bootRun
        ```
6. A API estará disponível em:
    ```bash
    http://localhost:8080
    ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usabilidade

A aplicação Cashtrack foi desenvolvida para oferecer uma experiência intuitiva e responsiva ao usuário. A seguir, estão algumas das funcionalidades disponíveis no frontend:

- ➕ **Cadastro de transações:** Registre despesas e receitas com poucos cliques.
- 🔐 **Login seguro:** Autenticação via token JWT com persistência de sessão através de cookies.
- ⚙️ **Gerenciamento de conta:** Altere suas informações pessoais ou exclua sua conta diretamente pelo painel.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ROADMAP -->
## Roadmap

- v0.2.0
  - [ ] Implementação dos endpoints de histórico
  - [ ] Atualizar versão do spring security para evitar vulnerabilidades mapeadas
  - [ ] Disponibilizar postman para testes
- v0.3.0 ~ v1.0.0
  - [ ] Testes de integração end-to-end automatizados
  - [ ] Adição da cobertura completa de testes unitários
  - [ ] Configurar detekt no projeto para qualidade de código

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTRIBUTING -->
## Contribuindo

Contribuições são muito bem-vindas! Se você deseja sugerir melhorias, reportar bugs ou adicionar novas funcionalidades ao Cashtrack, você pode usar as opções de reportar bugs ou features, ou então abrir um Pull-Request:

### Como contribuir

1. **Fork este repositório**
2. Crie uma branch para sua feature ou correção:
    ```bash
    git checkout -b minha-feature
    ```
3. Faça suas alterações e commits:
    ```bash
    git commit -m 'Adiciona nova funcionalidade'
    ```
4. Envie para o repositório remoto:
    ```bash
    git push origin minha-feature
    ```
5. Abra um Pull Request

### Regras de contribuição
- Sempre mantenha seu código limpo e documentado.
- Escreva commits claros e objetivos.
- Siga a estrutura do projeto e os padrões de nomenclatura existentes.
- Certifique-se de que suas alterações não quebram funcionalidades existentes.
- Sugestões de contribuição

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- LICENSE -->
## Licença do projeto

Este projeto está licenciado sob os termos da licença MIT. Seus autores podem ser encontrados na aba de [Contributors](https://github.com/P-py/cash-track-api/graphs/contributors) do GitHub.

Você pode consultar os detalhes completos no arquivo [`LICENSE.txt`](./LICENSE.txt).

Sinta-se à vontade para usar, modificar e distribuir este projeto, respeitando os termos da licença.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contato

Entre em contato com os desenvolvedores do projeto Cashtrack:

- **Pedro Salviano Santos** – 236586@facens.br
- **Luiz Gustavo Motta Viana** – 236428@facens.br
- **Ronaldo Simeone Antonio** – 190232@facens.br
- **Erick Ferreira Ribeiro** – 237046@facens.br

Projeto desenvolvido como parte da disciplina de **Desenvolvimento Web**

Facens – 2025

Sinta-se à vontade para contribuir, abrir issues ou enviar sugestões via GitHub!


<p align="right">(<a href="#readme-top">back to top</a>)</p>

[contributors-shield]: https://img.shields.io/github/contributors/P-py/cash-track.svg?style=for-the-badge
[kotlin-shield]: https://img.shields.io/badge/kotlin-black?logo=kotlin
[spring-shield]: https://img.shields.io/badge/spring-boot-black?logo=spring-boot
[postgresql-shield]: https://img.shields.io/badge/postgresql-black?logo=postgresql
[contributors-url]: https://github.com/P-py/cash-track-api/graphs/contributors
[license-shield]: https://img.shields.io/github/license/P-py/cash-track-api.svg?style=for-the-badge
[license-url]: https://github.com/P-py/cash-track-api/blob/main/LICENSE.txt
[product-screenshot]: https://raw.githubusercontent.com/P-py/cash-track/refs/heads/main/assets/images/swagger_demo.png