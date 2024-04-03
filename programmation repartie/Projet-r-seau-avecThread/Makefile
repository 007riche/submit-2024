CC=gcc
CFLAGS= -Wall


# graph_adj_mat.o: graph_adj_mat.c 
# 	$(CC) $(CFLAGS) -c graph_adj_mat.c 

# main : main.c graph_adj_mat.o
# 	$(CC) $(CFLAGS) main.c graph_adj_mat.o -o main


BIN= main

OBJ= graph_adj_mat.o


%.o: %.c
	$(CC) $(CFLAGS) -c $^ -o $@

$(BIN): $(OBJ)
	$(CC) $(CFLAGS) $(BIN).c $^ -o $@

vis: $(BIN)
	./$(BIN) | dot -Tpdf > vis.pdf


clean: 
	$(RM) -r $(BIN) $(OBJ) main *.dSYM vis.pdf graph_adj_mat.o  