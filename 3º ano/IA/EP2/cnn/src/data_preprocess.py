import tensorflow as tf
from tensorflow import keras
from keras import datasets

def load_and_preprocess_data():
    # carrega o conjunto de dados MNIST
    (train_images, train_labels), (test_images, test_labels) = datasets.mnist.load_data()

    # normaliza as imagens para o intervalo [0, 1]
    train_images = train_images / 255.0
    test_images = test_images / 255.0

    # adiciona uma dimensão de canal para as imagens
    train_images = train_images[..., tf.newaxis]
    test_images = test_images[..., tf.newaxis]

    return (train_images, train_labels), (test_images, test_labels)

def filter_binary_classes(images, labels, target_class):
    # cria rótulos binários: 1 se o rótulo for igual à target_class, 0 caso contrário
    binary_labels = (labels == target_class).astype(int)
    return images, binary_labels
