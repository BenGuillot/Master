#R CMD BATCH "Draw.R"
modelname = "Simulateur_MM1.data"
data = read.table(modelname)
attach(data);

temps = V1
Moyenne = V2

plot(temps,Moyenne,type="l",xlab="temps",ylab="E[N]",col="red")