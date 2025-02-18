import java.util.ArrayList;

public class Expr {
    private final ArrayList<Term> terms = new ArrayList<>();

    public void addTerm(Term term) {
        terms.add(term);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Term term : terms) {
            str.append(term.toString());
            str.append("+");
        }
        return str.substring(0, str.length() - 1);
        //去掉末尾的"+"
    }

    public void print() {
        System.out.println("Expr " + this);
    }

}
