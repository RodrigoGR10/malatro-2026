package cl.uchile.dcc
import munit.FunSuite
import rango.{Two, Three, Four, Five, Six, Ace, King}
import pinta.{Hearts, Diamonds, Spades, Clubs}
import joker._
import exceptions._

class HandExceptionTest extends FunSuite {
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

  test("addCard throws TooManyCardsException when adding ninth card") {
    for _ <- 1 to 8 do hand.addCard(twoH)
    interceptMessage[TooManyCardsException]("A hand cannot have more than 8 cards.") {
      hand.addCard(twoH)
    }
  }

  test("removeCard throws InvalidCardIndexException with negative index") {
    hand.addCard(twoH)
    interceptMessage[InvalidCardIndexException]("Card index -1 is invalid. Hand has 1 card(s).") {
      hand.removeCard(-1)
    }
  }

  test("removeCard throws InvalidCardIndexException with out of range index") {
    hand.addCard(twoH)
    interceptMessage[InvalidCardIndexException]("Card index 1 is invalid. Hand has 1 card(s).") {
      hand.removeCard(1)
    }
  }

  test("addJoker throws TooManyJokersException when adding third joker") {
    hand.addJoker(GreedyJoker)
    hand.addJoker(DeviousJoker)
    interceptMessage[TooManyJokersException]("A hand cannot have more than 2 jokers.") {
      hand.addJoker(EvenSteven)
    }
  }

  test("removeJoker throws InvalidJokerIndexException with negative index") {
    hand.addJoker(GreedyJoker)
    interceptMessage[InvalidJokerIndexException]("Joker index -1 is invalid. Hand has 1 joker(s).") {
      hand.removeJoker(-1)
    }
  }

  test("removeJoker throws InvalidJokerIndexException with out of range index") {
    hand.addJoker(GreedyJoker)
    interceptMessage[InvalidJokerIndexException]("Joker index 1 is invalid. Hand has 1 joker(s).") {
      hand.removeJoker(1)
    }
  }

  test("playHand throws TooManyPlaysException on fourth attempt") {
    for _ <- 1 to 6 do hand.addCard(twoH)
    hand.playHand(List(0))
    hand.playHand(List(0))
    hand.playHand(List(0))
    interceptMessage[TooManyPlaysException]("Cannot play more than 3 times.") {
      hand.playHand(List(0))
    }
  }

  test("playHand throws TooFewCardsToPlayException with empty list") {
    hand.addCard(twoH)
    interceptMessage[TooFewCardsToPlayException]("Must play at least 1 card.") {
      hand.playHand(List.empty)
    }
  }

  test("playHand throws TooManyCardsToPlayException with more than 5 indices") {
    for _ <- 1 to 8 do hand.addCard(twoH)
    interceptMessage[TooManyCardsToPlayException]("Cannot play more than 5 cards.") {
      hand.playHand(List(0, 1, 2, 3, 4, 5))
    }
  }

  test("playHand throws InvalidPlayIndexException with out of range index") {
    hand.addCard(twoH)
    interceptMessage[InvalidPlayIndexException]("Index 5 is out of range. Hand has 1 card(s).") {
      hand.playHand(List(5))
    }
  }

  test("discardHand returns discarded cards correctly") {
    hand.addCard(twoH)
    hand.addCard(threeD)
    hand.addCard(fourS)
    val discarded = hand.discardHand(List(0, 2))
    assertEquals(discarded, List(twoH, fourS))
  }

  test("discardHand removes discarded cards from hand") {
    hand.addCard(twoH)
    hand.addCard(threeD)
    hand.addCard(fourS)
    hand.discardHand(List(0, 2))
    assertEquals(hand.cards, List(threeD))
  }

  test("discardHand with one index discards and removes the card") {
    hand.addCard(aceH)
    hand.addCard(kingS)
    val discarded = hand.discardHand(List(0))
    assertEquals(discarded, List(aceH))
    assertEquals(hand.cards, List(kingS))
  }

  test("discardHand with 5 indices discards all and leaves hand empty") {
    hand.addCard(twoH)
    hand.addCard(threeD)
    hand.addCard(fourS)
    hand.addCard(fiveC)
    hand.addCard(sixH)
    val discarded = hand.discardHand(List(0, 1, 2, 3, 4))
    assertEquals(discarded, List(twoH, threeD, fourS, fiveC, sixH))
    assertEquals(hand.cards, List.empty)
  }

  test("discardHand throws TooManyDiscardsException on fourth attempt") {
    for _ <- 1 to 6 do hand.addCard(twoH)
    hand.discardHand(List(0))
    hand.discardHand(List(0))
    hand.discardHand(List(0))
    interceptMessage[TooManyDiscardsException]("Cannot discard more than 3 times.") {
      hand.discardHand(List(0))
    }
  }

  test("discardHand throws TooFewCardsToPlayException with empty list") {
    hand.addCard(twoH)
    interceptMessage[TooFewCardsToPlayException]("Must discard at least 1 card.") {
      hand.discardHand(List.empty)
    }
  }

  test("discardHand throws TooManyCardsToPlayException with more than 5 indices") {
    for _ <- 1 to 8 do hand.addCard(twoH)
    interceptMessage[TooManyCardsToPlayException]("Cannot discard more than 5 cards.") {
      hand.discardHand(List(0, 1, 2, 3, 4, 5))
    }
  }

  test("discardHand throws InvalidPlayIndexException with out of range index") {
    hand.addCard(twoH)
    interceptMessage[InvalidPlayIndexException]("Index 5 is out of range. Hand has 1 card(s).") {
      hand.discardHand(List(5))
    }
  }
}