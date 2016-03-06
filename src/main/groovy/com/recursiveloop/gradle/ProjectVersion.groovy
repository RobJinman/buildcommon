package com.recursiveloop.gradle;


class ProjectVersion {
  Integer major
  Integer minor
  Integer build
  Boolean release

  ProjectVersion(Integer major, Integer minor) {
    this.major = major
    this.minor = minor
    this.build = 0
    this.release = Boolean.FALSE
  }

  ProjectVersion(Integer major, Integer minor, Integer build) {
    this(major, minor)
    this.build = build
  }

  ProjectVersion(Integer major, Integer minor, Integer build, Boolean release) {
    this(major, minor, build)
    this.release = release
  }

  @Override
  String toString() {
    "$major.$minor.$build${release ? '' : '-SNAPSHOT'}"
  }
}
