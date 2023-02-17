Cette procédure détaille l'installation et le lancement de l'application sur la partie back-end. 

Prérequis : 
- DBeaver (ou sur tout autre logiciel de gestion de base de données)
- Docker 

1 - Créer sur DBeaver une connexion PostgreSQL, en nommant la database ftg-car sur le port 5432. Définir ftg-car en nom d'utilisateur, ainsi que password en mot de passe (conformément aux informations présentes dans le application.yml)

2 - Dans le terminal, faire un docker compose up -d pour créer dans Docker le conteneur de la base de données à partir du docker-compose.yml.

3 - Tester la connexion créée en 1-, la base de donnée devrait être accessible.

4 - Jouer le script "V1_0_0__init_database.sql" sur DBeaver (Menu "Editeur SQL" > "Script SQL") pour créer les tables du projet en base de données, puis le script "mock_dataV1.sql" pour peupler les tables avec des données.

5 - Lancer le FlywayApplication.java pour lancer l'application côté back-end. 



