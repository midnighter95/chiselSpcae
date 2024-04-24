import mill._
import mill.scalalib._
import mill.scalalib.scalafmt._
import $file.common

object v {
  val scala = "2.13.10"
  val oslib =  ivy"com.lihaoyi::os-lib:0.9.1"
  val chiselCrossVersions = Map(
    "5.0.0" -> (ivy"org.chipsalliance::chisel:5.0.0", ivy"org.chipsalliance:::chisel-plugin:5.0.0"),
    "6.0.0" -> (ivy"org.chipsalliance::chisel:6.0.0", ivy"org.chipsalliance:::chisel-plugin:6.0.0"),
  )
  val chiseltest = Map(
    "5.0.0" -> (ivy"edu.berkeley.cs::chiseltest:5.0.0"),
    "6.0.0" -> (ivy"edu.berkeley.cs::chiseltest:6.0.0"),
  )
  val scalatest = ivy"org.scalatest::scalatest:3.2.0"
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

  def mainModule = playground(crossValue)

  def scalatestIvy = v.scalatest

  def chiseltestIvy = v.chiseltest(crossValue)
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

    def mainModule = examples

    def scalatestIvy = v.scalatest

    def chiseltestIvy = v.chiseltest("6.0.0")

  }

}