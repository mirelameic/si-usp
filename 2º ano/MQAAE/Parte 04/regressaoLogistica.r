if(!require(pacman)) install.packages("pacman")
library(pacman)

if(!require(car)) install.packages("car")
library(car)

if(!require(DescTools)) install.packages("DescTools")
library(DescTools)

if(!require(BiocManager)) install.packages("BiocManager")
library(BiocManager)

pacman::p_load(dplyr, psych, car, MASS, DescTools, QuantPsyc, ggplot2)

# Importando DataSet e omitindo valores nulos
dados <- read.csv('Maths.csv', sep = ',', stringsAsFactors = TRUE)
dados <- na.omit(dados) 

dados2 <- dados[,c(23,30,33)]
glimpse(dados2)

# Checando balanceamentos

table(dados2$romantic)
summary(dados2)
# nenhum dado grave de desbalanceamento

############## Regressão Logística Binária ###############
# Checagem das categorias de referência
levels(dados2$romantic) # categoria de referência: "não"

# existência de relação romântica é variável dependente

# Checagem de pressupostos
# 1) variável dependente dicotômica (mutuamente exclusivas)
## sim, quem está em uma relação romântica não pode não estar ao mesmo tempo
# 2) independência das observações
## sim, não há medidas repetidas, cada registro se refere a um aluno diferente

# construção do modelo
mod <- glm(romantic ~ absences + G3, 
           family = binomial(link = 'logit'), data = dados2)
# família d distribuição binomial, e função de ligação é a função logit
# assim, regressão logística binária

# 3) ausência de outliers
plot(mod, which = 5)
# pontos além da linha pontilhada, que mal aparece, então tudo ok

# análise do resíduo padronizado: preocupante quando estão alem da faixa -3 +3
# tudo ok
summary(stdres(mod))

# 4) ausência de multicolinearidade 
# correlação muito alta entre 2 ou mais variáveis independentes
pairs.panels(dados2)
#limite 0.8 ou 0.9
# entre absences e G3: 0,03, tudo certo

vif(mod) 
# fator de inflação - problema de multicolinearidade quanto tá menor que 10

# 5) relação linear entre cada variável independente contínua e o logito da dependente

intlog <- dados2$absences * log(dados2$absences)

dados2$intlog <- intlog

intlog2 <- dados2$G3 * log(dados2$G3)

dados2$intlog2 <- intlog2

modint <- glm(romantic ~ G3 + absences + intlog + intlog2, 
              family = binomial(link='logit'), data = dados2)

summary(modint)

## outra opção: logito

logito <- mod$linear.predictors
dados2$logito <- logito

ggplot(dados2, aes(logito, absences)) + 
  geom_point(size = 0.5, alpha = 0.5) + 
  geom_smooth(method = 'loess') + 
  theme_classic()

ggplot(dados2, aes(logito, G3)) + 
  geom_point(size = 0.5, alpha = 0.5) + 
  geom_smooth(method = 'loess') + 
  theme_classic()

# 6) análise de modelo
# efeito geral: overall effects

Anova(mod, type="II", test="Wald")

# efeitos específicos

summary(mod)

# interpratação dos coeficientes em razão de chances - odds ratio 
# odds ratio eh colocar coeficiente e colocar como expoente de valor e (Euler)
# razões de chances com intervalo de confiança 95% (usando erro padrão = SPSS)
exp(cbind(OR = coef(mod), confint.default(mod)))

# obtenção das razões de chance com IC 95% (usando log-likelihood)
exp(cbind(OR = coef(mod), confint(mod)))

# criação de segundo modelo
mod2 <- glm(romantic ~ G3, 
              family = binomial(link='logit'), data = dados2)

# overall effects
Anova(mod2, type="II", test="Wald")
summary(mod2)

# razões de chance
exp(cbind(OR = coef(mod2), confint.default(mod2)))

# obtenção das razões de chance com IC 95% (usando log-likelihood)
exp(cbind(OR = coef(mod2), confint(mod2)))

# avaliação da qualidade e comparação entre modelos
# pseudo r quadrado
# porcentagem de variância (indep) está sendo explicada pelo modelo
PseudoR2(mod, which = "Nagelkerke")
PseudoR2(mod2, which = "Nagelkerke")
