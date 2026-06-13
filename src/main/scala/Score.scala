package cl.uchile.dcc

/**
 * Represents the score of a played hand.
 *
 * A score is composed of chips and a multiplier.
 * The final score is calculated as chips * mult.
 *
 * @param _chips the initial chip value
 * @param _mult the initial multiplier value
 */
class Score(private var _chips: Int, private var _mult: Int) {
  /** Returns the current chip value. */
  def chips: Int = _chips
  /** Sets the chip value. */
  def chips_=(value: Int): Unit = _chips = value

  /** Returns the current multiplier value. */
  def mult: Int = _mult
  /** Sets the multiplier value. */
  def mult_=(value: Int): Unit = _mult = value

  override def equals(obj: Any): Boolean = {
    if !obj.isInstanceOf[Score] then false
    else {
      val other = obj.asInstanceOf[Score]
      this._chips == other._chips && this._mult == other._mult
    }
  }
}