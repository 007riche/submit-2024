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
#include <string.h>


int main(int argc, char *argv[]) {
   int run = 1;
 
   
    printf("Test good\n");    
    int tid = getpid();
 int PROC_DIMMAC_NUM = 0;


    /* Etape 1 : créer une socket */   
    int ds = socket(PF_INET,SOCK_STREAM,0);
  if (ds == -1){
        printf("Client : pb creation socket\n");
        exit(1); // je choisis ici d'arrêter le programme car le reste
            // dépendent de la réussite de la création de la socket.
    }
    
  
    
  struct sockaddr_in client;
  client.sin_family = AF_INET;
  client.sin_addr.s_addr = INADDR_ANY;
  client.sin_port = htons(0);
  
  if(bind(ds,(struct sockaddr *) &client, sizeof(client)) < 0){
    perror("Client : erreur bind");
    close(ds); // je libère les ressources avant de terminer.
    exit(1); // je choisis de quitter le programme : la suite dépend de la réussite du nommage.
  }
	
    struct sockaddr_in adrServ;
    adrServ.sin_addr.s_addr = inet_addr("127.0.0.1");
    adrServ.sin_family = AF_INET;
    adrServ.sin_port = htons(1111);
    

    int lgAdr = sizeof(struct sockaddr_in);
        

    /* Etape 3 : envoyer une demande de connexion au serveur.*/
    int conn = connect(ds,(struct sockaddr *) &adrServ,lgAdr);
    // je traite les valeurs de retour

    
    if (conn <0){
        perror ("Client: pb au connect :");
        close (ds); // je libère les ressources avant de terminer.
        exit (1); // je choisi de quitter le programme : la suite dépend
            // de la réussite de l'envoi de la demande de connexion.
    }
    printf("Client : demande de connexion reussie \n");
  
   char m[50]; 
    int mesg_size;
    // Echange
    printf("CLIENT EN ATTENTE %i", tid);
while(send(ds,&tid,sizeof(int),0)<=0);
while((mesg_size = recv(ds, m, 50,0))>0) {
m[mesg_size] = '\0';
write(ds, m, strlen(m));
memset(m, 0, 50);
}


while(1);

}



   /* Etape 4 : envoyer un message au serveur. Ce message est une chaîne de caractères saisie au clavier. Vous pouvez utiliser une autre fonction pour la saisie. */

//     printf("saisir un message à envoyer (moins de 200 caracteres) \n");
//     char m[202]; 
//     fgets(m, sizeof(m), stdin); // copie dans m la chaîne saisie que
//                     // clavier (incluant les esaces et le
//                     // saut de ligne à la fin).
//     m[strlen(m)-1]  = '\0'; // je retire le saut de ligne                                      
//    int snd = send(ds,m,strlen(m)+1,0);
//      if(snd<0){
//         perror("client: ereur lors du send:");
//         close(ds);
//         exit(1);
//     }
//     if(snd==0){
//         printf("client: serveur deconnecter \n");
//         close(ds);
//         exit(1);
//     }
//     if(snd<strlen(m)+1){
//         printf("client: attention, je n'ai pas pu deposer q'une partie du message \n");
//     }

   
//     printf("Client : j'ai déposé %d octets \n", snd);

//     printf("Client : envoi fait, j'attends la reponse du serveur \n");
    // int reponse;
    // int rcv = recv (ds,&reponse,sizeof(int),0) ;
    // /* Traiter TOUTES les valeurs de retour (voir le cours ou la documentation). */
    // if (rcv<0){
    //     perror ( "Client: probleme recv :");
    //     close(ds);
    //     exit (1);
    // }
    // if (rcv==0){
    //     perror ( "Client: serveur out of reach :");
    //     close(ds);
    //     exit (1);
    // }

    //  while (run) {
//     printf("ATTENTE du client %i", tid);
//     sprintf(m, "%d", tid);
// while (send(ds,m, strlen(m)+1,0)<=0) {
//     printf("Tryin to send my process ID %i to server", tid);
// };
// sprintf(m, "%s", "");
// while (recv(ds,m,50*sizeof(char),0))
// {
//     printf("NEW MESSAGE FROM SERVER: %s",m);
// }

  // int newport = 1112 + atoi(argv[1]);

//    }

    // close (ds);
    // printf("SERVER : je termine\n");
// printf("Port client est le : %d\n", newport);

    /* Etape 2 : designer la socket du serveur : avoir une structure qui
        contient l'adresse de cette socket (IP + numéro de port. */