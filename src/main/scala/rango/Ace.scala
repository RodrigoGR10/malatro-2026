package cl.uchile.dcc
package rango
import joker._

/**
 * Rank Ace: order 14, chip value 11, classification Impar.
 *
 * The Ace can act as order 1 or 14 when detecting straights.
 */
object Ace extends Rank {
  val orden: Int = 14
  val valor: Int = 11
  val clasificacion: ClasificacionRango = Impar

  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + valor
    score
  }
}