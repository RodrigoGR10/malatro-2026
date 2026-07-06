package cl.uchile.dcc
package controller

import observer.Observer

/**
 * Controls the game flow using the State pattern.
 *
 * @param hand the hand managed by this controller
 * @param targetScore the minimum score required to win
 */
class GameController(val hand: Hand, val targetScore: Int = 1000) extends Observer[Hand] {
  private var _state: GameState = new PreGame
  private var _totalScore: Int = 0
  private var _lastPlayScore: Int = 0
  private var _finished: Boolean = false

  hand.attach(this)

  /** Returns the current state. */
  def state: GameState = _state

  /** Sets the current state. */
  def state_=(newState: GameState): Unit =
    _state = newState

  /** Returns the accumulated score for the current game. */
  def totalScore: Int = _totalScore

  /** Returns the score earned by the latest played hand. */
  def lastPlayScore: Int = _lastPlayScore

  /** Returns true when the game has ended. */
  def isFinished: Boolean = _finished

  /** Returns true when the game ended and the target score was reached. */
  def hasWon: Boolean =
    _finished && _totalScore >= targetScore

  /** Returns true when the game ended without reaching the target score. */
  def hasLost: Boolean =
    _finished && _totalScore < targetScore

  /** Returns a message that summarizes the current game result. */
  def resultMessage: String =
    if state.isPreGame then
      "Game has not started."
    else if !isFinished then
      s"Game in progress. Total score: $_totalScore."
    else if hasWon then
      s"Game finished: target reached with $_totalScore points. You win!"
    else
      s"Game finished: only $_totalScore of $targetScore points reached. You lose."

  /** Starts the game. */
  def startGame(): Unit =
    _state.startGame(this)

  /** Plays the cards at the given indices and returns the score gained. */
  def playHand(indices: List[Int]): Int =
    _state.playHand(this, indices)

  /** Discards the cards at the given indices and returns the discarded cards. */
  def discardHand(indices: List[Int]): List[Card] =
    _state.discardHand(this, indices)

  /** Registers a successful play in the controller score. */
  def registerPlay(playedCards: List[Card]): Int =
    val playScore = ScoreCalculator.calculate(playedCards, hand.jokers)
    _lastPlayScore = playScore
    _totalScore = _totalScore + playScore
    if _state.isRoundEnd then
      finishGame()
    playScore

  /** Finishes the game. */
  private def finishGame(): Unit =
    _finished = true

  override def update(subject: observer.Subject[Hand], event: Hand): Unit =
    _state = new RoundEnd
}