package cl.uchile.dcc

import joker.*
import munit.FunSuite
import pinta.*
import rango.*

class ScoreCalculatorTest extends FunSuite {
  test("calculates the project annex straight flush example") {
    val cards = List(
      new Card(Two, Diamonds), new Card(Three, Diamonds),
      new Card(Four, Diamonds), new Card(Five, Diamonds),
      new Card(Six, Diamonds)
    )
    assertEquals(ScoreCalculator.calculate(cards, List(GreedyJoker, DeviousJoker)), 5060)
  }

  test("does not duplicate card chips with multiple jokers") {
    val result = ScoreCalculator.calculate(List(new Card(Two, Diamonds)), List(GreedyJoker, EvenSteven))
    assertEquals(result, 56)
  }

  test("calculates high card without jokers") {
    assertEquals(ScoreCalculator.calculate(List(new Card(Ace, Hearts)), List.empty), 16)
  }
}