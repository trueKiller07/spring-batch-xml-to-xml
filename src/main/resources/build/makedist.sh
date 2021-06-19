#!/bin/sh
#
# Creates a zip file out of the build components so that Jenkins
# can copy to the dev appserver for integration tests.
#

cd /opt/internal/ma/jenkins/workspace/$1/source/target/
mkdir -p dist/bin
mkdir -p dist/config
cp europeangateway-api.jar dist/
cp europeangateway-api-exe.jar dist/

# Copy the default props files
cp config/application.properties dist/config/
cp config/application-testing.properties dist/config/application-default.properties
cp config/logback-spring.xml dist/config/

# Add scripts
cp bin/* dist/bin/
cd dist

# Zip it up so that Jenkins can copy it to the dev integration server
zip -r europeangateway-dist.zip *