package cl.uchile.dcc
package rango

/**
 * Represents the rank of a playing card.
 *
 * Every rank exposes its numeric order (used for straight detection),
 * its chip value (contributed to the score), and its classification.
 */
trait Rank {
  /** The numeric order of this rank, used to detect straights. */
  val orden: Int
  /** The chip value this rank contributes to the score when played. */
  val valor: Int
  /** The classification of this rank: Par, Impar, or Figura. */
  val clasificacion: ClasificacionRango
}