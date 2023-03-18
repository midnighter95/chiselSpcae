package playground

import chisel3._
import chisel3.util._
import chiseltest._
import chiseltest.RawTester.test

object playground extends App{

  println(getVerilogString(new Passthrough))

  test(new Passthrough()) { c =>
    c.in.poke(0.U) // Set our input to value 0
    c.out.expect(0.U) // Assert that the output correctly has 0
    c.in.poke(1.U) // Set our input to value 1
    c.out.expect(1.U) // Assert that the output correctly has 1
    c.in.poke(2.U) // Set our input to value 2
    c.out.expect(2.U) // Assert that the output correctly has 2
  }
  println("SUCCESS!!") // Scala Code: if we get here, our tests passed!

}























