package cl.uchile.dcc
import rango.Ace
import rango.Rank

// Métodos auxiliares para validar combinaciones de póker
object PokerHelpers {

  // Verifica que la mano tenga entre 1 y 5 cartas
  def validHand(cards: List[Card]): Boolean =
    cards.nonEmpty && cards.size <= 5

  // Verifica que las 5 cartas sean de la misma pinta
  def isFlush(cards: List[Card]): Boolean =
    validHand(cards) && cards.size == 5 && cards.map(_.suit).distinct.size == 1

  // Verifica que las 5 cartas sean consecutivas
  def isStraight(cards: List[Card]): Boolean = {
    if (!validHand(cards) || cards.size != 5) false
    else {
      val normal = cards.map(_.rank.orden).distinct.sorted
      val aceLow = cards.map(c => if (c.rank == Ace) 1 else c.rank.orden).distinct.sorted

      (normal.size == 5 && isConsecutive(normal)) || (aceLow.size == 5 && isConsecutive(aceLow))
    }
  }

  // Cuenta cuántas cartas hay de cada rango
  def rankCounts(cards: List[Card]): Map[Rank, Int] =
    cards.groupBy(_.rank).map { case (rank, sameRankCards) =>
      rank -> sameRankCards.size
    }

  // Verifica que una lista de números ordenados sea consecutiva
  private def isConsecutive(values: List[Int]): Boolean =
    values.zip(values.drop(1)).forall { case (a, b) => b == a + 1 }
}