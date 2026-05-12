package cl.uchile.dcc

class Score(val chips: Int, val mult: Int) {
  override def equals(obj: Any): Boolean = {
    if !obj.isInstanceOf[Score] then false
    else {
      val other = obj.asInstanceOf[Score]
      this.chips == other.chips && this.mult == other.mult
    }
  }
}