abstract class Node {

    public String text = "$";

    // The argument of print(int) is the number of characters to indent.
    // Every subclass of Node must implement print(int).
    void print(int n) {
        System.out.printf(text);
    }

    // The first argument of print(int, boolean) is the number of characters
    // to indent.  It is interpreted the same as for print(int).
    // The second argument is only useful for lists (nodes of classes
    // Cons or Nil).  For all other subclasses of Node, the boolean
    // argument is ignored.  Therefore, print(n,p) defaults to print(n)
    // for all classes other than Cons and Nil.
    // For classes Cons and Nil, print(n,TRUE) means that the open
    // parenthesis was printed already by the caller.
    // Only classes Cons and Nil override print(int,boolean).
    // For correctly indenting special forms, you might need to pass
    // additional information to print.  What additional information
    // you pass is up to you.  If you only need one more bit, you can
    // encode that in the sign bit of n.  If you need additional parameters,
    // make sure that you define the method print in all the appropriate
    // subclasses of Node as well.
    void print(int n, boolean p) {
        print(n);
    }

    protected Node car;
    protected Node cdr;
    // For parsing Cons nodes, for printing trees, and later for
    // evaluating them, we need some helper functions that test
    // the type of a node and that extract some information.

    public boolean isBoolean() {
        return false;
    }  // BooleanLit

    public boolean isNumber() {
        return false;
    }  // IntLit

    public boolean isString() {
        return false;
    }  // StringLit

    public boolean isSymbol() {
        return false;
    }  // Ident

    public boolean isNull() {
        return false;
    }  // nil

    public boolean isPair() {
        return false;
    }  // Cons


    public Node getCar() {
        return car;
    }

    public Node getCdr() {
        return cdr;
    }

    public void setCar(Node a) {
        car = a;
    }

    public void setCdr(Node d) {
        cdr = d;
    }

    public boolean isProcedure() {
        return false;
    }

    public Node apply(Node args,Environment env) throws InvalidApplyException{
        throw new InvalidApplyException();
    }

    public String getName() throws GetNameException{
        throw new GetNameException("Get name called on literal value");
    }

    public abstract Node eval(Node node, Environment env);

}
