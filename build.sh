#!/bin/bash
#
# Copyright 2024 Timo Stülten (pionira GmbH)
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

if [ "$1" == "-q" ] || [ "$1" == "--quiet" ]; then
  MVN_OPTIONS=--quiet
fi

# Setup for macOS
if [ "Darwin" == "$(uname -s)" ]; then
  GRAALVM_HOME="$(/usr/libexec/java_home -v 21 | grep graal)"
  export GRAALVM_HOME
  export JAVA_HOME=${GRAALVM_HOME}
fi

if ("${JAVA_HOME}"/bin/javac -version >/dev/null); then
  echo "Use Java from ${JAVA_HOME}"
else
  echo "Error calling javac. Abort"
  exit 1
fi

echo "Build Uber-JAR"
# shellcheck disable=SC2086
mvn ${MVN_OPTIONS} clean package -Dquarkus.package.type=uber-jar
