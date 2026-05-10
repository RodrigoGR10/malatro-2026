package cl.uchile.dcc
import rango.Rank
import scala.collection.mutable

// Métodos auxiliares para validar combinaciones de póker
object PokerHelpers {

  def validHand(cards: List[Card]): Boolean =
    cards.nonEmpty && cards.size <= 5

  def rankCounts(cards: List[Card]): mutable.Map[Rank, Int] = {
    val counts: mutable.Map[Rank, Int] = mutable.Map.empty
    for card <- cards do
      val current = counts.getOrElse(card.rank, 0)
      counts.update(card.rank, current + 1)
    counts
  }

  def isConsecutive(values: List[Int]): Boolean = {
    var consecutive = true
    for i <- 0 until values.size - 1 do
      if values(i + 1) != values(i) + 1 then
        consecutive = false
    consecutive
  }
}