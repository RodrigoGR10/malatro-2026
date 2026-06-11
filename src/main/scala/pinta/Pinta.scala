package cl.uchile.dcc
package pinta
import joker._

/** Represents the suit of a playing card. */
trait Pinta {
  def applyScore(score: Score, j: Joker): Score
}