//Implementation fault attack against DES
#include <stdio.h>
#include <stdlib.h>
#include <gmp.h>
#include<stdint.h>

void IPpermut(int* x){
	
	int ip[] = {
		58,	50,	42,	34,	26,	18,	10,	2,
		60,	52,	44,	36,	28,	20,	12,	4,
		62,	54,	46,	38,	30,	22,	14,	6,
		64,	56,	48,	40,	32,	24,	16,	8,
		57,	49,	41,	33,	25,	17,	9,	1,
		59,	51,	43,	35,	27,	19,	11,	3,
		61,	53,	45,	37,	29,	21,	13,	5,
		63,	55,	47,	39,	31,	23,	15,	7
	};
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
	
	int pinv[] = {
		9,	17,	23,	31,
		13,	28,	2,	18,
		24,	16,	30,	6,
		26,	20,	10,	1,
		8,	14,	25,	3,
		4,	29,	11,	19,
		32,	12,	22,	7,
		5,	27,	15,	21
	};
	int tmp;
	int res[32]; 
	//printf("permutation du chiffré : \n");
	for (int i = 0; i < 32; i++){
		tmp = pinv[i];
		res[i] = x[tmp-1];
		//printf("%d",res[i]);
		//if (i ==3 || i ==  7 || i == 11 || i == 15 || i == 19 || i == 23 || i == 27)
        //		printf(" ");
	}
	//printf("\n");
	for (int i = 0; i < 32; i ++){
		x[i] = res[i];
	}
}

void SBOX_FINDER (int* FC,int* res){
		int C[] = {	0,0,0,1,1,1,1,0,
			1,1,1,1,0,1,0,0,
			1,0,0,1,1,1,1,1,
			0,1,0,0,0,0,0,1,
			0,1,1,0,1,1,0,1,
			1,1,0,1,0,1,0,1,
			0,1,0,1,0,1,1,1,
			1,0,0,0,1,0,1,0};
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
        	if (S[i][0] + S[i][1] + S[i][2] + S[i][3] > 0)
        		printf("SBOX %d ", i+1);
        }
}

void Keyfinder (){
	int FC[32][64] = {
		{0,0,0,1,1,1,0,0,1,1,1,0,0,1,0,1,1,0,0,1,1,1,1,1,0,1,0,0,0,1,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,1,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,1,1,0,1,0,0,1,1,1,1,1,0,0,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,0,0,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,0,0,1,0,0,1,0,0,1,1,1,0,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,0,0,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,1,0,0,1,0,0,1,1,0,1,1,0,0,0,0,0,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,0,0,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,1,1,0,1,0,0,1,0,0,1,0,0,1,1,0,1,1,0,1,0,0,0,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,1,1,0,1,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,0,1,0,1,1,0,1,0,0,1,0,0,1,1,0,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,1,1,0,1,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,1,1,1,1,0,1,1,1,0,1,0,0,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,0,0},
	 	{0,0,0,1,0,1,1,0,1,0,1,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,0,0,1,1,1,1,1,0,1,1,1,0,0,0,0,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,1,1,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,1,0,1,1,0,1,1,1,0,0,0,0,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,1,0,0,1,0,0,1,0,1,1,1,0,1,0,0,0,0,0,0,0,1,1,0,1,1,0,1,1,1,0,0,0,1,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
	 	{0,0,0,1,1,1,1,0,1,1,1,1,0,1,0,0,1,0,0,0,1,1,1,1,0,1,0,0,1,0,0,0,0,0,1,0,1,1,0,1,1,1,0,1,0,0,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,1},
	 	{0,1,0,1,1,1,1,0,1,1,1,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,1,1,0,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,0,0,0,1,0,1,0},
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
	int res[32];
	for (int i = 0; i < 32; i ++){
		SBOX_FINDER(FC[i], res);
		printf("\n");
	}
}

int main (){

	Keyfinder();
		
	return(0);	
}
