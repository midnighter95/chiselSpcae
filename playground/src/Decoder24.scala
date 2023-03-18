package playground

import chisel3._
import chisel3.util.BitPat
import chisel3.util.BitPat.bitPatToUInt
import chisel3.util.experimental.decode._

class Decoder24 extends Module{
  val in = IO(Input(UInt(2.W)))
  val out = IO(Output(UInt(4.W)))
  out := chisel3.util.experimental.decode.decoder(
    input = in, truthTable = TruthTable(
      Seq(
        BitPat("b00") -> BitPat("b0001"),
        BitPat("b01") -> BitPat("b0010"),
        BitPat("b10") -> BitPat("b0100"),
        BitPat("b11") -> BitPat("b1000")
      ),
      BitPat("b1111") // error
    )
  )
}
