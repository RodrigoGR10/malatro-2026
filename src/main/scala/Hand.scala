package cl.uchile.dcc

class Hand(private var _cards: List[Card] = List.empty, private var _jokers: List[Joker] = List.empty) {

  def cards: List[Card] =
    _cards

  def jokers: List[Joker] =
    _jokers

  def addCard(card: Card): Unit =
    _cards = _cards :+ card

  def removeCard(index: Int): Unit = {
    var result: List[Card] = List.empty
    for i <- 0 until _cards.size do
      if i != index then
        result = result :+ _cards(i)
    _cards = result
  }

  def addJoker(joker: Joker): Unit =
    _jokers = _jokers :+ joker

  def removeJoker(index: Int): Unit = {
    var result: List[Joker] = List.empty
    for i <- 0 until _jokers.size do
      if i != index then
        result = result :+ _jokers(i)
    _jokers = result
  }

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

object Hand {
  def apply(cards: List[Card] = List.empty, jokers: List[Joker] = List.empty): Hand =
    new Hand(cards, jokers)
}