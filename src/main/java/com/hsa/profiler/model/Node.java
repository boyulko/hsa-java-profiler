package com.hsa.profiler.model;

public class Node {

  public int element;
  public int h;  //for height
  public Node leftChild;
  public Node rightChild;

  public Node(int element) {
    leftChild = null;
    rightChild = null;
    this.element = element;
    h = 0;
  }

}
