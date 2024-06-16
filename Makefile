BUILD_DIR := build

default: jlox

clean:
	@ rm -rf $(BUILD_DIR)
	@ rm -rf gen

jlox:
	@ $(MAKE) -f util/jlox.make DIR=. PACKAGE=lox

run:
	@ java -cp build app.lox.Lox

.PHONY: clean jlox
