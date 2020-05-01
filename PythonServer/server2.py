import socket

soc = socket.socket()
host = "192.168.43.53"
port = 2001
soc.bind((host, port))
soc.listen(5)




while True:
    conn, addr = soc.accept()
    print("Got connection from",addr)
    file = open('sample.txt')
    text = file.read().encode("UTF-8")
    message_to_send = "chuj".encode("UTF-8")
    conn.send(len(text).to_bytes(2, byteorder='big'))
    conn.send(text)

