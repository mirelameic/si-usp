# Bibliotecas/pacotes
if(!require(dplyr))
  install.packages("dplyr")
library(dplyr)

if(!require(psych))
  install.packages("psych")
library(psych)

library(ggplot2)
library(dplyr)
library(RVAideMemoire)
library(rstatix)
library(tidyverse)
library(car)

# Importando DataSet e omitindo valores nulos
dados <- read.csv('database.csv', sep = ',', stringsAsFactors = T)
dados <- na.omit(dados)

# Formatando data
data <- (dados$SurveyYear)
data <- sapply(data,function(x) strptime(x,format="%d/%m/%Y"))
class(data)
print(data)

######### VARIÁVEIS QUALITATIVAS #########
# Frequências absolutas:
table(dados$Gender)
table(dados$Country)
table(dados$DemographicsQuestion)
table(dados$DemographicsResponse)
table(dados$Question)

# Frequências relativas:
prop.table(table(dados$Gender))
prop.table(table(dados$Country))
prop.table(table(dados$DemographicsQuestion))
prop.table(table(dados$DemographicsResponse))
prop.table(table(dados$Question))

######### VARÁVEIS QUANTITATIVAS CONTÍNUAS #########
# Analisando as amplitudes
range(dados$Value)

# Avaliando a quantidade de categorias necessárias
nclass.Sturges(dados$Value)

# Criando as tabelas com as faixas
table(cut(dados$Value, seq(0.0, 87, l = 15)))

# Função summary (média, mediana, quartis e valores mín e máx)
summary(dados$Value)
summary(question_num$Question)

# Moda
getmode <- function(dados) {
  uniqv <- unique(dados)
  uniqv[which.max(tabulate(match(dados, uniqv)))]
}
resultCountry <- getmode(dados$Country)
resultDemoQuestion <- getmode(dados$DemographicsQuestion)
resultDemoResponse <- getmode(dados$DemographicsResponse)
resultQuestion <- getmode(dados$Question)
resultGender <- getmode(dados$Gender)
resultSurveyYear <- getmode(dados$SurveyYear)
print(resultGender)
print(resultCountry)
print(resultDemoQuestion)
print(resultDemoResponse)
print(resultQuestion)
print(resultSurveyYear)

# Função describe e describeBy (média, desvio padrão[sd], erro, mediana)
describe(dados$Value)
describeBy(dados$Value, group = dados$Question)

# Erro
std_mean <- function(x) sd(x)/sqrt(length(x))
std_mean(dados$Value)

# Variância
var(dados$Value)
var(question_num$Question)


######### GRÁFICOS QUANTITATIVAS #########
boxplotValue <- boxplot(dados$Value, col="darkblue", border="black")
hist(dados$Value, col="darkblue", border="black")

######### GRÁFICOS QUALITATIVAS #########
x <- xtabs(~Gender, data = dados)
barplot(x,
        xlab = "Gêneros",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))

pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Gêneros")

x <- xtabs(~Country, data = dados)
barplot(x,
        xlab = "Países",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))

pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Países")

x <- xtabs(~DemographicsQuestion, data = dados)
barplot(x,
        xlab = "Perguntas demográficas",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))

pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Perguntas demográficas")

x <- xtabs(~DemographicsResponse, data = dados)
barplot(x,
        xlab = "Respostas demográficas",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))

pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Respostas demográficas")

x <- xtabs(~Question, data = dados)
barplot(x,
        xlab = "Perguntas",
        ylab = "Frequência absoluta",
        col = c("seagreen", "yellowgreen"))
pie(x, col = c("#5398ed", rgb(12, 58, 114, max = 255)), main = "Perguntas")

# Transformando Gender (F, M) para numérico
gender_num <- transform(dados,Gender = as.numeric(Gender))
print(gender_num$Gender)

# Transformando Question para numérico
question_num <- transform(dados,Question = as.numeric(Question))
print(question_num$Question)

######### ANOVA GENDER #########
boxplot(gender_num$Value ~ gender_num$Gender, col='pink', border='black')
anova1 <- aov(gender_num$Value ~ gender_num$Gender, data = gender_num)
summary(anova1)


######### ANOVA QUESTIONS #########
boxplot(question_num$Value ~ question_num$Question, xlab = "Perguntas", ylab = "Valores", main = "Boxplot", col='pink', border='black')
anova2 <- aov(question_num$Value ~ question_num$Question, data = question_num)
summary(anova2)

hist(anova2$residuals)

# SHAPIRO E KRUSKAL TESTS
shapiro.test(anova2$residuals[0:5000])
kruskal.test(anova2$residuals ~ question_num$Value, question_num)


