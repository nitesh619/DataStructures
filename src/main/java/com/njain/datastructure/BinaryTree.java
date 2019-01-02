package com.njain.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {

  private int maxDepth = 0;

  // Root - left - right
  public List<Integer> perOrder(TreeNode root) {
    List<Integer> preorder = new ArrayList<Integer>();
    List<TreeNode> stack = new ArrayList<TreeNode>();
    stack.add(root);
    int top = 0;

    while (!stack.isEmpty()) {
      TreeNode node = stack.remove(top);
      top--;
      if (node == null) {
        continue;
      }
      preorder.add(node.val);
      if (node.right != null) {
        top++;
        stack.add(top, node.right);
      }
      if (node.left != null) {
        top++;
        stack.add(top, node.left);
      }
    }
    return preorder;
  }

  public boolean isValidBST(TreeNode root) {
    return isValid(root, null, null);
  }

  public boolean isValid(TreeNode root, Integer min, Integer max) {
    if (root == null) {
      return true;
    }
    if (min != null && root.val <= min) {
      return false;
    }
    if (max != null && root.val >= max) {
      return false;
    }

    return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
  }

  public List<Integer> postOrder(TreeNode root) {
    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> output = new LinkedList<>();
    if (root == null) {
      return output;
    }

    stack.add(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.removeLast();
      output.addFirst(node.val);
      if (node.left != null) {
        stack.add(node.left);
      }
      if (node.right != null) {
        stack.add(node.right);
      }
    }
    return output;
  }

  public List<Integer> InOrder(TreeNode root) {
    TreeNode currNode = root;
    List<Integer> inorder = new ArrayList<Integer>();
    List<TreeNode> stack = new ArrayList<TreeNode>();
    stack.add(root);
    int top = 0;

    while (currNode != null && !stack.isEmpty()) {
      if (currNode.left != null) {
        top++;
        stack.add(top, currNode.left);
        currNode = currNode.left;
      } else {
        TreeNode node = stack.remove(top);
        top--;
        inorder.add(node.val);
        if (node.right != null) {
          currNode = node.right;
          top++;
          stack.add(top, node.right);
        }
      }
    }
    return inorder;
  }

  public void maxDepth(TreeNode root, int depth) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      maxDepth = Math.max(maxDepth, depth);
    }
    maxDepth(root.left, depth + 1);
    maxDepth(root.right, depth + 1);
  }

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftA = maxDepth(root.left);
    int rightA = maxDepth(root.right);

    return Math.max(leftA, rightA) + 1;
  }

  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return false;
    }
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();
    postOrderTraversal(root.left, left);
    postOrderTraversal(root.right, right);

    System.out.println(Arrays.toString(left.toArray()));
    System.out.println(Arrays.toString(right.toArray()));
    return left.containsAll(right) && left.size() == right.size();
  }

  private void rightOrderTraversal(TreeNode root, List<Integer> answer) {
    if (root == null) {
      answer.add(null);
      return;
    }
    rightOrderTraversal(root.right, answer);  // traverse right subtree
    rightOrderTraversal(root.left, answer);   // traverse left subtree
    answer.add(root.val);                    // visit the root
  }

  private void postOrderTraversal(TreeNode root, List<Integer> answer) {
    if (root == null) {
      answer.add(null);
      return;
    }
    rightOrderTraversal(root.left, answer);   // traverse left subtree
    rightOrderTraversal(root.right, answer);  // traverse right subtree
    answer.add(root.val);                    // visit the root
  }

  private void inOrderTraversal(TreeNode root, List<Integer> answer) {
    if (root == null) {
      return;
    }
    inOrderTraversal(root.left, answer);   // traverse left subtree
    answer.add(root.val);                    // visit the root
    inOrderTraversal(root.right, answer);  // traverse right subtree
  }

  public TreeNode inorderSuccessor(TreeNode root, TreeNode item, TreeNode previous) {
    if (root == null || item == null) {
      return null;
    }
    inorderSuccessor(root.left, item, root);   // traverse left subtree
    if (previous.val == item.val) {
      return root;
    }
    inorderSuccessor(root.right, item, root);  // traverse right subtree
    return null;
  }


  public List<List<Integer>> levelOrder(TreeNode root) {
    Queue<TreeNode> treeNodeQueue = new LinkedList<TreeNode>();
    List<List<Integer>> levelOrder = new ArrayList<List<Integer>>();
    if (root == null) {
      return levelOrder;
    }
    treeNodeQueue.offer(root);
    while (!treeNodeQueue.isEmpty()) {
      int level = treeNodeQueue.size();
      List<Integer> inner = new ArrayList<Integer>();
      for (int i = 0; i < level; i++) {
        TreeNode peek = treeNodeQueue.peek();
        if (peek.left != null) {
          treeNodeQueue.offer(peek.left);
        }
        if (peek.right != null) {
          treeNodeQueue.offer(peek.right);
        }
        inner.add(treeNodeQueue.poll().val);
      }
      levelOrder.add(inner);
    }
    return levelOrder;
  }

  /**
   * Definition for a binary tree node.
   */
  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

}
