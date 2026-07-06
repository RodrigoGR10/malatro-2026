package cl.uchile.dcc

import joker.*
import munit.FunSuite
import pinta.*
import rango.*

class ScoreCalculatorTest extends FunSuite {
  test("calculates the project annex straight flush example") {
    val cards = List(
      new Card(Two, Diamonds),
      new Card(Three, Diamonds),
      new Card(Four, Diamonds),
      new Card(Five, Diamonds),
      new Card(Six, Diamonds)
    )

    assertEquals(ScoreCalculator.calculate(cards, List(GreedyJoker, DeviousJoker)), 5060)
  }

  test("does not duplicate card chips with multiple jokers") {
    val cards = List(new Card(Two, Diamonds))

    assertEquals(ScoreCalculator.calculate(cards, List(GreedyJoker, EvenSteven)), 56)
  }

  test("calculates high card without jokers") {
    val cards = List(new Card(Ace, Hearts))

    assertEquals(ScoreCalculator.calculate(cards, List.empty), 16)
  }
}