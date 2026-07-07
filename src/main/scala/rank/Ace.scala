package cl.uchile.dcc
package rank
import joker.Joker

/**
 * Rank Ace: order 14, chip value 11, classification Impar.
 *
 * The Ace can act as order 1 or 14 when detecting straights.
 */
object Ace extends Rank {
  val order: Int = 14
  val chipValue: Int = 11
  val classification: RankClassification = Odd
}