#!/bin/bash

PROJECT_NAME="balabit/syslog-ng.org"

echo "syslog-ng.org github.io builder:"
echo " - TRAVIS_BUILD_NUMBER=$TRAVIS_BUILD_NUMBER"
echo " - TRAVIS_BRANCH=$TRAVIS_BRANCH"
echo " - TRAVIS_REPO_SLUG=$TRAVIS_REPO_SLUG"
echo " - TRAVIS_PULL_REQUEST=$TRAVIS_PULL_REQUEST"

[[ "$TRAVIS_REPO_SLUG" == "$PROJECT_NAME" ]] || exit 0
[[ "$TRAVIS_PULL_REQUEST" == "false" ]] || exit 0
[[ "$TRAVIS_BRANCH" == "master" ]] || exit 0

echo "Publishing the generated site..."

git config --global user.email "travis@travis-ci.org"
git config --global user.name "travis-ci"

cp -R out "$HOME"/syslog-ng.org

git clone --quiet \
          --branch=gh-pages \
          "https://${GH_TOKEN}@github.com/$PROJECT_NAME" \
          "$HOME"/gh-pages >/dev/null 2>/dev/null

rm -rf "$HOME"/gh-pages/{bundles,images,index.html}
mv "$HOME"/syslog-ng.org/* "$HOME"/gh-pages/

cd "$HOME"/gh-pages/
git add -f {bundles,images,index.html} >/dev/null 2>/dev/null
git commit -m "Publishing syslog-ng.org build #$TRAVIS_BUILD_NUMBER" >/dev/null 2>/dev/null
git push -fq origin gh-pages >/dev/null 2>/dev/null

echo "Finished!"
