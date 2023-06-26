#include "defToken.h"

#define extern_
#include "data.h"
#undef extern_

#include "decl.h"
#include <errno.h>

// Variáveis globais
static void init() {
  Line = 1;
  Putback = '\n';
}

// Uso do programa
static void usage(char *prog) {
  fprintf(stderr, "Usage: %s infile\n", prog);
  exit(1);
}

// Lista dos tokens printáveis
char *tokstr[] = { "+", "-", "*", "/", "intlit" };

// Scaneia o arquivo
static void scanfile() {
  struct token T;

  while (scan(&T)) {
    printf("Token %s", tokstr[T.token]);
    if (T.token == T_INT)
      printf(", value %d", T.intvalue);
    printf("\n");
  }
}

// Main
int main(int argc, char *argv[]) {
  if (argc != 2)
    usage(argv[0]);

  init();

  if ((Infile = fopen(argv[1], "r")) == NULL) {
    fprintf(stderr, "Unable to open %s: %s\n", argv[1], strerror(errno));
    exit(1);
  }

  scanfile();
  exit(0);
}