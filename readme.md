# 🕷️ Web Scraper de Apostas com Spring Boot

Este projeto é uma aplicação web simples construída com **Spring Boot**, **Thymeleaf** e **Jsoup** que permite ao usuário inserir uma URL de uma página de apostas e visualizar os dados extraídos de forma organizada em uma tabela.

---

## 🚀 Funcionalidades

* Interface web simples para inserir a URL alvo
* Extração (web scraping) de:

    * Nome do jogador
    * Odd padrão (aposta base)
    * Melhor odd disponível
    * Nome da casa de aposta com melhor odd
    * Categoria da aposta (ex.: Artilheiro, Campeão, etc.)
* Exclusão de seções indesejadas com base no nome da categoria
* Exibição dos dados em uma tabela HTML dinâmica via Thymeleaf

---

## 💠 Tecnologias

* Java 21
* Spring Boot (Web)
* Thymeleaf
* Jsoup (web scraping)
* Maven

---

## 📦 Como executar

1. Clone o projeto:

   ```bash
   git clone https://github.com/seu-usuario/web-scraper-apostas.git
   ```

2. Acesse o diretório:

   ```bash
   cd web-scraper-apostas
   ```

3. Execute o projeto:

   ```bash
   ./mvnw spring-boot:run
   ```

4. Acesse via navegador:

   ```
   http://localhost:8080
   ```

---

## ✅ Como usar

1. Informe uma URL válida da página a ser analisada.
2. Clique em **Scrape**.
3. Os dados extraídos aparecerão organizados em uma tabela.

---

## 🧠 TODO (Melhorias futuras)

* ✅ **Modularizar funcionamento**

    * Separar responsabilidades entre serviço de scraping, parser e controlador.
* 🫠 **Melhorar cache dos resultados**

    * Evitar reprocessar apostas já analisadas.
    * Atualizar dados dinamicamente com base na proximidade do evento.
* 🧠 **Refatorar código**

    * Melhorar estrutura, clareza e reuso.
    * Introduzir testes unitários.
* 🧠 **Melhorar design da interface**

    * Tornar responsiva e mais visualmente agradável.
    * Usar frameworks como Bootstrap ou Tailwind.
* 🧠 **Adicionar filtro na tabela**

    * Permitir buscar por jogador, categoria ou casa de aposta.

---

## 📄 Licença

Este projeto é livre para uso educacional ou pessoal. Contribuições são bem-vindas!
