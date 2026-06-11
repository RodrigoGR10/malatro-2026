package cl.uchile.dcc

import combinations.{Flush, HighCard, Pair, PokerCombination, Straight, StraightFlush, ThreeOfAKind}

/**
 * Evaluates the best poker combination for a given hand.
 *
 * Combinations are evaluated in order of priority from highest to lowest.
 * The first combination that matches is returned.
 */
object PokerHand {
  /** Combinations ordered from highest to lowest priority. */
  private val orderedCombinations: List[PokerCombination] =
    List(StraightFlush, Flush, Straight, ThreeOfAKind, Pair, HighCard)
  /**
   * Returns the highest-priority combination matched by the hand's cards.
   *
   * Always returns at least HighCard since it matches any valid hand.
   *
   * @param hand the player's hand to evaluate
   * @return the best PokerCombination for the given hand
   */
  def bestCombination(hand: Hand): PokerCombination = {
    var result: PokerCombination = HighCard
    var found = false
    for combination <- orderedCombinations do
      if !found && combination.matches(hand.cards) then
        result = combination
        found = true
    result
  }
}