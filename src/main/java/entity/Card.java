package entity;


public class Card {

    public final int cid;
    public final double balance;
    public final boolean isActive;
    public final int uid;

    public Card(int cid, double balance, boolean isActive, int uid) {
        this.cid = cid;
        this.balance = balance;
        this.isActive = isActive;
        this.uid = uid;
    }
}
