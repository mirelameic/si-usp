def train_and_evaluate(model, train_images, train_labels, test_images, test_labels, epochs=5):
    # treina o modelo nos dados de treinamento por um número especificado de épocas
    # avalia o modelo nos dados de teste e imprime a acurácia
    history = model.fit(train_images, train_labels, epochs=epochs, 
                        validation_data=(test_images, test_labels))
    test_loss, test_acc = model.evaluate(test_images, test_labels, verbose=2)
    print(f'Test accuracy: {test_acc}')
    predictions = model.predict(test_images)
    return history, test_loss, test_acc, predictions
