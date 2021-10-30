public class AVLTree{
//    static long leftRotation = 0;
//    static long righttRotation = 0;
    public static long balanceFactor(TreeNode N) {
        if (N != null)
            return treeHeight(N.left) - treeHeight(N.right);
        return 0;
    }
    public static long treeHeight(TreeNode node) {
        if (node != null)
            return node.height;
        return 0;
    }
    public static TreeNode rotateLeft(TreeNode x) {
        TreeNode y = x.right;
        y.leftSize += x.leftSize+1;
        TreeNode temp = y.left;
        if(temp==null){
            x.rightSize=0;
        }
        else{
            x.rightSize = 1+ temp.leftSize+ temp.rightSize;
        }
        y.left = x;
        x.right = temp;
        if (treeHeight(x.left) > treeHeight(x.right)) x.height = treeHeight(x.left) + 1;
        else x.height = treeHeight(x.right) + 1;
        if (treeHeight(y.left) > treeHeight(y.right)) y.height = treeHeight(y.left) + 1;
        else y.height = treeHeight(y.right) + 1;
        return y;
    }
    public static TreeNode rotateRight(TreeNode y) {
        TreeNode x = y.left;
        x.rightSize += y.rightSize + 1;

        TreeNode temp = x.right;
        if (temp==null){
            y.leftSize=0;
        }
        else{
            y.leftSize=1+ temp.leftSize+ temp.rightSize;
        }
        x.right = y;
        y.left = temp;
        if (treeHeight(y.left) > treeHeight(y.right)) y.height = treeHeight(y.left) + 1;
        else y.height = treeHeight(y.right) + 1;
        if (treeHeight(x.left) > treeHeight(x.right)) y.height = treeHeight(x.left) + 1;
        else x.height = treeHeight(x.right) + 1;
        return x;
    }
    public static TreeNode AVLinsert(TreeNode root, long a) {
        if (root == null) return (new TreeNode(a));
        if (a < root.data){
            root.leftSize++;
            root.left = AVLinsert(root.left, a);
        }
        else if (a > root.data){
            root.rightSize++;
            root.right = AVLinsert(root.right, a);
        }
        else return root;
        if (treeHeight(root.left) > treeHeight(root.right)) root.height = treeHeight(root.left) + 1;
        else root.height = treeHeight(root.right) + 1;
        if (balanceFactor(root) < -1 && a > root.right.data){

            return rotateLeft(root);
        }
        if (balanceFactor(root) < -1 && a < root.right.data) {
            root.right = rotateRight(root.right);

            return rotateLeft(root);
        }
        if (balanceFactor(root) > 1 && a < root.left.data){

            return rotateRight(root);
        }
        if (balanceFactor(root) > 1 && a > root.left.data) {
            root.left = rotateLeft(root.left);

            return rotateRight(root);
        }
        return root;
    }
    public static TreeNode AVLdeleteNode(TreeNode root, long a){
        if (root == null) return null;
        if (a < root.data) {
            root.leftSize--;
            root.left = AVLdeleteNode(root.left, a);
        }
        else if (a > root.data) {
            root.rightSize--;
            root.right = AVLdeleteNode(root.right, a);
        }
        else{
            if(root.left == null || root.right == null){
                TreeNode temp = null;
                if (temp == root.left) temp = root.right;
                else temp = root.left;
                if (temp == null){
                    temp = root;
                    root = null;
                }
                else root = temp;
            }
            else{
                TreeNode temp = BinaryTree.smallestNode(root.right);
                root.data = temp.data;
                root.rightSize--;
                root.right = AVLdeleteNode(root.right, temp.data);
            }
        }
        if (root == null)
            return root;
        if (treeHeight(root.left) > treeHeight(root.right)) root.height = treeHeight(root.left) + 1;
        else root.height = treeHeight(root.right) + 1;
        if (balanceFactor(root) > 1 && balanceFactor(root.left) >= 0)
            return rotateRight(root);
        if (balanceFactor(root) > 1 && balanceFactor(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        if (balanceFactor(root) < -1 && balanceFactor(root.right) <= 0)
            return rotateLeft(root);
        if (balanceFactor(root) < -1 && balanceFactor(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }
        return root;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        TreeNode root = null;
        for (int i = 0; i <n ; i++){
            root = BinaryTree.insert(root,sc.nextInt());
        }
        BinaryTree.postOrderTraversal(root);
    }
}

class TreeNode{
    TreeNode left, right;
    int leftSize =0, rightSize=0;
    long data, height = 0;
    public TreeNode(long data){
        this.data = data;
        this.height = 1;
    }
    public TreeNode(long data, TreeNode left, TreeNode right){
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
