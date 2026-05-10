package cl.uchile.dcc

// Combinación: 5 cartas de la misma pinta
object Flush extends PokerCombination {
  val name: String = "Flush"
  val baseScore: Score = Score(35, 4)

  def matches(cards: List[Card]): Boolean = {
    if !PokerHelpers.validHand(cards) || cards.size != 5 then false
    else {
      val firstSuit = cards(0).suit
      var allSame = true
      for card <- cards do
        if card.suit != firstSuit then
          allSame = false
      allSame
    }
  }
}