import numpy as np
from flask import Flask
import json
import os
import numpy
import http.client

app = Flask(__name__)

@app.route('/hello/message')
def hello():
    x = {'message':'Hello world',
         'osName':os.name}
    return x

@app.route('/stats/integer/mean')
def calculate_integer_mean():
    client = http.client.HTTPConnection(host='reactive-java', port=8081)
    client.request('GET', '/stats/sampledata/integer/1000')
    response = client.getresponse()


    raw_data = response.read()
    data = json.loads(raw_data)

    return {'size': len(data),
            'stats':{
                'mean': float(np.mean(data)),
                'sum': float(np.sum(data)),
                'min': float(np.min(data)),
                'max': float(np.max(data))
            }}

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)