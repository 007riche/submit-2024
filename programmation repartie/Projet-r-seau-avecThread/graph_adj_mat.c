#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#include "graph_adj_mat.h"

struct graph_adj_mat
{
    int numnodes;
    bool **edges;
};


graph *create_graph(int numnodes) {
    // printf("\n Called Create graph\n");
        graph *g = malloc(sizeof(*g));
        if (g==NULL)
        {
            return NULL;
        }

        g->numnodes = numnodes;
       // Matrix allocation
        g->edges = calloc(sizeof(bool*), g->numnodes);
        if (g->edges ==NULL) 
        {
            free(g);
            return NULL;
        }

        for (int i = 1; i <= g->numnodes ; i++)
        {
            g->edges[i] = calloc(sizeof(bool), g->numnodes);
            if (g->edges[i] == NULL)
            {
                destroy_graph(g);
                return NULL;
            }
            
        }
        // printf("\n GRAPH SUCCESSFULLY \n");
        return g;
        
}


void destroy_graph(graph *g){
            if (g->edges == NULL)
        {
            return;
        }
        for (int i = 1; i <= g->numnodes; i++)
        {
            if (g->edges[i] != NULL)
            {
                free(g->edges[i]);
            } 
            // else {
        // printf("\n ERROR ON EDGE\n\n");
        // }
        
        }
        
         free(g->edges);


        // else {
        // printf("\n ERROR\n\n");
        // }

}

void print_graph(graph *g) {
        printf("digraph {\n");
        for (int from = 1; from <= g->numnodes ; from++)
        {
            for (int to  = 1; to <= g->numnodes; to++)
            {
                if (g->edges[from][to])
                {
                    printf("%d -> %d; \n", from, to);
                }
            }
            
        }
        printf("}\n");

}


bool add_edge(graph *g, unsigned int from_node, unsigned int to_node) {
        assert(g != NULL);
    assert(from_node <= g->numnodes);
    assert(to_node <= g->numnodes);
        
        if (has_edge(g, from_node, to_node)) 
        {
            return false;
        }

        g->edges[from_node][to_node] = true;
        return true;

}

bool has_edge(graph *g, unsigned int from_node, unsigned int to_node) {
    assert(g != NULL);
    assert(from_node <= g->numnodes);
    assert(to_node <= g->numnodes);
    return g->edges[from_node][to_node];
}
