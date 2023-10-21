import java.util.Scanner;

public class AVLTree {
    public static long balanceFactor(TreeNode N) {
        if (N != null) {
            return treeHeight(N.left) - treeHeight(N.right);
        }
        return 0;
    }

    public static long treeHeight(TreeNode node) {
        if (node != null) {
            return node.height;
        }
        return 0;
    }

    public static TreeNode rotateLeft(TreeNode x) {
        TreeNode y = x.right;
        x.rightSize = y.leftSize;
        y.leftSize = x.leftSize + x.rightSize + 1;
        x.right = y.left;
        y.left = x;
        x.height = Math.max(treeHeight(x.left), treeHeight(x.right)) + 1;
        y.height = Math.max(treeHeight(y.left), treeHeight(y.right)) + 1;
        return y;
    }

    public static TreeNode rotateRight(TreeNode y) {
        TreeNode x = y.left;
        y.leftSize = x.rightSize;
        x.rightSize = y.leftSize + y.rightSize + 1;
        y.left = x.right;
        x.right = y;
        y.height = Math.max(treeHeight(y.left), treeHeight(y.right)) + 1;
        x.height = Math.max(treeHeight(x.left), treeHeight(x.right)) + 1;
        return x;
    }

    public static TreeNode AVLinsert(TreeNode root, long a) {
        if (root == null) {
            return new TreeNode(a);
        }
        if (a < root.data) {
            root.leftSize++;
            root.left = AVLinsert(root.left, a);
        } else if (a > root.data) {
            root.rightSize++;
            root.right = AVLinsert(root.right, a);
        } else {
            return root;
        }

        root.height = Math.max(treeHeight(root.left), treeHeight(root.right)) + 1;

        int balance = (int) balanceFactor(root);

        if (balance < -1 && a > root.right.data) {
            return rotateLeft(root);
        }
        if (balance < -1 && a < root.right.data) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }
        if (balance > 1 && a < root.left.data) {
            return rotateRight(root);
        }
        if (balance > 1 && a > root.left.data) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        return root;
    }

    public static TreeNode AVLdeleteNode(TreeNode root, long a) {
        if (root == null) {
            return null;
        }
        if (a < root.data) {
            root.leftSize--;
            root.left = AVLdeleteNode(root.left, a);
        } else if (a > root.data) {
            root.rightSize--;
            root.right = AVLdeleteNode(root.right, a);
        } else {
            if (root.left == null || root.right == null) {
                TreeNode temp = (root.left == null) ? root.right : root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                TreeNode temp = smallestNode(root.right);
                root.data = temp.data;
                root.rightSize--;
                root.right = AVLdeleteNode(root.right, temp.data);
            }
        }

        if (root == null) {
            return root;
        }

        root.height = Math.max(treeHeight(root.left), treeHeight(root.right)) + 1;
        int balance = (int) balanceFactor(root);

        if (balance > 1 && balanceFactor(root.left) >= 0) {
            return rotateRight(root);
        }
        if (balance > 1 && balanceFactor(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        if (balance < -1 && balanceFactor(root.right) <= 0) {
            return rotateLeft(root);
        }
        if (balance < -1 && balanceFactor(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        TreeNode root = null;
        for (int i = 0; i < n; i++) {
            root = AVLinsert(root, sc.nextInt());
        }
        postOrderTraversal(root);
    }

    static class TreeNode {
        TreeNode left, right;
        long data, height = 1;
        int leftSize = 0, rightSize = 0;

        TreeNode(long data) {
            this.data = data;
        }
    }
    
    public static TreeNode smallestNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    public static void postOrderTraversal(TreeNode root) {
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.data + " ");
        }
    }
}
