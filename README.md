
# Projeto de Automação de Testes com WebDriver

Este projeto está sendo desenvolvido durante o curso de **Automação de Testes com WebDriver**. Ele contém os passos necessários para instalar, configurar e executar os testes automatizados com WebDriver.

---

## 🚀 Documentação de Instalação e Setup do Projeto

### 🔹 Pré-requisitos

Certifique-se de ter os seguintes itens instalados em sua máquina:

- **Java JDK 11+**
- **Maven**
- **Eclipse IDE**

---

### 🔧 Passos para Instalação

#### 1. Clone o repositório:

```bash
git clone https://github.com/gttiGS/AutomacaoWebDriver.git
cd cursowebdriver
```

#### 2. Importe o projeto no Eclipse:

- Abra o Eclipse.
- Vá em **File > Import > Maven > Existing Maven Projects**.
- Selecione o diretório do projeto clonado.
- Clique em **Finish**.

#### 3. Instale as dependências:

No terminal, dentro do diretório do projeto, execute:

```bash
mvn clean install
```

#### 4. Configure o WebDriver:

- Baixe o WebDriver correspondente ao navegador que deseja usar (por exemplo, **ChromeDriver**).
- Adicione o caminho do WebDriver ao **PATH** do sistema.

---

## 🧪 Executando os Testes

- Certifique-se de que o **WebDriver** está configurado corretamente.
- No terminal, execute o seguinte comando para rodar os testes:

```bash
mvn test
```

---

## ❓ Problemas Comuns

- **Erro de versão do Java**: Verifique se o Java JDK 11+ está instalado e configurado no **PATH**.
- **Dependências não resolvidas**: Execute `mvn clean install` novamente.

---

## 📄 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

## 👨‍💻 Autor

Gustavo Supranzetti – [@gttiGS](https://github.com/gttiGS)
