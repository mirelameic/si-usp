# Bibliotecas necessárias
if(!require(dplyr))
  install.packages("dplyr")
library(dplyr)

if(!require(psych))
  install.packages("psych")
library(psych)

if(!require(ISwR))
  install.packages("ISwR")
library(ISwR)


# Importando DataSet e omitindo valores nulos
dados <- read.csv('Maths.csv', sep = ',', stringsAsFactors = F)
dados <- na.omit(dados)

# Variáveis qualitativas
# Frequências absolutas:
table(dados$school)
table(dados$sex)
table(dados$age)
table(dados$address)
table(dados$famsize)
table(dados$Pstatus)
table(dados$Medu)
table(dados$Fedu)
table(dados$Mjob)
table(dados$Fjob)
table(dados$reason)
table(dados$guardian)
table(dados$traveltime)
table(dados$studytime)
table(dados$failures)
table(dados$schoolsup)
table(dados$famsup)
table(dados$paid)
table(dados$activities)
table(dados$nursery)
table(dados$higher)
table(dados$internet)
table(dados$romantic)
table(dados$famrel)
table(dados$freetime)
table(dados$goout)
table(dados$Dalc)
table(dados$Walc)
table(dados$health)
table(dados$absences)
table(dados$G1)
table(dados$G2)
table(dados$G3)

# Frequências relativas:
prop.table(table(dados$school))
prop.table(table(dados$sex))
prop.table(table(dados$age))
prop.table(table(dados$address))
prop.table(table(dados$famsize))
prop.table(table(dados$Pstatus))
prop.table(table(dados$Medu))
prop.table(table(dados$Fedu))
prop.table(table(dados$Mjob))
prop.table(table(dados$Fjob))
prop.table(table(dados$reason))
prop.table(table(dados$guardian))
prop.table(table(dados$traveltime))
prop.table(table(dados$studytime))
prop.table(table(dados$failures))
prop.table(table(dados$schoolsup))
prop.table(table(dados$famsup))
prop.table(table(dados$paid))
prop.table(table(dados$activities))
prop.table(table(dados$nursery))
prop.table(table(dados$higher))
prop.table(table(dados$internet))
prop.table(table(dados$romantic))
prop.table(table(dados$famrel))
prop.table(table(dados$freetime))
prop.table(table(dados$goout))
prop.table(table(dados$Dalc))
prop.table(table(dados$Walc))
prop.table(table(dados$health))
prop.table(table(dados$absences))
prop.table(table(dados$G1))
prop.table(table(dados$G2))
prop.table(table(dados$G3))

# Variáveis quantitativas contínuas
# Amplitudes
range(dados$age)
range(dados$absences)
range(dados$G1)
range(dados$G2)
range(dados$G3)

# Função summary (média, mediana, quartis e valores mín e máx)
summary(dados$age)
summary(dados$absences)
summary(dados$G1)
summary(dados$G2)
summary(dados$G3)

# Mediana para data 
medianAge <- median(dados$age)
medianAbsences <- median(dados$absences)
medianG1 <- median(dados$G1)
medianG2 <- median(dados$G2)
medianG3 <- median(dados$G3)

print(medianAge)
print(medianAbsences)
print(medianG1)
print(medianG2)
print(medianG3)

# Moda
getmode <- function(dados) {
  uniqv <- unique(dados)
  uniqv[which.max(tabulate(match(dados, uniqv)))]
}
resultAge <- getmode(dados$age)
resultAbsences <- getmode(dados$absences)
resultG1 <- getmode(dados$G1)
resultG2 <- getmode(dados$G2)
resultG3 <- getmode(dados$G3)

print(resultAge)
print(resultAbsences)
print(resultG1)
print(resultG2)
print(resultG3)

# Função describe e describeBy (média, desvio padrão[sd], erro, mediana)
describe(dados$age)
describe(dados$absences)
describe(dados$G1)
describe(dados$G2)
describe(dados$G3)

# Erro
std_mean <- function(x) sd(x)/sqrt(length(x))
std_mean(dados$age)
std_mean(dados$absences)
std_mean(dados$G1)
std_mean(dados$G2)
std_mean(dados$G3)

# Variância
var(dados$age)
var(dados$absences)
var(dados$G1)
var(dados$G2)
var(dados$G3)

## GRÁFICOS QUANTITATIVAS
boxplotAge <- boxplot(dados$age, col="darkblue", border="black")
hist(dados$age, col="darkblue", border="black")

boxplotAbsences <- boxplot(dados$absences, col="darkblue", border="black")
hist(dados$absences, col="darkblue", border="black")

boxplotG1 <- boxplot(dados$G1, col="darkblue", border="black")
hist(dados$G1, col="darkblue", border="black")

boxplotG2 <- boxplot(dados$G2, col="darkblue", border="black")
hist(dados$G2, col="darkblue", border="black")

boxplotG3 <- boxplot(dados$G3, col="darkblue", border="black")
hist(dados$G3, col="darkblue", border="black")


## GRÁFICOS QUALITATIVAS

x <- xtabs(~Walc, data = dados)
barplot(x,
        xlab = "Consumo de álcool aos finais de semana",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))

pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Consumo de álcool aos finais de semana")

x <- xtabs(~school, data = dados)
barplot(x,
        xlab = "Escolas",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))

pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Escolas")

x <- xtabs(~sex, data = dados)
barplot(x,
        xlab = "Gênero",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))

pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Gênero")

x <- xtabs(~famsize, data = dados)
barplot(x,
        xlab = "Tamanho da família",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))

pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Tamanho da família")

x <- xtabs(~Pstatus, data = dados)
barplot(x,
        xlab = "Status de convivência dos pais",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))

pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Status de convivência dos pais")

x <- xtabs(~Mjob, data = dados)
barplot(x,
        xlab = "Emprego da mãe",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))
pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Emprego da mãe")

x <- xtabs(~Fjob, data = dados)
barplot(x,
        xlab = "Emprego do pai",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))
pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Emprego do pai")

x <- xtabs(~reason, data = dados)
barplot(x,
        xlab = "Razão para escolher a escola",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))
pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Razão para escolher a escola")

x <- xtabs(~guardian, data = dados)
barplot(x,
        xlab = "Cuidador",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))
pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Cuidador")

x <- xtabs(~schoolsup, data = dados)
barplot(x,
        xlab = "Suporte educacional extra",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))
pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Suporte educacional extra")

x <- xtabs(~famsup, data = dados)
barplot(x,
        xlab = "Suporte familiar educacional",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))
pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Suporte familiar educacional")

x <- xtabs(~paid, data = dados)
barplot(x,
        xlab = "Aulas extras pagas",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))
pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Aulas extras pagas")

x <- xtabs(~activities, data = dados)
barplot(x,
        xlab = "Atividades extracurriculares",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))
pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Atividades extracurriculares")

x <- xtabs(~nursery, data = dados)
barplot(x,
        xlab = "Frequentou a escola de enfermaria",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))
pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Frequentou a escola de enfermaria")

x <- xtabs(~higher, data = dados)
barplot(x,
        xlab = "Quer seguir no ensino superior",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))
pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Quer seguir no ensino superior")

x <- xtabs(~internet, data = dados)
barplot(x,
        xlab = "Acesso à internet em casa",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))
pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Acesso à internet em casa")

x <- xtabs(~romantic, data = dados)
barplot(x,
        xlab = "Em relacionamento amoroso",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))
pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Em relacionamento amoroso")
