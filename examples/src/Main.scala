package examples

import chisel3._

object Main extends App{
  println("hello world")
  println(chisel3.getVerilogString(new MyFirstStateMachine))
}