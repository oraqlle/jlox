
# jlox

Implementation of Bob Nystrom's Lox language in Java following his book [Crafting Interpreters](https://craftinginterpreters.com/)

## Building

In order to use jlox you will need the JDK and `make`. To build simply run:

```sh
make
```

This will output the compiled Java to `build/lox`.

## Usage

jlox can be used as a interpreter of a single Lox script or as a REPL. To run you can
either execute the full java command or use the supplementry Bash script

```sh
# Manually
java -cp build lox.Lox [script]

# Bash script
# chmod +x ./jlox first
./jlox [script]
```


