package empress

import scalatags.Text.all._
import scalatags.Text.tags2.nav

object SlideView {
  def template(
    slide: String,
    prev: Option[Int],
    next: Option[Int],
    presentationName: String
  ) =
    "<!DOCTYPE html>" + html(
      head(
        scalatags.Text.tags2.title()(presentationName),
        link(rel := "stylesheet", href := "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"),
        link(rel := "stylesheet", href := "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css"),
        link(rel := "stylesheet", href := "//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.5/styles/github.min.css"),
        meta(charset := "utf-8"),
        meta(httpEquiv := "X-UA-Compatible", content := "IE=edge"),
        meta(name := "viewport", content := "width=device-width, initial-scale=1")
      ),
      body(
        style := "padding-top: 50px;",
        nav(cls := "navbar navbar-default navbar-fixed-top")(
          div(
            cls := "container"
          )(
            div(cls := "navbar-header")(
              button(
                `type` := "button",
                cls := "navbar-toggle collapsed",
                data.toggle := "collapse",
                data.target := "#navbar",
                aria.expanded := "false",
                aria.controls := "navbar"
              )(
                span(cls := "icon-bar"),
                span(cls := "icon-bar"),
                span(cls := "icon-bar")
              ),
              a(
                cls := "navbar-brand",
                href := "#"
              )(
                presentationName
              )
            ),
            div(
              id := "navbar",
              cls := "navbar-collapse collapse"
            )(
              ul(cls := "nav navbar-nav navbar-right")(
                SlideView.slide(prev, next)
              )
            )
          )
        ),
        div(cls := "container")(raw(slide.toString)),
        script(src := "https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"),
        script(src := "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"),
        script(src := "//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.5/highlight.min.js")
      )
    )

  def slide(prev: Option[Int], next: Option[Int]): Seq[scalatags.Text.Modifier] =
    (prev, next) match {
      case (Some(p), Some(n)) => {
        Seq(
          li(
            a(href := s"/slides/$p")("Previous")
          ),
          li(
            a(href := s"/slides/$n")("Next")
          )
        )
      }
      case (Some(p), None) => {
        Seq(
          li(
            a(href := s"/slides/$p")("Previous")
          )
        )
      }
      case (None, Some(n)) => {
        Seq(
          li(
            a(href := s"/slides/$n")("Next")
          )
        )
      }
      case (None, None) => Seq() // display nothing!
    }
}
