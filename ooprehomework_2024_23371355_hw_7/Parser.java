public class Parser {
    private final Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public Expr parseExpr() {
        Expr expr = new Expr();
        expr.addTerm(parseTerm());
        //加入第一个项
        while (!lexer.isEnd() && lexer.getCurToken().getType() == Token.Type.ADD) {
            lexer.nextToken();
            expr.addTerm(parseTerm());
            //循环加入所有的项
        }
        expr.print();
        return expr;
    }

    public Term parseTerm() {
        Term term = new Term();
        term.addFactor(parseFactor());
        while (!lexer.isEnd() && lexer.getCurToken().getType() == Token.Type.MUL) {
            lexer.nextToken();
            term.addFactor(parseFactor());
        }
        term.print();
        return term;
    }

    public Factor parseFactor() {
        Token token = lexer.getCurToken();
        if (token.getType() == Token.Type.NUM) {
            return parseNum();
        } else if (token.getType() == Token.Type.VAR) {
            return parseVar();
        } else {
            lexer.nextToken();
            SubExpr subexpr = parseSubExpr();
            lexer.nextToken();
            return subexpr;
        }
    }

    public SubExpr parseSubExpr() {
        SubExpr subExpression = new SubExpr();
        subExpression.addTerm(parseSubTerm());
        while (!lexer.isEnd() && lexer.getCurToken().getType() == Token.Type.ADD) {
            lexer.nextToken();
            subExpression.addTerm(parseSubTerm());
        }
        int last = subExpression.toString().length() - 1;
        System.out.println("SubExpr " + subExpression.toString().substring(1, last));
        return subExpression;
    }

    public SubTerm parseSubTerm() {
        SubTerm subTerm = new SubTerm();
        subTerm.addFactor(parseNum());
        while (!lexer.isEnd() && lexer.getCurToken().getType() == Token.Type.MUL) {
            lexer.nextToken();
            subTerm.addFactor(parseNum());
        }
        subTerm.print();
        return subTerm;
    }

    public Num parseNum() {
        Num num;
        Token token = lexer.getCurToken();
        lexer.nextToken();
        num = new Num(Integer.parseInt(token.getContent()));
        num.print();
        return num;
    }

    public Var parseVar() {
        Token token = lexer.getCurToken();
        lexer.nextToken();
        Var var = new Var(token.getContent());
        var.print();
        return var;
    }
}
