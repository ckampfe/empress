package empress

// prevent ammonite from import the `/` function
import ammonite.ops.{$div => _, _}
import ammonite.ops.Path
import cats.effect._
import fs2.{Stream, StreamApp}
import fs2.StreamApp.ExitCode
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.headers.`Content-Type`
import org.http4s.MediaType._
import org.http4s.server.blaze._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.immutable.List

object SlideApp extends StreamApp[IO] {
  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] = {
  val slidesPath = args.headOption.getOrElse("")
  val presentationName = args.lift(1).getOrElse("A great talk")

  val p = Path(slidesPath)
  val mdSlides: Seq[Path] =
    ls.!(p)
      .|? (_.isFile)
      .|? (_.ext == "md")
      .|> (_.sortBy(_.toString))

  mdSlides.map(_.toString).foreach(println)

  val slides: Vector[Slide] = Slide.makeSlides(mdSlides)
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
              SlideView.template(slide.html, previous, next, presentationName).toString,
              `Content-Type`(`text/html`)
            )
           case None =>
             NotFound(s"Slide $slideNumber not found")
        }
    }

    BlazeBuilder[IO]
      .bindHttp(9000, "localhost")
      .mountService(slideService, "/")
      .serve
  }
}
