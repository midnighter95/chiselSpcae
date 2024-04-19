package examples

import chisel3._
import chisel3.util._

class MuxLookup extends Module{
  val in = IO(Input(UInt(1.W)))
  val out = IO(Output(UInt(1.W)))

  out := MuxLookup(in,
    0.U(1.W),
  )(Seq(
    0.U -> Table.T.asUInt,
    1.U -> Table.F.asUInt
  ))
}


object Table extends ChiselEnum {
  val F = Value(0x0.U)
  val T  = Value(0x1.U)
}
