// Ejemplo sencillo de manipulacion de vectores.
// Devuelve la cuenta inversa de 9 a 0 (inclusive)
{ int a[10]; int i;

  i = 0;
  while (i < 10) { a[i] = i;    i++;}
  i = 9;
  do { print(a[i]); i--;}  while (i >= 0)
}
