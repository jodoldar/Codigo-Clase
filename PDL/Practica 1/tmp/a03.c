//Ejemplo de secuencias de instrucciones y declaraciones 
{ int  x; bool z;     

  read(x);
  if ((x <= 9) && (x >= 0)) z = true; else z = false;

  int  i;  int  a[10]; 

  i = 0;
  while (i < 10) { a[i] = i; i++;}
  if (z) i = a[x]; else {}
  print(i);
}
