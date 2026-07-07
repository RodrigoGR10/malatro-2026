package cl.uchile.dcc
package rank
import joker.Joker

/**
 * Represents the rank of a playing card.
 */
trait Rank {
  /** Numeric order of the rank, used to detect straights. */
  val order: Int
  /** Chip value contributed by this rank. */
  val chipValue: Int
  /** Classification of the rank. */
  val classification: RankClassification

  /**
   * Adds this rank's chip value to the score, then delegates to the
   * joker so it can apply its own effect for this rank.
   *
   * @param score the current score to update
   * @param j the joker whose effect may apply
   * @return the updated score
   */
  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + chipValue
    j.affectRank(this, score)
  }
}