package com.recursiveloop.gradle;


class ProjectVersion {
  Integer major
  Integer minor
  Integer patch
  Boolean release

  ProjectVersion(Integer major, Integer minor, Integer patch) {
    this.major = major
    this.minor = minor
    this.patch = patch
    this.release = Boolean.FALSE
  }

  ProjectVersion(Integer major, Integer minor, Integer patch, Boolean release) {
    this(major, minor, patch)
    this.release = release
  }

  @Override
  String toString() {
    "$major.$minor.$patch${release ? '' : '-SNAPSHOT'}"
  }
}

