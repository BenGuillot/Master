
#include<stdlib.h>
#include<stdio.h>
#include<unistd.h>
#include<time.h>
#include <math.h>

#define Lambda 9
#define Mu 10

#define EPSILON 1e-5
#define MAXEVENT 1000000
#define MAXTEMPS 5000

double temps = 0;
long int n = 0; //nb client dans la file
int compteur = 0;//condition d'arrête 2
double cumul = 0:

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
	return -log(r)/(lbda*1.0); //-ln(U)/lambda , avec U  = unif(0,1)
}

void Ajouter_Ech(event e){
	if(ech.taille < MAXEVENT){
		ech.tab[ech.taille] = e;
		eh.taille++;
		printf("taille = %d \n", ech.taille);
	}
	else {
		printf("Echeancier plein\n"); exit(0);
	}
}

void init Ech(){
	event e;
	e.type = 0;
	e.date = 0;
	e.etat = 0;
	ech.taille = 0
	Ajouter_Ech(e);
}

void Arrive_event(event e){
	printf("Execute AC !\n");
	n++;

	event e1;
	e1.type = 0; //arrivée client
	e1.date = e.date + exponentielle(lambda);
	e1.etat = 0;

	Ajouter_Ech(e1);

	if (n == 1)
	{
		event e2;
		e2.type = 1; //service
		e2.datep = e.date + exponentielle(Mu);
		e2.etat = 0;

		Ajouter_Ech(e2);
	}

	temps = e.date;
}

void service_Event(event e){
	printf("Excecute SC !\n");
	if (n > 0){
		n--;
		if(n > 0){
			event e2;
			e2.type = 1; //service
			e2.datep = e.date + exponentielle(Mu);
			e2.etat = 0;

		Ajouter_Ech(e2)
		}
	}
	temps = e.date;
}

void affiche_echeancier(){
	event e;

	printf("---> temps %f est N = %ld  taille : %d [ ",temps,n, ech.taille);
	for (int i = 0; i < ech.taille; ++i)
	{
		e = ech.tab[i];
		if (e.type == 0)
			printf("AC %lf, %d,",e.date, e.etat);
		if (e.type == 1)
			printf("FS %lf, %d,",e.date, e.etat);	
	}
	printf("]\n\n");
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