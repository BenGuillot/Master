
#include<stdlib.h>
#include<stdio.h>
#include<unistd.h>
#include<time.h>

#define EPSILON 1e-5

long int temps = 1;
long int tempsVide = 0;
int compteur = 0;
long int n = 0;
long int cumule = 0;
int arret = 1e6;

/*
	void test_aleatoire(){
	long int test;
	for (int i = 0; i < 10; ++i)
	{	
		test = random();
		printf("%ld\n",test );

	}
	}
	//Après avoir relancer le programme plusieurs fois on constate
	//que les résultat sont toujours les mêmes : pseudo aléatoire

	void test_srandom(){
	long int test;
	
	for (int i = 0; i < 10; ++i)
	{	
		test = random()%100;
		printf("%ld\n",test );

	}
	}

	int Bernouilli(float a){
	long int test = random()%100;
	if (test < 100-(a*100))
		return 1;
	else return 0;

	}
*/
int nb_arrivees(float p0, float p2){
	double test = (double)random()/RAND_MAX;
	float p1 = 1-p0-p2;
	if(test < p0)
		return 0;
	else{
		if (test < p0+p1)
			return 1;
		else 
			return 2;
	} 
}

void arrive_event(){
	n += nb_arrivees(0.8,0.1);
	cumule += n;
}

void service_event(){
	if(n > 0){
		n--;
		arret --;
	}
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
	//0*p0+1*p1+2*p2 < 1 : condition de stabilité 
	//AC -> n+=nb_arrivees(0.4,0.35)
	//FS -> n--
	long double Nmoyen = 0;
	long double OldNMoyen = 0;
	while(condition_arret(OldNMoyen,Nmoyen) == 0){
		arrive_event();

		OldNMoyen = Nmoyen;
		Nmoyen = (long double)cumule / temps;
		fprintf(f1, "%5ld   %Lf\n",temps, Nmoyen);

		service_event();
		if(n == 0)
			tempsVide ++;
		temps++;
	}
	printf("temps file vide %f \n",(float)tempsVide/temps);
}

int main(){
/*
	srandom(getpid());
	//srandom : initialise la graine
	//srandom(time(NULL));
	//test_aleatoire();
	//test_srandom();
	for (int i = 0; i < 30; ++i)
	{
		int test = nb_arrivees(0.3, 0.5);
		printf("%d\n",test );
	}
*/	
	FILE *f1 = fopen("Simulateur_CST.data", "w");
	srandom(getpid() + time(NULL));
	Simulateur(f1);
	fclose(f1);

	return 0;
}
/*Loi M 	/ 	M/ 	1 
	  markovien : poisson, exponnentielle
	  			sortie
	  				serveur
stabilité : lambda < n
charge : p = (lambda/r)< 1

x : durée d'un phénomène / X ~Exp(lambda)
		durée moyenne = 1/lambda
f_x(x) = {lambda * Rho^(-lambda * x) pour tout x > 0
		 { 0 sinon 
	fonction de densité : P(X = x) = f_x(x)
	fonction de repartition (probabilité cumulé)
	F_x(x)=P(X<=x)= Integrale_(- infini)^x f(t)dt  = 1-Rho^(-lambda*x)
	==> génération d'un variable aleatoire selon la loi Exp(lambda) */