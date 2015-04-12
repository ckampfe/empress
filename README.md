Empress
=======

Fucking simple presentations.

## Why?

- You want to write your slides in Markdown, because like plain text, Markdown is classy, portable, and patriotic as fuck
- You don't want to mess around with smelly Keynote, Powerpoint, Prezi or Reveal.js.

## How?

- Write slides in Markdown files.
- Name the files like this to indicate presentation order:

```
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

- Use the `.jar` to present like so:

```
java -DslidesPath="/Users/clark/code/scala_talks/slides" \
     -DpresentationName="Scala And You" \
     -jar $PATH_TO_JAR
```

- If you haven't built the jar, build it like this:

```
$ git clone $THE_REPO
$ cd empress
$ activator
[empress] $ assembly
```

The `.jar` file will be in `target/scala-2.11`

- If you don't have a JRE, I feel sorry for you.
