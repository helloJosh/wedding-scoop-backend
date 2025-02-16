#!/bin/bash
set -e
PARENT_MODULE_DIR=$(pwd)

# 하위 모듈 경로 설정
AUTH_MODULE="$PARENT_MODULE_DIR/auth"
WEB_MODULE="$PARENT_MODULE_DIR/scoop"

# 상위 모듈의 타겟 디렉토리 설정
PARENT_TARGET_DIR="$PARENT_MODULE_DIR/ztarget"

# Maven clean package 실행
echo "Running Maven clean package..."
mvn clean package

# 상위 모듈의 target 디렉토리 생성
echo "Creating target directory in parent module..."

# 하위 모듈 JAR 파일 복사
echo "Copying JAR files to parent module target directory..."

if [ -f "$AUTH_MODULE/target/"*.jar ]; then
  cp "$AUTH_MODULE/target/"*.jar "$PARENT_TARGET_DIR/auth"
  echo "Copied consumer JAR from $AUTH_MODULE/target."
else
  echo "No JAR found in $AUTH_MODULE/target."
fi

if [ -f "$WEB_MODULE/target/"*.jar ]; then
  cp "$WEB_MODULE/target/"*.jar "$PARENT_TARGET_DIR/scoop"
  echo "Copied producer JAR from $WEB_MODULE/target."
else
  echo "No JAR found in $WEB_MODULE/target."
fi

echo "All JAR files copied to $PARENT_TARGET_DIR."