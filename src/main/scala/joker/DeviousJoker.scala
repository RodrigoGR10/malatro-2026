package cl.uchile.dcc
package joker
import combinations.{PokerCombination, Straight}

/** Joker that adds +100 chips if the played cards form a Straight. */
object DeviousJoker extends Joker {
  override def affectCombination(combination: PokerCombination, score: Score): Score = {
    if combination == Straight then
      score.chips = score.chips + 100
    score
  }
}