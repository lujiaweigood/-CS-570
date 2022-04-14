//Jiawei Lu
//20007605

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class Treap<E extends Comparable<E> > {
    private static class Node<E>{

        public E data;
        public int priority;
        public Node <E > left;
        public Node <E > right;

        public Node(E data, int priority) {
            if(data==null) {
                throw new IllegalArgumentException();
            }
            else {
                this.left=null;
                this.right=null;
                this.data=data;
                this.priority=priority;
            }
        }

        public Node<E> rotateRight(){

            Node<E> leftValue = this.left;
            Node<E> left = leftValue.right;
            this.left = left;
            leftValue.right = this;
            return leftValue;
        }

        public Node<E> rotateLeft() {
            Node<E> rightValue = this.right;
            Node<E> right = this.right.left;
            this.right = right;
            rightValue.left = this;
            return rightValue;
        }
        public String toString() {
            return this.data.toString();

        }}


    private Random priorityGenerator;
    public Node<E> root;

    public Treap() {
        root = null;
        priorityGenerator = new Random();
    }

    public Treap(long seed) {
        root = null;
        priorityGenerator = new Random(seed);
    }

    public void reheap(Node<E> child, Deque<Node<E>> stack) {
        while (!stack.isEmpty()) {
            Node<E> parent = stack.removeFirst();
            if (parent.priority < child.priority) {
                if (parent.data.compareTo(child.data) > 0) {
                    child = parent.rotateRight();
                }
                else {
                    child = parent.rotateLeft();
                }
                if (!stack.isEmpty()) {
                    if (stack.peekFirst().left == parent) {
                        stack.peekFirst().left = child;
                    }
                    else {
                        stack.peekFirst().right = child;
                    }
                }
                else {
                    this.root = child;
                }}
            else {
                break;
            }}
    }

    public boolean add(E key) {
        return add(key, priorityGenerator.nextInt());
    }

    public boolean add(E key, int priority) {
        if (root == null) {
            root = new Node<E>(key, priority);
            return true;
        }
            Node<E> newNode = new Node<E>(key, priority);
            Deque<Node<E>> stack = new ArrayDeque<Node<E>>();
            Node<E> temp = root;
            while (temp != null) {
                    if (temp.data.compareTo(key) < 0) {
                        stack.addFirst(temp);
                        if (temp.right == null) {
                            temp.right = newNode;
                            reheap(newNode, stack);
                            return true;
                        }
                        else {
                            temp = temp.right;
                        }
                    }
                    else if (temp.data.compareTo(key) > 0){
                        stack.addFirst(temp);
                        if (temp.left == null) {
                            temp.left = newNode;
                            reheap(newNode, stack);
                            return true;
                        }
                        else {
                            temp = temp.left;
                        }}
                    else {
                        return false;
                    }
            }
            return false;
    }

    public boolean delete(E key) {
        if (find(key) == false || root == null) {
            return false;
        } else {
            root = delete(root, key);
            return true;
        }
    }

    private Node<E> delete(Node<E> root, E key){
        if (root== null) {
            return null;
        }
        if (root.data.compareTo(key) > 0) {
            root.left = delete(root.left, key);
        }
        else if (root.data.compareTo(key) < 0) {
            root.right = delete(root.right, key);
        }
        else {
            if (root.left == null && root.right == null)
            {
                root = null;
            }
            else if (root.left != null && root.right != null)
            {
                if (root.left.priority < root.right.priority)
                {
                    root = root.rotateLeft();
                    root.left = delete(root.left, key);
                }
                else {
                    root = root.rotateRight();
                    root.right = delete(root.right, key);
                }
            }
            else {
                Node child = (root.left != null)? root.left: root.right;
                root = child;
            }
        }

        return root;
    }



    private boolean find(Node<E> root,E key) {
        if(root==null) {
            return false;
        }
        if(key.compareTo(root.data) == 0) {
            return true;
        }
        if(key.compareTo(root.data) < 0) {
            return find(root.left,key);
        }
        return find(root.right,key);
    }

    public boolean find(E key) {
        if(key==null) {
            return false;
        }
        return find(root, key);
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        preOrder(root, 1, str);
        return str.toString();
    }
    private void preOrder(Node<E> node, int depth, StringBuilder str) {
        for (int i = 1; i < depth; i++) {
            str.append("  ");
        }
        if (node == null) {
            str.append("null\n");
        }
        else {
            str.append("(key = " + node.toString() + ", ");
            str.append("priority = " + node.priority + ")");
            str.append("\n");
            preOrder(node.left, depth + 1, str);
            preOrder(node.right, depth + 1, str);
        }
    }



    public static void main(String[] args) {
        Treap<Integer> testTree = new Treap<Integer>();

        testTree.add(4,19);
        testTree.add(2,31);
        testTree.add(6,70);
        testTree.add(1,84);
        testTree.add(3,12);
        testTree.add(5,83);
        testTree.add(7,26);

        System.out.println(testTree.toString());

    }

}
