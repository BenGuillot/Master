 //Implementation fault attack against DES
#include <stdio.h>
#include <stdlib.h>
#include <gmp.h>
#include<stdint.h>
#include"DATA.h"

void IPpermut(int* x){
	int tmp;
	int res[64]; 
	for (int i = 0; i < 64; i++){
		tmp = ip[i];
		res[i] = x[tmp-1];
	}
	for (int i = 0; i < 64; i ++){
		x[i] = res[i];
	}
} 

void PINVpermut(int* x){
	int tmp;
	int res[32]; 
	//printf("permutation du chiffré : \n");
	for (int i = 0; i < 32; i++){
		tmp = pinv[i];
		res[i] = x[tmp-1];
		/*printf("%d",res[i]);
		if (i ==3 || i ==  7 || i == 11 || i == 15 || i == 19 || i == 23 || i == 27)
        		printf(" ");*/
	}
	//printf("\n");
	for (int i = 0; i < 32; i ++){
		x[i] = res[i];
	}
}

void Expansion(int* x, int* y){
	for (int i = 0; i < 48; i ++){
		x[i] = y[E[i]];
	}
}

void SBOX_FINDER (int* FC,int* res){
        IPpermut(FC);
        IPpermut(C);
        int p_FC32[32], p_C32[32];
        for (int i = 0; i < 32; i ++){
                p_FC32[i] = FC[i];
                p_C32[i] = C[i];
        }
        for (int i = 0; i < 32; i++){
                res[i] = (p_FC32[i] + p_C32[i]) % 2 ;
        }
        PINVpermut(res);
        //division de res en 8 tableau de 4 bits 
        //chaque tableau correspond a une S-BOX
        int S[8][4];
        for (int i = 0; i < 32; i ++){
        	if (i < 4){
        		S[0][i%4] = res[i];
        		//printf("%d",S[0][i%4]);
        	}
        	else if (i < 8){
        		S[1][i%4] = res[i];
        		//printf("%d",S[1][i%4]);
        	}
        	else if (i < 12){
        		S[2][i%4] = res[i];
        		//printf("%d",S[2][i%4]);
        	}
        	else if (i < 16){
        		S[3][i%4] = res[i];
        		//printf("%d",S[3][i%4]);
        	}
        	else if (i < 20){
        		S[4][i%4] = res[i];
        		//printf("%d",S[4][i%4]);
        	}
        	else if (i < 24){
        		S[5][i%4] = res[i];
        		//printf("%d",S[5][i%4]);		
        	}
        	else if (i < 28){
        		S[6][i%4] = res[i];
        		//printf("%d",S[6][i%4]);
        	}
        	else if (i < 32){
        		S[7][i%4] = res[i];
        		//printf("%d",S[7][i%4]);
        	}
        }
        for (int i = 0; i < 8; i ++){
        	if (S[i][0] + S[i][1] + S[i][2] + S[i][3] > 0){
        		printf("SBOX %d ", i+1);        
        	}
        }
}

void SubKey16finder (){
	//TROUVER K16
	int CL16[32], CR16[32], FCL16[32], FCR15[32], REFERENCE[32] /* P-1(L16 XOR L16*) */;
	int E_CR15[48], E_FCR15[48];
	int tmpC[6], tmpFC[6]; //variables à utiliser pour les S-BOX
	int SBOX_res[8][6][64] = {
								{
									{0}
								}
							}; //resultat des attaques sur les S-BOX
	int nbSolviables[8][6] = {
								{0}
							 };

	//utilisé pour chaques S-BOX
{	
	IPpermut(C); //permutation IP(C)
	for (int i = 0; i < 64; i ++){
		if (i < 32)
			CL16[i] = C[i]; //L16 = 32 premiers bits de C
		if (i > 31)
			CR16[i-32] = C[i]; //R16 = 32 derniers bits de C
	}

	/*for(int k = 0; k < 32; k ++){
				printf("%d",CL16[k]);
			}
			printf("\n");
	for(int k = 0; k < 32; k ++){
				printf("%d",CR16[k]);
			}
		*/
}
	//printf("\t ASSIGNATION CL16 & CR16 check\n");


	for (int i = 0; i < 8; i++){
		printf("############  SBOX %d ##########\n", i +1);
		for (int j = 0; j < 6; j ++){
		//ASSIGNATIONS
		//assignation du FC 
		{
			int usedFC = SBOX_opponent[i][j]; //FC a utiliser
			//printf("FAUTE %d \n", usedFC + 1);
			int tmpFC[64];
			for (int k = 0; k < 64; k++){
				tmpFC[k] = FC[usedFC][k];
			}
			IPpermut(tmpFC);
			/*for (int k = 0; k < 64; k++){
				printf("%d",tmpFC[k]);
			}
			printf("\n");*/
			//assignation de FC16 et FCR15
			for (int k = 0; k < 64; k ++){
				if (k < 32)
					FCL16[k] = tmpFC[k]; //FCL16 = 32 premiers bits de FC
				if (k > 31)
					FCR15[k-32] = tmpFC[k]; //FCR15 = 32 derniers bit de C
			}
			/*for(int k = 0; k < 32; k ++){
				printf("%d",FCL16[k]);
			}
			printf("\n");
			for(int k = 0; k < 32; k ++){
				printf("%d",FCR15[k]);
			}
			*/
		}
		//printf("\t ASSIGNATION FC(non print), FCL16 & FCR15 check\n");
		//assignation de REFERENCE = P-1(CL16 XOR FCL16)
		{	
			//printf("REFERENCE\n");
			for (int k = 0; k < 32; k ++){
				REFERENCE[k] = (CL16[k] + FCL16[k]) % 2;
			}
			PINVpermut(REFERENCE);
			/*for(int k = 0; k < 32; k ++){
				printf("%d",REFERENCE[k]);
			}*/
			//printf("\t ASSIGNATION REFERENCE CHECK\n");
		}
		//assignation E_CR15 et E_FCR15
		{
			Expansion(E_CR15, CR16);
			Expansion(E_FCR15, FCR15);
			/*printf("E_CR15\n");
			for (int k = 0; k < 48; ++k)
			{
				printf("%d",E_CR15[k]);
			}
			printf("\n");
			printf("E_FCR15\n");
			for (int k = 0; k < 48; ++k)
			{
				printf("%d",E_FCR15[k]);
			}*/
		}
		//printf("\t ASSIGNATION E_R15 & E_FR15 check\n");
		
		//recherche exhaustive de K16
			for (int k = 0; k < 64; k ++){
		//assignation K16tmp, tmpC, tmpFC
			{
				for (int l = 0; l < 6; l++){
					tmpC[l] = E_CR15[i*6+l] + K16[k][l] % 2;
					tmpFC[l] = E_FCR15[i*6+l] + K16[k][l] % 2;
				}
				/*printf("\nK16\n");
				for (int l = 0; l < 6; l++){
					printf("%d", K16[k][l]);
				}

				printf("\nTMPC\n");
				for (int l = 0; l < 6; l++){
					printf("%d", tmpC[l]);
				}
				printf("\nTMPFC\n");
				for (int l = 0; l < 6; l++){
					printf("%d", tmpFC[l]);
				}*/
			}
		//printf("\t ASSIGNATION K16 & tmpC & tmpFC check\n");
				//passage dans la S-box
				//on résupère d'abords les bits externes de tmpC et tmpFC et leurs bits internes 
				int extTmpC[2], extTmpFC[2], intTmpC[4], intTmpFC[4];
				extTmpC[0] = tmpC[0]; extTmpC[1] = tmpC[5];
				extTmpFC[0] = tmpFC[0]; extTmpFC[1] = tmpFC[5];
				for (int l = 1; l < 5; l++){
					intTmpC[l-1] = tmpC[l];
					intTmpFC[l-1] = tmpFC[l];
				}
				//calcul des lignes et colonnes de la S-BOX
				int rowC = 0, columnC = 0;
				int rowFC = 0, columnFC = 0;
				//row
				{
					if (extTmpC[0] == 1){
					rowC += 2;
					}
					if (extTmpC[1] == 1){
						rowC += 1;
					}
					if (extTmpFC[0] == 1){
						rowFC += 2;  
						}
					if (extTmpFC[1] == 1){
						rowFC += 1;
						}
				}
				//column
				{
					if (intTmpC[0] == 1){
						columnC += 8;
					}
					if (intTmpC[1] == 1){
						columnC += 4;
					}
					if (intTmpC[2] == 1){
						columnC += 2;
					}
					if (intTmpC[3] == 1){
						columnC += 1;
					}
					if (intTmpFC[0] == 1){
						columnFC += 8;
					}
					if (intTmpFC[1] == 1){
						columnFC += 4;
					}
					if (intTmpFC[2] == 1){
						columnFC += 2;
					}
					if (intTmpFC[3] == 1){
						columnFC += 1;
					}
				}
				int tmpsol = Sbox[i][rowC][columnC] ^ Sbox[i][rowFC][columnFC];
				int ref = 0;
				int k16 = 0;
				{
					if (REFERENCE[i*4] == 1){
						ref += 8;
					}
					if (REFERENCE[i*4 +1] == 1){
						ref += 4;
					}
					if (REFERENCE[i*4 + 2] == 1){
						ref += 2;
					}
					if (REFERENCE[i*4 + 3] == 1){
						ref += 1;
					}
				}
				{
					if (K16[k][0] == 1){
						k16 += 32;
					}
					if (K16[k][1] == 1){
						k16 += 16;
					}
					if (K16[k][2] == 1){
						k16 += 8;
					}
					if (K16[k][3] == 1){
						k16 += 4;
					}
					if (K16[k][4] == 1){
						k16 += 2;
					}
					if (K16[k][5] == 1){
						k16 += 1;
					}
				}
				/*printf("\nref = %d\n", ref);
				printf("rowC = %d\t columnC = %d\t sorite de S-BOX = %d\n",rowC, columnC, Sbox[i][rowC][columnC]);
				printf("rowFC = %d\t columnFC = %d\t sortie de S-BOX = %d\n",rowFC, columnFC, Sbox[i][rowFC][columnFC]);*/
				//printf("%d  || %d \n %d \n", Sbox[i][columnC][rowC], Sbox[i][columnFC][rowFC], tmpsol);
				//printf ("ref : %d || sortie de sbox : %d \n", ref, tmpsol);
				if (tmpsol == ref){
					SBOX_res[i][j][nbSolviables[i][j]] = k16;
					nbSolviables[i][j] ++;
				}
			}
		//printf("\n");
		}
		for (int j = 0; j < 6; j++) 
		{
			printf("%d solutions\t", j+1, nbSolviables[i][j]);
			for (int k = 0; k < nbSolviables[i][k]; k++) 
				printf("%x ", (long)SBOX_res[i][j][k]);
			printf("\n"); 
		}

		int x = 0;
		long subsubk16 = (long) SBOX_res[i][0][x];
		for (int j = 1; j < 6; ++j)
		{
			int y;
			for (y = 0; y < nbSolviables[i][j]; ++y)
			{
				if (subsubk16 == SBOX_res[i][j][y])
					break;
				if (y == nbSolviables[i][j]){
					
					if(x+1 >= nbSolviables[i][0]){
						printf("\nERROR\n",i,j);
						break;
					}
					j = 1;
					x ++;
					subsubk16 = (long) SBOX_res[i][0][x];
				}
			}		
		}
		printf("Solution S%d = %x\n",i+1,subsubk16);
	} 
	//indentifiction des chiffrés faux à utiliser contre les SBOX
	/*int res[32];
	for (int i = 0; i < 32; i ++){
		SBOX_FINDER(FC[i], res);
		printf("\n");
	}*/
}

int main (){

	SubKey16finder();
	
		
	return(0);	
}


Sbox 1
Faute 1 : 12 solutions	3 8 9 b f 16 23 28 29 2b 2f 36 
Faute 2 : 6 solutions	a b 16 17 3e 3f 
Faute 3 : 6 solutions	9 b 1c 1e 29 2b 
Faute 4 : 8 solutions	b f 1a 1e 23 27 2b 2f 
Faute 5 : 12 solutions	3 b 12 1a 23 25 26 27 2b 2d 2e 2f 
Faute 6 : 6 solutions	5 b 15 1b 25 35 
Solution S1 = b | K16 actuel = b

Sbox 2
Faute 1 : 8 solutions	8 9 c d e f 30 31 
Faute 2 : 6 solutions	1 3 8 a 21 23 
Faute 3 : 14 solutions	1 5 8 9 c d 20 21 24 25 28 29 2c 2d 
Faute 4 : 6 solutions	0 8 35 36 3d 3e 
Faute 5 : 8 solutions	4 8 14 18 21 27 31 37 
Faute 6 : 10 solutions	6 8 c e 1e 26 28 2c 2e 3e 
Solution S2 = 8 | K16 actuel = 2c8

Sbox 3
Faute 1 : 8 solutions	20 21 22 23 36 37 3e 3f 
Faute 2 : 4 solutions	5 7 3c 3e 
Faute 3 : 4 solutions	2a 2e 3a 3e 
Faute 4 : 6 solutions	2 a 36 37 3e 3f 
Faute 5 : 14 solutions	0 a c e 10 1a 1c 1e 2a 2b 2e 3a 3b 3e 
Faute 6 : 10 solutions	3 8 e 10 1e 23 28 2e 30 3e 
Solution S3 = 3e | K16 actuel = b23e

Sbox 4
Faute 1 : 16 solutions	a b 12 13 16 17 1e 1f 20 21 26 27 34 35 36 37 
Faute 2 : 8 solutions	28 29 2a 2b 34 35 36 37 
Faute 3 : 4 solutions	9 d 32 36 
Faute 4 : 8 solutions	0 3 7 8 b f 36 3e 
Faute 5 : 8 solutions	8 9 18 19 26 27 36 37 
Faute 6 : 8 solutions	2 3 16 17 22 23 36 37 
Solution S4 = 36 | K16 actuel = 2c8fb6

Sbox 5
Faute 1 : 8 solutions	e f 16 17 1e 1f 20 21 
Faute 2 : 6 solutions	21 23 24 25 26 27 
Faute 3 : 10 solutions	12 16 19 1d 21 23 25 27 2a 2e 
Faute 4 : 4 solutions	21 24 29 2c 
Faute 5 : 12 solutions	6 b d 16 1b 1d 21 2e 2f 31 3e 3f 
Faute 6 : 8 solutions	1 6 7 1f 21 26 27 3f 
Solution S5 = 21 | K16 actuel = b23eda1

Sbox 6
Faute 1 : 16 solutions	0 1 8 9 a b e f 10 11 1c 1d 24 25 2c 2d 
Faute 2 : 10 solutions	8 9 a b 19 1b 38 3a 3d 3f 
Faute 3 : 8 solutions	0 4 8 c 23 27 38 3c 
Faute 4 : 16 solutions	0 1 4 5 8 9 c d 13 1b 32 34 37 3a 3c 3f 
Faute 5 : 4 solutions	8 18 24 34 
Faute 6 : 4 solutions	8 18 24 34 
Solution S6 = 8 | K16 actuel = 2c8fb6848

Sbox 7
Faute 1 : 4 solutions	c d 1c 1d 
Faute 2 : 14 solutions	5 7 d f 10 12 20 22 28 2a 30 32 38 3a 
Faute 3 : 8 solutions	0 4 9 d 1a 1e 23 27 
Faute 4 : 8 solutions	5 7 d f 16 1e 36 3e 
Faute 5 : 4 solutions	c d 1c 1d 
Faute 6 : 8 solutions	0 d 11 18 20 2d 31 38 
Solution S7 = d | K16 actuel = b23eda120d

Sbox 8
Faute 1 : 8 solutions	4 6 19 1b 28 2a 38 3a 
Faute 2 : 10 solutions	a e 10 14 22 26 2a 2e 38 3c 
Faute 3 : 4 solutions	22 26 2a 2e 
Faute 4 : 10 solutions	0 a 10 1a 28 29 2a 38 39 3a 
Faute 5 : 16 solutions	1 5 a e 11 12 1a 1d 21 25 2a 2e 31 32 3a 3d 
Faute 6 : 16 solutions	4 5 e f 12 13 14 15 16 17 1c 1d 1e 1f 2a 2b 
Solution S8 = 2a | K16 actuel = 2c8fb684836a

int FC[32][64] = {
		{0,0,0,1,1,1,0,0,1,1,1,0,0,1,0,1,1,0,0,1,1,1,1,1,0,1,0,0,0,1,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,1,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,1,1,0,1,0,0,1,1,1,1,1,0,0,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,0,0,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,0,0,1,0,0,1,0,0,1,1,1,0,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,0,0,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,1,0,0,1,0,0,1,1,0,1,1,0,0,0,0,0,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,0,0,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,1,1,0,1,0,0,1,0,0,1,0,0,1,1,0,1,1,0,1,0,0,0,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,1,1,0,1,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,0,1,0,1,1,0,1,0,0,1,0,0,1,1,0,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,1,1,0,1,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,1,1,1,1,0,1,1,1,0,1,0,0,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,0,0},//7
	 	{0,0,0,1,0,1,1,0,1,0,1,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,0,0,1,1,1,1,1,0,1,1,1,0,0,0,0,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},//8
	 	{0,0,0,1,1,1,1,0,1,1,1,1,1,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,1,0,1,1,0,1,1,1,0,0,0,0,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},//9
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,1,0,0,1,0,0,1,0,1,1,1,0,1,0,0,0,0,0,0,0,1,1,0,1,1,0,1,1,1,0,0,0,1,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},//10
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,1,0,0,1,0,0,0,1,1,1,1,0,1,0,0,1,0,0,0,0,0,1,0,1,1,0,1,1,1,0,1,0,0,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,1},//11
	 	{0,1,0,1,1,1,1,0,1,1,1,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,1,1,0,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},//12
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,1,0,0,1,0,0,0,1,1,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,1},
	 	{0,1,0,1,1,1,1,0,1,1,1,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,0,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1,0,0,0,1,0,1,1},
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,1,0,0,1,0,0,0,1,1,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,0,0,1,1,0,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,1,0,0,0,0,1,1},
	 	{0,1,1,1,1,1,1,0,1,1,1,1,0,1,0,0,1,0,0,0,1,1,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,0,0,1,0,1,0,1,0,1,0,0,0,1,1,1,1,1,0,0,1,0,1,1},
	 	{0,0,0,1,1,1,1,0,1,1,0,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,1,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,1,0,0,1,0,1,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,0,0,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,1,0,1,1,0,0,1,0,1,0},
	 	{0,0,0,0,1,0,1,0,1,1,1,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,1,0,0,0,0,1,0,1,1,0,1,0,0,1,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,0,1,1,0,0,1,0,1,0},
	 	{0,0,0,1,1,0,1,0,1,1,1,1,0,1,0,0,1,1,0,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1,0,0,1,1,0,1,1,1,0,1,0,1,0,1,0,1,0,0,0,1,1,1,1,1,0,0,1,0,1,0},
	 	{0,0,0,1,1,0,1,0,1,1,1,1,0,1,0,0,1,1,0,1,1,1,1,0,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,0,1,0,1,0,1,1,1,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,0,1,0,1,1,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,0,1,0,1,0,1,1,1,1,0,1,0,0,1,1,0,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1,1,0,1,1,0,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,0,1,0,1,0,1,0},
	 	{1,0,0,0,1,0,1,0,1,1,1,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,0,1,1,0,1,0,1,0,1,0,0,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,0,0,1,1,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,0,1,1,0,1,0,1,0,1,0,0,0,1,0,0,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,1,0,0,0,0,0,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,0,1,0,0,0,1,0,0,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,1,0,1,1,0,0,1,1,1,1,1,1,1,0,1,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,0,1,0,0,0,1,0,1,1,1,1,0,0,1,1,1,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,0,0,1,1,0,0,1,1,1,1,1,0,1,0,1,0,0,0,1,1,1,1,0,1,1,0,0,1,1,0,1,0,1,0,1,0,0,0,1,0,0,1,1,1,0,0,1,1,0,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,1,0,1,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,0,0,1,1,1,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,0,0,1,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,0,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,1,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,0,0,0,1,0,1,0,1,1,1,0,0,0,1,1,0,1,0}                        
	};

	