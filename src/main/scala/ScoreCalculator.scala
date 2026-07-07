package cl.uchile.dcc
import joker.Joker

/**
 * Calculates the final score for a played hand.
 *
 * Determines the best combination, initializes the score with its
 * base values, adds each card's chip contribution, applies all
 * active jokers over the combination, and returns chips * mult.
 */
object ScoreCalculator:

  /**
   * Computes the final score for the given cards and jokers.
   *
   * @param cards the list of played cards (1 to 5)
   * @param jokers the list of active jokers (0 to 2)
   * @return the final score as chips * mult
   */
  def calculate(cards: List[Card], jokers: List[Joker]): Int =
    val combination = PokerHand.bestCombination(new Hand(cards))
    val score = new Score(combination.baseScore.chips, combination.baseScore.mult)
    for card <- cards do
      card.applyScore(score, jokers)
    for joker <- jokers do
      combination.applyScore(score, joker)
    score.chips * score.mult