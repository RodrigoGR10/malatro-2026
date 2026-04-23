package cl.uchile.dcc

object PokerHelpers {

  def validHand(cards: List[Card]): Boolean =
    cards.nonEmpty && cards.size <= 5

  def isFlush(cards: List[Card]): Boolean =
    validHand(cards) &&
      cards.size == 5 &&
      cards.map(_.suit).distinct.size == 1

  def isStraight(cards: List[Card]): Boolean = {
    if (!validHand(cards) || cards.size != 5) false
    else {
      val normal = cards.map(_.rank.orden).distinct.sorted
      val aceLow = cards.map(c => if (c.rank == Ace) 1 else c.rank.orden).distinct.sorted

      (normal.size == 5 && isConsecutive(normal)) ||
        (aceLow.size == 5 && isConsecutive(aceLow))
    }
  }

  def rankCounts(cards: List[Card]): Map[Rank, Int] =
    cards.groupBy(_.rank).map { case (rank, sameRankCards) =>
      rank -> sameRankCards.size
    }

  private def isConsecutive(values: List[Int]): Boolean =
    values.zip(values.drop(1)).forall { case (a, b) => b == a + 1 }
}