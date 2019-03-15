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
	IPpermut(C); //permutation IP(C)
	for (int i = 0; i < 64; i ++){
		if (i < 32)
			CL16[i] = C[i]; //L16 = 32 premiers bits de C
		if (i > 31)
			CR16[i-32] = C[i]; //R16 = 32 derniers bits de C
	}
	//printf("\t ASSIGNATION CL16 & CR16 check\n");
	for (int i = 0; i < 8; i++){
		printf("############  SBOX %d ##########\n", i +1);
		for (int j = 0; j < 6; j ++){
		//ASSIGNATIONS
		//assignation du FC 
			int usedFC = SBOX_opponent[i][j]; //FC a utiliser
			int tmpFC[64];
			for (int k = 0; k < 64; k++){
				tmpFC[k] = FC[usedFC][k];
			}
			IPpermut(tmpFC);
			for (int k = 0; k < 64; k ++){
				if (k < 32)
					FCL16[k] = tmpFC[k]; //FCL16 = 32 premiers bits de FC
				if (k > 31)
					FCR15[k-32] = tmpFC[k]; //FCR15 = 32 derniers bit de C
			}
		//assignation de REFERENCE = P-1(CL16 XOR FCL16)
			for (int k = 0; k < 32; k ++){
				REFERENCE[k] = (CL16[k] + FCL16[k]) % 2;
			}
			PINVpermut(REFERENCE);
		//assignation E_CR15 et E_FCR15
			Expansion(E_CR15, CR16);
			Expansion(E_FCR15, FCR15);
		//printf("\t ASSIGNATION E_R15 & E_FR15 check\n");
		
		//recherche exhaustive de K16
			for (int k = 0; k < 64; k ++){
		//assignation K16tmp, tmpC, tmpFC
				for (int l = 0; l < 6; l++){
					tmpC[l] = E_CR15[i*6+l] + K16[k][l] % 2;
					tmpFC[l] = E_FCR15[i*6+l] + K16[k][l] % 2;
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
				if (tmpsol == ref){
					SBOX_res[i][j][nbSolviables[i][j]] = k16;
					nbSolviables[i][j] ++;
				}
			}
		}
		for (int j = 0; j < 6; j++) 
		{
			for (int k = 0; k < nbSolviables[i][j]; k++) 
				printf("%x ", (long)SBOX_res[i][j][k]);
			printf("\n"); 
		}

		/*int x = 0;
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
		printf("Solution S%d = %x\n",i+1,subsubk16);*/
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
