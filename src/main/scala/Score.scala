package cl.uchile.dcc

class Score(private var _chips: Int, private var _mult: Int) {
  def chips: Int = _chips
  def chips_=(value: Int): Unit = _chips = value

  def mult: Int = _mult
  def mult_=(value: Int): Unit = _mult = value

  override def equals(obj: Any): Boolean = {
    if !obj.isInstanceOf[Score] then false
    else {
      val other = obj.asInstanceOf[Score]
      this._chips == other._chips && this._mult == other._mult
    }
  }
}