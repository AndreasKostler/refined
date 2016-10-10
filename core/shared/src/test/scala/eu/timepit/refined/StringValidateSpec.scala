package eu.timepit.refined

import eu.timepit.refined.TestUtils._
import eu.timepit.refined.string._
import org.scalacheck.Prop._
import org.scalacheck.Properties

class StringValidateSpec extends Properties("StringValidate") {

  property("EndsWith.isValid") = secure {
    val s = "abcd"
    val suffix: "cd" = "cd"
    isValid[EndsWith[suffix.type]](s) ?= s.endsWith(suffix)
  }

  property("EndsWith.showExpr") = secure {
    showExpr[EndsWith["cd"]]("abcd") ?= """"abcd".endsWith("cd")"""
  }

  property("MatchesRegex.isValid") = forAll { (s: String) =>
    isValid[MatchesRegex[".{2,10}"]](s) ?= s.matches(".{2,10}")
  }

  property("MatchesRegex.showExpr") = secure {
    showExpr[MatchesRegex[".{2,10}"]]("Hello") ?= """"Hello".matches(".{2,10}")"""
  }

  property("Regex.isValid") = secure {
    isValid[Regex](".*")
  }

  property("Regex.showExpr") = secure {
    showExpr[Regex]("(a|b)") ?= """isValidRegex("(a|b)")"""
  }

  property("StartsWith.isValid") = secure {
    val s = "abcd"
    val prefix: "ab" = "ab"
    isValid[StartsWith[prefix.type]](s) ?= s.startsWith(prefix)
  }

  property("StartsWith.showExpr") = secure {
    showExpr[StartsWith["ab"]]("abcd") ?= """"abcd".startsWith("ab")"""
  }

  property("Uri.isValid") = secure {
    isValid[Uri]("/a/b/c")
  }

  property("Uri.showResult") = secure {
    val jvmErr = showResult[Uri](" /a/b/c") ?=
        "Uri predicate failed: Illegal character in path at index 0:  /a/b/c"

    val jsErr = showResult[Uri](" /a/b/c") ?=
        "Uri predicate failed: Malformed URI in  /a/b/c at -1"

    jvmErr || jsErr
  }

  property("Uuid.isValid") = secure {
    isValid[Uuid]("9ecce884-47fe-4ba4-a1bb-1a3d71ed6530")
  }

  property("Uuid.showResult.Passed") = secure {
    showResult[Uuid]("9ecce884-47fe-4ba4-a1bb-1a3d71ed6530") ?= "Uuid predicate passed."
  }

  property("Uuid.showResult.Failed") = secure {
    showResult[Uuid]("whops") ?= "Uuid predicate failed: Invalid UUID string: whops"
  }
}
