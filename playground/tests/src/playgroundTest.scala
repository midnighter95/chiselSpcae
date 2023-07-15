package playground

import chisel3._
import chiseltest._
import chiseltest.{ChiselUtestTester, _}
import utest.{TestSuite, _}

object playgroundTest extends TestSuite with ChiselUtestTester {
  def tests: Tests = Tests {
    test("playgroundTest pass") {
      def testcase() = {
        testCircuit(new PlaygroundModule,
          Seq(chiseltest.internal.NoThreadingAnnotation,
            chiseltest.simulator.WriteVcdAnnotation)) {
          dut: PlaygroundModule =>
            dut.in.poke(3.U)
            dut.out.expect(3.U)
        }
      }

      testcase()
    }

  }
}