# BITEHACK 2022 - "Oszczędzanie"

## Inspiracja
* ogromne ilości żywności są marnowane przez nadmierne lub nieodpowiednie nawożenie i opryski upraw
* na rynku pojawiają się czujniki mierzące parametry punktu roli, w szczególności natężenie występowania konkretnych szkodników
* oszczędność pestycydów/nawozów → oszczędność żywności, pieniędzy, energii, gazów cieplarnianych, środowiska, podniesienie jakości żywności i gleby, ratujemy pszczoły (:D) 

## Pomysł
* stworzenie algorytmu i sterownika
* algorytm na podstawie zebranych danych wyznacza dozowanie środka dla danego punktu areału rolnego
* wyjście algorytmu jest wejściem dla sterownika
* sterownik, bazując na aktualnej pozycji ciągnika (GPS) i wyjściu algorytmu podejmuje decyzję o dozowaniu środka z danej grupy dyszy w danej chwili

## Technologie
* Python 3 (Flask, numpy)
* MongoDB
* Java (Swing, Gson)

## Ilość środka w funkcji zmierzonych parametrów
* dobranie odpowiedniej zależności funkcyjnej do znalezienia heatmapy
* parametry: ilość konkretnego szkodnika w jednostce czasu, opady, pora roku (etap rozwoju szkodnika, etap rozwoju uprawy), kierunek i siła wiatru (proces oprysku)

## Baza danych
* rozmieszczenie sensorów
* zarejestrowane pomiary
* koordynaty areału rolniczego
* dane klientów

## Serwer logiki biznesowej
* obsługa zapytań sterownika
* kontakt z bazą danych
* obliczenie heurystyki
* przesłanie wyniku do sterownika

## Sterownik
* zapytania do serwera
* na podstawie danych z serwera wyznaczenie natężenia oprysku dla poszczególnych grupy dyszy
kontakt z systemem GPS

## Pozyskiwanie danych
* obecnie, głównie mock (generowane dane)
* docelowo odczyty z rzeczywistych czujników, zapytania do API pogodowego, bazy danych pestycydów

## Kierunki rozwoju
* zwiększenie liczby parametrów funkcji (pestycydy, koszty, inne parametry atmosferyczne)
* wyznaczanie oceny obecnej sytuacji na obszarze na podstawie zmian w pomiarach na przestrzeni czasu do oceny korzyści płynących ze sterownika i w celu modyfikacji parametrów funkcji
* skonstruowanie bardziej zaawansowanych algorytmów optymalizacji i/lub zaadoptowanie metod sztucznej inteligencji / ML
* system wyznaczania optymalnej trasy oraz sugerowania korzystnych dni oprysków (wpływ pogody, pory roku, innych przesłanek)




