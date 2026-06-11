package cl.uchile.dcc
package rango
import joker._

/** Rank Six: order 6, chip value 6, classification Par. */
object Six extends Rank {
  val orden: Int = 6
  val valor: Int = 6
  val clasificacion: ClasificacionRango = Par

  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + valor
    score
  }
}