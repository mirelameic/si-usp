#include "defToken.h"
#include "data.h"
#include "decl.h"

//Funções do scanner léxico

// Retorna a posição do caractere c, ou -1 se não houver
static int posicaoCaract(char *s, int c) {
  char *p;
  p = strchr(s, c);
  return (p ? p - s : -1);
}

// Pega o próximo caractere
static int next(void) {
  int c;

  if (Putback) {
    c = Putback;
    Putback = 0;
    return c;
  }

  // Le do arquivo
  c = fgetc(Infile);
  if ('\n' == c)
    Line++;
  return c;
}

// Put back an unwanted character
static void putback(int c) {
  Putback = c;
}

// Pula os caracteres indesejados
static int skip(void) {
  int c;

  c = next();
  while (' ' == c || '\t' == c || '\n' == c || '\r' == c || '\f' == c) {
    c = next();
  }
  return (c);
}

// Scaneia um inteiro
static int scanint(int c) {
  int k, val = 0;

  // Converte pra int
  while ((k = posicaoCaract("0123456789", c)) >= 0) {
    val = val * 10 + k;
    c = next();
  }

  // Chegou num caractere nao inteiro
  putback(c);
  return val;
}

// Scaneia o tolen
int scan(struct token *t) {
  int c;

  // Pula espaço em branco
  c = skip();

  // Determina o token
  switch (c) {
  case EOF:
    return (0);
  case '+':
    t->token = T_ADD;
    break;
  case '-':
    t->token = T_SUB;
    break;
  case '*':
    t->token = T_MULT;
    break;
  case '/':
    t->token = T_DIV;
    break;
  default:

    // Caso seja um digito
    if (isdigit(c)) {
      t->intvalue = scanint(c);
      t->token = T_INT;
      break;
    }

    //Se nao reconhecer
    printf("Caractere nao reconhecido '%c' na linha %d\n", c, Line);
    exit(1);
  }

  // Achou o token
  return (1);
}