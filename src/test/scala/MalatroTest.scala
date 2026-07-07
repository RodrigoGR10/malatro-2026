package cl.uchile.dcc
import joker.*
import suit.{Clubs, Diamonds, Hearts, Spades}
import rank.*

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

  test("Dos Scores con los mismos chipValuees son iguales") {
    assertEquals(new Score(30, 4), new Score(30, 4))
  }

  test("Dos Scores con distintos chipValuees no son iguales") {
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

  test("Ace tiene order 14, chipValue 11, classification Impar") {
    assertEquals(Ace.order, 14)
    assertEquals(Ace.chipValue, 11)
    assertEquals(Ace.classification, Odd)
  }

  test("Two tiene order 2, chipValue 2, classification Par") {
    assertEquals(Two.order, 2)
    assertEquals(Two.chipValue, 2)
    assertEquals(Two.classification, Even)
  }

  test("Three tiene order 3, chipValue 3, classification Impar") {
    assertEquals(Three.order, 3)
    assertEquals(Three.chipValue, 3)
    assertEquals(Three.classification, Odd)
  }

  test("Four tiene order 4, chipValue 4, classification Par") {
    assertEquals(Four.order, 4)
    assertEquals(Four.chipValue, 4)
    assertEquals(Four.classification, Even)
  }

  test("Five tiene order 5, chipValue 5, classification Impar") {
    assertEquals(Five.order, 5)
    assertEquals(Five.chipValue, 5)
    assertEquals(Five.classification, Odd)
  }

  test("Six tiene order 6, chipValue 6, classification Par") {
    assertEquals(Six.order, 6)
    assertEquals(Six.chipValue, 6)
    assertEquals(Six.classification, Even)
  }

  test("Seven tiene order 7, chipValue 7, classification Impar") {
    assertEquals(Seven.order, 7)
    assertEquals(Seven.chipValue, 7)
    assertEquals(Seven.classification, Odd)
  }

  test("Eight tiene order 8, chipValue 8, classification Par") {
    assertEquals(Eight.order, 8)
    assertEquals(Eight.chipValue, 8)
    assertEquals(Eight.classification, Even)
  }

  test("Nine tiene order 9, chipValue 9, classification Impar") {
    assertEquals(Nine.order, 9)
    assertEquals(Nine.chipValue, 9)
    assertEquals(Nine.classification, Odd)
  }

  test("Ten tiene order 10, chipValue 10, classification Par") {
    assertEquals(Ten.order, 10)
    assertEquals(Ten.chipValue, 10)
    assertEquals(Ten.classification, Even)
  }

  test("Jack tiene order 11, chipValue 10, classification Figura") {
    assertEquals(Jack.order, 11)
    assertEquals(Jack.chipValue, 10)
    assertEquals(Jack.classification, Figure)
  }

  test("Queen tiene order 12, chipValue 10, classification Figura") {
    assertEquals(Queen.order, 12)
    assertEquals(Queen.chipValue, 10)
    assertEquals(Queen.classification, Figure)
  }

  test("King tiene order 13, chipValue 10, classification Figura") {
    assertEquals(King.order, 13)
    assertEquals(King.chipValue, 10)
    assertEquals(King.classification, Figure)
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
