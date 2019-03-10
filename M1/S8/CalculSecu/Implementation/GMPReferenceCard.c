#include <stdio.h>
/* utilisation de lib gmp */
#include <gmp.h>
 
int main(void)
{
    char result[256];
 
    /* trois entiers */
    int int32[3];
 
    /* trois entier gmp */
    mpz_t integ[3];
 
    /* initialisation des entiers gmp */
    mpz_init(integ[0]);
    mpz_init(integ[1]);
    mpz_init(integ[2]);
 
    /* assignation des entiers */
    int32[0] = 3000000;
    int32[1] = 10000;
 
    /* assignation des entiers gmp */
    mpz_set_str(integ[0], "3000000", 10);
    mpz_set_str(integ[1], "10000", 10);
 
    /* multiplication */
    int32[2] = int32[0] * int32[1];
 
    /* multiplication gmp */
    mpz_mul(integ[2], integ[0], integ[1]);
 
    /* affichage du resultat */
    printf("result: %d\n", int32[2]);
 
    /* affichage du resultat gmp */
    if (NULL != mpz_get_str(result, 10, integ[2])) {
        printf("result (gmp): %s\n", result);
    }
 
    /* nettoyage gmp */
    mpz_clear(integ[0]);
    mpz_clear(integ[1]);
    mpz_clear(integ[2]);
 
    return 0;
}
