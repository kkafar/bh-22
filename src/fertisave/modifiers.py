from numpy import sqrt

from collections import defaultdict
SEASON_MODIFIER = defaultdict(lambda x: 1)
SEASON_MODIFIER['spring'] = 1.1
SEASON_MODIFIER['summer'] = 1.3
SEASON_MODIFIER['autumn'] = 0.8
SEASON_MODIFIER['winter'] = 0

RAIN_MODIFIER = 1.3

WIND_MODIFIER = 0.02

def function(k):
    return min(k ** 2 / 20, 100)

def weight(point_a, point_b):
    return 1 / sqrt((point_a[0] - point_b[0])**2 + (point_a[1] - point_b[1])**2)
