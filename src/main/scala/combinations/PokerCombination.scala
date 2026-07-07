package cl.uchile.dcc
package combinations
import joker.Joker

trait PokerCombination {
  val name: String
  val baseScore: Score
  def matches(cards: List[Card]): Boolean

  /**
   * Lets the given joker react to this combination being scored
   * (double dispatch). Does not add this combination's base score;
   * `ScoreCalculator` initializes the score with it exactly once.
   */
  def applyScore(score: Score, j: Joker): Score =
    j.affectCombination(this, score)
}