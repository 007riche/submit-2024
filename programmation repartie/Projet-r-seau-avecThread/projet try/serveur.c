#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
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


typedef struct myNet myNet;
typedef struct InpClientTh CL;

 struct myNet 
{
  pthread_t listenerThread;
  struct sockaddr_in server;
  pid_t clientsPID[1000]; // les ID  des PROCESSUS clients
int clientsDimmacNode[1000]; // les noeuds au format DIMMAC des clients
int servSockDesc;
int clientsSKDs[1000]; // les description des sockets des clients
pthread_t clientsHandlers[1000];
int runningStauts;
int filled;
int usedSize;
};

struct InpClientTh 
{
  myNet *res;
  int node;
};



void * clientSetup(CL *client) {
// myNet *targetNet = (myNet *) targetNetwork;
CL *proc = (CL *) client;
int sock = (int ) proc->res->clientsSKDs[proc->node];
printf("Description du socket Nouvel arrivant: %i\n", sock);
char m[50]; 
int mesg_size;
mesg_size = recv(proc->res->clientsSKDs[proc->node], m, 50,0);
if(atoi(m))
{ int PID = atoi(m);
  printf("MESSAGE RECU: PID: %i \n", PID);
  memset(m, 0, 50);
}

}

int main(void) {
    FILE* fichier = NULL;
    char chaine[TAILLE_MAX]; 
    int nbsommet=10;
    unsigned int firstint;
    unsigned int secondint;
    int run = 1;
    

      //*********************************************************PARSEUR*********************************************************

    //*************************************************************************************************************
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


  myNet reseau;
  reseau.runningStauts=1;
  reseau.filled = 0;
 reseau.servSockDesc = socket(PF_INET,SOCK_STREAM,0);
  //  if (ds == -1){
    if (reseau.servSockDesc == -1){
    perror("Serveur : probleme creation socket : ");
    exit(1); // je choisis ici d'arrêter le programme car le reste
	     // dépendent de la réussite de la création de la socket.
  }
 printf("Serveur: creation de la socket : ok\n");

  reseau.server.sin_family = AF_INET;
reseau.server.sin_addr.s_addr = inet_addr("127.0.0.1");
reseau.server.sin_port = htons(1111) ;

  if(bind(reseau.servSockDesc,(struct sockaddr *) &reseau.server, sizeof(reseau.server)) < 0){
    perror("Serveur : erreur bind");
    close(reseau.servSockDesc); 
    exit(1); 
  }
  printf("Serveur: nommage : ok\n");
  int ecoute = listen(reseau.servSockDesc,2);
  if (ecoute < 0){
    printf("Serveur : je suis sourd(e)\n");
    close (reseau.servSockDesc);
    exit (1);
  } 
 
  printf("Serveur: mise en écoute : ok\n");


  printf("Serveur : EN ATTENTE DE CLIENTS \n"); 

  struct sockaddr_in adCv ; 
  socklen_t lgCv = sizeof (struct sockaddr_in);

  int dsCv;
  while((dsCv = accept(reseau.servSockDesc,(struct sockaddr *) &adCv,&lgCv))) {
if (dsCv < 0){ 
    perror ( "Serveur, probleme accept :");
    close(reseau.servSockDesc);
    exit (1);
  }
  printf("SERVER: le client %s:%d est connecté  \n",inet_ntoa(adCv.sin_addr),ntohs(adCv.sin_port));
  reseau.clientsSKDs[reseau.filled] = dsCv;
  reseau.clientsDimmacNode[reseau.filled] = reseau.filled+1;
  CL currentClient ; 
    currentClient.res = &reseau;
    currentClient.node = reseau.filled;
 
  if(pthread_create(&reseau.clientsHandlers[reseau.filled], NULL, clientSetup, (void*) &currentClient) < 0) {
perror("Impossible de creer un nouveau thread");
return 1;
  }
reseau.filled ++;
  }
  

 
  


// pthread_create (&reseau.listenerThread, NULL, clientSetup, &reseau);

// pthread_join (reseau.listenerThread, NULL);


  
// char rep=' ';
// printf("0: PPOUR TERMINER LE RESEAU\n");
// do {
//   rep=scanf("%c", rep);
//   printf("\n0: PPOUR TERMINER LE RESEAU\n");
// }
// while(rep='0');

// reseau.runningStauts=rep;
// puts("FERMETURE DU SERVEUR");

  // return EXIT_SUCCESS;
 
 // close(ds);
  // return 0;


}









// while (targetNet->runningStauts)
// {
  // 
  //  struct sockaddr_in adCv ; 
  // socklen_t lgCv = sizeof (struct sockaddr_in);
// 
  // int dsCv = accept(targetNet->servSockDesc,(struct sockaddr *) &adCv,&lgCv);
  // if (dsCv < 0){ 
    // perror ( "Serveur, probleme accept :");
    // close(targetNet->servSockDesc);
    // exit (1);
  // }
// 
//  
  // printf("SERVER: le client %s:%d est connecté  \n",inet_ntoa(adCv.sin_addr),ntohs(adCv.sin_port));
// 
// if(targetNet->runningStauts == 0)
// {
  // close(targetNet->servSockDesc);
  // pthread_exit(NULL);
// }
// }




 /*if (argc!=2){
        printf("Utilisation : %s nombreDeSites\n", argv[0]); int argc, char *argv[]
        exit(1);
    }
	*/
  
   


 //  struct sockaddr_in tabAd[5];
  


  

     //*********************************************************SERVEUR*********************************************************

    
 

 
  // int ds = socket(PF_INET,SOCK_STREAM,0);
//  reseau.servSockDesc = socket(PF_INET,SOCK_STREAM,0);
//   //  if (ds == -1){
//     if (reseau.servSockDesc == -1){
//     perror("Serveur : probleme creation socket : ");
//     exit(1); // je choisis ici d'arrêter le programme car le reste
// 	     // dépendent de la réussite de la création de la socket.
//   }
//  printf("Serveur: creation de la socket : ok\n");
// //  &ds;
//   // struct sockaddr_in server;
//   // server.sin_family = AF_INET;
//   // server.sin_addr.s_addr = inet_addr("127.0.0.1");
//   // server.sin_port = htons(1111) ;
//   // pthread_t clientsHandlers[nbsommet];
//   // int clientSKDS[nbsommet];
//   reseau.server.sin_family = AF_INET;
// reseau.server.sin_addr.s_addr = inet_addr("127.0.0.1");
// reseau. server.sin_port = htons(1111) ;
// //   unsigned int 
//   if(bind(reseau.servSockDesc,(struct sockaddr *) &reseau.server, sizeof(reseau.server)) < 0){
//     perror("Serveur : erreur bind");
//     close(reseau.servSockDesc); // je libère les ressources avant de terminer.
//     exit(1); // je choisis de quitter le programme : la suite dépend de la réussite du nommage.
//   }

//   // je continue à alimenter le programme avec traces d'exécution
//   printf("Serveur: nommage : ok\n");


//   int ecoute = listen(reseau.servSockDesc,2);
//   if (ecoute < 0){
//     printf("Serveur : je suis sourd(e)\n");
//     close (reseau.servSockDesc);
//     exit (1);
//   } 
 
//   // je continue à alimenter le programme avec traces d'exécution
//   printf("Serveur: mise en écoute : ok\n");


//   printf("Serveur : j'attends la demande d'un client (accept) \n"); 

  // clientSKDS[i] = dsCv;
  // char buffer[500];
 
  // int rcv = recv(dsCv,buffer,sizeof(buffer),0) ;
  // close (dsCv);


//  /* Traiter TOUTES les valeurs de retour (voir le cours ou la documentation). */
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


//   printf("Serveur : j'ai recu %d octets \n", rcv);
//   printf("Serveur : contenu du message : %s \n", buffer);

//   int snd = send(dsCv,&rcv,sizeof(int),0);
  
//   /* Traiter TOUTES les valeurs de retour (voir le cours ou la documentation). */
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
//   printf("Serveur : fin du dialogue avec le client\n");



