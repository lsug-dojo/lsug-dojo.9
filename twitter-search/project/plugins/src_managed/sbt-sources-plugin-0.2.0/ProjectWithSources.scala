/**
 * Copyright (C) 2010, Mikko Peltonen, Jon-Anders Teigen
 * Licensed under the new BSD License.
 * See the LICENSE file for details.
 */

import sbt._

trait ProjectWithSources extends BasicDependencyProject with UpdateSourcesTask {
  val self = this
}