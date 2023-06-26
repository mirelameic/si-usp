#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>

//Tokens: adição, subtração, multiplicação, divisão e inteiros
enum {
  T_ADD, T_SUB, T_MULT, T_DIV, T_INT
};

//Token structure
struct token {
  int token;
  int intvalue;
};