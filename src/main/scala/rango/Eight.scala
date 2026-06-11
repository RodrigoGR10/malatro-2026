package cl.uchile.dcc
package rango
import joker._

/** Rank Eight: order 8, chip value 8, classification Par. */
object Eight extends Rank {
  val orden: Int = 8
  val valor: Int = 8
  val clasificacion: ClasificacionRango = Par

  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + valor
    score
  }
}