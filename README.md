# 🃏 BlackJackGame  

Un projet [libGDX](https://libgdx.com/) généré avec [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).  

Ce projet utilise un modèle de base incluant des lanceurs d'application simples et une extension de `ApplicationAdapter` pour dessiner une interface utilisateur basique.  

---

## 🌍 Plateformes  

- **`core`** : Module principal contenant la logique de l'application partagée par toutes les plateformes.  
- **`lwjgl3`** : Plateforme de bureau principale utilisant LWJGL3.  

---

## 📖 Documentation Technique  

### 📝 Description Générale  

**BlackJackGame** est une implémentation du jeu de Blackjack développée avec le framework **libGDX**.  
Le jeu est conçu pour être extensible, modulaire et utilise une architecture orientée objet.  

---

### 🛠️ Architecture du Projet  

Le projet suit le modèle **MVC (Modèle-Vue-Contrôleur)** et est structuré en plusieurs composants :  

#### 📋 Composants Principaux  

- **Main** : Classe principale qui étend `Game` et gère les différents écrans.  
- **Screens** : Interfaces et classes pour les différents écrans du jeu, implémentant l'interface `Screen` de libGDX :  
  - 🏠 **MenuScreen** : Écran du menu principal avec options (démarrer, paramètres, quitter).  
  - 🎰 **CasinoScreen** : Permet de choisir le casino et le type de jeu.  
  - 🕹️ **GameScreen** : Gère l'affichage du jeu en cours.  
  - ⚙️ **SettingsScreen** : Écran pour ajuster les paramètres du jeu.  
- **GameLogic** : Contient la logique des règles du Blackjack et la gestion des tours.  
- **Player** : Classe abstraite pour représenter un joueur, avec :  
  - 🙋‍♂️ **HumanPlayer** : Gère le joueur humain.  
  - 🤖 **AIPlayer** : Implémente une logique pour les joueurs contrôlés par l'ordinateur.  
  - 🎩 **Dealer** : Représente le croupier avec des règles spécifiques.  
- **Deck** et **Card** : Classes pour gérer les cartes et les paquets.  
- **Hand** : Représente la main d'un joueur.  

---

### 📂 Diagramme de Classes  

Un diagramme UML détaillant les relations entre les classes est disponible dans le fichier :  
📄 **`classe.plantuml`**  

---

### 📜 Détails Techniques  

#### 🖥️ Langage et Bibliothèques  

- **Java 11** : Version utilisée pour le développement.  
- **libGDX 1.13.0** : Framework de développement de jeux en Java.  
- **Gradle** : Outil de build et de gestion des dépendances.  

---

#### 🖥️ Gestion des Écrans  

- **Système d'écrans de libGDX** : Permet de naviguer entre les différentes parties du jeu :  
  - **`Main`** initialise le jeu et définit l'écran actif.  
  - Chaque écran implémente les méthodes :  
    - `show()`, `render()`, `resize()`, `pause()`, `resume()`, `hide()`, `dispose()`.  

---

#### 🃏 Logique du Jeu  

- **Règles du Blackjack** :  
  - Gérées dans la classe `GameLogic`.  
  - Inclut les actions : tirer une carte, se tenir, doubler, etc.  

- **Gestion des Joueurs** :  
  - **`HumanPlayer`** : Interagit avec l'utilisateur.  
  - **`AIPlayer`** : Applique une logique automatique pour l'ordinateur.  
  - **`Dealer`** : Suit des règles spécifiques pour le croupier.  

- **Cartes et Paquets** :  
  - **`Deck`** : Mélange et distribue les cartes.  
  - **`Card`** : Définit chaque carte (valeur et couleur).  
  - **`Hand`** : Gère les cartes dans la main d'un joueur.  

---

#### 🎲 Casinos  

- Le jeu propose plusieurs variantes avec des casinos uniques :  
  - 💎 **CasinoPremium** : Règles avancées, mises élevées.  
  - 🏪 **CasinoDuCoin** : Version classique et accessible.  

---

### ⚙️ Fichiers de Configuration  

- **`build.gradle`** : Configure Gradle, les dépendances et les tâches.  
- **`gradle.properties`** : Définit les propriétés globales (versions, etc.).  
- **`settings.gradle`** : Liste les sous-projets.  
- **`.gitignore`** : Spécifie les fichiers à ignorer dans le contrôle de version.  
- **`.editorconfig`** : Règles de style de code pour une cohérence entre les éditeurs.  

---

## 🚀 Instructions d'Installation  

1. **Cloner le dépôt :**  

   ```bash
   git clone <url-du-dépôt>

2. **Lancer le jeu :**
   
   ```bash
   ./gradlew run
