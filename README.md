# Gestion des dépenses de voyage

Exercice du cours **420-A17 — Développement d'applications mobiles Android**
(Collège de Bois-de-Boulogne).

Une entreprise automatise la gestion des dépenses de voyage de ses employés.
L'employé saisit trois montants — vol, hébergement, restaurant — et l'application
calcule la contribution versée par l'entreprise selon le montant total.

## Règles de calcul

| Montant total | Contribution |
|---|---|
| supérieur à 2 000 $ | 10 % |
| de 1 000 $ à 2 000 $ inclus | 5 % |
| inférieur à 1 000 $ | 1,5 % |

## Prototype retenu

Quatre écrans reliés par des intents, avec persistance SQLite :
MenuActivity
├── EditionActivity ──putExtra──► ResultatActivity ──insert──► SQLite
└── ListeActivity ◄──select──────────────────────────────────── SQLite

## Structure

| Paquet | Rôle |
|---|---|
| `com.example.gestiondepenses` | les quatre activités |
| `modele` | `Depense` — conteneur d'une ligne de la table |
| `utils` | `Utilitaire` — calculs et formatage, méthodes statiques |
| `dao` | `DbHelper` (schéma) et `DepenseAdapter` (insertion, lecture) |

## Table `DEPENSES`

| Colonne | Type |
|---|---|
| `_id` | INTEGER PRIMARY KEY AUTOINCREMENT |
| `MontantVol` | REAL |
| `MontantHebergement` | REAL |
| `MontantRestaurant` | REAL |
| `MontantTotal` | REAL |
| `Contribution` | REAL |
| `DateSaisie` | TEXT — format ISO `yyyy-MM-dd HH:mm:ss` |

La date est stockée en `TEXT` : SQLite ne possède pas de type `DATE`, et le format
ISO présente l'avantage de se trier correctement en ordre alphabétique.

## Validation

Chaque montant doit être renseigné et positif ; le total doit être supérieur à zéro.
La virgule décimale est acceptée en saisie.

## Environnement

Java · minSdk 24 · Gradle Kotlin DSL · SQLite