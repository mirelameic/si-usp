from tensorflow import keras
from keras import models, layers

def build_multiclass_model(input_shape):
    # cria um modelo sequencial com camadas convolucionais, de pooling, e densas
    # utiliza a função de ativação ReLU para as camadas convolucionais e densas
    # a última camada densa utiliza a função de ativação softmax para classificação multiclasses
    model = models.Sequential([
        layers.Conv2D(32, (3, 3), activation='relu', input_shape=input_shape),
        layers.MaxPooling2D((2, 2)),
        layers.Conv2D(64, (3, 3), activation='relu'),
        layers.MaxPooling2D((2, 2)),
        layers.Conv2D(64, (3, 3), activation='relu'),
        layers.Flatten(),
        layers.Dense(64, activation='relu'),
        layers.Dense(10, activation='softmax')
    ])
    # configura a função de perda sparse_categorical_crossentropy para classificação multiclasses
    model.compile(optimizer='adam',
                  loss='sparse_categorical_crossentropy',
                  metrics=['accuracy'])
    return model

def build_binary_model(input_shape):
    # cria um modelo sequencial com camadas convolucionais, de pooling, e densas
    # utiliza a função de ativação ReLU para as camadas convolucionais e densas
    # a última camada densa utiliza a função de ativação sigmoid para classificação binária
    model = models.Sequential([
        layers.Conv2D(32, (3, 3), activation='relu', input_shape=input_shape),
        layers.MaxPooling2D((2, 2)),
        layers.Conv2D(64, (3, 3), activation='relu'),
        layers.MaxPooling2D((2, 2)),
        layers.Conv2D(64, (3, 3), activation='relu'),
        layers.Flatten(),
        layers.Dense(64, activation='relu'),
        layers.Dense(1, activation='sigmoid')
    ])
    # configura a função de perda binary_crossentropy para classificação binária
    model.compile(optimizer='adam',
                  loss='binary_crossentropy',
                  metrics=['accuracy'])
    return model
