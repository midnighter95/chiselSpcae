package playground

import chisel3._
import chisel3.util._

object playground extends App{
  println("This is your playground.")
}

class Example extends Module{
  val in = IO(Input(UInt(5.W)))
  val out = IO(Output(UInt(5.W)))
  out := in
}






















