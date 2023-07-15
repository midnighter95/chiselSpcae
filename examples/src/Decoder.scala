package examples

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

class LUT(table: Seq[Int], inputWidth: Int, outputWidth: Int) extends Module {
  val in = IO(Input(UInt(inputWidth.W)))
  val out = IO(Output(UInt(outputWidth.W)))
  out := chisel3.util.experimental.decode.decoder.espresso(in, TruthTable(table.zipWithIndex.map {
    case (data, addr) => (BitPat(addr.U(inputWidth.W)), BitPat(data.U(outputWidth.W)))
  }, BitPat.N(outputWidth)))
}

class Rec7LUT extends LUT(
  Seq(127, 125, 123, 121, 119, 117, 116, 114,
    112, 110, 109, 107, 105, 104, 102, 100, 99, 97,
    96, 94, 93, 91, 90, 88, 87, 85, 84, 83, 81, 80,
    79, 77, 76, 75, 74, 72, 71, 70, 69, 68, 66, 65,
    64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53,
    52, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42, 41,
    40, 40, 39, 38, 37, 36, 35, 35, 34, 33, 32, 31,
    31, 30, 29, 28, 28, 27, 26, 25, 25, 24, 23, 23,
    22, 21, 21, 20, 19, 19, 18, 17, 17, 16, 15, 15,
    14, 14, 13, 12, 12, 11, 11, 10, 9, 9, 8, 8, 7,
    7, 6, 5, 5, 4, 4, 3, 3, 2, 2, 1, 1, 0),7,7)

class Rsqrt7LUT extends LUT(
  Seq(52, 51, 50, 48, 47, 46, 44, 43,
    42, 41, 40, 39, 38, 36, 35, 34,
    33, 32, 31, 30, 30, 29, 28, 27,
    26, 25, 24, 23, 23, 22, 21, 20,
    19, 19, 18, 17, 16, 16, 15, 14,
    14, 13, 12, 12, 11, 10, 10, 9,
    9, 8, 7, 7, 6, 6, 5, 4,
    4, 3, 3, 2, 2, 1, 1, 0,
    127, 125, 123, 121, 119, 118, 116, 114,
    113, 111, 109, 108, 106, 105, 103, 102,
    100, 99, 97, 96, 95, 93, 92, 91,
    90, 88, 87, 86, 85, 84, 83, 82,
    80, 79, 78, 77, 76, 75, 74, 73,
    72, 71, 70, 70, 69, 68, 67, 66,
    65, 64, 63, 63, 62, 61, 60, 59,
    59, 58, 57, 56, 56, 55, 54, 53),7,7)
