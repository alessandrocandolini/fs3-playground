package example2

import munit.{CatsEffectSuite, ScalaCheckEffectSuite}
import org.scalacheck.effect.PropF.*
import fs2.*

class StreamFilterSpec extends CatsEffectSuite with ScalaCheckEffectSuite:

  test(
    "filler should behave as the identity function when the source contains all the events"
  ) {
    forAllF { (s: Stream[Pure, Int]) =>

      val program = s.compile.drain

      program.run(initialState).map { case (MockConsole(out), _) =>
        assertEquals(out, List.empty)
      }

    }
  }
