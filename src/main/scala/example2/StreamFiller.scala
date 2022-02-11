package example2

import cats.effect.IO

object StreamFiller:

  def ioProgram: IO[Unit] = IO.println("stream filler")
