package playground

import chisel3._
import chisel3.util._
import chiseltest._

import chiseltest.{ChiselUtestTester, _}
import utest.{TestSuite, _}

import scala.util.Random

object exampleTest extends TestSuite with ChiselUtestTester{
  def tests: Tests = Tests {
    test("example pass") {
      def testcase() = {
        testCircuit(new Passthrough,
          Seq(chiseltest.internal.NoThreadingAnnotation,
            chiseltest.simulator.WriteVcdAnnotation)) {
          dut : Passthrough =>
            dut.in.poke(1.U)
            dut.out.expect(1.U)
        }
      }
      testcase()
    }

  }
}