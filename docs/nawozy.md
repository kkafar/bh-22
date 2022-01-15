# Efekt końcowy

1. Wizualizacja desktopowa heatmapy ilustrującej dawkę nawozu w danym punkcie areału.
2. Logika biznesowa odpowiadająca za obliczanie dawki nawozu w danym punkcie areału.
3. Baza danych z wcześniej przygotowanymi danymi określającymi natężenie występowania szkodnika (na areał / czas, do zastanowienia).
4. Generator danych do bazy danych, dla którego wejściem jest opis obszaru, a wyjściem rozsądnie wyglądające dane z czujników. 


Jeżeli piszemy dodatkowo sterownik to potrzebne będą: 

1. Moduł obliczający jakąś sensowną trasę dla traktora i pozwalający na pobranie aktualnej jego pozycji przez sterownik
2. Sam sterownik który określa natężenie środka na poszczególnych dyszach. 

# Informacje

1. Dla danego obszaru statycznie obliczamy mapę nawożenia. 
2. Mapa nawożenia określa dawkę nawozu (środka stonkobójczego) w danym miejscu. 
3. Określona dawka jest wykorzystywana przez sterownik (komputer) znajdujący się w traktorze. 
4. Traktor regularnie zgłasza swoją pozycję jako koordynaty GPS, co pozwala sterownikowi na dobranie odpowiedniej dawki.
5. Obszar zadawany jest danymi geograficznymi, mapa, pozycje GPS czujników (możliwe dodatkowe informacje).
6. Obszar modelujemy poprzez prostokątne płytki (proste figury geometryczne, prostokąty)

Warto rozważyć pulę parametrów na podstawie których podejmujemy decyzję!
Pora roku (stadium rozwoju owada)
Natężenie owada
<!-- Cena pestycydów -->
Prognoza pogody (deszcz, wiatr)

# Technologia 

1. Wizualizacja: Java (Swing, JavaFX)
2. Baza danych (MongoDB albo SQL) postawiony na legionie. (trzymamy tylko n (100) ostatnich pomiarów z czujnika).
3. Generator: Python
4. Logika biznesowa: Python


# Kierunki rozwoju

1. Podczas listowania pól pojawia się informacja czy na dane ole powinniśmy dziś wyjechać, czy dopiero później na podstawie poziomu insektów oraz prognozy pogody.  