package com.njain.datastructure;

class Men {
  public int number;
}

public class Test{
  public void doIt(int i , Men p){
    i = 5;
    p.number = 8;
  }

  public static void main(String args[]){
    int x = 0;
    Men p = new Men();
    new Test().doIt(x, p);
    System.out.println(x + " " + p.number);
  }
}