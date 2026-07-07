package cl.uchile.dcc
package joker
import rank.{Rank, Even}

/** Joker that adds +4 to the multiplier for each even-ranked card played. */
object EvenSteven extends Joker {
  override def affectRank(rank: Rank, score: Score): Score = {
    if rank.classification == Even then
      score.mult = score.mult + 4
    score
  }
}