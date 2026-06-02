package cl.uchile.dcc
package rango

/** Rank Nine: order 9, chip value 9, classification Impar. */
object Nine extends Rank {
  val orden: Int = 9
  val valor: Int = 9
  val clasificacion: ClasificacionRango = Impar

  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + valor
    score
  }
}