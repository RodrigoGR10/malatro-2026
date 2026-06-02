package cl.uchile.dcc
package rango

/** Rank Seven: order 7, chip value 7, classification Impar. */
object Seven extends Rank {
  val orden: Int = 7
  val valor: Int = 7
  val clasificacion: ClasificacionRango = Impar

  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + valor
    score
  }
}