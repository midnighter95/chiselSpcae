package playground

import chisel3._
import chiseltest._
import chiseltest.{ChiselUtestTester, _}
import utest.{TestSuite, _}

object zextTest extends TestSuite with ChiselUtestTester{
  def tests: Tests = Tests {
    test("zextTest pass") {
      def testcase() = {
        testCircuit(new Zext,
          Seq(chiseltest.internal.NoThreadingAnnotation,
            chiseltest.simulator.WriteVcdAnnotation)) {
          dut : Zext =>
            dut.in.poke("b11000".U)
            dut.out.expect(24.S)
        }
      }
      testcase()
    }

  }
}