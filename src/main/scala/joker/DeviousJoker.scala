package cl.uchile.dcc
package joker
import combinations.{PokerCombination, Straight, StraightFlush}
/**
 * Joker that adds +100 chips if the played cards form a Straight
 * or a Straight Flush, since a Straight Flush is also a Straight.
 */
object DeviousJoker extends Joker {
  override def affectCombination(combination: PokerCombination, score: Score): Score = {
    if combination == Straight || combination == StraightFlush then
      score.chips = score.chips + 100
    score
  }
}