import numpy as np


#data - słownik:
# 'map' - lista wierzchołków
# 'sensors' - lista czujników i każdy czujnik to współrzędna x i y oraz wartość czujnika
# 'wind' - (siła, kierunek)
# 'rain' - siła

# -1 - nie leży w polu



class Map:
    def __init__(self, vertices):
        self.vertices = np.array(vertices)
        left_down = (min(self.vertices[:, 0]), min(self.vertices[:, 1]))
        right_up = (max(self.vertices[:, 0]), max(self.vertices[:, 1]))
        self.rectangle = (left_down, right_up)

    def get_width(self):
        return self.rectangle[0][0] - self.rectangle[1][0]

    def get_height(self):
        return self.rectangle[0][1] - self.rectangle[1][1]


class CreateHeatmap:
    def __init__(self, size, data):
        self.data = data #zwraca słownik z danymi
        if isinstance(size, int):
            size = (size, size)
        self.size = size
        self.heatmap = np.zeros(size)
        self.sensors_value = np.zeros(size)
        self.map = Map(self.data['vertices'])

    def weight(self, point_a, point_b):
        return 1 / np.sqrt((point_a[0] - point_b[0])**2 + (point_a[1] - point_b[1])**2)

    def read_sensors(self):
        left_down, right_up = self.map.rectangle
        width_area = (right_up[0] - left_down[0]) / self.size[0]
        height_area = (right_up[1] - left_down[1]) /  self.size[1]
        for i in range (self.size[0]):
            for j in range (self.size[1]):
                value = 0
                weights_sum = 0
                point = ((i+0.5) * width_area, (j+0.5) * height_area)
                for (coor_x, coor_y), v in zip(self.data['sensors'], self.data['readings']):
                    w = self.weight(point, (coor_x, coor_y))
                    weights_sum += w
                    value += v * w
                self.sensors_value[i, j] = value / weights_sum

    def function(self, k):
        return min(k**2 / 10, 100)

    def point_in_area(self, x, y):
        count = 0
        for i in range (len(self.map.vertices)):
            a, b = self.map.vertices[i-1], self.map.vertices[i]
            if x > a[0] and x > b[0]:
                continue
            if x < a[0] and x < b[0]:
                continue
            y_prim = x * (a[1] - b[1]) / (a[0] - b[0]) + a[1] - a[0] * (a[1] - b[1]) / (a[0] - b[0])
            if y_prim >= y:
                count += 1
        return True if count % 2 == 1 else False

    def calculate_heatmap(self):
        for i in range(self.size[0]):
            for j in range(self.size[1]):
                self.heatmap[i, j] = self.function(self.sensors_value[i, j])
        wind_vector = self.data['wind_vector']
        u = int(np.sign(wind_vector[0]))
        v = int(np.sign(wind_vector[1]))
        for i in range(self.size[0]):
            for j in range(self.size[1]):
                if u and i+u >= 0 and i+u < self.size[0]:
                    self.heatmap[i+u, j] -= self.heatmap[i, j] * wind_vector[0] / 100
                    self.heatmap[i, j] += self.heatmap[i, j] * wind_vector[0] / 100
                if v and j+v >= 0 and j+v < self.size[1]:
                    self.heatmap[i, j+v] -= self.heatmap[i, j] * wind_vector[1] / 100
                    self.heatmap[i, j] += self.heatmap[i, j] * wind_vector[1] / 100
        self.heatmap[self.heatmap > 100] = 100
        left_down, right_up = self.map.rectangle
        width_area = (right_up[0] - left_down[0]) / self.size[0]
        height_area = (right_up[1] - left_down[1]) / self.size[1]
        for i in range(self.size[0]):
            for j in range(self.size[1]):
                point = ((i+0.5) * width_area, (j+0.5) * height_area)
                if not self.point_in_area(*point):
                    self.heatmap[i, j] = -1


    def generate_heatmap(self):
        self.read_sensors()
        self.calculate_heatmap()
        return self.heatmap, self.map.rectangle

if __name__ == '__main__':
    ch = CreateHeatmap(10)
    heatmap = ch.generate_heatmap()
    for i in range (10):
        for j in range (10):
            print (int(heatmap[i, j]), end="  ")
        print (" ")
