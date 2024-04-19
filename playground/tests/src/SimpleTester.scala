package playground

import org.scalatest._

import chisel3._
import chisel3.experimental.BundleLiterals._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SimpleTester extends AnyFlatSpec with ChiselScalatestTester with Matchers {
  behavior.of("SimpleTest")

  it should "test simpleTest circuits" in {
    test(new Example) { c =>
      c.in.poke(false.B)
      c.out.expect(false.B)
    }
  }

}