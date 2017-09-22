// -*- coding: utf-8 -*-

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/sha.h>
#include <openssl/md5.h>


int detecter_corps(FILE *fichier){

  fseek(fichier, 0, SEEK_SET);

  int position = 0; 
  char *line; 
  size_t len = 0;
  ssize_t read;

  while ((read = getline(&line, &len, fichier)) != -1) {
    if(read == 2){
      position += 2;
      break;
    }
    position += read;
  }

  return position;
}

int extraire_corps(FILE *fichier, char **corps){
  int corps_pos = detecter_corps(fichier);
  int nb_octets_lus = 0;
  char *buffer = malloc(sizeof(char)*1024);

  fseek(fichier, corps_pos, SEEK_SET);

  char *line;
  size_t len = 0;
  ssize_t read;

  while ((read = getline(&line, &len, fichier)) != -1) {
    if(read != 2)
      strcpy(buffer+nb_octets_lus, line);
    nb_octets_lus += read;
  }

  *corps = malloc(sizeof(char) * strlen(buffer));
  strcpy(*corps, buffer);
  free(buffer);

  return nb_octets_lus;
}

void calculer_MD5(unsigned char *resume, char *text, int nb_octets_lus){
  MD5_CTX contexte;
  MD5_Init (&contexte);
  MD5_Update(&contexte, text, nb_octets_lus);
  
  MD5_Final (resume, &contexte);
}

void copy_debut_file(FILE *fichierToCopy, FILE* fichierCopy, char *copyName, int positionEntete){
  unsigned char buffer[1024];
  
  fichierCopy = fopen(copyName, "w");
  if(fichierCopy == NULL){printf("Erreur création fichier %s\n", copyName); return;}

  fseek(fichierToCopy, 0, SEEK_SET);
  int nb_octets_lus;
  nb_octets_lus = fread (buffer, 1, positionEntete-2, fichierToCopy);
  fwrite(buffer, 1, positionEntete-2, fichierCopy);

  fclose(fichierCopy);
}

void ajouter_champ(FILE *fichierCopy, char *copyName, char *secret, int positionEntete){
  int i;
  fichierCopy = fopen(copyName, "a+");
  printf("Position de l'entete : %d\n", positionEntete);
  char *champText = "X-UdC_authentique: ";
  fseek(fichierCopy, positionEntete, SEEK_SET);
  fwrite(champText, 1, strlen(champText), fichierCopy);
  fwrite(secret, 1, strlen(secret), fichierCopy);
}

int cert(){
    int i;
    unsigned char resume_md5[MD5_DIGEST_LENGTH];
    int nb_octets_lus;
    char *corps;
    char *secret = "c5dcb78732e1f3966647655229729843";

    FILE *fichier, *fichierCopy;

    char *nom_du_fichier="../email1.txt";    
    char *nom_du_fichier_copy = "../email1-secure.txt";

    fichier = fopen (nom_du_fichier, "r");
    if (fichier == NULL) {
      printf ("Le fichier %s ne peut pas être ouvert.\n", nom_du_fichier);
      return 0;
    }

    nb_octets_lus = extraire_corps(fichier, &corps);
    printf("Corps(%d) = %s\n", nb_octets_lus, corps);

    calculer_MD5(resume_md5, corps, nb_octets_lus);

    printf("Le résumé MD5 du corps du fichier email1.txt vaut: 0x");
    for(i = 0; i < MD5_DIGEST_LENGTH; i++) printf("%02x", resume_md5[i]);
    printf("\n"); 

    copy_debut_file(fichier, fichierCopy, nom_du_fichier_copy, detecter_corps(fichier));

    ajouter_champ(fichierCopy, nom_du_fichier_copy, secret, detecter_corps(fichier));
    
    return 0;
}

int main()
{
  cert();
  return 0;
}
