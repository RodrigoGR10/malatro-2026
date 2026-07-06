package cl.uchile.dcc
package controller

/** State representing an active player turn. */
class PlayerTurn extends GameState {
  override def playHand(context: GameController, indices: List[Int]): Int =
    val playedCards = context.hand.playHand(indices)
    context.registerPlay(playedCards)

  override def discardHand(context: GameController, indices: List[Int]): List[Card] =
    context.hand.discardHand(indices)

  override def isPlayerTurn: Boolean = true
}