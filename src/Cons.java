class Cons extends Node {
    private Special form;
    public String text = Keywords.LPAREN;

    public Special getForm() {
        return form;
    }

    public void setCar(Node a) {
        car = a;
        parseList(a);
    }
    public void setCdr(Node a) {
        if(cdr != null && cdr.getCar() != null){
            cdr.setCar(a);
        }
    }


    /**
     * Distinguishes between a cons node from a paren explicitly typed in the user's code,
     * and an implicit cons node in the tree which is not to be printed.
     */
    private boolean literalParen = false;

    public boolean isPair() {
        return true;
    }


    // parseList() `parses' special forms, constructs an appropriate
    // object of a subclass of Special, and stores a pointer to that
    // object in variable form.  It would be possible to fully parse
    // special forms at this point.  Since this causes complications
    // when using (incorrect) programs as data, it is easiest to let
    // parseList only look at the car for selecting the appropriate
    // object from the Special hierarchy and to leave the rest of
    // parsing up to the interpreter.
    void parseList(Node n) {
        if (isRegular(n)) {
            form = new Regular(n);
        } else {
            form = parseSpecial(n);
        }
    }

    private Special parseSpecial(Node n) {
        if (n == null) return null;
        Ident i = (Ident) n;
        String name = i.getName();
        if (name.equals(Keywords.QUOTE)) {
            return new Quote();
        } else if (name.equals(Keywords.LAMBDA)) {
            return new Lambda();
        } else if (name.equals(Keywords.BEGIN)) {
            return new Begin();
        } else if (name.equals(Keywords.IF)) {
            return new If();
        } else if (name.equals(Keywords.LET)) {
            return new Let();
        } else if (name.equals(Keywords.COND)) {
            return new Cond();
        } else if (name.equals(Keywords.DEFINE)) {
            return new Define();
        } else if (name.equals(Keywords.SET)) {
            return new Set();
        } else if (name.equals(Keywords.ELSE)) {
            return new Else();
        } else {
            return new Regular(n);
        }
    }

    /**
     * Is regular and not an Ident
     */
    private boolean isRegular(Node n) {
        return (n instanceof IntLit ||
                n instanceof StrLit ||
                n instanceof BooleanLit ||
                n instanceof Nil ||
                n instanceof Cons
        );
    }


    public Cons(Node a, Node d) {
        car = a;
        cdr = d;
        literalParen = false;
        parseList(car);
    }

    public Cons(Node a, Node d, boolean b) {
        car = a;
        cdr = d;
        literalParen = b;
        parseList(car);
    }

    void print(int n) {
        print(n, false);
    }

    void print(int n, boolean p) {
        if (form == null || this.cdr == null) return;
        boolean printRightParen = p;

        if (this.literalParen) {
            printRightParen = this.literalParen;
            System.out.printf(this.text);
        }

        if (PrettyPrintUtils.handlesIndentation(form)) {
            form.print(this, n, printRightParen);
            return;
        } else if (this.car instanceof Cons) {
            form.print(this, n, true);
        }//empty list
        else if (this.car instanceof Nil) {
            form.print(this, n, false);
        }//regular
        else {
            form.print(this, n, printRightParen);
        }

        //regular cdr
        if (this.cdr != null && !(cdr instanceof Nil)) {
            this.cdr.print(n, printRightParen);
        }
        //end of a list
        else if (this.cdr instanceof Nil && printRightParen) {
            this.cdr.print(n, printRightParen);
        }

    }


    @Override
    public String toString() {
        return text;
    }

    @Override
    public Node eval(Node node, Environment env){
        //non-procedure in operator position
//        Node firstArg = node.getCar().eval(node.getCar(),env);

//        if (!(firstArg.isProcedure() || firstArg instanceof Special) && this.literalParen) {
//            System.out.printf(InterpreterMessages.NON_FUNCTION_APPLY);
//            return new Nil();
//        }
        return form.eval(this, env);
    }


}
