package cl.uchile.dcc
package controller

/** State representing an active player turn. */
class PlayerTurn extends GameState {
  override def playHand(context: GameController, indices: List[Int]): Unit =
    context.hand.playHand(indices)
  override def discardHand(context: GameController, indices: List[Int]): Unit =
    context.hand.discardHand(indices)
  override def isPlayerTurn: Boolean = true
}
