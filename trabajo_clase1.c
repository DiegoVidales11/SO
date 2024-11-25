#include <stdio.h>

int main()
{
    int a = 4, b = 3;
    int *auxa, **auxaa, ***auxaaa;

    auxa = &a;
    auxaa = &auxa;
    auxaaa = &auxaa;

    printf("Valor de AUXAA: %p %d \n", auxaa, ***auxaaa);

    printf("Valor de A memoria: %p \n", &a);
    printf("Valor de B memoria: %p \n", &b);


    printf("Hola mundo");
    return 0;
}