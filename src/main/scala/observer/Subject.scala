package cl.uchile.dcc
package observer

/** Maintains a list of observers and notifies them of events. */
trait Subject[T] {
  /**
   * Subscribes an observer to this subject.
   *
   * @param observer the observer to attach
   */
  def attach(observer: Observer[T]): Unit

  /**
   * Unsubscribes an observer from this subject.
   *
   * @param observer the observer to detach
   */
  def detach(observer: Observer[T]): Unit

  /**
   * Notifies all attached observers of an event.
   *
   * @param event the event data to send
   */
  def notifyObservers(event: T): Unit
}