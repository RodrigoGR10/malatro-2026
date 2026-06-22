package cl.uchile.dcc
package controller

/** Initial state before the round starts. */
class PreGame extends GameState {
  override def startGame(context: GameController): Unit =
    context.state = new PlayerTurn
  override def isPreGame: Boolean = true
}
