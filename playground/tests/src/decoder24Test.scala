package playground

import chisel3._
import chiseltest.{ChiselUtestTester, _}
import utest.{TestSuite, _}

object decoder24Test extends TestSuite with ChiselUtestTester{
  def tests: Tests = Tests {
    test("24decoder pass") {
      def testcase() = {
        testCircuit(new Decoder24,
          Seq(chiseltest.internal.NoThreadingAnnotation,
            chiseltest.simulator.WriteVcdAnnotation)) {
          dut : Decoder24 =>
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

  }
}