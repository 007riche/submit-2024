 
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


int main(int argc, char *argv[]) {
    if (argc!=2){
        printf("Utilisation : %s nombreDeSites\n", argv[0]);
        exit(1);
    } 
 
 
    int exit_status;
    int nbProcess = atoi(argv[1]);
	
    //kill pour ne pas avoir l'erreur d'adress already in use
    exit_status = system("kill -9 $(lsof -ti :1111)");
    exit_status = system("./serveur &");

    for(int i = 0; i<nbProcess; i++) {
    	char buf[256];
        snprintf(buf, sizeof(buf), "./client %d",i);
        exit_status = system(buf);
        if(exit_status==-1) {
            perror("Failed opening terminal\n");
            exit(1);
        }
    }
}
