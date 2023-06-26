# Importando DataSet e omitindo valores nulos
dados <- read.csv('Maths.csv', sep = ',', stringsAsFactors = F)
dados <- na.omit(dados)

# selecionando apenas as variáveis utilizadas na análise
dados2 <- dados[,c(3,30,33)]

# padronizando dados
df <- scale(dados2)

############ AGRUPAMENTO HIERÁRQUICO ############
# gerando matriz de distância euclidiana
df.dist = dist(df)
df.dist

# análise de clusters utilizando a ligação completa
df.hclust = hclust(df.dist)
plot(df.hclust)

# selecionando apenas 5 grupos de clusters
groups.5 = cutree(df.hclust, 5)
table(groups.5)

# input do número do cluster no database original
dclusters <- cbind(dados2, cluster = groups.5)
head(dclusters)

medias <- aggregate(dados2, by=list(cluster=groups.5), mean)
print(medias)

############ AGRUPAMENTO NÃO-HIERÁRQUICO ############
library(factoextra)

# método de soma de quadrados, para avaliar a quantidade de clusters
fviz_nbclust(dados2, kmeans, method = "wss")

# cálculo do algoritmo k-means
set.seed(123)
km <- kmeans(df, 5, nstart = 25)
km

# input do número do cluster no database original
dclusters2 <- cbind(dados2, cluster = km$cluster)

medias2 <- aggregate(dados2, by=list(cluster=km$cluster), mean)
print(medias2)



