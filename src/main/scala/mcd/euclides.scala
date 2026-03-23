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
  var continuar = true    // variable para definir si el programa continúa
  while continuar do    // ciclo que dura hasta que el usuario ingresa 0 en el primer número
    println("Ingrese el primer número (0 para salir):")
    val a = readInt()    // primer número
    var ra = a    //variable mutable para aplicar Euclides
    if a == 0 then    // si es 0, termina el programa
      continuar = false
    else
      println("Ingrese el segundo número:")
      var b = readInt()    // segundo valor
      var rb = b    // variable mutable para aplicar Euclides
      if ra < 0 then  // si la variable es negativa se multiplica por -1 para dejarla positiva
        ra = ra * -1
      if rb < 0 then
        rb = rb * -1
      if b != 0 then    // si b es 0, no se puede obtener el MCD. Si no es 0, se realiza el algoritmo de Euclides
        while ra != 0 && rb != 0 do
          if rb <= ra then
            ra = ra % rb
          else
            rb = rb % ra
        // cuando llega alguna variable mutable a 0, se retorna la otra variable y se obtiene el MCD
        if ra == 0 then
          println(s"El MCD de $a y $b es $rb")
        else
          println(s"El MCD de $a y $b es $ra")
      else
        println("No se puede dividir por 0")
  println("Adiós!")
}