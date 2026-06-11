package cl.uchile.dcc
package combinations

import joker._

/**
 * Represents a poker hand combination such as Flush, Straight, or Pair.
 *
 * Each combination defines a name, a base score, and a validation
 * method that checks whether a given list of cards satisfies it.
 */
trait PokerCombination {
  /** The human-readable name of this combination. */
  val name: String
  /** The base score awarded when this combination is played. */
  val baseScore: Score
  /**
   * Returns true if the given list of cards satisfies this combination.
   *
   * @param cards the list of cards to evaluate (between 1 and 5)
   * @return true if the cards form this combination
   */
  def matches(cards: List[Card]): Boolean

  def applyScore(score: Score, j: Joker): Score
}