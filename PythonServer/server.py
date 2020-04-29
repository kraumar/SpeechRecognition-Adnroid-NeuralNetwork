import socket
import librosa
from keras.models import load_model
import numpy as np


soc = socket.socket()
host = "192.168.43.53"
port = 2004
soc.bind((host, port))
soc.listen(5)

classes = ['bed', 'bird', 'cat', 'dog', 'down', 'eight', 'five', 'four', 'go', 
'happy', 'house', 'left', 'marvin', 'nine', 'no', 'off', 'on', 'one', 'right', 
'seven', 'sheila', 'six', 'stop', 'three', 'tree', 'two', 'up', 'wow', 'yes', 
'zero']
model = load_model('best_model.hdf5')

def predict_audio():
    samples, sample_rate = librosa.load('output.wav', sr = 16000)
    samples = librosa.resample(samples, sample_rate, 8000)
    samples = librosa.util.fix_length(samples, 8000, mode='edge')
    prob = model.predict(samples.reshape(-1,8000,1))
    index=np.argmax(prob[0])
    return classes[index]

    
while True:
    conn, addr = soc.accept()
    print("Got connection from ",addr)
    with open('output.wav','wb') as f:
	    while True:
		    l = conn.recv(16*1024)
		    if not l: break
		    f.write(l)
    
    text = predict_audio()
    print(text)


    if(text != ''):
        conn.send(text.encode('utf-8'))


          


