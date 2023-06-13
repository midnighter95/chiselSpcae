package playground

import chisel3._
import chisel3.util._

class Zext extends Module {
  val in = IO(Input(UInt(5.W)))
  val out = IO(Output(SInt(6.W)))
  out := in.zext
}