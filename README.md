# Malatro
CC3002 - Metodologías de Diseño y Programación
Rodrigo Guerrero Rojas

El proyecto Malatro es un juego de cartas donde el jugador forma manos de póker usando una baraja estándar y diferentes tipos de Jokers para obtener el mayor puntaje posible antes de quedarse sin jugadas.

## Organización del código
```
src/main/scala/
├── rango/                  # Rangos de cartas y su clasificación
├── pinta/                  # Pintas de cartas
├── joker/                  # Jokers disponibles
├── combinations/           # Combinaciones de póker, helpers y evaluador
├── exceptions/             # Excepciones del juego
├── Card.scala              # Carta individual
├── Score.scala             # Puntaje de una jugada
├── Hand.scala              # Mano del jugador
src/test/scala/
├── MalatroTest.scala            # Tests de entidades base: Score, Card, rangos, pintas y Jokers
├── PokerCombinationTest.scala   # Tests de combinaciones, prioridad y casos con As
├── HandExceptionTest.scala     # Tests de excepciones: playHand, discardHand y límites
├──HandTest.scala               # Tests de operaciones de la mano del jugador
└── ScoreTest.scala             # Tests de applyScore en rangos, pintas, combinaciones y cartas
```

## Decisiones de diseño
Rangos y pintas son `object`, pues un rango o una pinta siempre representan lo mismo (mismo orden, valor y clasificación), así que tiene sentido modelarlos como objetos fijos en vez de crear instancias nuevas cada vez.
`PokerHelpers` concentra toda la lógica de validación de combinaciones en un solo lugar, evitando duplicación entre los distintos objetos de combinación.
`PokerCombination` permite que cada combinación implemente su propia validación de forma independiente, sin necesidad de un único método gigante con condicionales.

### Cálculo de puntaje con Jokers (double dispatch)
El cálculo de puntaje con Jokers se implementó usando double dispatch.
El trait `Joker` define tres métodos especializados: `affectRank`, `affectSuit` y `affectCombination`, uno por cada jerarquía que un Joker puede afectar (Rango, Pinta y Combinación de póker). Cada uno tiene una implementación por defecto que retorna el puntaje sin cambios.
`Rank.applyScore`, `Pinta.applyScore` y `PokerCombination.applyScore` realizan el primer dispatch: suman su puntaje base (chips/multiplicador) y delegan en el Joker recibido, llamando a `j.affectRank(this, score)` (o el método correspondiente según la jerarquía). El segundo dispatch ocurre al resolver cuál Joker concreto ejecuta ese método.
Para evitar duplicación, las implementaciones de `applyScore` en `Pinta` y `PokerCombination` se definen directamente en el trait, y cada objeto concreto (`Hearts`, `Diamonds`, `Straight`, `Flush`, etc.) las hereda.
Cada Joker concreto sobrescribe únicamente el método relevante a su efecto:
- `GreedyJoker` sobrescribe `affectSuit`: suma +3 al multiplicador si la pinta recibida es Diamantes.
- `EvenSteven` sobrescribe `affectRank`: suma +4 al multiplicador si la clasificación del rango recibido es Par.
- `ScaryFace` sobrescribe `affectRank`: suma +30 a los chips si la clasificación del rango recibido es Figura.
- `DeviousJoker` sobrescribe `affectCombination`: suma +100 a los chips si la combinación recibida es Straight.

De esta forma, es el propio Joker quien decide su efecto según el objeto (rango, pinta o combinación) que recibe como parámetro. Esto permite agregar nuevos Jokers en el futuro sin modificar el código existente de rangos, pintas o combinaciones.
`Card.applyScore` recibe una lista de jokers e itera sobre ella: por cada joker activo, aplica primero la interacción del rango (`rank.applyScore`) y luego la de la pinta (`suit.applyScore`) con ese joker.

### Otras decisiones
Las excepciones están organizadas en el paquete `exceptions` y los jokers en el paquete `joker`, siguiendo el principio de una clase/trait por archivo.
`equals` en `Card` y `Score` permite comparar instancias distintas que representen la misma carta o el mismo puntaje, lo cual es necesario para los tests.

## Testing
Se cubre creación e igualdad de objetos, validación de cada combinación de póker con casos positivos y negativos, prioridad entre combinaciones cuando una mano satisface más de una, el comportamiento dual del As en escaleras (orden 1 y 14), todas las operaciones de la mano del jugador junto con sus excepciones (incluyendo `discardHand`), y el cálculo de puntaje de rangos, pintas, combinaciones y cartas con los efectos de cada Joker mediante double dispatch.
El proyecto alcanza un 97% de cobertura de líneas en `src/main/scala`.