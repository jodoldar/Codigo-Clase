// Ejemplo (absurdo) sintactico-semantico sin errores.
// Comprobad el resultado con la funcion "mostraTDS" 
{
  int a;       read(a);
  bool b[7];   int i; i = 0;

  while (i < 7) {
    b[i] = a > 0 ; 
    read (a);
    i++;
  }

  int c[27];
  int j; j = 0;
  do  {  
    c[j] = j;
    j += 1;
  } while (j < 27);

  int d;
  bool e;
  d = c[4];
  e = b[4] && ( d > 0 );
}
