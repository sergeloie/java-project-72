build:
	make -C app build

run-dist:
	make -C app run-dist

test:
	make -C app test

report:
	make -C app report

lint:
	make -C app lint

check:
	make -C app check

.PHONY: build
