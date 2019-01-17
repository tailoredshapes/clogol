.PHONY : deploy create
TARGET = disconnect
VERSION = 0.1.0-SNAPSHOT
AWS_ACCOUNT_ID = 890764614009

target/${$TARGET-$VERSION}-standalone.jar :
	lein uberjar

deploy : target/${$TARGET-$VERSION}-standalone.jar
	aws lambda update-function-code \
	--function-name clogol-$(TARGET) \
	--zip-file fileb://./target/$(TARGET)-$(VERSION)-standalone.jar

create : target/${$TARGET-$VERSION}-standalone.jar
	aws lambda create-function \
	--function-name clogol-$(TARGET) \
	--handler $(TARGET).core::handler \
	--runtime java8 \
	--memory 512 \
	--timeout 10 \
	--role arn:aws:iam::$(AWS_ACCOUNT_ID):role/lambda_basic_role \
	--zip-file fileb://./target/$(TARGET)-$(VERSION)-standalone.jar
