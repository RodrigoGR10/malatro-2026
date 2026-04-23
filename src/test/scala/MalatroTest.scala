package cl.uchile.dcc
import munit.FunSuite
import rango.{Ace, Two, King, Impar, Figura, Par}
import pinta.{Hearts, Diamonds, Spades}
import cl.uchile.dcc.{GreedyJoker, DeviousJoker, EvenSteven, ScaryFace}

class MalatroTest extends FunSuite {
  var score: Score = _
  var asH: Card = _
  var twoD: Card = _
  var kingS: Card = _
  var hand: Hand = _

  override def beforeEach(context: BeforeEach): Unit = {
    score = Score(100, 5)
    asH = Card(Ace, Hearts)
    twoD = Card(Two, Diamonds)
    kingS = Card(King, Spades)
    hand = Hand()
  }

  test("Score se crea correctamente con chips y multiplicador") {
    assertEquals(score.chips, 100)
    assertEquals(score.mult, 5)
  }

  test("Card se crea correctamente con rango y pinta") {
    assertEquals(asH.rank, Ace)
    assertEquals(asH.suit, Hearts)
  }

  test("Dos Cards con mismo rango y pinta son iguales") {
    val c1 = Card(Ace, Hearts)
    val c2 = Card(Ace, Hearts)
    assertEquals(c1, c2)
  }

  test("Dos Cards diferentes no son iguales") {
    assertNotEquals(asH, twoD)
  }

  test("Hand se crea vacía por defecto") {
    assertEquals(hand.cards.size, 0)
    assertEquals(hand.jokers.size, 0)
  }

  test("Ranks devuelven orden, valor y clasificación correctos") {
    assertEquals(Ace.orden, 14)
    assertEquals(Ace.valor, 11)
    assertEquals(Ace.clasificacion, Impar)
    assertEquals(King.orden, 13)
    assertEquals(King.valor, 10)
    assertEquals(King.clasificacion, Figura)
    assertEquals(Two.orden, 2)
    assertEquals(Two.valor, 2)
    assertEquals(Two.clasificacion, Par)
  }

  test("Jokers son objetos distintos entre sí") {
    assert(GreedyJoker != DeviousJoker)
    assert(GreedyJoker != EvenSteven)
    assert(GreedyJoker != ScaryFace)
  }
}
