import socket
import librosa
from keras.models import load_model

soc = socket.socket()
host = "192.168.43.53"
port = 2004
soc.bind((host, port))
soc.listen(5)

classes = ["right","eight","cat","tree","bed","happy","go","dog","no","wow","nine","left","stop",
"three","background noise","sheila","one","bird","zero","seven","up","marvin","two","house","down",
"six","yes","on","five","off","four"]
model = load_model('best_model.hdf5')

def predict_audio():
    samples, sample_rate = librosa.load('output.wav', sr = 16000)
    audio = librosa.resample(samples, sample_rate, 8000)
    prob = model.predict(audio.reshape(-1,8000,1))
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
    
    # text = predict_audio()
    # print(text)	


          


