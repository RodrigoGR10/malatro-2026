package cl.uchile.dcc
package rango

/** Rank Five: order 5, chip value 5, classification Impar. */
object Five extends Rank {
  val orden: Int = 5
  val valor: Int = 5
  val clasificacion: ClasificacionRango = Impar

  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + valor
    score
  }
}