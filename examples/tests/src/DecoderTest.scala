package examples

import chisel3._
import chiseltest._
import chiseltest.{ChiselUtestTester, _}
import utest.{TestSuite, _}

object DecoderTest extends TestSuite with ChiselUtestTester {
  def tests: Tests = Tests {
    test("Decoder24 pass") {
      def testcase() = {
        testCircuit(new Decoder24,
          Seq(chiseltest.internal.NoThreadingAnnotation,
            chiseltest.simulator.WriteVcdAnnotation)) {
          dut: Decoder24 =>
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
      testcase()
    }
    test("rec7LUT pass") {
      def testcase() = {
        testCircuit(new Rec7LUT,
          Seq(chiseltest.internal.NoThreadingAnnotation,
            chiseltest.simulator.WriteVcdAnnotation)) {
          dut: Rec7LUT =>
            dut.in.poke(0.U)
            dut.out.expect(127.U)
            dut.in.poke(1.U)
            dut.out.expect(125.U)
        }
      }
      testcase()
    }
  }
}
