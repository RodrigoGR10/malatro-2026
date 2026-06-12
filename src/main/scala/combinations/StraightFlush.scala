package cl.uchile.dcc
package combinations

/**
 * Poker combination: five consecutive cards of the same suit.
 *
 * Base score: 100 chips, multiplier x8.
 * This is the highest-priority combination in Malatro.
 */
object StraightFlush extends PokerCombination {
  val name: String = "Straight Flush"
  val baseScore: Score = new Score(100, 8)
  /** Returns true if the cards form both a Straight and a Flush. */
  def matches(cards: List[Card]): Boolean =
    PokerHelpers.validHand(cards) && PokerHelpers.isStraightFlush(cards)

}