package cl.uchile.dcc
import munit.FunSuite
import rango.{Two, Three, Four, Five, Six, Ace, King}
import pinta.{Hearts, Diamonds, Spades, Clubs}
import joker._

class HandTest extends FunSuite {
  var hand: Hand = _
  val twoH = new Card(Two, Hearts)
  val threeD = new Card(Three, Diamonds)
  val fourS = new Card(Four, Spades)
  val fiveC = new Card(Five, Clubs)
  val sixH = new Card(Six, Hearts)
  val aceH = new Card(Ace, Hearts)
  val kingS = new Card(King, Spades)

  override def beforeEach(context: BeforeEach): Unit = {
    hand = new Hand()
  }

  test("addCard agrega una carta a la mano vacía") {
    hand.addCard(twoH)
    assertEquals(hand.cards, List(twoH))
  }

  test("addCard agrega varias cartas en orden") {
    hand.addCard(twoH)
    hand.addCard(threeD)
    hand.addCard(fourS)
    assertEquals(hand.cards, List(twoH, threeD, fourS))
  }

  test("removeCard elimina la carta en el índice dado") {
    hand.addCard(twoH)
    hand.addCard(threeD)
    hand.addCard(fourS)
    hand.removeCard(1)
    assertEquals(hand.cards, List(twoH, fourS))
  }

  test("removeCard elimina la primera carta correctamente") {
    hand.addCard(twoH)
    hand.addCard(threeD)
    hand.removeCard(0)
    assertEquals(hand.cards, List(threeD))
  }

  test("removeCard elimina la última carta correctamente") {
    hand.addCard(twoH)
    hand.addCard(threeD)
    hand.removeCard(1)
    assertEquals(hand.cards, List(twoH))
  }

  test("removeCard en mano de una carta la deja vacía") {
    hand.addCard(aceH)
    hand.removeCard(0)
    assertEquals(hand.cards, List.empty)
  }

  test("addJoker agrega un Joker a la mano vacía") {
    hand.addJoker(GreedyJoker)
    assertEquals(hand.jokers, List(GreedyJoker))
  }

  test("addJoker agrega múltiples Jokers en orden") {
    hand.addJoker(GreedyJoker)
    hand.addJoker(DeviousJoker)
    assertEquals(hand.jokers, List(GreedyJoker, DeviousJoker))
  }

  test("removeJoker elimina el Joker en el índice dado") {
    hand.addJoker(GreedyJoker)
    hand.addJoker(DeviousJoker)
    hand.removeJoker(0)
    assertEquals(hand.jokers, List(DeviousJoker))
  }

  test("removeJoker elimina el último Joker correctamente") {
    hand.addJoker(GreedyJoker)
    hand.addJoker(EvenSteven)
    hand.removeJoker(1)
    assertEquals(hand.jokers, List(GreedyJoker))
  }

  test("removeJoker en lista de un Joker la deja vacía") {
    hand.addJoker(GreedyJoker)
    hand.removeJoker(0)
    assertEquals(hand.jokers, List.empty)
  }

  test("playHand retorna las cartas correspondientes a los índices") {
    hand.addCard(twoH)
    hand.addCard(threeD)
    hand.addCard(fourS)
    hand.addCard(fiveC)
    hand.addCard(sixH)
    val played = hand.playHand(List(0, 2, 4))
    assertEquals(played, List(twoH, fourS, sixH))
  }

  test("playHand elimina las cartas jugadas de la mano") {
    hand.addCard(twoH)
    hand.addCard(threeD)
    hand.addCard(fourS)
    hand.playHand(List(0, 1))
    assertEquals(hand.cards, List(fourS))
  }

  test("playHand con un solo índice retorna una carta y la elimina") {
    hand.addCard(aceH)
    hand.addCard(kingS)
    val played = hand.playHand(List(0))
    assertEquals(played, List(aceH))
    assertEquals(hand.cards, List(kingS))
  }

  test("playHand con 5 cartas retorna todas y deja la mano vacía") {
    hand.addCard(twoH)
    hand.addCard(threeD)
    hand.addCard(fourS)
    hand.addCard(fiveC)
    hand.addCard(sixH)
    val played = hand.playHand(List(0, 1, 2, 3, 4))
    assertEquals(played, List(twoH, threeD, fourS, fiveC, sixH))
    assertEquals(hand.cards, List.empty)
  }
}