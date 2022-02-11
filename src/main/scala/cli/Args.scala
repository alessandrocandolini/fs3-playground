package cli

import scala.CanEqual.derived
import com.monovore.decline.Command
import com.monovore.decline.Opts
import cats.implicits.*

enum Verbose derives CanEqual:
  case Verbose
  case Quiet

enum Example derives CanEqual:
  case FizzBuzz
  case FillStream

case class Args(
  verbose: Verbose,
  example: Example
) derives CanEqual

object Args:

  val defaultVerbose: Verbose = Verbose.Verbose

  val verboseOpts: Opts[Verbose] = (Opts
    .flag("verbose", help = "Verbose output")
    .map(_ => Verbose.Verbose) <+>
    Opts
      .flag("quiet", help = "Quiet output")
      .map(_ => Verbose.Quiet))
    .withDefault(defaultVerbose)

  val exampleOpts: Opts[Example] = Opts
    .flag("fizzbuzz", help = "fizzbuzz example")
    .map(_ => Example.FizzBuzz) <+>
    Opts
      .flag("fill", help = "fill stream")
      .map(_ => Example.FillStream)

  val readArgs: Opts[Args] = (verboseOpts, exampleOpts).mapN(Args.apply)
