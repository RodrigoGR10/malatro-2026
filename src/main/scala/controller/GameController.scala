package cl.uchile.dcc
package controller
import observer.Observer

/**
 * Controls the game flow using the State pattern.
 *
 * Observes its Hand and transitions to RoundEnd when notified
 * that the player has run out of plays.
 *
 * @param hand the hand managed by this controller
 */
class GameController(val hand: Hand) extends Observer[Hand] {
  private var _state: GameState = new PreGame
  hand.attach(this)

  /** Returns the current state. */
  def state: GameState = _state
  /** Sets the current state. */
  def state_=(newState: GameState): Unit = _state = newState

  /** Starts the game. */
  def startGame(): Unit = _state.startGame(this)
  /** Plays the cards at the given indices. */
  def playHand(indices: List[Int]): Unit = _state.playHand(this, indices)
  /** Discards the cards at the given indices. */
  def discardHand(indices: List[Int]): Unit = _state.discardHand(this, indices)

  override def update(subject: observer.Subject[Hand], event: Hand): Unit =
    _state = new RoundEnd
}
