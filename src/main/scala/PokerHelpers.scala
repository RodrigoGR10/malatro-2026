package cl.uchile.dcc
import rango.{Rank, Ace}
import scala.collection.mutable

/**
 * Utility methods shared across poker combination validators.
 *
 * These methods handle common operations such as hand validation,
 * rank counting, suit checking, and combination detection.
 */
object PokerHelpers {

  /**
   * Returns true if the given list contains between 1 and 5 cards.
   *
   * @param cards the list of cards to validate
   * @return true if cards is non-empty and has at most 5 elements
   */
  def validHand(cards: List[Card]): Boolean =
    cards.nonEmpty && cards.size <= 5

  /**
   * Counts how many times each rank appears in the given list of cards.
   *
   * Example: A, A, 7, 7, 7 => Map(A -> 2, 7 -> 3)
   *
   * @param cards the list of cards to count
   * @return a mutable map from rank to occurrence count
   */
  def rankCounts(cards: List[Card]): mutable.Map[Rank, Int] = {
    val counts: mutable.Map[Rank, Int] = mutable.Map.empty
    for card <- cards do
      if counts.contains(card.rank) then
        counts(card.rank) += 1
      else
        counts(card.rank) = 1
    counts
  }

  /**
   * Returns true if the given list of integers forms a consecutive sequence.
   *
   * Precondition: the list must be sorted in ascending order before calling this method.
   * Example: 2,3,4,5,6 => true / 2,3,5,6,7 => false
   *
   * @param values a sorted list of integers to check
   * @return true if each element is exactly one greater than the previous
   */
  def isConsecutive(values: List[Int]): Boolean = {
    var consecutive = true
    for i <- 0 until values.size - 1 do
      if values(i + 1) != values(i) + 1 then
        consecutive = false
    consecutive
  }

  /**
   * Returns true if all cards in the list share the same suit.
   *
   * Returns false if the list is empty.
   *
   * @param cards the list of cards to check
   * @return true if all cards have the same suit
   */
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

  /**
   * Returns true if at least one rank appears exactly two times.
   *
   * Note: also returns true for hands with two pairs or a full house,
   * since those also contain a rank that appears exactly twice.
   * Priority between combinations is handled by PokerHand.bestCombination.
   *
   * @param cards the list of cards to check
   * @return true if at least one rank appears exactly twice
   */
  def isPair(cards: List[Card]): Boolean = {
    val counts = rankCounts(cards)
    var foundPair = false
    for (_, count) <- counts do
      if count == 2 then foundPair = true
    foundPair
  }
  /**
   * Returns true if at least one rank appears three times.
   *
   * Priority between combinations is handled by PokerHand.bestCombination.
   *
   * @param cards the list of cards to check
   * @return true if at least one rank appears exactly three times
   */
  def isThreeOfAKind(cards: List[Card]): Boolean = {
    val counts = rankCounts(cards)
    var foundThree = false
    for (_, count) <- counts do
      if count >= 3 then foundThree = true
    foundThree
  }
  /**
   * Returns true if the 5 cards have consecutive orders.
   *
   * The Ace can act as order 1 or order 14.
   *
   * @param cards the list of cards to check
   * @return true if the cards form a straight
   */
  def isStraight(cards: List[Card]): Boolean = {
    if cards.size != 5 then false
    else {
      var normal: List[Int] = List.empty
      for card <- cards do
        if !normal.contains(card.rank.orden) then
          normal = normal :+ card.rank.orden
      normal = normal.sorted

      var aceLow: List[Int] = List.empty
      for card <- cards do
        val order = if card.rank == Ace then 1 else card.rank.orden
        if !aceLow.contains(order) then
          aceLow = aceLow :+ order
      aceLow = aceLow.sorted

      (normal.size == 5 && isConsecutive(normal)) || (aceLow.size == 5 && isConsecutive(aceLow))
    }
  }
  /**
   * Returns true if all 5 cards share the same suit.
   *
   * @param cards the list of cards to check
   * @return true if the cards form a flush
   */
  def isFlush(cards: List[Card]): Boolean =
    cards.size == 5 && sameSuit(cards)
  /**
   * Returns true if the cards form both a straight and a flush.
   *
   * @param cards the list of cards to check
   * @return true if the cards form a straight flush
   */
  def isStraightFlush(cards: List[Card]): Boolean =
    isStraight(cards) && isFlush(cards)
}