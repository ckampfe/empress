package empress

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.profiles.pegdown.Extensions
import com.vladsch.flexmark.profiles.pegdown.PegdownOptionsAdapter
import ammonite.ops.Path

import scala.io.Source

class Slide(val html: String)

object Slide {
  val options = PegdownOptionsAdapter.flexmarkOptions(Extensions.ALL)
  val parser = Parser.builder(options).build
  val renderer = HtmlRenderer.builder(options).build

  def apply(fileName: Path) = {
    val markdown = parser.parse(Source.fromFile(fileName.toString).mkString)
    val html = renderer.render(markdown)

    new Slide(html)
  }

  def makeSlides(slideFileNames: Seq[Path]): Vector[Slide] =
    slideFileNames.map(Slide.apply).toVector
}
