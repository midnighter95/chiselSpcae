package examples

import chisel3._
import chiseltest._

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
class DecoderTester extends AnyFlatSpec with ChiselScalatestTester with Matchers {
  behavior.of("DecoderTester")

  it should "test Decoder24 circuits with VCD" in {
    test(new Decoder24).withAnnotations(Seq(WriteVcdAnnotation)) { dut: Decoder24 =>
      dut.in.poke(0.U)
      dut.out.expect(1.U)
      dut.in.poke(1.U)
      dut.out.expect(2.U)
      dut.in.poke(2.U)
      dut.out.expect(4.U)
      dut.in.poke(3.U)
      dut.out.expect(8.U)
    }
  }

  it should "test Rec7LUT circuits" in {
    test(new Rec7LUT) { dut: Rec7LUT =>
      dut.in.poke(0.U)
      dut.out.expect(127.U)
      dut.in.poke(1.U)
      dut.out.expect(125.U)
    }
  }
}
