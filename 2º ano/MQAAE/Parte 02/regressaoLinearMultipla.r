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

if(!require(pacman)) install.packages("pacman")
library(pacman)

if(!require(nycflights13)) install.packages("nycflights13")
library(nycflights13)

if(!require(fastDummies)) install.packages("fastDummies")
library(fastDummies)

if(!require(ggplot2)) install.packages("ggplot2")
library(ggplot2)

if(!require(graphics)) install.packages("graphics")
library(graphics)

# Importando DataSet e omitindo valores nulos
dados <- read.csv('Maths.csv', sep = ',', stringsAsFactors = F)
dados <- na.omit(dados)

## Criando dummies com consumo de álcool diário
dados2 <- dummy_cols(dados, select_columns = 'Walc')

######################### Regressão Linear Simples #########################
# Intercept = coeficiente linear, coeficiente angular com cada variável
modAbsence <- lm(dados2$G3 ~ dados2$absences)
modAge <- lm(dados2$G3 ~ dados2$age)
modAlcohol <- lm(dados2$G3 ~ dados2$Walc_4)

summary(modAbsence)
summary(rstandard(modAbsence))
summary(modAge)
summary(rstandard(modAge))
summary(modAlcohol)
summary(rstandard(modAlcohol))

anova1 <- aov(modAbsence)
anova2 <- aov(modAge)
anova3 <- aov(modAlcohol)

shapiro.test(anova1$residuals)
shapiro.test(anova2$residuals)
shapiro.test(anova3$residuals)

boxplot(dados2$G3 ~ dados2$absences, xlab = "Faltas", ylab = "Nota da prova final", main = "Boxplot", col='pink', border='black')
boxplot(dados2$G3 ~ dados2$age, xlab = "Idades", ylab = "Nota da prova final", main = "Boxplot", col='pink', border='black')
boxplot(dados2$G3 ~ dados2$Walc_4, xlab = "Consumo de álcool em altas quantidades em finais de semana", ylab = "Nota da prova final", main = "Boxplot", col='pink', border='black')

######################### Regressão Linear Múltipla #########################
# Construção do modelo
modelo <- lm(G3 ~ absences + age + Walc_4, dados2)

# Summary: coeficiente de regressão, t value, p value, erro padrão
summary(modelo)

# Coeficiente de correlção: r^2

# Análise gráfica
par(mfrow=c(2,2))
plot(modelo)
par(mfrow=c(1,1))

# Normalidade dos resíduos:
shapiro.test(modelo$residuals)

# Outliers nos resíduos
summary(rstandard(modelo))

# Independência dos resíduos (Durbin-Watson)
durbinWatsonTest(modelo)

# Homocedasticidade (Breusch-Pagan)
bptest(modelo)

# Ausência de Multicolinearidade (VIF > 10)
vif(modelo)


graph <- scatterplot3d(dados2$G3 ~ as.numeric(dados2$absences) + as.numeric(dados2$age)  + as.numeric(dados2$Walc_4),
                      pch = 16, angle = 30, color = "steelblue", box = FALSE,
                      xlab="Tempo de revisão", ylab="Tempo de sono", zlab="Notas")
graph$plane3d(mod, col="black", draw_polygon = TRUE)


################## Backward #######################
# modelo com todas as variáveis
modelo <- lm(G3 ~ absences + age + Walc_4, dados2)
summary(modelo)

step(modelo, direction="backward")
step(modelo, direction="forward")
step(modelo, direction="both")
