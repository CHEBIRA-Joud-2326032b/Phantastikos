# 🐺 Simulation de Colonie de Lycanthropes

## 📖 Description
Ce projet a été réalisé dans le cadre d’un travail pratique visant à modéliser une colonie de lycanthropes, en utilisant le langage **Java**.  
L’objectif principal est de simuler les interactions sociales et biologiques au sein de meutes, tout en respectant des règles liées à la hiérarchie, à la reproduction et aux comportements spécifiques des lycanthropes.

Cette simulation permet de découvrir et d’implémenter des concepts d’algorithmique avancée appliqués à un système dynamique et évolutif.

---

## ✨ Caractéristiques principales
### 🐺 Modélisation des lycanthropes
Chaque lycanthrope est défini par des attributs uniques comme :  
- **Âge**
- **Sexe**
- **Force**
- **Domination**
- **Rang hiérarchique** (α, β, ω, etc.)

### 🧑‍🤝‍🧑 Hiérarchie sociale
Les meutes respectent une organisation stricte :  
- Dirigées par un **couple dominant** (les α).  
- Les rangs inférieurs obéissent ou se rebellent.  

### 🌱 Reproduction et survie
- Les couples dominants assurent la continuité de la meute via la reproduction.
- Les jeunes grandissent et peuvent grimper dans la hiérarchie au fil du temps.

### 📣 Communication et hurlements
- Les hurlements servent à marquer la **domination**, appeler à la **soumission** ou signaler une présence.

### 🕒 Évolution temporelle
- Gestion du **vieillissement**, des **conflits internes**, de la **séparation** des meutes et de la création de nouvelles colonies.

---

## 🎯 Objectifs pédagogiques
- **Appliquer des concepts d’algorithmique avancée** dans un système dynamique.
- **Modéliser des interactions complexes** en programmation orientée objet.
- Développer une compréhension des **systèmes biologiques simulés**.

---

## 🛠️ Organisation du projet
- **Langage utilisé** : Java.  
- **Structure du projet** :
  - **Classes** : Pour modéliser les lycanthropes et leur comportement.
  - **Simulation** : Gérant les interactions sociales et temporelles.
  - **Tests unitaires** : Validant les fonctionnalités principales.


<br>
<br>
<br>
<br>
--------------------------------------------------------------------------------------------------------------------------------------------------------
<br>
<br>
<br>
<br>



# 🏥 PHANTASTIKOS

## 📖 Description
*Fantasy Hospital* est une simulation d’hôpital fantastique, développée en **Java**, permettant de gérer des créatures surnaturelles. Ce projet modélise les interactions médicales, sociales et biologiques entre différentes espèces, ainsi que leur comportement dans des services spécialisés.  

L’objectif principal est de concevoir un système ludique et équilibré, tout en appliquant des concepts avancés de programmation orientée objet et d'algorithmique.

---

## ✨ Fonctionnalités principales

### 🧙 Les créatures
Le jeu gère plusieurs types de créatures :
- Elfes
- Nains
- Orques
- Hommes-bêtes
- Zombies
- Vampires
- Lycanthropes
- Reptiliens  

**Caractéristiques communes** :
- Nom complet, sexe, poids, taille, âge, moral et liste de maladies.

**Comportements** :
- Attendre (diminue le moral).
- Hurler ou s’emporter (en cas de moral bas).
- Tomber malade ou être soignée.
- Trépasser en cas de maladies graves.  

**Particularités** :
- Elfes et vampires démoralisent les autres en trépassant.
- Orques, hommes-bêtes, lycanthropes et vampires peuvent contaminer d'autres créatures.
- Zombies et vampires peuvent se régénérer après la mort.
- Certaines espèces patientent mieux en groupe, tandis que les créatures VIP (elfes, nains, vampires et reptiliens) perdent rapidement patience.

---

### 🦠 Les maladies
Les maladies incluent :
- Maladie débilitante chronique (MDC)
- Syndrome fear of missing out (FOMO)
- Dépendance aux réseaux sociaux (DRS)
- Porphyrie érythropoïétique congénitale (PEC)
- Zoopathie paraphrénique lycanthropique (ZPL)
- Autres maladies personnalisables  

**Caractéristiques des maladies** :
- Nom complet et abrégé.
- Niveau actuel et niveau maximal.  

**Actions possibles** :
- Diminuer ou augmenter leur niveau.
- Déterminer si une maladie atteint un stade létal.

---

### 🏥 Les services médicaux
Chaque service médical est dédié à un type de créature et dispose de :
- Un nom, une superficie, une capacité maximale.
- Une liste de créatures présentes.
- Un budget classé : inexistant, médiocre, insuffisant, faible.

**Types de services** :
- **Centres de quarantaine** : accueillent uniquement les créatures contagieuses et incluent un niveau d’isolation.
- **Cryptes** : réservées aux créatures régénérantes, avec des paramètres de ventilation et de température.

---

### 🩺 Les médecins
Chaque médecin appartient à une espèce et dispose des caractéristiques suivantes :
- Nom, sexe, âge.  

**Actions possibles** :
- Examiner et soigner les créatures dans un service.
- Réviser le budget d’un service médical.
- Transférer une créature d’un service à un autre.

---

### 🏢 L’hôpital fantastique
L’hôpital regroupe l’ensemble des services médicaux et médecins.  

**Caractéristiques** :
- Nom de l’hôpital.
- Nombre maximal de services médicaux.
- Liste des services et médecins disponibles.

**Fonctionnalités** :
- Afficher le nombre total de créatures et leur répartition.
- Gérer les événements temporels (maladies, moral, budget, etc.).
- Permettre à l’utilisateur de diriger un médecin pour accomplir un nombre limité d’actions.

---

## 🎯 Objectifs pédagogiques
- Maîtriser la programmation orientée objet et les concepts d’héritage et de polymorphisme.
- Modéliser des interactions complexes entre objets.
- Gérer un système dynamique avec des événements aléatoires.

---

## 🛠️ Organisation du projet
- **Langage utilisé** : Java.
- **Structure du code** :
  - Classes pour modéliser les créatures, maladies, médecins et services médicaux.
  - Simulations pour gérer l’évolution temporelle et les interactions.
  - Tests unitaires pour valider chaque fonctionnalité.

---

## ✍️ Auteurs
 Chebira Joud, Egea Allan, Douchy Maxime 
