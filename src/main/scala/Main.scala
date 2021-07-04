import cats.effect.IO
import cats.instances.list
import cats.Traverse.nonInheritedOps.toTraverseOps
import cats.effect.IOApp
import cats.effect.ExitCode

object Main extends IOApp.Simple:
  val run = program

val program: IO[Unit] = msgs.traverse(myPrint).void

val msg1 = "Hello world"
val msg2 = "I was compiled by Scala 3. :)"

val msgs = List(msg1, msg2)

val myPrint: String => IO[Unit] = s => IO(println(s))
