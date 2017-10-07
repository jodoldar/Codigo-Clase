// Ejemplo del uso de operadores logicos
{ int x; int y; bool z; 

  z = true; 
  do {
    read(x); read(y);
    if ((x == y) || false)       print(1);        
    elseif ((x > 0) && (x != 0)) print(2);               
    elseif (!(x >= 0) && true)   print(3); 
    else { print(0); z = false; }
  }
  while (z);
}
