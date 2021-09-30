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

object Main extends IOApp.Simple:
  def run: IO[Unit] = ioProgram
