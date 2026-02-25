# 🎨 PinBoard - Éditeur de Dessins Vectoriels (JavaFX)

## 📝 Présentation du Projet
**PinBoard** est un éditeur graphique robuste développé dans le cadre du cursus de Sorbonne Université. L'objectif principal était de concevoir une architecture logicielle hautement **extensible** en appliquant les principes de la programmation orientée objet et plusieurs Design Patterns académiques.

L'application permet de manipuler des planches de dessin (Boards) sur lesquelles on peut ajouter, sélectionner, déplacer et grouper des formes géométriques.

## 🏗️ Architecture & Design Patterns
Le projet repose sur une séparation stricte entre le **Modèle** (représentation des données) et la **Vue** (interface graphique).

| Design Pattern | Application dans le projet |
| :--- | :--- |
| **Stratégie (Strategy)** | Utilisé pour la gestion des outils (`Tool`). On change dynamiquement le comportement de la souris selon l'outil sélectionné (Sélecteur, Rectangle, Ellipse, Cœur). |
| **Composite** | Implémenté via `ClipGroup`. Un groupe d'objets est lui-même un `Clip`, permettant de manipuler des ensembles de formes comme une entité unique. |
| **Commande (Command)** | Utilisé pour le système **Undo/Redo**. Chaque action (déplacement, création, suppression) est encapsulée dans un objet commande. |
| **Observateur (Observer)** | La fenêtre d'édition écoute les changements du modèle (`Board`) pour mettre à jour l'affichage en temps réel. |
| **Singleton** | Appliqué au presse-papier (`Clipboard`) pour permettre le copier-coller entre différentes fenêtres de l'application. |

## 🌟 Mon Extension : La Forme "Cœur"
En plus des fonctionnalités de base (Rectangles, Ellipses), j'ai enrichi l'éditeur avec une nouvelle forme géométrique :
- **ClipHeart** : Une classe implémentant l'interface `Clip` utilisant des courbes de Bézier pour le rendu.
- **ToolHeart** : Un outil dédié permettant de dessiner des cœurs par cliquer-glisser, parfaitement intégré à la barre d'outils existante.
- Elle supporte toutes les opérations standards : changement de couleur, redimensionnement, déplacement et groupement.

## 🚀 Fonctionnalités Clés
- **Édition riche** : Ajout de formes, sélection simple et multiple (touche Shift).
- **Manipulation** : Déplacement à la souris, suppression, groupement/dégroupement.
- **Couleurs** : Palette interactive pour modifier le remplissage des formes.
- **Persistance** : Sauvegarde et chargement des dessins via la sérialisation Java.
- **Historique** : Annulation et rétablissement illimités des actions.

## 🛠️ Installation
1. Pré-requis : JDK 11+ et JavaFX.
2. Compilation :
   ```bash
   mvn clean install
