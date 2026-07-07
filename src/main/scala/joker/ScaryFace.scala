package cl.uchile.dcc
package joker
import rank.{Rank, Figure}

/** Joker that adds +30 chips for each face card (Jack, Queen, King) played. */
object ScaryFace extends Joker {
  override def affectRank(rank: Rank, score: Score): Score = {
    if rank.classification == Figure then
      score.chips = score.chips + 30
    score
  }
}