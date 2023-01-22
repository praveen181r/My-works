import requests
from flask import Flask, render_template, request
import numpy as np
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing import image
import os

app = Flask(__name__)
model = load_model('ECG.h5')


@app.route('/')
@app.route('/home.html')
def home():
    return render_template('home.html')


@app.route('/about.html')
def about():
    return render_template('about.html')


@app.route('/Atrial fibrillation.html')
def atrial_fibrillation():
    return render_template('Atrial fibrillation.html')


@app.route('/Atrial flutter.html')
def atrial_flutter():
    return render_template('Atrial flutter.html')


@app.route('/predict.html', methods=['GET', 'POST'])
def predict():
    if request.method == 'POST':
        f = request.files['file']
        basepath = os.path.dirname('__file__')
        filepath = os.path.join(basepath, "uploads", f.filename)
        f.save(filepath)

        img = image.load_img(filepath, target_size=(64, 64))
        x = image.img_to_array(img)
        x = np.expand_dims(x, axis=0)

        pred = np.argmax(model.predict(x), axis=1)
        print("prediction", pred)
        index = ['Left Branch Block', 'Normal', 'Premature Atrial Contraction', 'Premature Ventricular Contractions', 'Right Bundle Branch Block', 'Ventricular Fibrillation']
        result = str(index[pred[0]])

        return render_template('result.html', result=result)
    else:
        return render_template('predict.html')


@app.route('/Supraventricular.html')
def supraventricular():
    return render_template('Supraventricular.html')


@app.route('/Ventricular fibrillation.html')
def ventricular_fibrillation():
    return render_template('Ventricular fibrillation.html')


@app.route('/Ventricular tachycardia.html')
def ventricular_tachycardia():
    return render_template('Ventricular tachycardia.html')


if __name__ == '__main__':
    app.run()
