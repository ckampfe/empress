package controllers

import ammonite.ops._
import play.api.mvc._
import play.twirl.api.Html


/**
 * Created by clark on 4/11/15.
 */
class Slide extends Controller {

  val mdSlides =
    (System.getProperty("slidesPath"))
      .|> (Path.apply)
      .|> (ls)
      .|? (_.isFile)
      .|? (_.ext == "md")
      .|> (_.sortBy(_.toString))

  mdSlides.map(_.toString).foreach(println)

  val slides = models.Slide.makeSlides(mdSlides)

  def index = Action {
    Ok(mdSlides.mkString("\n"))
  }

  def show(slideNumber: Int = 0) = Action {
    val previous = if (slideNumber - 1 < 0) None else Some(slideNumber - 1)
    val next = if (slideNumber + 1 > slides.toList.length - 1) None else Some(slideNumber + 1)

    slides.lift(slideNumber) match {
      case Some(slide) =>
        Ok(
          Html(
            empress.views.Slide.template(slide.html, previous, next).toString
          )
        )
       case None => NotFound(s"Slide $slideNumber not found")
    }
  }
}
