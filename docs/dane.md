# Informacje o polu (dla logiki biznesowej)

* lista punktów (współrzędne geograficzne) wierzchołków pola (dane nieuporządkowane)
* lista współrzędnych czujników zbierających informacje o owadzie (współrzędne geograficzne, dane nieuporządkowane)
* Czujnik będzie obiektem przechowującym dane o pomiarach

# Informacje o pogodzie (logika biznesowa)

* aktualna siła i kierunek wiatru (jako wektor 2 floatów, jego długość to siłą wiatru)
* temperatura (Celsjusze)
* pora roku (string)

# Informacje o środku

* przedział temperatury stosowania
* proporcje rozcieńczania (ml na litr wody)
* cena za litr (USD)

# Informacja do wizualizacji

* tablica 2D wartości (-1 oznacza że kwadrat nie należy do pola, a tak floaty jako poziom natężenia środku)
* komunikat czy nawozić