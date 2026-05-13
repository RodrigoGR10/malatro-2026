package cl.uchile.dcc

/**
 * Poker combination: five cards of the same suit.
 *
 * Base score: 35 chips, multiplier x4.
 */
object Flush extends PokerCombination {
  val name: String = "Flush"
  val baseScore: Score = Score(35, 4)
  /** Returns true if all 5 cards share the same suit. */
  def matches(cards: List[Card]): Boolean =
    PokerHelpers.validHand(cards) && PokerHelpers.isFlush(cards)
}