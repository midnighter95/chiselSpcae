import mill._
import mill.scalalib._
import mill.scalalib.publish._
import coursier.maven.MavenRepository

trait HasChisel
  extends ScalaModule {
  // Define these for building chisel from source
  def chiselModule: Option[ScalaModule]

  override def moduleDeps = super.moduleDeps ++ chiselModule

  def chiselPluginJar: T[Option[PathRef]]

  override def scalacOptions = T(super.scalacOptions() ++ chiselPluginJar().map(path => s"-Xplugin:${path.path}"))

  override def scalacPluginClasspath: T[Agg[PathRef]] = T(super.scalacPluginClasspath() ++ chiselPluginJar())

  // Define these for building chisel from ivy
  def chiselIvy: Option[Dep]

  override def ivyDeps = T(super.ivyDeps() ++ chiselIvy)

  def chiselPluginIvy: Option[Dep]

  override def scalacPluginIvyDeps: T[Agg[Dep]] = T(super.scalacPluginIvyDeps() ++ chiselPluginIvy.map(Agg(_)).getOrElse(Agg.empty[Dep]))
}

trait PlaygroundModule
  extends ScalaModule
    with HasChisel {


  override def ivyDeps = T(super.ivyDeps() )
}

trait PlaygroundTestModule
  extends TestModule
    with HasChisel
    with TestModule.ScalaTest {

  def arithmeitcModule: PlaygroundModule

  def chiselModule = None

  def chiselPluginJar: T[Option[PathRef]] = T(arithmeitcModule.chiselPluginJar())

  def chiselIvy: Option[Dep] = arithmeitcModule.chiselIvy

  def chiselPluginIvy: Option[Dep] = arithmeitcModule.chiselPluginIvy

  def scalatestIvy: Dep

  def chiseltestIvy: Dep

  def utestIvy: Dep

  override def defaultCommandName() = "test"

  override def ivyDeps = T(
    super.ivyDeps() ++ Agg(
      scalatestIvy,
      chiseltestIvy,
      utestIvy
    )
  )

  override def moduleDeps = super.moduleDeps ++ Some(arithmeitcModule)
}