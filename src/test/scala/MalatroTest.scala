package cl.uchile.dcc
import joker.*
import pinta.{Clubs, Diamonds, Hearts, Spades}
import rango.*

import munit.FunSuite

class MalatroTest extends FunSuite {
  var score: Score = _
  var asH: Card = _
  var twoD: Card = _
  var kingS: Card = _
  var hand: Hand = _

  override def beforeEach(context: BeforeEach): Unit = {
    score = new Score(100, 5)
    asH = new Card(Ace, Hearts)
    twoD = new Card(Two, Diamonds)
    kingS = new Card(King, Spades)
    hand = new Hand()
  }

  test("Score se crea correctamente con chips y multiplicador") {
    assertEquals(score.chips, 100)
    assertEquals(score.mult, 5)
  }

  test("Dos Scores con los mismos valores son iguales") {
    assertEquals(new Score(30, 4), new Score(30, 4))
  }

  test("Dos Scores con distintos valores no son iguales") {
    assertNotEquals(new Score(10, 2), new Score(30, 4))
  }

  test("Score.equals retorna false con objeto de otro tipo") {
    assert(!new Score(10, 2).equals("texto"))
  }

  test("Card se crea correctamente con rango y pinta") {
    assertEquals(asH.rank, Ace)
    assertEquals(asH.suit, Hearts)
  }

  test("Dos Cards con mismo rango y pinta son iguales") {
    assertEquals(new Card(Ace, Hearts), new Card(Ace, Hearts))
  }

  test("Dos Cards con distinto rango no son iguales") {
    assertNotEquals(asH, twoD)
  }

  test("Dos Cards con mismo rango pero distinta pinta no son iguales") {
    assertNotEquals(new Card(Ace, Hearts), new Card(Ace, Spades))
  }

  test("Dos Cards con distinto rango pero misma pinta no son iguales") {
    assertNotEquals(new Card(Ace, Hearts), new Card(Two, Hearts))
  }

  test("Card.equals retorna false con objeto de otro tipo") {
    assert(!new Card(Ace, Hearts).equals(42))
  }

  test("Ace tiene orden 14, valor 11, clasificacion Impar") {
    assertEquals(Ace.orden, 14)
    assertEquals(Ace.valor, 11)
    assertEquals(Ace.clasificacion, Impar)
  }

  test("Two tiene orden 2, valor 2, clasificacion Par") {
    assertEquals(Two.orden, 2)
    assertEquals(Two.valor, 2)
    assertEquals(Two.clasificacion, Par)
  }

  test("Three tiene orden 3, valor 3, clasificacion Impar") {
    assertEquals(Three.orden, 3)
    assertEquals(Three.valor, 3)
    assertEquals(Three.clasificacion, Impar)
  }

  test("Four tiene orden 4, valor 4, clasificacion Par") {
    assertEquals(Four.orden, 4)
    assertEquals(Four.valor, 4)
    assertEquals(Four.clasificacion, Par)
  }

  test("Five tiene orden 5, valor 5, clasificacion Impar") {
    assertEquals(Five.orden, 5)
    assertEquals(Five.valor, 5)
    assertEquals(Five.clasificacion, Impar)
  }

  test("Six tiene orden 6, valor 6, clasificacion Par") {
    assertEquals(Six.orden, 6)
    assertEquals(Six.valor, 6)
    assertEquals(Six.clasificacion, Par)
  }

  test("Seven tiene orden 7, valor 7, clasificacion Impar") {
    assertEquals(Seven.orden, 7)
    assertEquals(Seven.valor, 7)
    assertEquals(Seven.clasificacion, Impar)
  }

  test("Eight tiene orden 8, valor 8, clasificacion Par") {
    assertEquals(Eight.orden, 8)
    assertEquals(Eight.valor, 8)
    assertEquals(Eight.clasificacion, Par)
  }

  test("Nine tiene orden 9, valor 9, clasificacion Impar") {
    assertEquals(Nine.orden, 9)
    assertEquals(Nine.valor, 9)
    assertEquals(Nine.clasificacion, Impar)
  }

  test("Ten tiene orden 10, valor 10, clasificacion Par") {
    assertEquals(Ten.orden, 10)
    assertEquals(Ten.valor, 10)
    assertEquals(Ten.clasificacion, Par)
  }

  test("Jack tiene orden 11, valor 10, clasificacion Figura") {
    assertEquals(Jack.orden, 11)
    assertEquals(Jack.valor, 10)
    assertEquals(Jack.clasificacion, Figura)
  }

  test("Queen tiene orden 12, valor 10, clasificacion Figura") {
    assertEquals(Queen.orden, 12)
    assertEquals(Queen.valor, 10)
    assertEquals(Queen.clasificacion, Figura)
  }

  test("King tiene orden 13, valor 10, clasificacion Figura") {
    assertEquals(King.orden, 13)
    assertEquals(King.valor, 10)
    assertEquals(King.clasificacion, Figura)
  }

  test("Las cuatro pintas son distintas entre sí") {
    assert(Hearts != Diamonds)
    assert(Hearts != Spades)
    assert(Hearts != Clubs)
    assert(Diamonds != Spades)
    assert(Diamonds != Clubs)
    assert(Spades != Clubs)
  }

  test("Los cuatro Jokers son distintos entre sí") {
    assert(GreedyJoker != DeviousJoker)
    assert(GreedyJoker != EvenSteven)
    assert(GreedyJoker != ScaryFace)
    assert(DeviousJoker != EvenSteven)
    assert(DeviousJoker != ScaryFace)
    assert(EvenSteven != ScaryFace)
  }

  test("Hand se crea vacía por defecto") {
    assertEquals(hand.cards.size, 0)
    assertEquals(hand.jokers.size, 0)
  }
}