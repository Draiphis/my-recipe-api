# 🍽️ My Recipe API

API REST de gestion de recettes développée en **Java 17 / Spring Boot 4**, avec persistance **PostgreSQL** containerisée via Docker Compose.

---

## 📋 Sommaire

- [Stack technique](#-stack-technique)
- [Prérequis](#-prérequis)
- [Démarrage rapide](#-démarrage-rapide)
- [Endpoints](#-endpoints)
- [Documentation OpenAPI](#-documentation-openapi)
- [Structure du projet](#-structure-du-projet)
- [Configuration](#-configuration)

---

## 🛠️ Stack technique

| Composant         | Version       |
|-------------------|---------------|
| Java              | 17            |
| Spring Boot       | 4.0.6         |
| Spring Data JPA   | —             |
| Spring Web MVC    | —             |
| PostgreSQL        | 18            |
| springdoc-openapi | 3.0.2         |
| Lombok            | —             |
| Maven             | (via wrapper) |

---

## ✅ Prérequis

- **JDK 17+**
- **Docker** & **Docker Compose**
- (Optionnel) Maven — un wrapper `mvnw` est fourni

---

## 🚀 Démarrage rapide

### 1. Cloner le projet

```bash
git clone <url-du-repo>
cd my-recipe-api
```

### 2. Lancer la base de données PostgreSQL

```bash
docker compose up -d
```

La base est exposée sur `localhost:5432` (user: `root`, password: `root`, db: `db`).

### 3. Lancer l'application

```bash
./mvnw spring-boot:run
```

> Sur Windows : `mvnw.cmd spring-boot:run`

L'API démarre sur **http://localhost:8080**.

### 4. Lancer les tests

```bash
./mvnw test
```

---

## 📡 Endpoints

Base URL : `http://localhost:8080`

| Méthode | URL              | Description                  | Code retour |
|---------|------------------|------------------------------|-------------|
| `GET`   | `/recette/list`  | Liste toutes les recettes    | `200 OK`    |
| `GET`   | `/recette/{id}`  | Récupère une recette par ID  | `200` / `404` |
| `POST`  | `/recette`       | Crée une nouvelle recette    | `201 Created` |
| `PUT`   | `/recette/{id}`  | Met à jour une recette       | `204` / `404` |
| `DELETE`| `/recette/{id}`  | Supprime une recette         | `204` / `404` |

### Exemple — Création

```bash
curl -X POST http://localhost:8080/recette \
  -H "Content-Type: application/json" \
  -d '{"nom": "Quiche lorraine"}'
```

### Exemple — Liste

```bash
curl http://localhost:8080/recette/list
```

### Modèle `Recette`

```json
{
  "id": 1,
  "nom": "tarte aux pommes"
}
```

> Le champ `nom` est requis (`@NotBlank`).

---

## 📖 Documentation OpenAPI

Une fois l'application démarrée :

- **Swagger UI** → http://localhost:8080/swagger-ui.html
- **OpenAPI JSON** → http://localhost:8080/v3/api-docs

---

## 🗂️ Structure du projet

```
my-recipe-api/
├── compose.yml                  # Stack Docker (PostgreSQL)
├── pom.xml
├── mvnw / mvnw.cmd              # Wrapper Maven
└── src/
    ├── main/
    │   ├── java/com/mns/cda/my_recipe_api/
    │   │   ├── MyRecipeApiApplication.java
    │   │   ├── controller/RecetteController.java
    │   │   ├── dao/RecetteDao.java
    │   │   ├── model/Recette.java
    │   │   └── view/RecetteView.java
    │   └── resources/
    │       ├── application.properties
    │       └── data-init.sql     # Jeu de données initial
    └── test/
        └── java/com/mns/cda/my_recipe_api/
            └── MyRecipeApiApplicationTests.java
```

---

## ⚙️ Configuration

Fichier : `src/main/resources/application.properties`

| Propriété                          | Valeur                                |
|------------------------------------|---------------------------------------|
| `spring.datasource.url`            | `jdbc:postgresql://localhost:5432/db` |
| `spring.datasource.username`       | `root`                                |
| `spring.datasource.password`       | `root`                                |
| `spring.jpa.hibernate.ddl-auto`    | `create`                              |
| `spring.sql.init.mode`             | `always`                              |

> ⚠️ Avec `ddl-auto=create`, le schéma est recréé à chaque démarrage et le fichier `data-init.sql` réinjecte les données de seed (`tarte aux pommes`, `poulet tandori`, `pates et steak`).

---

## 👤 Auteur

Projet réalisé dans le cadre de la formation **CDA — Java / DevOps**.
