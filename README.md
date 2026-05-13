# Malatro
CC3002 - Metodologías de Diseño y Programación
Rodrigo Guerrero Rojas

El proyecto Malatro es un juego de cartas donde el jugador forma manos de póker usando una baraja estándar y diferentes tipos de Jokers para obtener el mayor puntaje posible antes de quedarse sin jugadas.

## Organización del código
```
src/main/scala/
├── rango/                  # Rangos de cartas y su clasificación
├── pinta/                  # Pintas de cartas
├── Card.scala              # Carta individual
├── Score.scala             # Puntaje de una jugada
├── Hand.scala              # Mano del jugador
├── Joker.scala             # Jokers disponibles
├── PokerCombination.scala  # Interfaz base de combinaciones
├── PokerHelpers.scala      # Lógica compartida de validación
├── PokerHand.scala         # Determina la mejor combinación
└── StraightFlush/Flush/Straight/ThreeOfAKind/Pair/HighCard.scala
src/test/scala/
├── MalatroTest.scala            # Tests de entidades base: Score, Card, rangos, pintas y Jokers
├── PokerCombinationTest.scala   # Tests de combinaciones, prioridad y casos con As
└── HandTest.scala               # Tests de operaciones de la mano del jugador
```

## Decisiones de diseño
Rangos y pintas son `object`, pues un rango siempre tiene el mismo orden, valor y clasificación sin importar cuándo o dónde se use. Como sus propiedades nunca cambian y siempre representan lo mismo, tiene sentido modelarlos como objetos fijos en lugar de crear instancias nuevas cada vez.

`PokerHelpers` concentra toda la lógica de validación de combinaciones en un solo lugar, evitando duplicación entre los distintos objetos de combinación.

Se utiliza el trait `PokerCombination` para que cada combinación implemente su propia validación de forma independiente. Esto permite agregar nuevas combinaciones sin modificar el código existente.

`Joker` como trait con cada Joker como un `object` que extiende el trait, garantizando una única instancia por tipo. Esto permite identificarlos y compararlos directamente, y en el futuro cada uno podrá implementar su propio efecto sin afectar a los demás.

`equals` en `Card` y `Score`fueron implementados para permitir comparar instancias distintas que representen la misma carta o el mismo puntaje.

## Testing
Se buscó cubrir creación e igualdad de objetos, validación de cada combinación de póker con casos positivos y negativos, prioridad entre combinaciones cuando una mano satisface más de una, el comportamiento dual del As en escaleras como 1 y como 14, y todas las operaciones de la mano del jugador como agregar, quitar y jugar cartas y Jokers.