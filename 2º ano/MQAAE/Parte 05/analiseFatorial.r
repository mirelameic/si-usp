if(!require(dplyr))
  install.packages("dplyr")
library(dplyr)

if(!require(psych))
  install.packages("psych")
library(psych)

if(!require(GPArotation))
  install.packages("GPArotation")
library(GPArotation)

if(!require(Rcmdr))
  install.packages("Rcmdr")
library(Rcmdr)

if(!require(corrplot))
  install.packages("corrplot")
library(corrplot)

dados <- read.csv('Maths.csv', sep = ',', stringsAsFactors = TRUE)
dados <- na.omit(dados) 

# selecionando as variáveis numéricas
dados2 <- dados[,c(3,7,8,13,14,15,25,26,27,28,29,31,32,33)]
View(dados2)

# calculando a matriz de correlações
matcor <- cor(dados2)
print(matcor, digits = 2)

require(corrplot)
corrplot(matcor, method="circle")

# calculando correlações parciais
partial.cor <- function (X, ...)
{
  R <- cor(X, ...)
  RI <- solve(R)
  D <- 1/sqrt(diag(RI))
  Rp <- -RI * (D %o% D)
  diag(Rp) <- 0
  rownames(Rp) <- colnames(Rp) <- colnames(X)
  Rp
}
matcorp <- partial.cor(dados2)

# kmo
p = 14
idiag <- seq(1, by = p + 1, length = p)
somar2 <- sum((as.numeric(matcor)[-idiag])^2)
cat("\n KMO = ",somar2 / (somar2 + sum((as.numeric(matcorp)[-idiag])^2)))

KMO(dados2)

# maa (msa)
for (j in 1:p) {
  somar2j <- sum(matcor[j, -j]^2)
  cat("\n MAA", j, "=", somar2j / (somar2j + sum(matcorp[j, -j]^2)))
}

# teste de bartlett
bartlett.test(list(dados2$age, dados2$Medu, dados2$Fedu, dados2$traveltime,
                   dados2$studytime, dados2$failures, dados2$freetime, 
                   dados2$goout, dados2$Dalc, dados2$Walc,
                   dados2$health, dados2$G1, dados2$G2, dados2$G3))

# definição de fatores
acpcor <- prcomp(dados2, scale = TRUE)
summary(acpcor)

plot(1:ncol(dados2), acpcor$sdev^2, type = "b", xlab = "Componente",
     ylab = "Variância", pch = 20, cex.axis = 1.3, cex.lab = 1.3)

# no fator 4 começa estabilização, será esse número o escolhido

# definição das cargas fatoriais
carfat<-principal(dados2, nfactors=4, rotate="none", score=TRUE)
carfat


# estimação das comunalidades e das variâncias específicas
comum <- rowSums(carfat^2)
vespec <- diag(matcor) - comum
estimat <- cbind(comum, vespec, diag(matcor))
rownames(estimat) <- colnames(dados2)
colnames(estimat) <- c("Comunalidade", "Variância única", "Variância")
View(estimat)

# rotação pelo método varimax
carfatr<-principal(dados2, nfactors=4, rotate="varimax", score=TRUE)
carfatr

## acho que isso não faz sentido pq temos mais fatores que 2
# plotação das estimativas das cargas fatoriais das variáveis sem rotação
plot(carfat, pch = 20, col = "red", xlab = "Fator 1", ylab = "Fator 2")
text(carfat, rownames(carfat), adj = 1)

# plotação das estimativas das cargas fatoriais das variáveis sem rotação
plot(carfatr$loadings, pch = 20, col = "red", xlab = "Fator 1", ylab = "Fator 2")
text(carfatr$loadings, rownames(carfat), adj = 1)

