#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]) {
    int i,j;
    if(argc==1){
	printf("-c	 Mostrará \"Compilar\"\n");
	printf("-E	 Mostrará \"Preprocesar\"\n");
	printf("-iruta   Mostrará \"Incluir + ruta\"\n");
    }
    for (i=0; i<argc; i++) {
        if (i!=0){
            char opc[100] = "";
            if(argv[i][0]=='-'){
                switch (argv[i][1]) {
                    case 'c':
                        strcat(opc,"Compilar");
                        printf("Argumento %d es %s\n",i,opc);
                        break;
                    case 'E':
                        strcat(opc,"Preprocesar");
                        printf("Argumento %d es %s\n",i,opc);
                        break;
                    case 'i':
                        strcat(opc,"Incluir");
                        printf("Argumento %d es %s%s\n",i,opc,argv[i]+2);
                        break;
                        
                    default:
                        break;
                }
            }
        }
    }
}

