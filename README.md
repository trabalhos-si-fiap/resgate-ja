# 🚨 Resgate Já

Projeto universitário desenvolvido com Java 21, Spring Boot 3.5 e PostgreSQL para auxiliar a **gestão de voluntários e coordenadores em missões de ajuda humanitária**, como entrega de alimentos ou água em áreas atingidas por desastres naturais.

---

## 🧩 Tecnologias utilizadas

- **Java 21**
- **Spring Boot 3.5**
- **PostgreSQL**
- **Docker & Docker Compose**

---

## 🛠️ Funcionalidades

- Cadastro de coordenadores e voluntários
- Criação de missões por coordenadores (ex: entrega de mantimentos)
- Atribuição de voluntários às missões
- Gerenciamento do status das missões
- Armazenamento seguro dos dados em banco PostgreSQL

---

## 🐳 Rodando o projeto com Docker

> Pré-requisitos:
> - Docker
> - Docker Compose

1. Clone o repositório:

```bash
git clone https://github.com/trabalhos-si-fiap/resgate-ja.git
cd resgate-ja

docker-compose up --build
```

Acesse o swagger da aplicação:

A API estará disponível em: http://localhost:8080/swagger-ui/index.html

📚 Sobre o projeto
Este projeto foi desenvolvido como parte das entregas do curso de Sistemas de Informação da FIAP.
Seu principal objetivo é oferecer uma solução simples, mas eficiente, para gestão de missões humanitárias e voluntários em situações de emergência.

📄 Licença
Distribuído sob a licença MIT. Veja LICENSE para mais informações.
