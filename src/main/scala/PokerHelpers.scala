package cl.uchile.dcc
import rango.{Rank, Ace}
import scala.collection.mutable

object PokerHelpers {
  def validHand(cards: List[Card]): Boolean =
    cards.nonEmpty && cards.size <= 5

  def rankCounts(cards: List[Card]): mutable.Map[Rank, Int] = {
    val counts: mutable.Map[Rank, Int] = mutable.Map.empty
    for card <- cards do
      if counts.contains(card.rank) then
        counts(card.rank) += 1
      else
        counts(card.rank) = 1
    counts
  }

  def isConsecutive(values: List[Int]): Boolean = {
    var consecutive = true
    for i <- 0 until values.size - 1 do
      if values(i + 1) != values(i) + 1 then
        consecutive = false
    consecutive
  }

  def sameSuit(cards: List[Card]): Boolean = {
    if cards.isEmpty then false
    else {
      val firstSuit = cards(0).suit
      var same = true
      for card <- cards do
        if card.suit != firstSuit then same = false
      same
    }
  }

  def isPair(cards: List[Card]): Boolean = {
    val counts = rankCounts(cards)
    var foundPair = false
    for (_, count) <- counts do
      if count == 2 then foundPair = true
    foundPair
  }

  def isThreeOfAKind(cards: List[Card]): Boolean = {
    val counts = rankCounts(cards)
    var foundThree = false
    for (_, count) <- counts do
      if count >= 3 then foundThree = true
    foundThree
  }

  def isStraight(cards: List[Card]): Boolean = {
    if cards.size != 5 then false
    else {
      var normal: List[Int] = List.empty
      for card <- cards do
        if !normal.contains(card.rank.orden) then
          normal = normal :+ card.rank.orden
      normal = normal.sorted

      var aceLow: List[Int] = List.empty
      for card <- cards do {
        val order = if card.rank == Ace then 1 else card.rank.orden
        if !aceLow.contains(order) then
          aceLow = aceLow :+ order
      }
      aceLow = aceLow.sorted

      (normal.size == 5 && isConsecutive(normal)) || (aceLow.size == 5 && isConsecutive(aceLow))
    }
  }

  def isFlush(cards: List[Card]): Boolean =
    cards.size == 5 && sameSuit(cards)

  def isStraightFlush(cards: List[Card]): Boolean =
    isStraight(cards) && isFlush(cards)
}