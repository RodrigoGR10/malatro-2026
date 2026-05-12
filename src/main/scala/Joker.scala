package cl.uchile.dcc
/**
 * Represents a Joker card that can modify the scoring of a played hand.
 *
 * Jokers remain active during a round and are differentiated by their type.
 */
trait Joker

/**
 * Joker that adds +3 to the multiplier for each Diamond card played.
 */
object GreedyJoker extends Joker
/**
 * Joker that adds +100 chips if the played cards form a Straight.
 */
object DeviousJoker extends Joker
/**
 * Joker that adds +4 to the multiplier for each even-ranked card played.
 */
object EvenSteven extends Joker
/**
 * Joker that adds +30 chips for each face card (Jack, Queen, King) played.
 */
object ScaryFace extends Joker