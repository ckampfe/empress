package controllers

import java.io.File

import play.api.mvc._


/**
 * Created by clark on 4/11/15.
 */
class Slide extends Controller {

  val mdSlides = (new File(System.getProperty("slidesPath"))).listFiles().toList.map(_.getCanonicalPath)

  mdSlides foreach println

  val slides = models.Slide.makeSlides(mdSlides)

  def index = Action {
    Ok(mdSlides.mkString("\n"))
  }

  def show(slideNumber: Long = 1) = Action {
    val previous = if (slideNumber - 1 < 1) None else Some(slideNumber - 1)
    val next = if (slideNumber + 1 > slides.toList.length) None else Some(slideNumber + 1)

    slides.get(slideNumber) match {
      case Some(slide) => Ok(views.html.slide(slide.html, previous, next))
      case None => NotFound(s"Slide $slideNumber not found")
    }
  }
}
