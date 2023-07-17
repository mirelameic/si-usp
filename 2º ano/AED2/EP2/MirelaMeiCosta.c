/*

Mirela Mei - 11208392

*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define t 3
typedef struct node {
    int key[2 * t];
    struct node* children[2 * t + 1];
    int numKeys;
    bool leaf;
} Node;

typedef struct {
    Node* root;
} BTree;

int main(int argc, char* argv[]);
void readInputFile(BTree* tree, char inputFile[], char outputFile[]);
void printTree(BTree* tree, Node* x, FILE* output);
bool createTree(BTree* tree);
void splitChild(Node* x, int i, Node* y);
void insertNonFull(Node* x, int k);
void insertTree(BTree* tree, int k);
Node* findPredecessor(Node* y);
Node* findSuccessor(Node* y);
int findKeyPosition(Node* x, int k);
Node* findSubTree(Node* x, int k);
void removeKey(Node* x, int k);
void removeNode(Node* x, int k);
void removeRoot(BTree* tree, int k);
void removeTree(BTree* tree, int k);

int posicaoSubTree = 0;

int main(int argc, char* argv[]) {
    if (argc != 3) {
        printf("Uso: %s input_file.txt output_file.txt\n", argv[0]);
        return 1;
    }
    BTree tree;
    readInputFile(&tree, argv[1], argv[2]);
    return 0;
}

void readInputFile(BTree* tree, char inputFile[], char outputFile[]) {
    char operation;
    int value;

    FILE* input = NULL;
    input = fopen(inputFile, "r");
    FILE* output = NULL;
    output = fopen(outputFile, "w");

    bool treeCreated = createTree(tree);

    if (input && treeCreated) {
        while (fscanf(input, "%c %d", &operation, &value) != EOF) {
            switch (operation) {
                case 'i':
                    insertTree(tree, value);
                    break;
                case 'r':
                    removeTree(tree, value);
                    break;
                case 'p':
                    printTree(tree, tree->root, output);
                    fprintf(output, "\n");
                    break;
                case 'f':
                    return;
                    break;
            }
        }
    }
    fclose(input);
    fclose(output);
}

void printTree(BTree* tree, Node* x, FILE* output) {
    int i;
    if (x->numKeys == 0 && x == tree->root) {
        fprintf(output, "Vazia");
        return;
    }

    fprintf(output, "(");
    if (x->leaf) {
        for (i = 1; i <= x->numKeys; i++) {
            if (i == x->numKeys)
                fprintf(output, "%d", x->key[i]);
            else
                fprintf(output, "%d ", x->key[i]);
        }
    } else {
        for (i = 1; i <= x->numKeys; i++) {
            printTree(tree, x->children[i], output);
            fprintf(output, " %d ", x->key[i]);
        }
        printTree(tree, x->children[i], output);
    }

    fprintf(output, ")");
}

bool createTree(BTree* tree) {
    Node* x;
    if (!(x = (Node*)malloc(sizeof(Node))))
        return false;
    x->leaf = true;
    x->numKeys = 0;
    tree->root = x;
    return true;
}

void splitChild(Node* x, int i, Node* y) {
    int j;
    Node* z;
    if ((z = (Node*)malloc(sizeof(Node)))) {
        z->leaf = y->leaf;
        z->numKeys = t - 1 + y->leaf;

        if (y->leaf) {
            z->key[1] = y->key[t];
            j = 2;
        } else {
            j = 1;
        }

        for (j; j <= t - 1 + y->leaf; j++) {
            if (y->leaf)
                z->key[j] = y->key[j + t - 1];
            else
                z->key[j] = y->key[j + t];
        }

        if (!y->leaf) {
            for (j = 1; j <= t; j++)
                z->children[j] = y->children[j + t];
        }

        y->numKeys = t - 1;

        for (j = x->numKeys + 1; j >= i + 1; j--) {
            x->children[j + 1] = x->children[j];
        }

        x->children[i + 1] = z;

        for (j = x->numKeys + 1; j >= i; j--) {
            x->key[j + 1] = x->key[j];
        }

        x->key[i] = y->key[t];
        x->numKeys++;
    }
}

void insertNonFull(Node* x, int k) {
    int i = x->numKeys;
    if (x->leaf) {
        while (i >= 1 && k < x->key[i]) {
            x->key[i + 1] = x->key[i];
            i--;
        }
        x->key[i + 1] = k;
        x->numKeys++;
    } else {
        while (i >= 1 && k < x->key[i]) {
            i--;
        }
        i++;
        if (x->children[i]->numKeys == 2 * t - 1) {
            splitChild(x, i, x->children[i]);
            if (k > x->key[i])
                i++;
        }
        insertNonFull(x->children[i], k);
    }
}

void insertTree(BTree* tree, int k) {
    Node* root = tree->root;
    Node* s;
    if (root->numKeys == 2 * t - 1) {
        if ((s = (Node*)malloc(sizeof(Node)))) {
            tree->root = s;
            s->leaf = false;
            s->numKeys = 0;
            s->children[1] = root;
            splitChild(s, 1, root);
            insertNonFull(s, k);
        }
    } else
        insertNonFull(root, k);
}

Node* findPredecessor(Node* y) {
    if (!y->leaf)
        findPredecessor(y->children[y->numKeys + 1]);
    return y;
}

Node* findSuccessor(Node* y) {
    if (!y->leaf)
        findSuccessor(y->children[1]);
    return y;
}

int findKeyPosition(Node* x, int k) {
    int i = x->numKeys;
    while (i >= 1) {
        if (k == x->key[i])
            return i;
        i--;
    }
    return -1;
}

Node* findSubTree(Node* x, int k) {
    Node* subTree = NULL;
    int i = 1;
    while (i <= x->numKeys) {
        if (k < x->key[i]) {
            subTree = x->children[i];
            posicaoSubTree = i;
            return subTree;
        } else if (k == x->key[i] || (k > x->key[i] && i == x->numKeys)) {
            subTree = x->children[i + 1];
            posicaoSubTree = i + 1;
            return subTree;
        } else
            i++;
    }
    return subTree;
}

void removeKey(Node* x, int k) {
    int position = findKeyPosition(x, k);
    int j;
    if (position == x->numKeys) {
        x->numKeys--;
    } else {
        for (j = position; j < x->numKeys; j++) {
            x->key[j] = x->key[j + 1];
        }
        x->numKeys--;
    }
}

void removeNode(Node* x, int k) {
    int keyPosition = findKeyPosition(x, k);
    int j;

    // Case 0
    if (x->leaf && keyPosition == -1)
        return;

    // Case 1
    else if (keyPosition != -1 && x->leaf) {
        removeKey(x, k);
    }

    // Case 2
    else if (!x->leaf && keyPosition != -1) {
        Node* y = x->children[keyPosition];
        Node* z = x->children[keyPosition + 1];

        if (y->numKeys >= t) {  // 2a
            Node* predecessor = findPredecessor(y);
            x->key[keyPosition] = predecessor->key[predecessor->numKeys];

            if (y->leaf) {
                Node* successor = findSuccessor(z);
                successor->key[1] = x->key[keyPosition];
                z->children[1] = predecessor->children[predecessor->numKeys + 1];
            }

            removeNode(y, predecessor->key[predecessor->numKeys]);
        } else if (z->numKeys >= t) {  // 2b
            Node* successor = findSuccessor(z);
            if (z->leaf) {
                x->key[keyPosition] = successor->key[2];
                removeNode(successor, successor->key[1]);
            } else {
                x->key[keyPosition] = successor->key[1];
                removeNode(successor, successor->key[1]);
            }
        } else {  // 2c
            int i = 1;
            for (i; i < z->numKeys; i++) {
                z->key[i] = z->key[i + 1];
            }
            i = 1;
            for (i; i <= z->numKeys; i++) {
                z->key[i] = z->key[i + 1];
            }
            z->numKeys--;

            y->numKeys++;
            y->key[y->numKeys] = k;

            int currentZPosition = 1;

            y->numKeys++;
            j = y->numKeys;
            if (!y->leaf) {
                y->key[j] = x->key[keyPosition];
                j = y->numKeys + 1;
            }
            while (j < 2 * t - 1) {
                y->key[j] = z->key[currentZPosition];
                j++;
                currentZPosition++;
            }

            for (j = keyPosition; j <= x->numKeys; j++) {
                x->key[j] = x->key[j + 1];
            }
            for (j = keyPosition + 1; j <= x->numKeys + 1; j++) {
                x->children[j] = x->children[j + 1];
            }
            x->numKeys--;

            free(z);
            removeNode(y, k);
        }
    }

    // Case 3
    else if (!x->leaf && keyPosition == -1) {
        int i = 1;
        int j;
        Node* subTree = findSubTree(x, k);

        if (!subTree)
            return;

        if (subTree->numKeys == t - 1) {
            if (posicaoSubTree + 1 < x->numKeys + 1 && x->children[posicaoSubTree + 1]->numKeys >= t) {  // 3a1
                subTree->numKeys++;
                subTree->key[subTree->numKeys] = x->key[posicaoSubTree];
                subTree->children[subTree->numKeys + 1] = x->children[posicaoSubTree + 1]->children[1];
                int aux;
                for (aux = 1; aux < x->children[posicaoSubTree + 1]->numKeys; aux++)
                    x->children[posicaoSubTree + 1]->key[aux] = x->children[posicaoSubTree + 1]->key[aux + 1];
                for (aux = 1; aux <= x->children[posicaoSubTree + 1]->numKeys; aux++)
                    x->children[posicaoSubTree + 1]->children[aux + 1] = x->children[posicaoSubTree + 1]->children[aux];
                x->children[posicaoSubTree + 1]->numKeys--;
                x->key[posicaoSubTree] = x->children[posicaoSubTree + 1]->key[1];
            } else if (posicaoSubTree > 1 && x->children[posicaoSubTree - 1]->numKeys >= t) {  // 3a2
                int ant = x->children[posicaoSubTree - 1]->key[x->children[posicaoSubTree - 1]->numKeys];
                x->key[posicaoSubTree - 1] = ant;
                x->children[posicaoSubTree - 1]->numKeys--;
                subTree->numKeys++;
                int aux;
                for (aux = subTree->numKeys; aux >= 1; aux--)
                    subTree->key[aux] = subTree->key[aux - 1];
                if (x->children[posicaoSubTree + 1])
                    subTree->key[1] = x->key[posicaoSubTree - 1];
                aux = subTree->numKeys + 1;
                for (aux; aux <= 2; aux--)
                    subTree->children[aux] = subTree->children[aux - 1];

                if (x->children[posicaoSubTree + 1])
                    subTree->children[1] = x->children[posicaoSubTree - 1]->children[x->children[posicaoSubTree - 1]->numKeys + 1];
            } else if (x->children[posicaoSubTree - 1]->numKeys == t - 1 && x->children[posicaoSubTree + 1]->numKeys == t - 1) {  // 3b
                if (posicaoSubTree - 1 >= 1) {
                    int numKeysPrevious = x->children[posicaoSubTree - 1]->numKeys;
                    int auxCurrent = numKeysPrevious + 1;
                    int auxNext = 1;

                    x->children[posicaoSubTree - 1]->numKeys += subTree->numKeys;

                    while (auxNext <= subTree->numKeys) {
                        x->children[posicaoSubTree - 1]->key[auxCurrent] = subTree->key[auxNext];
                        auxCurrent++;
                        auxNext++;
                    }
                    auxCurrent = numKeysPrevious;
                    auxNext = 1;
                    while (auxNext + 1 <= subTree->numKeys + 1) {
                        x->children[posicaoSubTree - 1]->children[auxCurrent] = subTree->children[auxNext];
                        auxNext++;
                        auxCurrent++;
                    }
                    auxCurrent = posicaoSubTree - 1;
                    while (auxCurrent + 1 <= x->numKeys) {
                        x->key[auxCurrent] = x->key[auxCurrent + 1];
                        auxCurrent++;
                    }
                    auxCurrent = posicaoSubTree;
                    while (auxCurrent + 1 <= x->numKeys + 1) {
                        x->children[auxCurrent] = x->children[auxCurrent + 1];
                        auxCurrent++;
                    }
                    x->numKeys--;
                    x->children[posicaoSubTree - 1]->numKeys--;
                } else if (posicaoSubTree + 1 <= x->numKeys + 1) {
                    int auxNext = 1;
                    int numOriginalSubTreeKeys = subTree->numKeys;
                    int auxCurrent = subTree->numKeys;
                    subTree->numKeys += x->children[posicaoSubTree + 1]->numKeys;
                    while (auxNext <= x->children[posicaoSubTree + 1]->numKeys) {
                        subTree->key[auxCurrent] = x->children[posicaoSubTree + 1]->key[auxNext];
                        auxCurrent++;
                        auxNext++;
                    }
                    auxCurrent = numOriginalSubTreeKeys + 1;
                    auxNext = 1;
                    while (auxNext + 1 <= subTree->numKeys + 1) {
                        subTree->children[auxCurrent] = x->children[posicaoSubTree + 1]->children[auxNext];
                        auxNext++;
                        auxCurrent++;
                    }

                    auxCurrent = posicaoSubTree;
                    while (auxCurrent + 1 <= x->numKeys) {
                        x->key[auxCurrent] = x->key[auxCurrent + 1];
                        auxCurrent++;
                    }
                    auxCurrent = posicaoSubTree + 1;
                    while (auxCurrent + 1 <= x->numKeys + 1) {
                        x->children[auxCurrent] = x->children[auxCurrent + 1];
                        auxCurrent++;
                    }
                    x->numKeys--;
                    subTree->numKeys--;
                }
            } else
                return;
        }
        removeNode(subTree, k);
    }
}

void removeRoot(BTree* bTree, int k) {
    Node* root = bTree->root;
    if (root->numKeys == 0) return;
    else
        removeNode(root, k);
    if (root->numKeys == 0 && !root->leaf) {
        bTree->root = root->children[1];
        free(root);
    }
}

void removeTree(BTree* bTree, int k) {
    int i;
    bool deleted = false;
    for (i = 1; i <= bTree->root->numKeys; i++) {
        if (bTree->root->key[i] == k) {
            removeRoot(bTree, k);
            deleted = true;
        }
    }
    if (!deleted) removeNode(bTree->root, k);
}
