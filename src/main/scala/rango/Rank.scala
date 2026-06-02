package cl.uchile.dcc
package rango

/**
 * Represents the rank of a playing card.
 */
trait Rank {
  /** Numeric order of the rank, used to detect straights. */
  val orden: Int
  /** Chip value contributed by this rank. */
  val valor: Int
  /** Classification of the rank. */
  val clasificacion: ClasificacionRango

  def applyScore(score: Score, j: Joker): Score
}