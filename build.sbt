name := "empress"

version := "0.1.0"

scalaVersion := "2.12.1"

val http4sVersion = "0.15.2a"

libraryDependencies ++= Seq(
  "org.http4s"  %% "http4s-dsl"          % http4sVersion,
  "org.http4s"  %% "http4s-blaze-server" % http4sVersion,
  "org.pegdown"  % "pegdown"             % "1.6.0",
  "com.lihaoyi" %% "ammonite-ops"        % "0.8.1",
  "com.lihaoyi" %% "scalatags"           % "0.6.2"
)
