// Ejemplo de uso de operadores de incremento.
// Debe devolver: 4, 5, 6, 6, 9, 7, 14, 7, 28, 28, 28
{ int a; int b;

  a = 4; print(a++); print(a); print(++a); print(a);
  b = 7; a= 2;
  b+=a; print(b); b-=a; print(b); b*=a; print(b); b/=a; print(b);
  print(a=b=28); print(a); print(b);
} 
