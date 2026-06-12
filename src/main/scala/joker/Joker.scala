package cl.uchile.dcc
package joker
import rango.Rank
import pinta.Pinta
import combinations.PokerCombination

/**
 * Represents a Joker card that can modify the scoring of a played hand.
 *
 * Jokers remain active during a round and are differentiated by their type.
 * Each joker implements its own effect by overriding the relevant
 * affect method; the other methods default to returning the score
 * unchanged.
 */
trait Joker {
  /**
   * Applies this joker's effect when scoring a rank, if any.
   *
   * @param rank the rank being scored
   * @param score the current score to update
   * @return the updated score
   */
  def affectRank(rank: Rank, score: Score): Score = score

  /**
   * Applies this joker's effect when scoring a suit, if any.
   *
   * @param suit the suit being scored
   * @param score the current score to update
   * @return the updated score
   */
  def affectSuit(suit: Pinta, score: Score): Score = score

  /**
   * Applies this joker's effect when scoring a poker combination, if any.
   *
   * @param combination the combination being scored
   * @param score the current score to update
   * @return the updated score
   */
  def affectCombination(combination: PokerCombination, score: Score): Score = score
}