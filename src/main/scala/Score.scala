package cl.uchile.dcc
/**
 * Represents the score of a poker hand, composed of a chip count and a multiplier.
 *
 * The final score of a hand is computed as chips * mult.
 *
 * @param chips the base chip count of the hand
 * @param mult  the multiplier applied to the chip count
 */
class Score(val chips: Int, val mult: Int) {
  /**
   * Returns true if the given object is a Score with the same chips and mult values.
   *
   * @param obj the object to compare with
   * @return true if obj is a Score with equal chips and mult
   */
  override def equals(obj: Any): Boolean = {
    if !obj.isInstanceOf[Score] then false
    else {
      val other = obj.asInstanceOf[Score]
      this.chips == other.chips && this.mult == other.mult
    }
  }
}