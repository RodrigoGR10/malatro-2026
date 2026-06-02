package cl.uchile.dcc
package rango

/** Rank Three: order 3, chip value 3, classification Impar. */
object Three extends Rank {
  val orden: Int = 3
  val valor: Int = 3
  val clasificacion: ClasificacionRango = Impar

  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + valor
    score
  }
}