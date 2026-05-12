package cl.uchile.dcc

/**
 * Represents the player's hand in a game of Malatro.
 *
 * @param _cards  the initial list of cards in the hand
 * @param _jokers the initial list of active Jokers
 */
class Hand(private var _cards: List[Card] = List.empty, private var _jokers: List[Joker] = List.empty) {
  /** Returns the current list of cards in the hand. */
  def cards: List[Card] =
    _cards
  /** Returns the current list of active Jokers. */
  def jokers: List[Joker] =
    _jokers
  /**
   * Adds a card to the end of the hand.
   *
   * @param card the card to add
   */
  def addCard(card: Card): Unit =
    _cards = _cards :+ card
  /**
   * Removes the card at the given index from the hand.
   *
   * @param index the zero-based index of the card to remove
   */
  def removeCard(index: Int): Unit = {
    var result: List[Card] = List.empty
    for i <- 0 until _cards.size do
      if i != index then
        result = result :+ _cards(i)
    _cards = result
  }
  /**
   * Adds a Joker to the end of the active Joker list.
   *
   * @param joker the Joker to add
   */
  def addJoker(joker: Joker): Unit =
    _jokers = _jokers :+ joker
  /**
   * Removes the Joker at the given index from the active Joker list.
   *
   * @param index the zero-based index of the Joker to remove
   */
  def removeJoker(index: Int): Unit = {
    var result: List[Joker] = List.empty
    for i <- 0 until _jokers.size do
      if i != index then
        result = result :+ _jokers(i)
    _jokers = result
  }
  /**
   * Plays the cards at the given indices.
   *
   * @param indices a list of zero-based card indices to play
   * @return the list of cards at the given indices, in order
   */
  def playHand(indices: List[Int]): List[Card] = {
    var played: List[Card] = List.empty
    for i <- indices do
      played = played :+ _cards(i)
    var remaining: List[Card] = List.empty
    for i <- 0 until _cards.size do
      var isPlayed = false
      for j <- indices do
        if j == i then isPlayed = true
      if !isPlayed then
        remaining = remaining :+ _cards(i)
    _cards = remaining
    played
  }
}