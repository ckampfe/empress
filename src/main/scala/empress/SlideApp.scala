package empress

// prevent ammonite from import the `/` function
import ammonite.ops.{$div => _, _}
import cats.effect._
import fs2.{Stream, StreamApp}
import fs2.StreamApp.ExitCode
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.headers.`Content-Type`
import org.http4s.MediaType._
import org.http4s.server.blaze._
import scala.concurrent.ExecutionContext.Implicits.global


object SlideApp extends StreamApp[IO] {
  val mdSlides =
    ammonite.ops.Path(System.getProperty("slidesPath"))
      .|> (ls)
      .|? (_.isFile)
      .|? (_.ext == "md")
      .|> (_.sortBy(_.toString))

  mdSlides.map(_.toString).foreach(println)

  val slides = Slide.makeSlides(mdSlides)

  val slideService = HttpService[IO] {
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
            SlideView.template(slide.html, previous, next).toString,
            `Content-Type`(`text/html`)
          )
         case None =>
           NotFound(s"Slide $slideNumber not found")
      }
   }

  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] = {
    BlazeBuilder[IO]
      .bindHttp(9000, "localhost")
      .mountService(SlideApp.slideService, "/")
      .serve
  }
}
