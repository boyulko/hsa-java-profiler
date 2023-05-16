package com.hsa.profiler.service;

import com.hsa.profiler.model.Node;
import java.util.concurrent.ThreadLocalRandom;

public class AvlTreeService {

  private Node rootNode;

  public AvlTreeService() {
    rootNode = null;
  }

  public void insertElement(int element) {
    rootNode = insertElement(ThreadLocalRandom.current().nextInt(0, 50000), rootNode);
  }

  public boolean searchElement(int element) {
    return searchElement(rootNode, ThreadLocalRandom.current().nextInt(0, 50000));
  }

  private int getHeight(Node node) {
    return node == null ? -1 : node.h;
  }

  private int getMaxHeight(int leftNodeHeight, int rightNodeHeight) {
    return leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;
  }


  private Node insertElement(int element, Node node) {

    if (node == null) {
      node = new Node(element);
    } else if (element < node.element) {
      node.leftChild = insertElement(element, node.leftChild);
      if (getHeight(node.leftChild) - getHeight(node.rightChild) == 2) {
        if (element < node.leftChild.element) {
          node = rotateWithLeftChild(node);
        } else {
          node = doubleWithLeftChild(node);
        }
      }
    } else if (element > node.element) {
      node.rightChild = insertElement(element, node.rightChild);
      if (getHeight(node.rightChild) - getHeight(node.leftChild) == 2) {
        if (element > node.rightChild.element) {
          node = rotateWithRightChild(node);
        } else {
          node = doubleWithRightChild(node);
        }
      }
    }
    // if the element is already present in the tree, we will do nothing

    node.h = getMaxHeight(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;

    return node;

  }

  private Node rotateWithLeftChild(Node node2) {
    Node node1 = node2.leftChild;
    node2.leftChild = node1.rightChild;
    node1.rightChild = node2;
    node2.h = getMaxHeight(getHeight(node2.leftChild), getHeight(node2.rightChild)) + 1;
    node1.h = getMaxHeight(getHeight(node1.leftChild), node2.h) + 1;
    return node1;
  }

  private Node rotateWithRightChild(Node node1) {
    Node node2 = node1.rightChild;
    node1.rightChild = node2.leftChild;
    node2.leftChild = node1;
    node1.h = getMaxHeight(getHeight(node1.leftChild), getHeight(node1.rightChild)) + 1;
    node2.h = getMaxHeight(getHeight(node2.rightChild), node1.h) + 1;
    return node2;
  }

  private Node doubleWithLeftChild(Node node3) {
    node3.leftChild = rotateWithRightChild(node3.leftChild);
    return rotateWithLeftChild(node3);
  }

  private Node doubleWithRightChild(Node node1) {
    node1.rightChild = rotateWithLeftChild(node1.rightChild);
    return rotateWithRightChild(node1);
  }

  private boolean searchElement(Node head, int element) {
    boolean check = false;
    while ((head != null) && !check) {
      int headElement = head.element;
      if (element < headElement) {
        head = head.leftChild;
      } else if (element > headElement) {
        head = head.rightChild;
      } else {
        check = true;
        break;
      }
      check = searchElement(head, element);
    }
    return check;
  }

}
