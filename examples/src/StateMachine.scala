package examples

import chisel3._
import chisel3.util.BitPat
import chisel3.util.BitPat.bitPatToUInt
import chisel3.util.experimental.decode._

class MyFirstStateMachine extends Module{
  val i = IO(Input(UInt(1.W)))
  val o = IO(Output(UInt(1.W)))

  val state = RegInit(0.U(1.W))
  o:= state

  val myseq = Seq(
    (BitPat("b00"),BitPat("b0")),
    (BitPat("b01"),BitPat("b1")),
    (BitPat("b10"),BitPat("b1")),
    (BitPat("b11"),BitPat("b0"))
  )
  val mytable: TruthTable = TruthTable(myseq,BitPat("b0"))

  state := chisel3.util.experimental.decode
    .decoder(state ## i , mytable)

}

class MySecondStateMachine extends Module{
  val i = IO(Input(UInt(1.W)))
  val o = IO(Output(UInt(1.W)))

  val state = RegInit(0.U(1.W))
  o:= state

  // add "?" for default case
  val mystr = Seq(
    "00->0",
    "01->1",
    "10->1",
    "11->0",
    "?")
    .mkString("\n")
  val mytable: TruthTable = TruthTable.fromString(mystr)

  state := chisel3.util.experimental.decode.decoder(state ## i , mytable)

}

class MyThirdStateMachine extends Module{
  val i = IO(Input(UInt(1.W)))
  val o = IO(Output(UInt(1.W)))

  val state = RegInit(0.U(1.W))
  o:= state

  val Y = "1"
  val N = "0"

  def to(
          stateI: String,
          i: String
        )(stateO: String
        ) = s"$stateI$i->$stateO"

  val mystr = Seq(
    to("0",i = Y)("1"),
    to("0",i = N)("0"),
    to("1",i = Y)("0"),
    to("1",i = N)("1"),
    "?")
    .mkString("\n")
  val mytable: TruthTable = TruthTable.fromString(mystr)

  state := chisel3.util.experimental.decode.decoder(state ## i , mytable)

}


