from flask import Flask, request
from heatmap_logic import CreateHeatmap
import weatherinfo as wi
import mapinfo as mi
import numpy as np
# import pesticideinfo as pi

app = Flask(__name__)

def ndarray_to_list(arr):
    return [
        row.tolist() for row in arr
    ]

@app.route("/", methods=['POST'])
def main():
    weather_provider = wi.WeatherInfoProvider()
    map_info_provider = mi.MapInfoProvider()
    received_request = request.get_json(silent=True, force=True)
    # tutaj musi przyjść id rolnika i id pola
    # czyli received_request powinien mieć format:
    # {
    #   'clientId': <int>,
    #   'mapId': <int> 
    # }

    # tutaj zaciągam wszystkie dane od poszczególnych providerów
    # najpierw trzeba zaciągnąć dane z bazy danych
    map_info = map_info_provider.get_info(received_request['clientId'], received_request['mapId'])
    weather_info = weather_provider.get_info(map_info['vertices'][0])

    # odpalam na nich logikę biznesową
    heat_map, (lower_left, upper_right) = CreateHeatmap(30, map_info | weather_info).generate_heatmap()

    # dostaję jakiś wynik i potrzebuję przerobić go na jsona

    # uzyskanego jsona przesyłam jako odpowiedź
    return {
        'heatMap': ndarray_to_list(heat_map),
        'lowerLeft': lower_left,
        'upperRight': upper_right,
        'sensors': map_info['sensors']
    }
