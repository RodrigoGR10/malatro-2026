package cl.uchile.dcc
package observer

/** Receives notifications from a Subject it is attached to. */
trait Observer[T] {
  /**
   * Called when the subject this observer is attached to changes.
   *
   * @param subject the subject that triggered the notification
   * @param event   the event data sent by the subject
   */
  def update(subject: Subject[T], event: T): Unit
}
