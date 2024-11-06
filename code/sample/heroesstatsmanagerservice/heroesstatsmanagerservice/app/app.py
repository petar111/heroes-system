from flask import Flask
import json
import os

app = Flask(__name__)

@app.route('/')
def hello():
    x = {'message':'Hello world',
         'osName':os.name}
    return x

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)