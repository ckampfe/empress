package empress

import org.http4s.server.{Server, ServerApp}
import org.http4s.server.blaze._

object Main extends ServerApp {
  override def server(args: List[String]) = {
    BlazeBuilder
      .bindHttp(9000, "localhost")
      .mountService(SlideApp.slideService, "/")
      .start
  }
}
