package cl.uchile.dcc

/**
 * Poker combination: any valid hand that does not satisfy a higher combination.
 *
 * Base score: 5 chips, multiplier x1.
 */
object HighCard extends PokerCombination {
  val name: String = "High Card"
  val baseScore: Score = Score(5, 1)
  /** Returns true for any hand with between 1 and 5 cards. */
  def matches(cards: List[Card]): Boolean =
    PokerHelpers.validHand(cards)
}