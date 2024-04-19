import mill._
import mill.scalalib._
import mill.scalalib.scalafmt._
import $file.common

object v {
  val scala = "2.13.10"
  val spire = ivy"org.typelevel::spire:0.18.0"
  val evilplot = ivy"io.github.cibotech::evilplot:0.9.0"
  val oslib =  ivy"com.lihaoyi::os-lib:0.9.1"
  val mainargs = ivy"com.lihaoyi::mainargs:0.5.0"
  val chiselCrossVersions = Map(
    "5.0.0" -> (ivy"org.chipsalliance::chisel:5.0.0", ivy"org.chipsalliance:::chisel-plugin:5.0.0"),
    "6.0.0" -> (ivy"org.chipsalliance::chisel:6.0.0", ivy"org.chipsalliance:::chisel-plugin:6.0.0"),
  )
  val chiseltest = Map(
    "5.0.0" -> (ivy"edu.berkeley.cs::chiseltest:5.0.0"),
    "6.0.0" -> (ivy"edu.berkeley.cs::chiseltest:6.0.0"),
  )
  val scalatest = ivy"org.scalatest::scalatest:3.2.0"
  val utest = ivy"com.lihaoyi::utest:latest.integration"
}





trait Playground
  extends common.PlaygroundModule
    with ScalafmtModule
    with Cross.Module[String] {

  override def scalaVersion = T(v.scala)

  override def millSourcePath = os.pwd / "playground"


  def chiselModule = None

  def chiselPluginJar = None

  def chiselIvy = Some(v.chiselCrossVersions(crossValue)._1)

  def chiselPluginIvy = Some(v.chiselCrossVersions(crossValue)._2)
}

trait PlaygroundTest
  extends common.PlaygroundTestModule
    with ScalafmtModule
    with Cross.Module[String]{

  override def scalaVersion = T(v.scala)

  override def millSourcePath = os.pwd / "playground" / "tests"

  def arithmeitcModule = playground(crossValue)

  def scalatestIvy = v.scalatest

  def chiseltestIvy = v.chiseltest(crossValue)

  def utestIvy = v.utest
}



object playground extends Cross[Playground](v.chiselCrossVersions.keys.toSeq)

object playgroundtest extends mill.Cross[PlaygroundTest](v.chiselCrossVersions.keys.toSeq)

object examples
  extends common.PlaygroundModule
    with ScalafmtModule {

  override def scalaVersion = T(v.scala)

  override def millSourcePath = os.pwd / "examples"


  def chiselModule = None

  def chiselPluginJar = None

  def chiselIvy = Some(v.chiselCrossVersions("6.0.0")._1)

  def chiselPluginIvy = Some(v.chiselCrossVersions("6.0.0")._2)

  object tests extends common.PlaygroundTestModule
    with ScalafmtModule {
    override def scalaVersion = T(v.scala)

    def arithmeitcModule = examples

    def scalatestIvy = v.scalatest

    def chiseltestIvy = v.chiseltest("6.0.0")

    def utestIvy = v.utest


  }

}