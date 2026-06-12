package cl.uchile.dcc
package rango
import joker.Joker

/** Rank Queen: order 12, chip value 10, classification Figura. */
object Queen extends Rank {
  val orden: Int = 12
  val valor: Int = 10
  val clasificacion: ClasificacionRango = Figura

  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + valor
    j.affectRank(this, score)
  }
}