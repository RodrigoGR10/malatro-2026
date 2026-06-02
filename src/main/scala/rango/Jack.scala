package cl.uchile.dcc
package rango

/** Rank Jack: order 11, chip value 10, classification Figura. */
object Jack extends Rank {
  val orden: Int = 11
  val valor: Int = 10
  val clasificacion: ClasificacionRango = Figura

  def applyScore(score: Score, j: Joker): Score = {
    score.chips = score.chips + valor
    score
  }
}