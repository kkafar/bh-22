import pymongo
import creds
import certifi
import datetime

def map_info_factory(
    vertices: list,
    sensors: list,
    readings: list
):
    return {
        'vertices': vertices,
        'sensors': sensors,
        'readings': readings
    }

def get_atlas_db_url(username: str, password: str):
    return f"mongodb+srv://{username}:{password}@cluster0.rcldl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"



class MapInfoProvider(object):
    def __init__(self):
        pass

    
    def get_info(self, clientId: int, mapId: int) -> dict:
        # wyciągnięcie jsona z bazy danych

        # 1. połączyć się z bazą danych
        # 2. wyciągnąć dane
        # 3. zwrócić inof w takiej postaci jak jest potrzebne do wejścia algorytmu
        # implementującego logikę biznesową
        url_provider = get_atlas_db_url
        db_client = pymongo.MongoClient(url_provider(*creds.credentials), tlsCAFile=certifi.where())
        db = db_client.get_database(name='Productionv3')
        collection = db[str(clientId)]
        data = collection.find_one({'name': str(mapId)})
        dates = [datetime.datetime.strptime(element['date'], '%Y-%m-%d %H:%M:%S') for element in data['readings']]
        newest_date = str(max(dates))
        readings = None
        for element in data['readings']:
            if element['date'] == newest_date:
                readings = element['values']
                break
        return map_info_factory(data['vertices'], data['sensors'], readings)
