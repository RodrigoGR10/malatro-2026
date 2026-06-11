package cl.uchile.dcc
import joker._

/**
 * Poker combination: three cards of the same rank.
 *
 * Base score: 30 chips, multiplier x3.
 */
object ThreeOfAKind extends PokerCombination {
  val name: String = "Three of a Kind"
  val baseScore: Score = new Score(30, 3)
  /** Returns true if at least one rank appears exactly three times. */
  def matches(cards: List[Card]): Boolean =
    PokerHelpers.validHand(cards) && PokerHelpers.isThreeOfAKind(cards)

  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + baseScore.chips
    score.mult = score.mult + baseScore.mult
    score
  }
}