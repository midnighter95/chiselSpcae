package playground

import chisel3._

class Passthrough extends Module {
  val in = IO(Input(UInt(2.W)))
  val out = IO(Output(UInt(2.W)))
  out := in
}
