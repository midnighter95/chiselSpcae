package examples

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
class MuxTester extends AnyFlatSpec with ChiselScalatestTester with Matchers {
  behavior.of("MuxTest")

  it should "test Mux circuits" in {
    test(new MuxLookup) { c =>
      c.in.poke(0.U)
      c.out.expect(1.U)
    }
  }
}
