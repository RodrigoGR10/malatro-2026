package cl.uchile.dcc
package joker
import suit.{Suit, Diamonds}

/** Joker that adds +3 to the multiplier for each Diamond card played. */
object GreedyJoker extends Joker {
  override def affectSuit(suit: Suit, score: Score): Score = {
    if suit == Diamonds then
      score.mult = score.mult + 3
    score
  }
}