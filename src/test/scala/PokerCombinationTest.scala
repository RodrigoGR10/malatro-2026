package cl.uchile.dcc
import munit.FunSuite
import rango.{Ace, Two, Three, Four, Five, Six, Nine, Ten, Jack, Queen, King}
import pinta.{Hearts, Diamonds, Spades, Clubs}

class PokerCombinationTest extends FunSuite {

  val twoHeart = new Card(Two, Hearts)
  val twoDiamond = new Card(Two, Diamonds)
  val twoClub = new Card(Two, Clubs)
  val threeHeart = new Card(Three, Hearts)
  val threeDiamond = new Card(Three, Diamonds)
  val threeClub = new Card(Three, Clubs)
  val fourHeart = new Card(Four, Hearts)
  val fourSpade = new Card(Four, Spades)
  val fiveHeart = new Card(Five, Hearts)
  val fiveDiamond = new Card(Five, Diamonds)
  val sixHeart = new Card(Six, Hearts)
  val sixDiamond = new Card(Six, Diamonds)
  val nineHeart = new Card(Nine, Hearts)
  val tenHeart = new Card(Ten, Hearts)
  val jackHeart = new Card(Jack, Hearts)
  val jackClub = new Card(Jack, Clubs)
  val queenHeart = new Card(Queen, Hearts)
  val queenDiamond = new Card(Queen, Diamonds)
  val kingHeart = new Card(King, Hearts)
  val aceHeart = new Card(Ace, Hearts)
  val aceSpade = new Card(Ace, Spades)

  test("StraightFlush expone su puntaje base correctamente") {
    assertEquals(StraightFlush.baseScore, new Score(100, 8))
  }

  test("StraightFlush aplica con 5 cartas consecutivas de la misma pinta") {
    assert(StraightFlush.matches(List(twoHeart, threeHeart, fourHeart, fiveHeart, sixHeart)))
  }

  test("StraightFlush no aplica si es solo Flush") {
    assert(!StraightFlush.matches(List(twoHeart, threeHeart, fiveHeart, kingHeart, aceHeart)))
  }

  test("StraightFlush no aplica si es solo Straight") {
    assert(!StraightFlush.matches(List(twoHeart, threeDiamond, fourSpade, fiveHeart, sixDiamond)))
  }

  test("Flush expone su puntaje base correctamente") {
    assertEquals(Flush.baseScore, new Score(35, 4))
  }

  test("Flush aplica con 5 cartas de la misma pinta") {
    assert(Flush.matches(List(twoHeart, threeHeart, fiveHeart, kingHeart, aceHeart)))
  }

  test("Flush no aplica si las pintas son distintas") {
    assert(!Flush.matches(List(twoHeart, threeDiamond, fourHeart, fiveHeart, sixHeart)))
  }

  test("Flush no aplica con menos de 5 cartas") {
    assert(!Flush.matches(List(twoHeart, threeHeart, fourHeart)))
  }

  test("Flush no aplica con lista vacía") {
    assert(!Flush.matches(List.empty))
  }

  test("Straight expone su puntaje base correctamente") {
    assertEquals(Straight.baseScore, new Score(30, 4))
  }

  test("Straight aplica con 5 cartas consecutivas de pintas distintas") {
    assert(Straight.matches(List(twoHeart, threeDiamond, fourSpade, fiveHeart, sixDiamond)))
  }

  test("Straight no aplica si las cartas no son consecutivas") {
    assert(!Straight.matches(List(twoHeart, threeDiamond, fourSpade, sixHeart, nineHeart)))
  }

  test("Straight no aplica con menos de 5 cartas") {
    assert(!Straight.matches(List(twoHeart, threeHeart, fourHeart)))
  }

  test("Straight no aplica con lista vacía") {
    assert(!Straight.matches(List.empty))
  }

  test("El As actúa como 1 en la escalera A-2-3-4-5") {
    assert(Straight.matches(List(aceSpade, twoDiamond, threeClub, fourSpade, fiveDiamond)))
  }

  test("El As actúa como 14 en la escalera 10-J-Q-K-A") {
    assert(Straight.matches(List(tenHeart, jackClub, queenDiamond, kingHeart, aceHeart)))
  }

  test("ThreeOfAKind expone su puntaje base correctamente") {
    assertEquals(ThreeOfAKind.baseScore, new Score(30, 3))
  }

  test("ThreeOfAKind aplica con tres cartas del mismo rango") {
    assert(ThreeOfAKind.matches(List(twoHeart, twoDiamond, twoClub, fiveHeart, sixHeart)))
  }

  test("ThreeOfAKind aplica con cuatro cartas del mismo rango") {
    assert(ThreeOfAKind.matches(List(twoHeart, twoDiamond, twoClub, new Card(Two, Spades))))
  }

  test("ThreeOfAKind no aplica si solo hay par") {
    assert(!ThreeOfAKind.matches(List(twoHeart, twoDiamond, fourSpade, fiveHeart, sixHeart)))
  }

  test("ThreeOfAKind no aplica con lista vacía") {
    assert(!ThreeOfAKind.matches(List.empty))
  }

  test("Pair expone su puntaje base correctamente") {
    assertEquals(Pair.baseScore, new Score(10, 2))
  }

  test("Pair aplica con dos cartas del mismo rango") {
    assert(Pair.matches(List(twoHeart, twoDiamond, fourSpade, fiveHeart, sixHeart)))
  }

  test("Pair no aplica si todos los rangos son distintos") {
    assert(!Pair.matches(List(twoHeart, threeHeart, fourHeart, nineHeart, kingHeart)))
  }

  test("Pair no aplica si hay trío pero no par exacto") {
    assert(!Pair.matches(List(twoHeart, twoDiamond, twoClub, fiveHeart, sixHeart)))
  }

  test("Pair no aplica con lista vacía") {
    assert(!Pair.matches(List.empty))
  }

  test("HighCard expone su puntaje base correctamente") {
    assertEquals(HighCard.baseScore, new Score(5, 1))
  }

  test("HighCard aplica con cualquier mano válida") {
    assert(HighCard.matches(List(twoHeart, fourSpade, sixDiamond, nineHeart, kingHeart)))
  }

  test("HighCard aplica con una sola carta") {
    assert(HighCard.matches(List(aceHeart)))
  }

  test("HighCard no aplica con lista vacía") {
    assert(!HighCard.matches(List.empty))
  }

  test("La prioridad elige StraightFlush sobre Flush y Straight") {
    val hand = new Hand(List(tenHeart, jackHeart, queenHeart, kingHeart, aceHeart))
    assertEquals(PokerHand.bestCombination(hand), StraightFlush)
  }

  test("La prioridad elige Flush sobre Straight cuando no es consecutivo") {
    val hand = new Hand(List(twoHeart, threeHeart, fiveHeart, kingHeart, aceHeart))
    assertEquals(PokerHand.bestCombination(hand), Flush)
  }

  test("La prioridad elige ThreeOfAKind sobre Pair") {
    val hand = new Hand(List(twoHeart, twoDiamond, twoClub, fiveHeart, sixHeart))
    assertEquals(PokerHand.bestCombination(hand), ThreeOfAKind)
  }

  test("La prioridad elige Pair cuando no hay combinación mayor") {
    val hand = new Hand(List(twoHeart, twoDiamond, fourSpade, fiveHeart, nineHeart))
    assertEquals(PokerHand.bestCombination(hand), Pair)
  }

  test("La prioridad elige HighCard cuando nada más aplica") {
    val hand = new Hand(List(twoHeart, threeDiamond, fourSpade, nineHeart, kingHeart))
    assertEquals(PokerHand.bestCombination(hand), HighCard)
  }

  test("El As puede actuar como 1 en la escalera A-2-3-4-5") {
    val cards = List(aceSpade, twoDiamond, threeClub, fourSpade, fiveDiamond)
    assertEquals(PokerHand.bestCombination(new Hand(cards)), Straight)
  }

  test("El As puede actuar como 14 en la escalera 10-J-Q-K-A") {
    val cards = List(tenHeart, jackClub, queenDiamond, kingHeart, aceHeart)
    assertEquals(PokerHand.bestCombination(new Hand(cards)), Straight)
  }
}