package cl.uchile.dcc
package rango
import joker._

/** Rank Ten: order 10, chip value 10, classification Par. */
object Ten extends Rank {
  val orden: Int = 10
  val valor: Int = 10
  val clasificacion: ClasificacionRango = Par

  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + valor
    score
  }
}