#README : A propos de la version 28/02/2018
_____
Le programme **se lance via la classe LauncherIHM** dans le package *"main/java/IHM/"*.


Cette classe crée une nouvelle instance de "WelcomeFrame" qui est la page d'acceuil. 


- En cliquant sur "Jouer" une nouvelle instance de **TirageFrame** est créée
- Les autres botons ne sont pas encore implémentés

La classe **TirageFrame** affiche la liste des tirages disponibles dans le fichier csv fourni. Il est possible, via le menu, de retourner à la page d'acceuil *Go back to previous menu* ou de générer une image en sélectionnant au moins une ligne avant de cliquer sur *Recup données* -> création d'une instance de **FractaleFrame**.

A la création du dessin une boite de dialogue s'affiche pour indiquer les options clavier. A savoir zoomer ou se translater. 

Le menu offre la possibilité de retourner à la sélection des tirages *Go back to previous menu* ou de sauvegarder *save* l'image, cette dernière s'enregistrant au format png dans le package *"main/Images/"*

_____


##PROCHAINS OBJECTIFS :

- **Partie IHM** : 
    - Donner la possibilité de choisir ses propres numéros
    - Ajouter le choix de générer des fractales de Julia ou bien un MANDELBROT ou bien etc...


- **Partie Fractales** : 
    - Modifier le dégradé de couleurs des images 
    - Ameliorer l'utilisation des paramètres B1 à E2

- ** Tests** : Finir et intégrer les tests dans le programme

