package cl.uchile.dcc
import munit.FunSuite

class MalatroTest extends FunSuite {
  var score: Score = _
  var asH: Card = _
  var twoD: Card = _
  var kingS: Card = _
  var hand: Hand = _

  override def beforeEach(context: BeforeEach): Unit = {
    score = new Score(100, 5)
    asH = new Card(Ace, Heart)
    twoD = new Card(Two, Diamond)
    kingS = new Card(King, Spade)
    hand = new Hand()
  }

  test("Score se crea correctamente con chips y multiplicador") {
    assertEquals(score.chips, 100)
    assertEquals(score.mult, 5)
  }

  test("Card se crea correctamente con rango y pinta") {
    assertEquals(asH.rank, Ace)
    assertEquals(asH.suit, Heart)
  }

  test("Dos Cards con mismo rango y pinta son iguales") {
    val c1 = new Card(Ace, Heart)
    val c2 = new Card(Ace, Heart)
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
    assertNotEquals(GreedyJoker, DeviousJoker)
    assertNotEquals(GreedyJoker, EvenSteven)
    assertNotEquals(GreedyJoker, ScaryFace)
  }
}
