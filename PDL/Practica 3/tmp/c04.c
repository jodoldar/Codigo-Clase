// Calcula el factorial de un múmero > 0 y < 13
{ int n; int fac; int i; bool f;

  f = true; fac = 1;
  while ( f ) {
    read(n);
    if ((n > 0) && (n < 13)) {
      i = 2;
      while (i <= n) { fac = fac * i; i++; }
      print(fac);
      f = false;
    }
    else {}
  }
}
