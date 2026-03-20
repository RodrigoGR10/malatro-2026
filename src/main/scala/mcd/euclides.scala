package cl.uchile.dcc
package mcd

/*
El siguiente import es necesario sí quieren leer números enteros desde la consola.
Pueden leer un entero de la siguiente forma:

  val a = readInt()
  
y luego usarlo como deseen

  val res = a + 5
  println(s"El resultado del número ingresado más 5 es $res")
  
*/
import scala.io.StdIn.readInt

@main def euclidesInteractivo(): Unit = {
  var continuar = true
  while continuar do
    println("Ingrese el primer número (0 para salir):")
    val a = readInt()
    var ra = a
    if a == 0 then
      continuar = false
    else
      println("Ingrese el segundo número:")
      var b = readInt()
      var rb = b
      if b != 0 then
        while ra != 0 && rb != 0 do
          if rb <= ra then
            ra = ra % rb
          else
            rb = rb % ra
        if ra == 0 then
          println(s"El MCD de $a y $b es $rb")
        else
          println(s"El MCD de $a y $b es $ra")
      else
        println("No se puede dividir por 0")
  println("Adiós!")
}
