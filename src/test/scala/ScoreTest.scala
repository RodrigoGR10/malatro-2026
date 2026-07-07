package cl.uchile.dcc
import munit.FunSuite
import rank._
import suit._
import joker._
import combinations._

class ScoreTest extends FunSuite {

  test("Rank.applyScore delegates to affectRank (no effect from DeviousJoker)") {
    val score = new Score(0, 1)
    Two.applyScore(score, DeviousJoker)
    assertEquals(score.chips, 0)
    assertEquals(score.mult, 1)
  }

  test("EvenSteven adds +4 mult on Two (Even)") {
    val score = new Score(0, 1)
    Two.applyScore(score, EvenSteven)
    assertEquals(score.mult, 5)
  }

  test("EvenSteven does not add mult on Three (Odd)") {
    val score = new Score(0, 1)
    Three.applyScore(score, EvenSteven)
    assertEquals(score.mult, 1)
  }

  test("EvenSteven does not add mult on Jack (Figure)") {
    val score = new Score(0, 1)
    Jack.applyScore(score, EvenSteven)
    assertEquals(score.mult, 1)
  }

  test("ScaryFace adds +30 chips on Jack (Figure)") {
    val score = new Score(0, 1)
    Jack.applyScore(score, ScaryFace)
    assertEquals(score.chips, 30)
  }

  test("ScaryFace adds +30 chips on Queen (Figure)") {
    val score = new Score(0, 1)
    Queen.applyScore(score, ScaryFace)
    assertEquals(score.chips, 30)
  }

  test("ScaryFace adds +30 chips on King (Figure)") {
    val score = new Score(0, 1)
    King.applyScore(score, ScaryFace)
    assertEquals(score.chips, 30)
  }

  test("ScaryFace does not add chips on Two (Even)") {
    val score = new Score(0, 1)
    Two.applyScore(score, ScaryFace)
    assertEquals(score.chips, 0)
  }

  test("ScaryFace does not add chips on Three (Odd)") {
    val score = new Score(0, 1)
    Three.applyScore(score, ScaryFace)
    assertEquals(score.chips, 0)
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
    assertEquals(score.chips, 0)
    assertEquals(score.mult, 1)
  }

  test("ScaryFace does not modify score via affectCombination default") {
    val score = new Score(0, 1)
    Straight.applyScore(score, ScaryFace)
    assertEquals(score.chips, 0)
    assertEquals(score.mult, 1)
  }

  test("DeviousJoker adds +100 chips via Straight.applyScore") {
    val score = new Score(0, 1)
    Straight.applyScore(score, DeviousJoker)
    assertEquals(score.chips, 100)
    assertEquals(score.mult, 1)
  }

  test("DeviousJoker adds +100 chips via StraightFlush.applyScore too") {
    val score = new Score(0, 1)
    StraightFlush.applyScore(score, DeviousJoker)
    assertEquals(score.chips, 100)
  }

  test("Flush.applyScore is not affected by DeviousJoker") {
    val score = new Score(0, 1)
    Flush.applyScore(score, DeviousJoker)
    assertEquals(score.chips, 0)
    assertEquals(score.mult, 1)
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