import pymongo
import creds
from pprint import pprint
import certifi

def get_atlas_db_url(username: str, password: str):
    return f"mongodb+srv://{username}:{password}@cluster0.rcldl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"

def get_legion_db_url(username: str, password: str):
    # return f"mongodb://{username}:{password}@7232-195-150-8-23.ngrok.io:27017"
    return f"mongodb://{username}:{password}@192.168.3.64:27017"

def connect_to_db(username: str, password: str, db: str):
    if db == 'atlas':
        url_provider = get_atlas_db_url
    elif db == 'legion':
        url_provider = get_legion_db_url
    else:
        raise RuntimeError(f"Unknown url provider {db}")

    print(f"Connecting to {db} mongodb with credentials: {username}:{password}")
    db_client = pymongo.MongoClient(url_provider(username, password), tlsCAFile=certifi.where())

    print("Acquire test database")
    db = db_client.get_database(name='test')
    print("Database names")
    print(db.name)

    collection = db['testCollection']
    some_data = {'name': 'testName'}
    more_data = [
        {'name': f'test{i}'} for i in range(100)
    ]
    print("Attempting insertion")
    result = collection.insert_one(some_data)
    print("Insertion completed")
    print("result")
    print(result.inserted_id)


    print("Attempting massive insertion")
    collection.insert_many(more_data)
    print("Massive insertion completed")

    
def connect_to_atlas(username: str, password: str):
    connect_to_db(username, password, 'atlas')

def connect_to_legion(username: str, password: str):
    connect_to_db(username, password, 'legion')


if __name__ == "__main__":
    connect_to_atlas(*creds.credentials)
