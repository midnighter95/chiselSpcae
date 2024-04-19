package examples

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ZextTester extends AnyFlatSpec with ChiselScalatestTester with Matchers {
  behavior.of("ZextTester")

  it should "test Zext circuits" in {
    test(new Zext) { dut: Zext =>
      dut.in.poke("b11000".U)
      dut.out.expect(24.S)
    }
  }

}
