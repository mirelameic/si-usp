import sys
import os
import numpy as np
from sklearn.metrics import confusion_matrix

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from data_preprocess import load_and_preprocess_data, filter_binary_classes
from src.cnn_model import build_multiclass_model, build_binary_model
from src.train_and_evaluate import train_and_evaluate
from src.plot import plot_confusion_matrix, plot_accuracy, plot_loss

# carrega e pré-processa os dados
(train_images, train_labels), (test_images, test_labels) = load_and_preprocess_data()

# constrói e treina o modelo multiclasses
print("\n \033[95m Training and evaluating multiclass model... \033[0m")
multiclass_model = build_multiclass_model((28, 28, 1))
multiclass_history, _, _, multiclass_predictions = train_and_evaluate(multiclass_model, train_images, train_labels, test_images, test_labels)

# transforma as previsões do modelo multiclasses para rótulos de classes
multiclass_pred_labels = np.argmax(multiclass_predictions, axis=1)

# calcula a matriz de confusão para o modelo multiclasses
multiclass_cm = confusion_matrix(test_labels, multiclass_pred_labels)

# plota matriz de confusão, precisão e erro do modelo multiclasses
plot_confusion_matrix(multiclass_cm, class_names=[str(i) for i in range(10)], title='Multiclass Confusion Matrix', filename='plot/multiclass_confusion_matrix.png')
plot_accuracy(multiclass_history, filename='plot/multiclass_accuracy.png')
plot_loss(multiclass_history, filename='plot/multiclass_loss.png')

# constrói e treina o modelo binário
# fazendo a classificação entre a classe 0 e as outras classes
target_class = 0
print(f"\n \033[95m Training and evaluating binary model for class {target_class}... \033[0m")
binary_train_images, binary_train_labels = filter_binary_classes(train_images, train_labels, target_class)
binary_test_images, binary_test_labels = filter_binary_classes(test_images, test_labels, target_class)
binary_model = build_binary_model((28, 28, 1))
binary_history, _, _, binary_predictions = train_and_evaluate(binary_model, binary_train_images, binary_train_labels, binary_test_images, binary_test_labels)

# transforma as previsões do modelo binário para rótulos de classes
binary_pred_labels = (binary_predictions > 0.5).astype(int).flatten()

# calcula a matriz de confusão para o modelo binário
binary_cm = confusion_matrix(binary_test_labels, binary_pred_labels)

# plota matriz de confusão, precisão e erro do modelo binário
plot_confusion_matrix(binary_cm, class_names=['Not ' + str(target_class), str(target_class)], title=f'Binary Confusion Matrix (Not {target_class} vs {target_class})', filename=f'plot/binary_confusion_matrix_{target_class}.png')
plot_accuracy(binary_history, filename=f'plot/binary_accuracy_{target_class}.png')
plot_loss(binary_history, filename=f'plot/binary_loss_{target_class}.png')
