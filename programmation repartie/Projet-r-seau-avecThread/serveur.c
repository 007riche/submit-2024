#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#include <string.h>
#include <assert.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <arpa/inet.h>

#define TAILLE_MAX 1000

int main(int argc, char *argv[]) {
    /*if (argc!=2){
        printf("Utilisation : %s nombreDeSites\n", argv[0]);
        exit(1);
    }
	*/
    //*********************************************************PARSEUR*********************************************************

    FILE* fichier = NULL;
    char chaine[TAILLE_MAX]; 
    int nbsommet;
    unsigned int firstint;
    unsigned int secondint;
    
 
    // fichier = fopen("dsjc250.5.col", "r");
    fichier = fopen("test.col", "r");

    if (fichier != NULL)
    {
        fgets(chaine, TAILLE_MAX, fichier);
        //printf("%s", chaine); 
        while (chaine[0]!='p') // On lit le fichier tant que les lignes commencent par p
        {
            fgets(chaine, TAILLE_MAX, fichier);
            //printf("%s *****************", chaine); 
        }
        char *token;
        const char s[2] = " ";
        /* get the first token */
        token = strtok(chaine, s);
        token = strtok(NULL, s);
        token = strtok(NULL, s);

        nbsommet = atoi(token);
        
        printf( " Le nombre de sommet est : %d\n", nbsommet );
        
        int matrice[nbsommet+1][nbsommet+1];

        //Initialisation matrice à 0
        for(int i=0; i<=nbsommet; i++) {
            for(int j=0;j<=nbsommet;j++) {
                matrice[i][j]=0;
            }
        }
        
        while (fgets(chaine, TAILLE_MAX, fichier) != NULL) // On lit le fichier tant qu'on ne reçoit pas d'erreur (NULL)
        {
            token = strtok(chaine, s);
            token = strtok(NULL, s);
            firstint = atoi(token);
            token = strtok(NULL, s);
            secondint = atoi(token);

            matrice[firstint][secondint]=1;
            matrice[secondint][firstint]=1;
            //printf("\nAjout [%d][%d]\n", firstint, secondint);
        }

        for(int i=0; i<=nbsommet; i++) {
            for(int j=0;j<=nbsommet;j++) {
                printf("%d", matrice[i][j]);
                if(j==nbsommet){
                    printf("\n");
                }
            }
        }

    } 

   /*char buf[256];
   snprintf(buf, sizeof(buf), "./launcher %d",nbsommet);
   int exit_status = system(buf);*/


     //*********************************************************SERVEUR*********************************************************


    int sizeTab;
   struct sockaddr_in tabAd[5];
  // je passe en paramètre un numero de port pour la socket
  // d'écoute. Je teste donc le passage de paramètres

//   if (argc<2){
//     printf("utilisation: %s numero_port\n", argv[0]);
//     exit(1);
//   }
  
  /* Etape 1 : créer une socket (la socket qui permettra de recevoir
     les demandes de connexion.*/
 
  int ds = socket(PF_INET,SOCK_STREAM,0);

  /*
  int option = 1;
  setsockopt(ds, SOL_SOCKET, SO_REUSEADDR, &option, sizeof(option));

    int tr=1;
    // kill "Address already in use" error message
    if (setsockopt(ds,SOL_SOCKET,SO_REUSEADDR,&tr,sizeof(int)) == -1) {
        perror("setsockopt");
        exit(1);
    }
   */

  /* /!\ : Il est indispensable de tester les valeurs de retour de
     toutes les fonctions et agir en fonction des valeurs possibles.*/
  if (ds == -1){
    perror("Serveur : probleme creation socket : ");
    exit(1); // je choisis ici d'arrêter le programme car le reste
	     // dépendent de la réussite de la création de la socket.
  }
  

  // J'alimente le programme avec traces d'exécution pour observer ce
  // qui se produit à l'exécution et mieux localiser les erreurs. 
  printf("Serveur: creation de la socket : ok\n");


  // Je peux déjà tester l'exécution de cette étape avant de passer à la suite.
  
  /* Etape 2 : nommer la socket. Elle aura une ou des IP de la machine
     sur laquelle le programme sera exécuté et le numéro de port passé
     en paramètre*/

  int portServ = 1111;

  struct sockaddr_in server;
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = inet_addr("127.0.0.1");
  server.sin_port = htons(portServ) ;
  
   /*char buf[256];
   snprintf(buf, sizeof(buf), "kill $(lsof -ti :%d)",portServ);
   int exit_status = system(buf);*/

  if(bind(ds,(struct sockaddr *) &server, sizeof(server)) < 0){
    perror("Serveur : erreur bind");
    close(ds); // je libère les ressources avant de terminer.
    exit(1); // je choisis de quitter le programme : la suite dépend de la réussite du nommage.
  }

  // je continue à alimenter le programme avec traces d'exécution
  printf("Serveur: nommage : ok\n");


  // Je peux tester l'exécution de cette étape avant de passer à la suite.


  /* Etape 3 : mise en ecoute de la socket nommée. Cette opération
     dédie la socket à la réception des demandes de connexion. Pour
     cet exercice, limiter la file des demandes de connexions à 5. */
  
  int ecoute = listen(ds,2);
  if (ecoute < 0){
    printf("Serveur : je suis sourd(e)\n");
    close (ds);
    exit (1);
  } 
 
  // je continue à alimenter le programme avec traces d'exécution
  printf("Serveur: mise en écoute : ok\n");


  // Je peux tester l'exécution de cette étape avant de passer à la suite.
 
  /* etape 4 : attendre et traiter une demande de connexion d'un
     client. Rappel : lorsque le serveur accepte une demande de
     connexion, il crée une nouvelle socket. Cette dernière est
     connectée à celle du client et elle est à utiliser pour
     communiquer avec lui.*/

  // avant un appel à une fonction bloquante, il est intéressant
  // d'afficher un message le signalant. Vous saurez ainsi à quelle
  // étape de l'exécution le blocage se produit.


  while(5){
     
   printf("Serveur : j'attends la demande d'un client (accept) \n"); 
   
   struct sockaddr_in adCv ; // pour obtenir l'adresse du client accepté.
   socklen_t lgCv = sizeof (struct sockaddr_in);

   int dsCv = accept(ds,(struct sockaddr *) &adCv,&lgCv);
   if (dsCv < 0){ // je pense toujours à traiter les valeurs de retour.
      perror ( "Serveur, probleme accept :");
      close(ds);
      exit (1);
   }
   /* je peux afficher l'adresse de la socket du client accepté :
      adresse IP et numéro de port de la structure adCv. Attention à
      faire les conversions du format réseau vers le format
      hôte. Utiliser la fonction inet_ntoa(..) pour l'IP.*/
   printf("Serveur: le client %s:%d est connecté  \n",inet_ntoa(adCv.sin_addr),ntohs(adCv.sin_port));
   // Je peux tester l'exécution de cette étape avant de passer à la suite.
   
   /* Etape 5 : réception d'un message de type chaîne de caractères */
   
   // char buffer[500];
   // /* attendre un message dont la taille maximale est 500 octets. Pour
   //    cet exercice, il est demandé de ne faire qu'un seul appel à recv
   //    pour recevoir un message. */
   // int rcv = recv(dsCv,buffer,sizeof(buffer),0) ;

   // /* Traiter TOUTES les valeurs de retour (voir le cours ou la documentation). */
   //    if (rcv<0){
   //       perror ( "Serveur: probleme recv :");
   //       close(ds);
   //       exit (1);
   //    }
   //    if (rcv==0){
   //       perror ( "Serveur: client out of reach :");
   //       close(ds);
   //       exit (1);
   //    }


   // /* Afficher le nombre d'octets EFFECTIVEMENT reçus. : /!\ Faire la
   //    différence entre le nombre d'octets qu'on demande à extraire
   //    depuis le buffer de réception d'une socket et le nombre d'octets
   //    qu'on a effectivement réussi à extraire.*/

   // printf("Serveur : j'ai recu %d octets \n", rcv);
   // printf("Serveur : contenu du message : %s \n", buffer);

   // // Je peux tester l'exécution de cette étape avant de passer à la suite.
   
   // /* Etape 6 : répondre au client en lui envoyant le nombre d'octets
   //    effectivement reçus à l'étape 5. Pour cet exercice, faire un seul
   //    appel de la fonction send.*/ 
   // int snd = send(dsCv,&rcv,sizeof(int),0);
   
   // /* Traiter TOUTES les valeurs de retour (voir le cours ou la documentation). */
   //    if(snd<0){
   //       perror("Serveur: ereur lors du send:");
   //       close(ds);
   //       exit(1);
   //    }
   //    if(snd==0){
   //       printf("Serveur: client deconnecter \n");
   //       close(ds);
   //       exit(1);
   //    }
   //    if(snd<sizeof(int)){
   //       printf("Serveur: attention, je n'ai pas pu deposer q'une partie du message \n");
   //    }


      
   // /* Etape 7 : fermeture de la socket du client */ 
   // printf("Serveur : fin du dialogue avec le client\n");
   // close (dsCv);

  }
  
  //Etape 8 : pour cet exercice, je ne traite qu'un client, donc, je termine proprement.
 // close (ds);
  printf("Serveur : je termine\n");
  
   


}
