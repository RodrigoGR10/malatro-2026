package cl.uchile.dcc

/**
 * Represents the player's hand
 *
 * @param _cards  the initial list of cards in the hand
 * @param _jokers the initial list of active Jokers
 */
class Hand(private var _cards: List[Card] = List.empty, private var _jokers: List[Joker] = List.empty) {

  private var _playCount: Int = 0
  private var _discardCount: Int = 0

  /** Returns the current list of cards in the hand. */
  def cards: List[Card] =
    _cards
  def cards_=(value: List[Card]): Unit = _cards = value

  /** Returns the current list of active Jokers. */
  def jokers: List[Joker] =
    _jokers
  def jokers_=(value: List[Joker]): Unit = _jokers = value
  /**
   * Adds a card to the end of the hand.
   *
   * @param card the card to add
   */
  def addCard(card: Card): Unit =
    if _cards.size >= 8 then
      throw new TooManyCardsException("A hand cannot have more than 8 cards.")
    _cards = _cards :+ card
  /**
   * Removes the card at the given index from the hand.
   *
   * @param index the zero-based index of the card to remove
   */
  def removeCard(index: Int): Unit = {
    if index < 0 || index >= _cards.size then
      throw new InvalidCardIndexException(s"Card index $index is invalid. Hand has ${_cards.size} card(s).")
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
    if _jokers.size >= 2 then
      throw new TooManyJokersException("A hand cannot have more than 2 jokers.")
    _jokers = _jokers :+ joker
  /**
   * Removes the Joker at the given index from the active Joker list.
   *
   * @param index the zero-based index of the Joker to remove
   */
  def removeJoker(index: Int): Unit = {
    if index < 0 || index >= _jokers.size then
      throw new InvalidJokerIndexException(s"Joker index $index is invalid. Hand has ${_jokers.size} joker(s).")
    var result: List[Joker] = List.empty
    for i <- 0 until _jokers.size do
      if i != index then
        result = result :+ _jokers(i)
    _jokers = result
  }

  private def validateIndices(indices: List[Int]): Unit = {
    for idx <- indices do
      if idx < 0 || idx >= _cards.size then
        throw new InvalidPlayIndexException(s"Index $idx is out of range. Hand has ${_cards.size} card(s).")
  }

  /**
   * Plays the cards at the given indices.
   *
   * @param indices a list of zero-based card indices to play
   * @return the list of cards at the given indices, in order
   */
  def playHand(indices: List[Int]): List[Card] = {
    if _playCount >= 3 then
      throw new TooManyPlaysException("Cannot play more than 3 times.")
    if indices.size < 1 then
      throw new TooFewCardsToPlayException("Must play at least 1 card.")
    if indices.size > 5 then
      throw new TooManyCardsToPlayException("Cannot play more than 5 cards.")
    validateIndices(indices)
    _playCount += 1
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