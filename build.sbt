name := "empress"

version := "0.2.0"

scalaVersion := "2.12.5"

val http4sVersion = "0.18.9"

libraryDependencies ++= Seq(
  "org.http4s"    %% "http4s-dsl"          % http4sVersion,
  "org.http4s"    %% "http4s-blaze-server" % http4sVersion,
  "org.pegdown"    % "pegdown"             % "1.6.0",
  "com.lihaoyi"   %% "ammonite-ops"        % "1.1.0",
  "com.lihaoyi"   %% "scalatags"           % "0.6.7",
  "org.typelevel" %% "cats-effect"         % "1.0.0-RC"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xfuture",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ywarn-unused-import",
  "-Ypartial-unification"
)
