#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "graph_adj_mat.h"


#define TAILLE_MAX 1000


int main(int argc, char *argv[])
{


     FILE* fichier = NULL;
    char chaine[TAILLE_MAX]; 
    int nbsommet;
    // int lowestNodeIndex=0;
    unsigned int firstint=1;
    unsigned int secondint=1;
    
 
    // fichier = fopen("test.col", "r");
    fichier = fopen("dsjc250.5.col", "r");


     if (fichier != NULL)
    {
        fgets(chaine, TAILLE_MAX, fichier);
        // printf("%s", chaine); 
        while (chaine[0]!='p') // On lit le fichier tant qu'on ne reçoit pas d'erreur (NULL)
        {
            fgets(chaine, TAILLE_MAX, fichier);
           
            // printf("%s", chaine); 
        }
        char *token;
        const char s[2] = " ";
        /* get the first token */
        token = strtok(chaine, s);
        token = strtok(NULL, s);
        token = strtok(NULL, s);

        nbsommet = atoi(token);
        
       
        // printf( " Le nombre de sommet est : %d\n", nbsommet );
        // fgets(firstIndexLine, TAILLE_MAX, fichier);

// if (fgets(chaine, TAILLE_MAX, fichier) != NULL)
// {
//     token = strtok(chaine, s);
//     token = strtok(NULL, s);
//     firstint = atoi(token);
//     token = strtok(NULL, s);
//     secondint = atoi(token);

//     lowestNodeIndex = firstint;
// }

        // while (firstIndexLine[0] != 'p')
        // {
        //     
        // }
        
        
        // token = strtok(chaine, s);
        //     token = strtok(NULL, s);
        //     lowestNodeIndex = atoi(token);

        // printf("Lowest: %d Firstint: %d Secondint: %d", lowestNodeIndex, firstint, secondint);

        graph *g1 = create_graph(nbsommet);
        // add_edge(g1, firstint, secondint);

        // int matrice[nbsommet+1][nbsommet+1];
        
        //Initialisation matrice à 0
        // for(int i=0; i<=nbsommet; i++) {
        //     for(int j=0;j<=nbsommet;j++) {
        //         matrice[i][j]=0;
        //     }
        // }
        
        while (fgets(chaine, TAILLE_MAX, fichier) != NULL) // On lit le fichier tant qu'on ne reçoit pas d'erreur (NULL)
        {
            token = strtok(chaine, s);
            token = strtok(NULL, s);
            firstint = atoi(token);
            token = strtok(NULL, s);
            secondint = atoi(token);

            // matrice[firstint][secondint]=1;
            // matrice[secondint][firstint]=1;

                add_edge(g1, firstint, secondint);
                // printf("\nAjout [%d][%d]\n", firstint, secondint);
                // add_edge(g1, secondint, firstint);

        }

        // for(int i=0; i<=nbsommet; i++) {
        //     for(int j=0;j<=nbsommet;j++) {
        //         printf("%d", matrice[i][j]);
        //         if(j==nbsommet){
        //             printf("\n");
        //         }
        //     }
        // }


        print_graph(g1);
    } 



    // graph *g1 = create_graph(5);

        // add_edge(g1, 0, 0);
        // add_edge(g1, 0, 1);
        // add_edge(g1, 0, 2);
        // add_edge(g1, 0, 3);
        // add_edge(g1, 0, 4);
        // add_edge(g1, 1, 3);
        // add_edge(g1, 2, 0);
        //     print_graph(g1);

        //     destroy_graph(g1);
    return 0;
}
