package csc4101;// Main.java -- the main program

import java.io.*;

public class Main {
    // Array of token names used for debugging the scanner
    public static final String TokenName[] = {
	"QUOTE",			// '
	"LPAREN",			// (
	"RPAREN",			// )
	"DOT",				// .
	"TRUE",				// #t
	"FALSE",			// #f
	"INT",				// integer constant
	"STRING",			// string constant
	"IDENT"				// identifier
    };

    public static void main (String argv[]) {

        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String filename = argv[0];
        try {
            System.setIn(new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println("failure");
        }
        // create scanner that reads from standard input
        Scanner scanner = new Scanner(System.in);

	if (argv.length > 2) {
	    System.err.println("Usage: java Main " + "[-d]");
	    System.exit(1);
	}
	
	// if commandline option -d is provided, debug the scanner
        if (argv.length > 0 && argv[0].equals("-d")) {
            // debug scanner
            Token tok = scanner.getNextToken();
            while (tok != null) {
		int tt = tok.getType();
		System.out.print(TokenName[tt]);
		if (tt == Token.INT)
		    System.out.println(", intVal = " + tok.getIntVal());
		else if (tt == Token.STRING)
		    System.out.println(", strVal = " + tok.getStrVal());
		else if (tt == Token.IDENT)
		    System.out.println(", name = " + tok.getName());
		else
		    System.out.println();

		tok = scanner.getNextToken();
	    }
	}
	
	// Create parser
	Parser parser = new Parser(scanner);
	Node root;
	
	// Parse and pretty-print each input expression
	root = parser.parseExp();
	//while (root != null) {
        root.print(0);
        System.out.printf("\n");
	    //root = parser.parseExp();
	//}
	System.exit(0);
    }
}
