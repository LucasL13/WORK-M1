/**
    Nom du binôme : LOIGNON Lucas et FAUCONNIER Axel

    Description de la classe : Classe abstraite qui décrit le comportement général d'un client

    Fonctionnalités :
        -> Se connecte au serveur
        -> Recoit et envoie des messages pour le jeu
        -> Traite les messages reçus

    Dependances : ReseauxToolbox : une classe abstraite qui fournit des méthodes pour faciliter les communications réseaux

 **/

import java.net.Socket;

public abstract class Client extends ReseauxToolbox{

    protected boolean connecte;             // Indicateur de la réussite de la connexion
    protected Socket sock;                  // Socket pour la communication avec le serveur

    protected String serveurAddress;        // L'adresse du serveur
    protected int serveurPort;              // Le port du serveur

    protected boolean EOC;  // Fin de communication
    protected boolean SAL;  // Le serveur attend une lettre
    protected boolean EGW;  // Fin de partie : victoire
    protected boolean EGL;  // Fin de partie : défaite
    protected boolean EWC;  // Mauvais caractere envoyé
    protected boolean LWS;  // Precedente tentative : réussie
    protected boolean LWF;  // Precedente tentative : echouée
    protected boolean GET;  // Recuperer le mot caché

    // Une fonction pour établir la connexion avec le serveur de jeu
    protected abstract void se_connecter();

    // Une fonction qui analyse et traite le message reçu du serveur
    // Lit sur l'entrée standard et envoie au serveur lorsque ce dernier attend une lettre
    protected abstract void receive_message(String message);

    // Une fonction à implementer selon le client pour le déroulement et l'affichage de la partie
    protected abstract void jouer();

    // Une fonction à implementer selon le client pour fermer la socket et quitter le programme
    protected abstract void stopClient();

}
