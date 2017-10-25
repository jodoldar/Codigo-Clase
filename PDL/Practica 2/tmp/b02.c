// Ejemplo (sin sentido) con opeadores logicos: 5 errores
{ int a[20];
  bool b;
  int  c;
  int  b;                         // Identificador repetido

  b = ((a[2] > 0) && true) || c;  // Error en "expresion logica"
  b = ! (a[2] * 10);              // Error en "expresion unaria"
  b = a[7] == b;                 // Error en "expresion de igualdad"
  if (a[7] < b )                 // Error en "expresion de relacional"
    a[7] = c;
  elseif (a[7] >= c)
    a[7] *= c;
  else {};
}
