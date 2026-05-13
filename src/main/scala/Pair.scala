package cl.uchile.dcc

/**
 * Poker combination: two cards of the same rank.
 *
 * Base score: 10 chips, multiplier x2.
 */
object Pair extends PokerCombination {
  val name: String = "Pair"
  val baseScore: Score = new Score(10, 2)
  /** Returns true if at least one rank appears exactly two times. */
  def matches(cards: List[Card]): Boolean =
    PokerHelpers.validHand(cards) && PokerHelpers.isPair(cards)
}