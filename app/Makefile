run-dist:
	@./build/install/app/bin/app testFile1.json testFile2.json

lint:
	@./gradlew checkstyleMain checkstyleTest

test:
	@./gradlew test

build:
	@./gradlew clean build

report:
	@./gradlew jacocoTestReport

check:
	@./gradlew check
	
asciinema:
	./build/install/app/bin/app -h
	./build/install/app/bin/app ~/java-project-71/app/src/test/resources/file11.json ~/java-project-71/app/src/test/resources/file12.json
	./build/install/app/bin/app -f plain ./src/test/resources/file21.yml ./src/test/resources/file22.yml
	./build/install/app/bin/app -f json ./src/test/resources/file21.yml ./src/test/resources/file22.yml

.PHONY: build
