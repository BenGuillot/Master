
#include<stdlib.h>
#include<stdio.h>
#include<unistd.h>
#include<time.h>
#include<math.h>

#define Lambda 9
#define Mu 10

#define EPSILON 1e-5
#define MAXEVENT 1000000
#define MAXTEMPS 10000

double temps = 0;
long int n = 0; //nb client dans la file
int compteur = 0;//condition d'arrête 2
double cumul = 0;

typedef struct event{
	int type; //0 pour arriver et 1 pour service 
	double date;
	int etat; //0 pour non traité 1 pour traité 
}event;

typedef struct Echeancier{
	event tab[MAXEVENT];
	int taille;
}Echeancier;

Echeancier ech;

double exponentielle (int lbda){
	double r = (double)random()/RAND_MAX;
	while (r == 0 || r == 1){ //tant que X = 0 ou 1, on refait un random
		r = (double)random()/RAND_MAX;
	}
	return - log(r)/(lbda*1.0); //-ln(U)/lambda , avec U  = unif(0,1)
}

void Ajouter_Ech(event e){
	if(ech.taille < MAXEVENT){
		ech.tab[ech.taille] = e;
		ech.taille++;
		//printf("taille = %d \n", ech.taille);
	}
	else {
		//printf("Echeancier plein\n"); exit(0);
	}
}

void init_Ech(){
	event e;
	e.type = 0;
	e.date = 0;
	e.etat = 0;
	ech.taille = 0;
	Ajouter_Ech(e);
}

void Arrive_event(event e){
	//printf("Execute AC !\n");
	n++;

	event e1;
	e1.type = 0; //arrivée client
	e1.date = e.date + exponentielle(Lambda);
	e1.etat = 0;

	Ajouter_Ech(e1);

	if (n == 1)
	{
		event e2;
		e2.type = 1; //service
		e2.date = e.date + exponentielle(Mu);
		e2.etat = 0;

		Ajouter_Ech(e2);
	}

	temps = e.date;
}

void service_event(event e){
	//printf("Excecute SC !\n");
	if (n > 0){
		n--;
		if(n > 0){
			event e2;
			e2.type = 1; //service
			e2.date = e.date + exponentielle(Mu);
			e2.etat = 0;

		Ajouter_Ech(e2);
		}
	}
	temps = e.date;
}

void affiche_echeancier(){
	event e;

	//printf("---> temps %f est N = %ld  taille : %d [ ",temps,n, ech.taille);
	for (int i = 0; i < ech.taille; ++i)
	{
		e = ech.tab[i];
		/*if (e.type == 0)
			//printf("AC %lf, %d,",e.date, e.etat);
		if (e.type == 1)
			//printf("FS %lf, %d,",e.date, e.etat);	*/
	}
	//printf("]\n\n");
}

event extraire(){//extrait l'event le plus ancien non traité
	int i, imin;
	event min;

	for (int i = 0; i < ech.taille; ++i)
	{
		if (ech.tab[i].etat == 0)
		{
			min = ech.tab[i];
			imin = i;
			break;
		}
	}

	for (int i = 0; i < ech.taille; ++i)
	{
		if (min.date > ech.tab[i].date && ech.tab[i].etat == 0)
		{
			min = ech.tab[i];
			imin = i;
		}
	}

	ech.tab[imin].etat = 1;
	return min;
}

int condition_arret(long double Old, long double New){
	if(fabs(Old-New) < EPSILON && temps > 1000){
		compteur ++;
		if (compteur > 1e3)
			return 1;
	}
	return 0;
}

void Simulateur(FILE* f1){
	long double OldNmoyen;
	long double Nmoyen;

	init_Ech();
	event e;

	while(condition_arret(OldNmoyen, Nmoyen) == 0/*temps < MAXTEMPS*/){
		e = extraire();
		cumul += (e.date-temps)*n;

		OldNmoyen = Nmoyen;
		Nmoyen = cumul/temps;

		if(temps == 0){
			//printf("temps = 0 et N = 0 et Nmoyen = 0 \n");
			fprintf(f1,"0 \t 0 \n");
		}
		else{
			//printf("temps = %f et N = %ld et Nmoyen = %Lf \n",temps,n,Nmoyen);
			fprintf(f1,"%f \t %Lf \n",temps,Nmoyen);
		}

		if(e.type == 0){
			Arrive_event(e);
		}
		if (e.type == 1) {
			service_event(e);
		}
	}	
	printf("N moyen : %Lf\n", Nmoyen);
}

int main(int argc, char const *argv[])
{
	FILE *f1 = fopen("Simulateur_MM1.data", "w");
	srandom(getpid() + time(NULL));
	Simulateur(f1);
	fclose(f1);

	return 0;
}
