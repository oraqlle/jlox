BUILD_DIR := build

default: jlox

# Clean build directory
clean:
	@ rm -rf $(BUILD_DIR)

# Build GenerateAst tool and run
generate_ast:
	@ $(MAKE) -f util/jlox.make DIR=. PACKAGE=tool
	@ java -cp build tool.GenerateAst lox

# Build jlox
jlox: generate_ast
	@ $(MAKE) -f util/jlox.make DIR=. PACKAGE=lox

# Run jlox
run:
	@ java -cp build app.lox.Lox

.PHONY: clean jlox generate_ast run
