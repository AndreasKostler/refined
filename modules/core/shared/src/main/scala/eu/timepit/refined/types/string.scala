package eu.timepit.refined.types

import eu.timepit.refined.api.Refined
import eu.timepit.refined.collection.NonEmpty

/** Module for `String` refined types. */
object string extends StringTypes

trait StringTypes {

  /** A `String` that is not empty. */
  type NonEmptyString = String Refined NonEmpty
}
