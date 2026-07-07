package cl.uchile.dcc
import munit.FunSuite
import rank._
import suit._
import joker._
import combinations._

class ScoreTest extends FunSuite {
  test("Two.applyScore adds 2 chips with no relevant joker") {
    val score = new Score(0, 1)
    Two.applyScore(score, DeviousJoker)
    assertEquals(score.chips, 2)
    assertEquals(score.mult, 1)
  }

  test("Nine.applyScore adds 9 chips, ScaryFace does not add chips") {
    val score = new Score(0, 1)
    Nine.applyScore(score, ScaryFace)
    assertEquals(score.chips, 9)
  }

  test("Ten.applyScore adds 10 chips, EvenSteven adds mult") {
    val score = new Score(0, 1)
    Ten.applyScore(score, EvenSteven)
    assertEquals(score.chips, 10)
    assertEquals(score.mult, 5)
  }

  test("Ace.applyScore adds 11 chips") {
    val score = new Score(0, 1)
    Ace.applyScore(score, DeviousJoker)
    assertEquals(score.chips, 11)
  }

  test("King.applyScore adds 10 chips") {
    val score = new Score(0, 1)
    King.applyScore(score, DeviousJoker)
    assertEquals(score.chips, 10)
  }

  test("Six.applyScore adds 6 chips") {
    val score = new Score(0, 1)
    Six.applyScore(score, DeviousJoker)
    assertEquals(score.chips, 6)
  }

  test("Seven.applyScore adds 7 chips, ScaryFace does not add chips") {
    val score = new Score(0, 1)
    Seven.applyScore(score, ScaryFace)
    assertEquals(score.chips, 7)
  }

  test("Eight.applyScore adds 8 chips, EvenSteven adds mult") {
    val score = new Score(0, 1)
    Eight.applyScore(score, EvenSteven)
    assertEquals(score.chips, 8)
    assertEquals(score.mult, 5)
  }

  test("EvenSteven adds +4 mult on Two (Par)") {
    val score = new Score(0, 1)
    Two.applyScore(score, EvenSteven)
    assertEquals(score.mult, 5)
  }

  test("EvenSteven does not add mult on Three (Impar)") {
    val score = new Score(0, 1)
    Three.applyScore(score, EvenSteven)
    assertEquals(score.mult, 1)
  }

  test("EvenSteven does not add mult on Jack (Figura)") {
    val score = new Score(0, 1)
    Jack.applyScore(score, EvenSteven)
    assertEquals(score.mult, 1)
  }

  test("ScaryFace adds +30 chips on Jack (Figura)") {
    val score = new Score(0, 1)
    Jack.applyScore(score, ScaryFace)
    assertEquals(score.chips, 40)
  }

  test("ScaryFace adds +30 chips on Queen (Figura)") {
    val score = new Score(0, 1)
    Queen.applyScore(score, ScaryFace)
    assertEquals(score.chips, 40)
  }

  test("ScaryFace adds +30 chips on King (Figura)") {
    val score = new Score(0, 1)
    King.applyScore(score, ScaryFace)
    assertEquals(score.chips, 40)
  }

  test("ScaryFace does not add chips on Two (Par)") {
    val score = new Score(0, 1)
    Two.applyScore(score, ScaryFace)
    assertEquals(score.chips, 2)
  }

  test("ScaryFace does not add chips on Three (Impar)") {
    val score = new Score(0, 1)
    Three.applyScore(score, ScaryFace)
    assertEquals(score.chips, 3)
  }

  test("Diamonds.applyScore adds +3 mult with GreedyJoker") {
    val score = new Score(0, 1)
    Diamonds.applyScore(score, GreedyJoker)
    assertEquals(score.mult, 4)
  }

  test("Hearts.applyScore does not modify score with GreedyJoker") {
    val score = new Score(0, 1)
    Hearts.applyScore(score, GreedyJoker)
    assertEquals(score.chips, 0)
    assertEquals(score.mult, 1)
  }

  test("EvenSteven does not modify score via affectSuit default") {
    val score = new Score(0, 1)
    Diamonds.applyScore(score, EvenSteven)
    assertEquals(score.chips, 0)
    assertEquals(score.mult, 1)
  }

  test("GreedyJoker does not modify score via affectCombination default") {
    val score = new Score(0, 1)
    Straight.applyScore(score, GreedyJoker)
    assertEquals(score.chips, 30)
    assertEquals(score.mult, 5)
  }

  test("ScaryFace does not modify score via affectCombination default") {
    val score = new Score(0, 1)
    Straight.applyScore(score, ScaryFace)
    assertEquals(score.chips, 30)
    assertEquals(score.mult, 5)
  }

  test("Straight.applyScore adds base score and +100 with DeviousJoker") {
    val score = new Score(0, 1)
    Straight.applyScore(score, DeviousJoker)
    assertEquals(score.chips, 130)
    assertEquals(score.mult, 5)
  }

  test("Straight.applyScore adds only base score without DeviousJoker") {
    val score = new Score(0, 1)
    Straight.applyScore(score, GreedyJoker)
    assertEquals(score.chips, 30)
    assertEquals(score.mult, 5)
  }

  test("Flush.applyScore is not affected by DeviousJoker") {
    val score = new Score(0, 1)
    Flush.applyScore(score, DeviousJoker)
    assertEquals(score.chips, 35)
    assertEquals(score.mult, 5)
  }

  test("Card.applyScore with empty joker list adds card chips") {
    val card = new Card(Two, Hearts)
    val score = new Score(0, 1)
    card.applyScore(score, List.empty)
    assertEquals(score.chips, 2)
    assertEquals(score.mult, 1)
  }

  test("Card.applyScore with GreedyJoker on a Diamond card adds chips and mult") {
    val card = new Card(Two, Diamonds)
    val score = new Score(0, 1)
    card.applyScore(score, List(GreedyJoker))
    assertEquals(score.chips, 2)
    assertEquals(score.mult, 4)
  }

  test("Card.applyScore with EvenSteven on an even-rank card adds chips and mult") {
    val card = new Card(Four, Hearts)
    val score = new Score(0, 1)
    card.applyScore(score, List(EvenSteven))
    assertEquals(score.chips, 4)
    assertEquals(score.mult, 5)
  }

  test("Card.applyScore with ScaryFace on a face card adds extra chips") {
    val card = new Card(King, Spades)
    val score = new Score(0, 1)
    card.applyScore(score, List(ScaryFace))
    assertEquals(score.chips, 40)
    assertEquals(score.mult, 1)
  }

  test("Card.applyScore with multiple jokers adds rank chips once") {
    val card = new Card(Two, Diamonds)
    val score = new Score(0, 1)
    card.applyScore(score, List(GreedyJoker, EvenSteven))
    assertEquals(score.chips, 2)
    assertEquals(score.mult, 8)
  }

  test("Card rank_= and suit_= update the card") {
    val card = new Card(Two, Hearts)
    card.rank = Ace
    card.suit = Diamonds
    assertEquals(card.rank, Ace)
    assertEquals(card.suit, Diamonds)
  }
}