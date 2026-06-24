# GestãoFit

Sistema de gestão para academias desenvolvido em Android utilizando Kotlin, Jetpack Compose e Room Database.

## Funcionalidades

* Cadastro de alunos
* Controle de mensalidades
* Fluxo de caixa
* Registro de entradas e saídas
* Dashboard financeiro
* Persistência de dados com Room
* Interface moderna com Jetpack Compose

## Tecnologias Utilizadas

* Kotlin
* Jetpack Compose
* Material Design 3
* Room Database
* ViewModel
* LiveData / State
* Android Studio

## Estrutura do Projeto

```
com.seuapp.gestaofit
│
├── data
│   ├── dao
│   ├── entity
│   └── repository
│
├── domain
│   └── model
│
├── presentation
│   ├── screen
│   ├── components
│   └── viewmodel
│
└── MainActivity.kt
```

## Requisitos

* Android Studio Koala ou superior
* JDK 17
* Android SDK 34+

## Instalação

Clone o projeto:

```bash
git clone https://github.com/seu-usuario/gestaofit.git
```

Abra no Android Studio e execute:

```bash
Sync Project with Gradle Files
```

Depois:

```bash
Run > Run 'app'
```

## Versionamento

Este projeto utiliza Git e Conventional Commits:

* feat: nova funcionalidade
* fix: correção de bugs
* refactor: melhoria de código
* chore: manutenção
* docs: documentação

### Exemplos

```bash
git commit -m "feat: adiciona cadastro de alunos"
git commit -m "fix: corrige cálculo do saldo"
```

## Licença

Projeto desenvolvido para fins educacionais e de estudo.
