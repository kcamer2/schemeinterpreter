class Let extends Special {
    private static final String text = "let\n";
    @Override
    public String getText() {
        return text;
    }
    @Override
    public void print(Node t, int n, boolean p){
        super.print(t, n, p);
        int newIndentation = n + Constants.INDENTATION;
        PrettyPrintUtils.printSubsequentIndented(t.cdr,newIndentation,p);
    }
    @Override
    public Node eval(Node node, Environment env){
        throw new Error("Not implemented");
    }
}