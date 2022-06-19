package tdd;

public class Bank {

    public Money reduce(Expression source, String to) {
        return source.reduce(this, to);
    }

    public int rate(String from, String to){
        return (from.equals("CHF") && to.equals("USD")) ? 2 : 1;
    }

    public void addRate(String chf, String usd, int i) {

    }
}
