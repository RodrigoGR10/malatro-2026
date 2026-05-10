package cl.uchile.dcc
import rango.Ace

// Combinación: 5 cartas consecutivas sin importar la pinta
object Straight extends PokerCombination {
  val name: String = "Straight"
  val baseScore: Score = Score(30, 4)

  private def alreadyIn(lst: List[Int], value: Int): Boolean = {
    var found = false
    for x <- lst do
      if x == value then found = true
    found
  }

  def matches(cards: List[Card]): Boolean = {
    if !PokerHelpers.validHand(cards) || cards.size != 5 then false
    else {
      var normal: List[Int] = List.empty
      for card <- cards do
        if !alreadyIn(normal, card.rank.orden) then
          normal = normal :+ card.rank.orden
      normal = normal.sorted

      var aceLow: List[Int] = List.empty
      for card <- cards do
        val order = if card.rank == Ace then 1 else card.rank.orden
        if !alreadyIn(aceLow, order) then
          aceLow = aceLow :+ order
      aceLow = aceLow.sorted

      (normal.size == 5 && PokerHelpers.isConsecutive(normal)) || (aceLow.size == 5 && PokerHelpers.isConsecutive(aceLow))
    }
  }
}