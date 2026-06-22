package cl.uchile.dcc
package controller
import exceptions.InvalidTransitionException

/** Base class for all controller states. Invalid actions throw by default. */
class GameState protected() {
  /** Starts the game. Invalid by default. */
  def startGame(context: GameController): Unit = error("startGame")
  /** Plays a hand. Invalid by default. */
  def playHand(context: GameController, indices: List[Int]): Unit = error("playHand")
  /** Discards a hand. Invalid by default. */
  def discardHand(context: GameController, indices: List[Int]): Unit = error("discardHand")

  private def error(action: String): Nothing =
    throw new InvalidTransitionException(s"Invalid transition: cannot '$action' in state '${this.getClass.getSimpleName}'")

  /** Returns true if this is the PreGame state. For testing only. */
  def isPreGame: Boolean = false
  /** Returns true if this is the PlayerTurn state. For testing only. */
  def isPlayerTurn: Boolean = false
  /** Returns true if this is the RoundEnd state. For testing only. */
  def isRoundEnd: Boolean = false
}
