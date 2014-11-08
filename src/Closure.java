// Closure.java -- the data structure for function closures

// Class Closure is used to represent the value of lambda expressions.
// It consists of the lambda expression itself, together with the
// environment in which the lambda expression was evaluated.

// The method apply() takes the environment out of the closure,
// adds a new frame for the function call, defines bindings for the
// parameters with the argument values in the new frame, and evaluates
// the function body.

class Closure extends Node {
    private Node fun;		// a lambda expression
    private Environment env;	// the environment in which the function
				// was defined

    public Closure(Node f, Environment e)	{ fun = f;  env = e; }

    public Node getFun()		{ return fun; }
    public Environment getEnv()		{ return env; }

    // TODO: The method isProcedure() should be defined in
    // class Node to return false.
    public boolean isProcedure()	{ return true; }

    public void print(int n) {
	// there got to be a more efficient way to print n spaces
	for (int i = 0; i < n; i++) {
        System.out.print(' ');
    }
	System.out.println("#{Procedure");
	fun.print(n+3);
	for (int i = 0; i < n; i++) {
        System.out.print(' ');
    }
	System.out.println('}');
    }

    //TODO: apply for Builtin and closure
    public Node apply (Node args) {
	return null;
    }

    @Override
    public Node eval(Node node, Environment env){
        throw new Error(DebugMessages.CANNOT_EVAL);
    }
}