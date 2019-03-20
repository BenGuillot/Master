//Implementation fault attack against DES
#include <stdio.h>
#include <stdlib.h>
#include<stdint.h>
#include"DATA.h"

//Convertie le tableau tab en int 
int TabToDec(int* tab, int taille){
	int val = 0;
	int tmp = 1;
	for(int i = taille - 1; i >= 0; i --){
		if(tab[i] == 1){
			val += tmp;
		}
		tmp = tmp * 2;
	}
	return val;
}
//Fonction appliquant la permutation IP au tableau x passé en argument
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
//Fonction appliquant la permutation IP-1 au tableau x passé en argument
void PINVpermut(int* x){
	int tmp;
	int res[32]; 
	for (int i = 0; i < 32; i++){
		tmp = pinv[i];
		res[i] = x[tmp-1];
	}
	for (int i = 0; i < 32; i ++){
		x[i] = res[i];
	}
}
//Fonction appliquant une Expansion au tableau x passé en argument à partir des valeurs du tableau y
void Expansion(int* x, int* y){
	for (int i = 0; i < 48; i ++){
		x[i] = y[E[i]];
	}
}
//Fonction permettant d'identifer quels messages utiliser pour attaquer les S-BOX
void SBOX_FINDER (int* FC,int* res){
        IPpermut(FC);
        int tmpC[64];
        for (int i = 0; i < 64; i ++){
        	tmpC[i] = C[i];
        }
        IPpermut(tmpC);
        int p_FC32[32], p_C32[32];
        for (int i = 0; i < 32; i ++){
                p_FC32[i] = FC[i];
                p_C32[i] = tmpC[i];
        }
        for (int i = 0; i < 32; i++){
                res[i] = (p_FC32[i] + p_C32[i]) % 2 ;
        }
        PINVpermut(res);
        //division de res en 8 tableau de 4 bits 
        //chaque tableau correspond a une S-BOX
        int S[8][4];
        for (int i = 0; i < 32; i ++){
        	if (i < 4)
        		S[0][i%4] = res[i];
        	else if (i < 8)
        		S[1][i%4] = res[i];
        	else if (i < 12)
        		S[2][i%4] = res[i];
        	else if (i < 16)
        		S[3][i%4] = res[i];
        	else if (i < 20)
        		S[4][i%4] = res[i];
        	else if (i < 24)
        		S[5][i%4] = res[i];	
        	else if (i < 28)
        		S[6][i%4] = res[i];
        	else if (i < 32)
        		S[7][i%4] = res[i];
        }
        for (int i = 0; i < 8; i ++){
        	if (S[i][0] + S[i][1] + S[i][2] + S[i][3] > 0){
        		printf("\t SBOX %d ", i+1);        
        	}
        }
}
//Fonction utilisée pour retrouver la clé K, associe au tableau x la valeur L16 de C et celle R16 au tableau y
void L16_R16_assignation (int* x, int *y){
	int tmpC[64];
    for (int i = 0; i < 64; i ++){
        tmpC[i] = C[i];
    }
    IPpermut(tmpC);
	for (int i = 0; i < 64; i ++){
		if (i < 32)
			x[i] = tmpC[i]; //L16 = 32 premiers bits de C
		if (i > 31)
			y[i-32] = tmpC[i]; //R16 = 32 derniers bits de C
	}
}
//Fonction utilisée pour retrouver la clé K, associe au tableau x la valeur L16 de FC et celle R15 au tableau y
void FC_FCL16_FCR15_assignation(int* x, int* y, int* z, int i){
	for (int k = 0; k < 64; k++){
		x[k] = FC[i][k];
	}
	IPpermut(x);
	for (int k = 0; k < 64; k ++){
		if (k < 32)
			y[k] = x[k]; //FCL16 = 32 premiers bits de FC
		if (k > 31)
			z[k-32] = x[k]; //FCR15 = 32 derniers bit de C
	}
}
//Fonction utilisée pour retrouver la clé K, sauvegarde dans le tableau x la valeur P-1(y xor z)
void REFERENCE_assignation(int* x, int* y, int* z){
	for (int k = 0; k < 32; k ++){
		x[k] = (y[k] + z[k]) % 2;
	}
	PINVpermut(x);
}
//Fonction utilisée pour retrouver la clé K, isole les 4 bits de References a verifier
void RefIsolation(int* x, int* y, int i){
	for(int j = 0; j < 4; j ++){
		x[j] = y[i*4+j];
	}
}
//Fonction permettant de retrouver la sous clé K16
void SubKey16finder (){
	//variables
	int CL16[32], CR16[32], FCL16[32], FCR15[32], REFERENCE[32] /* P-1(L16 XOR L16*) */;
	int E_CR15[48], E_FCR15[48];
	int tmpREF[4];
	int tmpC[6], tmpFC[6]; //variables à utiliser pour les S-BOX
	int SBOX_res[8][6][64] = {{{0}}}; //resultat des attaques sur les S-BOX
	int nbSolviables[8][6] = {{0}}; //nombre de solutions viables par S-BOX
	int extTmpC[2], extTmpFC[2], intTmpC[4], intTmpFC[4];
	//utilisé pour chaques S-BOX
	L16_R16_assignation(CL16,CR16);
	for (int i = 0; i < 8 ; i++){
		printf("############  SBOX %d ##########\n", i +1);
		for (int j = 0; j < 6; j ++){
		//ASSIGNATIONS
			int usedFC = SBOX_opponent[i][j]; //FC a utiliser
			int tmpFC64[64];
			FC_FCL16_FCR15_assignation(tmpFC64, FCL16, FCR15, usedFC); //assignation du FC 
			REFERENCE_assignation(REFERENCE, CL16, FCL16); //assignation de REFERENCE = P-1(CL16 XOR FCL16)
		//assignation E_CR15 et E_FCR15
			Expansion(E_CR15, CR16);
			Expansion(E_FCR15, FCR15);
		//recherche exhaustive de K16
			for (int k = 0; k < 64; k ++){
		//assignation K16tmp, tmpC, tmpFC
				for (int l = 0; l < 6; l++){
					tmpC[l] = (E_CR15[i*6+l] + K16[k][l]) % 2;
					tmpFC[l] = (E_FCR15[i*6+l] + K16[k][l]) % 2;
				}
				//passage dans la S-box
				//on résupère d'abords les bits externes de tmpC et tmpFC et leurs bits internes 
				extTmpC[0] = tmpC[0]; extTmpC[1] = tmpC[5];
				extTmpFC[0] = tmpFC[0]; extTmpFC[1] = tmpFC[5];
				for (int l = 1; l < 5; l++){
					intTmpC[l-1] = tmpC[l];
					intTmpFC[l-1] = tmpFC[l];
				}
				//calcul des lignes et colonnes de la S-BOX
				int rowC = TabToDec(extTmpC, 2), columnC = TabToDec(intTmpC, 4);
				int rowFC = TabToDec(extTmpFC, 2), columnFC = TabToDec(intTmpFC, 4);
				int tmpsol = Sbox[i][rowC][columnC] ^ Sbox[i][rowFC][columnFC];
				RefIsolation(tmpREF, REFERENCE, i);
				int ref = TabToDec(tmpREF, 4);
				int k16 = TabToDec(K16[k], 6);
				if (tmpsol == ref){
					SBOX_res[i][j][nbSolviables[i][j]] = k16;
					nbSolviables[i][j] ++;
				}
			}
		}
		//affichage 
		for (int j = 0; j < 6; j++) 
		{
			for (int k = 0; k < nbSolviables[i][j]; k++) 
				printf("%x ", (long)SBOX_res[i][j][k]);
			printf("\n"); 
		}
	} 
}
//Fonction permettant de trouver les 256 K possibles a partir de la sous clé K16
void KeyFinder(){
	int  tmpK[64] = {0,1,0,1,0,0,0,1,
					 1,1,1,1,1,0,0,0,
					 0,0,0,0,1,0,0,0,
					 1,1,0,1,0,1,0,1,
					 1,1,1,0,0,0,0,0,
					 0,1,1,0,0,1,0,0,
					 0,0,0,1,1,0,0,0,
					 1,0,0,0,0,0,1,0};
	int LostEightPos[8] = {13, 14, 18, 19, 50, 53, 57, 59};
	for(int i = 0; i < 256; i ++){
		for(int j = 0; j < 8; j ++){
			tmpK[LostEightPos[j]] = theLostEight[i][j];
		}
		printf("*");

		for (int j = 0; j < 64; ++j)
		{
			printf("%d", tmpK[j]);
		}
		printf("\n");
	}
}

int main (){
	//identifiction des chiffrés faux à utiliser contre les SBOX
	/*int res[32];
	for (int i = 0; i < 32; i ++){
		printf("FAUTE %d \n", i);
		SBOX_FINDER(FC[i], res);
		printf("\n");
	}*/
	//identification des 48 bits de K16
	//SubKey16finder();
	//identification de la clé K
	KeyFinder();
	return(0);	
}
