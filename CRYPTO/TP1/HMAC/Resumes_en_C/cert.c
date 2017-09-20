// -*- coding: utf-8 -*-

#include <stdio.h>
#include <openssl/sha.h>
#include <openssl/md5.h>


int detecter_corps(FILE *fichier){
  char c;
  char buff[1024];
  int position = 0; 

 /* while((c = fgetc(fichier)) != EOF){
    printf("%c", c);
    if((int) c == 13)
      if(((c = fgetc(fichier)) != EOF) && (int) c == 10){
        position++;
        if(((c = fgetc(fichier)) != EOF) && (int) c == 13){
          position++;
          if(((c = fgetc(fichier)) != EOF) && (int) c == 10)
            return position++;
        }
      }
    
    position++;
  }*/

  while(1){
    fgets(buff, 255, fichier);

    if(buff[0] == 13 && buff[1] == 10)
      break;
      
    position += strlen(buff);
  }

  return position;
}

int cert(){
    int i;
    unsigned char resume_md5[MD5_DIGEST_LENGTH];
    //unsigned char buffer[1024];
    char buffer[255];
    int nb_octets_lus = 0;

    // On ouvre le fichier
    char *nom_du_fichier="../email1.txt";
    FILE *fichier = fopen (nom_du_fichier, "r");
    if (fichier == NULL) {
      printf ("Le fichier %s ne peut pas être ouvert.\n", nom_du_fichier);
      return 0;
    }

    MD5_CTX contexte;
    MD5_Init (&contexte);

    int corps_pos = detecter_corps(fichier);
    printf("\n\n Pos = %d", corps_pos);

    fseek(fichier, corps_pos, SEEK_SET);

    while(fgets(buffer, 255, fichier) != NULL){
      printf("%s", buffer);
        nb_octets_lus += strlen(buffer);
    }

    printf("\nBuffer(%d) : \n\n %s \n",nb_octets_lus, buffer);

    MD5_Update(&contexte, buffer, nb_octets_lus-4);

    MD5_Final (resume_md5, &contexte);
    printf("Le résumé MD5 du corps du fichier email1.txt vaut: 0x");
    for(i = 0; i < MD5_DIGEST_LENGTH; i++) printf("%02x", resume_md5[i]);
    printf("\n"); 

    // printf("\n\n\n Buffer : \n\n %s \n", buffer);
   /* MD5_CTX contexte;
    MD5_Init (&contexte); // Initialisation de la fonction de hachage
    nb_octets_lus = fread (buffer, 1, 1, fichier);   // Lecture du premier morceau
    while (nb_octets_lus != 0) {
      printf("-- %s \n\n", buffer);
      MD5_Update (&contexte, buffer, nb_octets_lus);                    // Digestion du morceau
      nb_octets_lus = fread (buffer+100, 1, 1, fichier); // Lecture du morceau suivant
    }
    fclose (fichier);
    MD5_Final (resume_md5, &contexte);
    printf("Le résumé MD5 du fichier \"butokuden.jpg\" vaut: 0x");
    for(i = 0; i < MD5_DIGEST_LENGTH; i++) printf("%02x", resume_md5[i]);
    printf("\n"); */
    
    return 0;
}

int main()
{
  cert();
  return 0;
}

/*
> make
> ./resumes
Le résumé MD5 du fichier "butokuden.jpg" vaut: 0xaeef572459c1bec5f94b8d62d5d134b5
> cat butokuden.jpg | md5
aeef572459c1bec5f94b8d62d5d134b5
*/
