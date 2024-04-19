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
}





class Playground extends ChiselModule
  with Cross.Module[String] { m =>

  override def scalaVersion = T(v.scala)

  override def millSourcePath = os.pwd / "arithmetic"

  def chiselModule = None

  def chiselPluginJar = None

  def chiselIvy = Some(v.chiselCrossVersions(crossValue)._1)

  def chiselPluginIvy = Some(v.chiselCrossVersions(crossValue)._2)

}

object playground extends Cross[Playground](v.chiselCrossVersions.keys.toSeq)

