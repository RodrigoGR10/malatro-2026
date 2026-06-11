package cl.uchile.dcc
package exceptions

/** Thrown when a play or discard index is out of range. */
class InvalidPlayIndexException(message: String) extends Exception(message)