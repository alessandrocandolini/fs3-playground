import cats.effect.IO
import cats.instances.list
import cats.syntax.traverse
import cats.Traverse.ops.toAllTraverseOps
import cats.effect.IOApp
import cats.effect.ExitCode
import FizzBuzz._
import Erratum._
import scala.CanEqual.derived
import org.w3c.dom.NamedNodeMap
import cats.data.EitherT

object Main extends IOApp.Simple:
  val run: IO[Unit] = ioProgram

enum Erratum derives CanEqual:
  case NaN
  case NegativeNumber

def ioProgram: IO[Unit] = input
  .map(pureProgram)
  .value
  .map(display)
  .flatMap(IO.println)

val input: EitherT[IO, Erratum, Int] = EitherT(
  IO.println("insert a positive number:")
    .flatMap(_ => IO.readLine)
    .map(x => parse(x).flatMap(validate))
)

val display: Either[Erratum, List[String]] => String = _.fold(displayErratum, _.mkString("\n"))

val displayErratum: Erratum => String = _.toString

val parse: String => Either[Erratum, Int] = _.toIntOption.toRight[Erratum](NaN)

val validate: Int => Either[Erratum, Int] = x => Either.cond(x > 0, x, NegativeNumber)

val pureProgram: Int => List[String] = n => List.range(0, n).map(fizzbuzz)

enum FizzBuzz derives CanEqual:
  case FizzBuzz
  case Fizz
  case Buzz
  case Other(n: Int)

val fizzbuzzImproved: Int => FizzBuzz =
  case n if n % 3 == 0 && n % 5 == 0 => FizzBuzz.FizzBuzz
  case n if n % 3 == 0 => Fizz
  case n if n % 5 == 0 => Buzz
  case n => Other(n)

val render: FizzBuzz => String        =
  case FizzBuzz.FizzBuzz => "FizzBuzz"
  case Fizz              => "Fizz"
  case Buzz              => "Buzz"
  case Other(n)          => s"$n"

val fizzbuzz: Int => String           = render compose fizzbuzzImproved

val fizzbuzzLegacy: Int => String =
  case n if n % 3 == 0 && n % 5 == 0 => "FizzBuzz"
  case n if n % 3 == 0 => "Fizz"
  case n if n % 5 == 0 => "Buzz"
  case n => s"$n"
