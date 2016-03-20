package controllers

import play.api.mvc._

class Application extends Controller {
  def index = Action {
    Redirect(routes.Slide.show(0))
  }
}
