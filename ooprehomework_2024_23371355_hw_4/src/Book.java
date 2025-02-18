public class Book {
    private final String name;
    private String magic;

    public Book(String name, String magic) {
        this.name = name;
        this.magic = magic;
    }

    public String getName() {
        return name;
    }

    public String getMagic() {
        return magic;
    }

    @Override
    public Book clone() {
        return new Book(name, magic);
    }

    @SuppressWarnings("checkstyle:LineLength")
    public int getScore() {
        int res = 0;
        for (int l = 0; l < magic.length(); l++) {
            for (int r = l + 1; r <= magic.length(); r++) {
                String s = magic.substring(l, r);
                int len = s.length();
                int num = 0;
                int jing = 0;
                int alp = 0;
                for (int i = 0; i < len; i++) {
                    if (Character.isDigit(s.charAt(i))) {
                        num++;
                    } else if (s.charAt(i) == '#') {
                        jing++;
                    } else if (Character.isAlphabetic(s.charAt(i))) {
                        alp++;
                    }
                }
                char start = s.charAt(0);
                char end = s.charAt(len - 1);
                if (start == '#' && end == '#' && jing == 2 && num > 0 && num >= alp) {
                    res++;
                }
            }
        }
        return res;
    }

    public void addMagic(String magic) {
        this.magic += magic;
    }

    public void subMagic(int a, int b) {
        if (a <= b) {
            if (a >= magic.length()) {
                magic = "";
            } else {
                magic = magic.substring(a, Integer.min(b + 1, magic.length()));
            }
        } else {
            magic = "";
        }
    }

    public boolean contains(String s) {
        return magic.contains(s);
    }

    public boolean equals(Book b) {
        return (magic.equals(b.getMagic()) && name.equals(b.getName()));
    } //override a new "equals()" function
}
