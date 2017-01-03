package empress

// prevent ammonite from import the `/` function
import ammonite.ops.{$div => _, _}
import org.http4s._
import org.http4s.dsl._
import org.http4s.headers.`Content-Type`
import org.http4s.MediaType._

object SlideApp {
  val mdSlides =
    (System.getProperty("slidesPath"))
      .|> (ammonite.ops.Path.apply)
      .|> (ls)
      .|? (_.isFile)
      .|? (_.ext == "md")
      .|> (_.sortBy(_.toString))

  mdSlides.map(_.toString).foreach(println)

  val slides = Slide.makeSlides(mdSlides)

  val slideService = HttpService {
    case GET -> Root / "slides" =>
      Ok(mdSlides.mkString("\n"))

    case GET -> Root / "slides" / IntVar(slideNumber) =>
      val previous =
        if (slideNumber - 1 < 0)
          None
        else
          Some(slideNumber - 1)

      val next =
        if (slideNumber + 1 > slides.toList.length - 1)
          None
        else
          Some(slideNumber + 1)

      slides.lift(slideNumber) match {
        case Some(slide) =>
          Ok(
            SlideView.template(slide.html, previous, next).toString
          ).withContentType(Some(`Content-Type`(`text/html`)))
         case None =>
           NotFound(s"Slide $slideNumber not found")
      }
   }
}
