# Cours11-Injection
Injection de dependance permettant de donner a chque class ce dont elle a besoin


Ayant cree de multiple couche de responsabilite dans notre application:
- le ViewModel a besoin d'un userRepository qui lui meme a besoin d'un userDao qui pour etre cree necessite de recuperer la database qui pour etre recupere necessite un context

- Cela cree de l'interdependance entre les class qui doivent se soucier de recevoir des parametres non parcequ'elles en ont directement besoin mais parcequ'une class dont elles ont besoin en a la necessite

Objectif: Donner a chaque class directement les parametres dont elle a besoin uniquement (identifiable via l'equivalence entre paraemtre de constructor et variables de la class)

Realisation:
- On modifie les constructor de nos class pour que chaque class recoivent uniquement ce dont elle a besoin
- On cree une class Injection qui cree toute les instances necessaires a chaque class et leur fournis (par exemple recupere la database puis cree le dao qu'elle passe en parametre au repository que l'on cree)
- Dans la class Injection on cree une fonction provideViewModelFactory cree une instance de ViewModelFactory et lui fournit tout les parametres dont pourait avoir besoin les differents viewModel du projet
- On cree une class ViewModelFactory qui permet de creer les instances des differents viewModel du projet (et est capable de leur fournir tout  les parametres dont ils ont besoin dans leurs constructors)



ressources : Cours libre acces de openclassrooms : https://openclassrooms.com/fr/courses/4568746-gerez-vos-donnees-localement-pour-avoir-une-application-100-hors-ligne/5106570-architecturez-votre-application-a-laide-dun-viewmodel#/id/r-5143252
