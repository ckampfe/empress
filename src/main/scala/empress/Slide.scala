package empress

import org.pegdown.{Extensions, PegDownProcessor}
import ammonite.ops.Path

import scala.io.Source

class Slide(val html: String)

object Slide {
  val markdownProcessor = new PegDownProcessor(Extensions.ALL) // YOLO

  def apply(fileName: Path) = {
    val markdown = Source.fromFile(fileName.toString).mkString
    val html = markdownProcessor.markdownToHtml(markdown)

    new Slide(html)
  }

  def makeSlides(slideFileNames: Seq[Path]): Vector[Slide] =
    slideFileNames.map(Slide.apply).toVector
}