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
import fs2._
import utils.CommandIOAppSimple
import com.monovore.decline.Opts
import cli.Args
import cli.Example

object Main
    extends CommandIOAppSimple(
      name = "fs3",
      header = "fs3"
    ):
  def run: Opts[IO[Unit]] = Args.readArgs.map(program)

  val program: Args => IO[Unit] = args =>
    args.example match {
      case Example.FillStream => IO.println("fill stream")
      case Example.FizzBuzz   => ioProgram
    }
