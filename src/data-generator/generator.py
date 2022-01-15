import random as rnd

import pymongo
import creds
import certifi
import datetime

def get_atlas_db_url(username: str, password: str):
    return f"mongodb+srv://{username}:{password}@cluster0.rcldl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"


def get_legion_db_url(username: str, password: str):
    # return f"mongodb://{username}:{password}@7232-195-150-8-23.ngrok.io:27017"
    return f"mongodb://{username}:{password}@192.168.3.64:27017"

def generate_area():
    x = 200 * rnd.random() - 100
    y = 100 * rnd.random() - 50
    w = 1.2 * rnd.random() + 1
    h = 1.2 * rnd.random() + 1
    #return [[x, y], [x+w, y], [x+w, y+h], [x, y+h]], (x, y, w, h)
    return [[20, 20], [22, 20], [22, 22], [24, 22], [24, 23], [23, 23], [23, 24], [20, 24]], (20, 20, 4, 4)




def generate_sensors(size, x, y, w, h):
    res = []
    for _ in range (size):
        X = w * rnd.random() + x
        Y = h * rnd.random() + y
        res.append((X, Y))
    return res


def generate_sensors_value(size):
    return [rnd.randint(0, 50) for _ in range(size)]

def generate_one_document(count_area, count_sensors):
    data = []
    for i in range (count_area):
        name = str(i+1)
        area = generate_area()
        sensors = generate_sensors(count_sensors, *area[1])
        readings = []
        for i in range (5):
            one_readings = {}
            one_readings['date'] = str(datetime.datetime(2022, 1, i+5))
            one_readings['values'] = generate_sensors_value(count_sensors)
            readings.append(one_readings)
        one = {
            'name': name,
            'vertices': area[0],
            'sensors': sensors,
            'readings': readings
        }
        data.append(one)
    return data


def add_data_db(username: str, password: str, db: str):
    if db == 'atlas':
        url_provider = get_atlas_db_url
    elif db == 'legion':
        url_provider = get_legion_db_url
    else:
        raise RuntimeError(f"Unknown url provider {db}")

    print(f"Connecting to {db} mongodb with credentials: {username}:{password}")
    db_client = pymongo.MongoClient(url_provider(username, password), tlsCAFile=certifi.where())

    print("Acquire test database")
    db = db_client.get_database(name='Production')
    print("Database names")
    print(db.name)

    for name_farmer in farmer_names:
        collection = db[name_farmer]
        count_area = rnd.randint(3, 7)
        data = generate_one_document(count_area, 15)
        print("Attempting massive insertion")
        print (data)
        collection.insert_many(data)
        print("Massive insertion completed")


if __name__ == "__main__":
    size = 2
    farmer_names = [str(i) for i in range(6, size+6)]
    add_data_db(*creds.credentials, 'atlas')


