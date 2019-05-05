package main.java.campionat.tool;


//Am nevoie de aceasta clasa pentru a putea modifica valorile variabilelor nrS,nrD,nrA,nrR,nrV,nrO atunci cand
// le pasez metodei det_punctaj(...) din clasa Utilizare_Colectii

public class Foo {
    private int num;

    public Foo(int num) {
        this.num = num;
    }
    public void adauga(int x){
        this.num+=x;
    }
    public int getNum(){
        return this.num;
    }
}