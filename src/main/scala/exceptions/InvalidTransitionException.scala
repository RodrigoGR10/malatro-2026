package cl.uchile.dcc
package exceptions

/** Thrown when an invalid state transition is attempted. */
class InvalidTransitionException(message: String) extends Exception(message)