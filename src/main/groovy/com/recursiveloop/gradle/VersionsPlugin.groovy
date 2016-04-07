package com.recursiveloop.gradle;

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project


class VersionsPluginExtension {
  File versionFile
}


class VersionsPlugin implements Plugin<Project> {
  void apply(Project project) {
    project.extensions.create("versions", VersionsPluginExtension)

    project.afterEvaluate {
      project.version = readVersion(project)

      if (System.env.BUILD_NUMBER != null) {
        project.version.build = System.env.BUILD_NUMBER.toInteger()
      }
      if (System.env.SOURCE_BUILD_NUMBER != null) {
        project.version.build = System.env.SOURCE_BUILD_NUMBER.toInteger()
      }
    }

    project.task("printVersion") {
      group = "Versioning"

      doLast {
        logger.quiet "Version: $project.version"
      }
    }
  }

  ProjectVersion readVersion(Project project) {
    if (!project.versions.versionFile.exists()) {
      throw new GradleException("Required version file does not exist:"
        + " $project.versions.versionFile.canonicalPath")
    }

    Properties props = new Properties()

    project.versions.versionFile.withInputStream { stream ->
      props.load(stream)
    }

    new ProjectVersion(props.major.toInteger(), props.minor.toInteger(),
      0, props.release.toBoolean())
  }
}
