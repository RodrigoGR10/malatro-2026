package cl.uchile.dcc
package combinations

import joker._

/**
 * Poker combination: five consecutive cards regardless of suit.
 *
 * Base score: 30 chips, multiplier x4.
 * The Ace can act as order 1 or 14.
 */
object Straight extends PokerCombination {
  val name: String = "Straight"
  val baseScore: Score = new Score(30, 4)
  /** Returns true if the 5 cards are consecutive. */
  def matches(cards: List[Card]): Boolean =
    PokerHelpers.validHand(cards) && PokerHelpers.isStraight(cards)

  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + baseScore.chips
    score.mult = score.mult + baseScore.mult
    score
  }
}