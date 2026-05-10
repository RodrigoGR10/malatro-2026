package cl.uchile.dcc
import rango.{Three, Four, Five, Six, Nine, Ten, Jack, Queen, Impar, Figura, Par}
import pinta.{Hearts, Diamonds, Spades, Clubs}
import rango.{Ace, Two, King, Impar, Figura, Par}
import munit.FunSuite

class PokerCombinationTest extends FunSuite {

  val twoHeart = Card(Two, Hearts)
  val twoDiamond = Card(Two, Diamonds)
  val twoClub = Card(Two, Clubs)

  val threeHeart = Card(Three, Hearts)
  val threeDiamond = Card(Three, Diamonds)
  val threeClub = Card(Three, Clubs)

  val fourHeart = Card(Four, Hearts)
  val fourDiamond = Card(Four, Diamonds)
  val fourSpade = Card(Four, Spades)

  val fiveHeart = Card(Five, Hearts)
  val fiveDiamond = Card(Five, Diamonds)

  val sixHeart = Card(Six, Hearts)
  val sixDiamond = Card(Six, Diamonds)

  val nineHeart = Card(Nine, Hearts)

  val tenHeart = Card(Ten, Hearts)
  val tenSpade = Card(Ten, Spades)

  val jackHeart = Card(Jack, Hearts)
  val jackClub = Card(Jack, Clubs)

  val queenHeart = Card(Queen, Hearts)
  val queenDiamond = Card(Queen, Diamonds)

  val kingHeart = Card(King, Hearts)
  val aceHeart = Card(Ace, Hearts)
  val aceSpade = Card(Ace, Spades)

  test("StraightFlush expone su puntaje base y valida correctamente") {
    val hand = List(twoHeart, threeHeart, fourHeart, fiveHeart, sixHeart)
    assertEquals(StraightFlush.baseScore, Score(100, 8))
    assert(StraightFlush.matches(hand))
  }

  test("Flush expone su puntaje base y valida correctamente") {
    val hand = List(twoHeart, threeHeart, fiveHeart, kingHeart, aceHeart)
    assertEquals(Flush.baseScore, Score(35, 4))
    assert(Flush.matches(hand))
  }

  test("Straight expone su puntaje base y valida correctamente") {
    val hand = List(twoHeart, threeDiamond, fourSpade, fiveHeart, sixDiamond)
    assertEquals(Straight.baseScore, Score(30, 4))
    assert(Straight.matches(hand))
  }

  test("ThreeOfAKind expone su puntaje base y valida correctamente") {
    val hand = List(twoHeart, twoDiamond, twoClub, fiveHeart, sixHeart)
    assertEquals(ThreeOfAKind.baseScore, Score(30, 3))
    assert(ThreeOfAKind.matches(hand))
  }

  test("Pair expone su puntaje base y valida correctamente") {
    val hand = List(twoHeart, twoDiamond, fourSpade, fiveHeart, sixHeart)
    assertEquals(Pair.baseScore, Score(10, 2))
    assert(Pair.matches(hand))
  }

  test("HighCard expone su puntaje base y valida correctamente") {
    val hand = List(twoHeart, fourSpade, sixDiamond, nineHeart, kingHeart)
    assertEquals(HighCard.baseScore, Score(5, 1))
    assert(HighCard.matches(hand))
  }

  test("La prioridad elige StraightFlush sobre Flush y Straight") {
    val hand = Hand(List(tenHeart, jackHeart, queenHeart, kingHeart, aceHeart))
    assertEquals(PokerHand.bestCombination(hand), StraightFlush)
  }

  test("La prioridad elige ThreeOfAKind sobre Pair") {
    val hand = Hand(List(twoHeart, twoDiamond, twoClub, fiveHeart, sixHeart))
    assertEquals(PokerHand.bestCombination(hand), ThreeOfAKind)
  }

  test("El As puede actuar como 1 en la escalera A-2-3-4-5") {
    val cards = List(aceSpade, twoDiamond, threeClub, fourSpade, fiveDiamond)
    assert(Straight.matches(cards))
    assertEquals(PokerHand.bestCombination(Hand(cards)), Straight)
  }

  test("El As puede actuar como 14 en la escalera 10-J-Q-K-A") {
    val cards = List(tenHeart, jackClub, queenDiamond, kingHeart, aceHeart)
    assert(Straight.matches(cards))
    assertEquals(PokerHand.bestCombination(Hand(cards)), Straight)
  }
}