## sbt project compiled with Dotty

A disappointingly simple payment categoriser that takes in CSV files with details of one payment on each line and adds a category to each line.  It does this by looking for substrings - this is the simplest thing that works but also means that it works with different column ordering for different banks' formats.

## Usage

This is a normal sbt project, you can compile code with `sbt compile` and run it
with `sbt run`, `sbt console` will start a Dotty REPL.

For more information on the sbt-dotty plugin, see the
[dotty-example-project](https://github.com/lampepfl/dotty-example-project/blob/master/README.md).
