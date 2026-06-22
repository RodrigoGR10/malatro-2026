package cl.uchile.dcc
package observer
import scala.collection.mutable

/** Reusable base implementation of Subject with duplicate control. */
class BaseSubject[T] protected() extends Subject[T] {
  private val observers: mutable.Set[Observer[T]] =
    mutable.LinkedHashSet.empty[Observer[T]]

  override def attach(observer: Observer[T]): Unit =
    observers += observer

  override def detach(observer: Observer[T]): Unit =
    observers -= observer

  override def notifyObservers(event: T): Unit =
    for observer <- observers do observer.update(this, event)
}