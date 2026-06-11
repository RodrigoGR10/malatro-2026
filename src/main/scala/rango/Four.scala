package cl.uchile.dcc
package rango
import joker._

/** Rank Four: order 4, chip value 4, classification Par. */
object Four extends Rank {
  val orden: Int = 4
  val valor: Int = 4
  val clasificacion: ClasificacionRango = Par

  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + valor
    score
  }
}