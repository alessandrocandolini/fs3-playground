package example2

import cats.effect.IO
import fs2.*
import cats.kernel.Order

trait Consecutive[T]:
  def areConsecutive(t1: T, t2: T): Boolean

object Consecutive:
  def apply[T](using t: Consecutive[T]): t.type = t

  def isHole[T: Consecutive](t1: T, t2: T): Boolean = !Consecutive[T].areConsecutive(t1, t2)

object StreamFiller:

  def ioProgram: IO[Unit] = IO.println("stream filler")

  def filler[F[_], T: Consecutive](source: F[T]): Stream[F, T] => Stream[F, T] = ???
