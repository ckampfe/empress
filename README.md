Empress
=======

Fucking simple presentations.

## Why?

- You want to write your slides in Markdown, because like plain text, Markdown is classy, portable, and patriotic as fuck
- You don't want to mess around with Keynote, Powerpoint, Prezi or Reveal.js like a peasant

## How?

- Write slides in Markdown files.
- Put the slides in a directory and name them like this to indicate presentation order:

```sh
1-getting_started.md
2-practical.md
3-exploratory.md
4-big_projects.md
5-class.md
6-value_object.md
7-object.md
8-immutability.md
9-pattern_matching.md
```

- Use the `.jar` to start the presentation server:

```sh
$ java -DslidesPath=$PATH_TO_SLIDES \
       -DpresentationName=$TITLE_OF_PRESENTATION \
       -jar $PATH_TO_JAR
```

- Give the people what they want:
```sh
$ open http://localhost:9000
```

- If you haven't built the jar, build it like this:

```sh
$ git clone $THE_REPO
$ cd empress
$ activator
[empress] $ assembly
```

The `.jar` file will be in `target/scala-2.11`

- If you don't have a JRE, I feel sorry for you.
