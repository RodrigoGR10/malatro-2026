package cl.uchile.dcc
package joker
import rango.{Rank, Par}

/** Joker that adds +4 to the multiplier for each even-ranked card played. */
object EvenSteven extends Joker {
  override def affectRank(rank: Rank, score: Score): Score = {
    if rank.clasificacion == Par then
      score.mult = score.mult + 4
    score
  }
}