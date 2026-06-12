package cl.uchile.dcc
package joker
import pinta.{Pinta, Diamonds}

/** Joker that adds +3 to the multiplier for each Diamond card played. */
object GreedyJoker extends Joker {
  override def affectSuit(suit: Pinta, score: Score): Score = {
    if suit == Diamonds then
      score.mult = score.mult + 3
    score
  }
}