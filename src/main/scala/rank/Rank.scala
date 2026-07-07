package cl.uchile.dcc
package rank
import joker.Joker

/** Represents the rank of a playing card. */
trait Rank {
  val order: Int
  val chipValue: Int
  val classification: RankClassification

  /**
   * Lets the given joker react to this rank being scored (double dispatch).
   * Does not add this rank's own chip value; that happens once per card
   * in `Card.applyScore`, regardless of how many jokers are active.
   */
  def applyScore(score: Score, j: Joker): Score =
    j.affectRank(this, score)
}