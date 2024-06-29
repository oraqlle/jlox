package app.lox;

import java.util.List;

class LoxFunction implements LoxCallable {
    private final Stmt.Function declaration;
    private final Environment closure;
    private final boolean isInitializer;

    LoxFunction(Stmt.Function declaration, Environment closure, boolean isInitializer) {
        this.declaration = declaration;
        this.closure = closure;
        this.isInitializer = isInitializer;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        Environment environment = new Environment(closure);

        if (declaration.params != null) {
            for (int i = 0; i < declaration.params.size(); i++) {
                environment.define(declaration.params.get(i).lexeme, arguments.get(i));
            }
        }

        try {
            interpreter.executeBlock(declaration.body, environment);
        } catch (Return returnValue) {
            if (isInitializer) {
                return closure.getAt(0, "this");
            }

            return returnValue.value;
        }

        if (isInitializer) {
            return closure.getAt(0, "this");
        }

        return null;
    }

    @Override
    public int arity() {
        return declaration.params.size();
    }

    public boolean isGetter() {
        return declaration.params == null;
    }

    LoxFunction bind(LoxInstance instance) {
        Environment environment = new Environment(closure);
        environment.define("this", instance);

        return new LoxFunction(declaration, environment, isInitializer);
    }

    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme + ">";
    }
}

