package cl.uchile.dcc
package rango
import joker._

/** Rank King: order 13, chip value 10, classification Figura. */
object King extends Rank {
  val orden: Int = 13
  val valor: Int = 10
  val clasificacion: ClasificacionRango = Figura

  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + valor
    score
  }
}