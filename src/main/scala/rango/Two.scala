package cl.uchile.dcc
package rango
import joker._

/** Rank Two: order 2, chip value 2, classification Par. */
object Two extends Rank {
  val orden: Int = 2
  val valor: Int = 2
  val clasificacion: ClasificacionRango = Par

  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + valor
    score
  }
}