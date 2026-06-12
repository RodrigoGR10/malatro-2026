package cl.uchile.dcc
package pinta
import joker.Joker

/** Represents the suit of a playing card. */
trait Pinta {
  /**
   * Delegates to the joker so it can apply its own effect for this suit.
   *
   * @param score the current score to update
   * @param j the joker whose effect may apply
   * @return the updated score
   */
  def applyScore(score: Score, j: Joker): Score =
    j.affectSuit(this, score)
}