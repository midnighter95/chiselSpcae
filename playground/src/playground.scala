package playground

import chisel3._
import chisel3.util._
import chiseltest._
import chiseltest.RawTester.test

object playground extends App{
  println("hello chisel")
}

class PlaygroundModule extends Module{
  val in = IO(Input(UInt(5.W)))
  val out = IO(Output(UInt(5.W)))
  out := in
}






















