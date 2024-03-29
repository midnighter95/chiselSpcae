import mill._
import mill.scalalib._
import mill.scalalib.publish._
import coursier.maven.MavenRepository

import $ivy.`de.tototec::de.tobiasroeser.mill.vcs.version::0.4.0`
import de.tobiasroeser.mill.vcs.version.VcsVersion

trait ArithmeticModule extends ScalaModule with PublishModule {
  // SNAPSHOT of Chisel is published to the SONATYPE
  override def repositoriesTask = T.task { super.repositoriesTask() ++ Seq(
    MavenRepository("https://oss.sonatype.org/content/repositories/snapshots"),
    MavenRepository("https://oss.sonatype.org/content/repositories/releases")
  ) }

  // override to build from source, see the usage of chipsalliance/playground
  def chisel3Module: Option[PublishModule] = None
  
  // override to build from source, see the usage of chipsalliance/playground
  def chisel3PluginJar: T[Option[PathRef]] = T {
    None
  }
  
  // override to build from source, see the usage of chipsalliance/playground
  def chiseltestModule: Option[PublishModule] = None

  // Use SNAPSHOT chisel by default, downstream users should override this for their own project versions.
  def chisel3IvyDep: T[Option[Dep]] = None

  def chisel3PluginIvyDep: T[Option[Dep]] = None

  def chiseltestIvyDep: T[Option[Dep]] = None

  // dependencies below is not managed by the Chisel team.
  def upickle: T[Dep]
  def osLib: T[Dep]
  def spire: T[Dep]
  def evilplot: T[Dep]
  def bc: T[Dep]  
  def utest: T[Dep]
  
  override def moduleDeps = Seq() ++ chisel3Module ++ chiseltestModule

  override def scalacPluginClasspath = T {
    super.scalacPluginClasspath() ++ chisel3PluginJar()
  }

  override def scalacPluginIvyDeps = T {
    Agg() ++ chisel3PluginIvyDep()
  }

  override def scalacOptions = T {
    super.scalacOptions() ++ chisel3PluginJar().map(path => s"-Xplugin:${path.path}")
  }

  override def ivyDeps = T { 
    Agg(
      upickle(),
      osLib(),
      spire(),
      evilplot()) ++ 
      chisel3IvyDep()
  }

  def publishVersion = de.tobiasroeser.mill.vcs.version.VcsVersion.vcsState().format()

  def pomSettings = PomSettings(
    description = artifactName(),
    organization = "me.jiuyang",
    url = "https://jiuyang.me",
    licenses = Seq(License.`Apache-2.0`),
    versionControl = VersionControl.github("sequencer", "arithmetic"),
    developers = Seq(
      Developer("sequencer", "Jiuyang Liu", "https://jiuyang.me/")
    )
  )
}
