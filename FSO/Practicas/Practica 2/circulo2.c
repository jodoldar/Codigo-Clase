#include <stdio.h>
#include "area.h"

#define PI 3.1416

main() {   
   float area2, radio;
   radio = 10;
   area2 = area(radio);
   printf("Area del circulo de radio %f es %f\n", radio, area2);
}

