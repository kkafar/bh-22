from curses import termattrs
import random
from collections import namedtuple

def weather_info_factory(
    temperature: float,
    season: str,
    wind_vector: tuple,
    rain: int
):
    assert isinstance(temperature, float)
    assert isinstance(season, str)
    assert isinstance(wind_vector, tuple) and len(wind_vector) == 2
    assert isinstance(rain, int)

    return {
        'temperature': temperature,
        'season': season,
        'wind_vector': wind_vector,
        'rain': rain
    }


class WeatherInfoProvider(object):
    def __init__(self):
        pass


    def get_info(self, location: tuple):
        return weather_info_factory(
            self._get_temperature_for(location),
            self._get_season(location),
            self._get_wind_info(location),
            self._get_rain_info(location)
        )
        
    
    def _get_temperature_for(self, location: tuple) -> float:
        # odwołanie do jakiegoś api pogodowego
        return random.random() * 40

    def _get_season(self, location: tuple) -> str:
        # odwołanie do serwera czasu
        # wzięcie pod uwagę półkuli i ustalenie pory roku
        return 'spring'


    def _get_wind_info(self, location: tuple) -> tuple:
        # odwołanie do jakiegoś api pogodowego
        return random.random(), random.random()

    def _get_rain_info(self, location: tuple) -> tuple:
        # odwołanie do jakiegoś api pogodowego
        return int(random.random() > 0.7)


