package models

import org.pegdown.{Extensions, PegDownProcessor}

import scala.io.Source

/**
 * Created by clark on 4/11/15.
 */
class Slide(
  val markdown: String,
  markdownProcessor: PegDownProcessor
) {
  val html = markdownProcessor.markdownToHtml(markdown)
}

object Slide {
  val markdownProcessor = new PegDownProcessor(Extensions.ALL) // YOLO

  def apply(fileName: String) = {
    val markdownText = Source.fromFile(fileName).mkString

    new Slide(
      markdown          = markdownText,
      markdownProcessor = markdownProcessor
    )
  }

  def makeSlides(slideFileNames: List[String]): Map[Long, Slide] = {
    // (null :: slideFileNames.map(Slide.apply) ::: List(null)).sliding(3,1)

    (1L to slideFileNames.length zip slideFileNames.map(Slide.apply)).toMap
  }
}
