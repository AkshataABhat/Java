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
class BinaryTree {
    public static TreeNode createTree() {
        TreeNode root = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter data: ");
        int data = sc.nextInt();
        if (data == -1) return null;
        root = new TreeNode(data);
        System.out.println("Enter left for " + data);
        root.left = createTree();
        System.out.println("Enter right for " + data);
        root.right = createTree();
        return root;
    }

    public static void inOrderTraversal(TreeNode root) {
        if (root == null) return;
        else {
            inOrderTraversal(root.left);
            System.out.print(root.data + " ");
            inOrderTraversal(root.right);
        }
    }

    public static void preOrderTraversal(TreeNode root) {
        if (root == null) return;
        else {
            System.out.print(root.data + " ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    public static void postOrderTraversal(TreeNode root) {
        if (root == null) return;
        else {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static TreeNode arrayToTree(long[] nums) {
        return formTree(nums, 0, nums.length - 1);
    }

    public static TreeNode formTree(long[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = (high + low) / 2;
        TreeNode node = new TreeNode((int) nums[mid]);
        node.left = formTree(nums, low, mid - 1);
        node.right = formTree(nums, mid + 1, high);
        return node;
    }

    public static TreeNode preOrderBST(int[] arr, int low, int high) {
        if (low > high) {
            return null;
        }
        TreeNode root = new TreeNode(arr[high]);
        int i;
        for (i = high; i >= low; i--) {
            if (arr[i] < root.data) {
                break;
            }
        }
        root.right = preOrderBST(arr, i + 1, high - 1);
        root.left = preOrderBST(arr, low, i);
        return root;
    }

    public static TreeNode bstFromPreorder(int[] arr) {
        TreeNode root = null;
        root = new TreeNode(arr[0]);
        TreeNode currentNode = null;
        for (int i = 1; i < arr.length; i++) {
            currentNode = root;
            int k = 0;
            while (k == 0) {
                if (arr[i] < currentNode.data && currentNode.left == null) {
                    currentNode.left = new TreeNode(arr[i]);
                    k++;
                } else if (arr[i] > currentNode.data && currentNode.right == null) {
                    currentNode.right = new TreeNode(arr[i]);
                    k++;
                } else if (arr[i] < currentNode.data) {
                    currentNode = currentNode.left;
                } else {
                    currentNode = currentNode.right;
                }
            }
        }
        return root;
    }

    public static BigInteger factorial(long k) {
        BigInteger m = new BigInteger("1");
        for (long i = 2; i <= k; i++) {
            m = m.multiply(BigInteger.valueOf(i));
        }
        return m;
    }

    public static TreeNode buildTree(int[] arr, int low, int high) {
        if (low > high) return null;
        int mid = (high + low) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = buildTree(arr, low, mid - 1);
        root.right = buildTree(arr, mid + 1, high);
        return root;
    }

    public static TreeNode insert(TreeNode root, long a) {
        if (root == null) {
            root = new TreeNode(a);
            return root;
        }
        if (a < root.data) {
            root.left = insert(root.left, a);
        } else if (a > root.data) {
            root.right = insert(root.right, a);
        }
        return root;
    }

    public static TreeNode smallestNode(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static TreeNode largestNode(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    public static TreeNode delete(TreeNode root, long a) {
        if (root == null) return null;
        if (root.data < a) root.right = delete(root.right, a);
        else if (root.data > a) root.left = delete(root.left, a);
        else {
            if (root.right == null)
                return root.left;
            else if (root.left == null)
                return root.right;
            root.data = smallestNode(root.right).data;
            root.right = delete(root.right, root.data);
        }
        return root;
    }

    public static TreeNode search(TreeNode root, long a) {
        if (root == null || root.data == a) return root;
        if (root.data < a) return search(root.right, a);
        return search(root.left, a);
    }

    public static long leftmostSum(TreeNode root) {
        long sum = 0;
        if (root != null) {
            if (root.left != null) {
                sum += root.data;
                leftmostSum(root.left);
            } else if (root.right != null) {
                sum += root.data;
                leftmostSum(root.right);
            }
        }
        return sum;
    }

    public static long rightmostSum(TreeNode root) {
        long sum = 0;
        if (root != null) {
            if (root.right != null) {
                rightmostSum(root.right);
                sum += root.data;
            } else if (root.left != null) {
                rightmostSum(root.left);
                sum += root.data;
            }
        }
        return sum;
    }

    public static long lastNodeSum(TreeNode root) {
        long sum = 0;
        if (root != null) {
            lastNodeSum(root.left);
            if (root.left == null && root.right == null) {
                sum += root.data;
            }
            lastNodeSum(root.right);
        }
        return sum;
    }

    boolean sameParent(TreeNode node, TreeNode a, TreeNode b) {
        if (node == null)
            return false;
        return ((node.left == a && node.right == b) || (node.left == b && node.right == a) || sameParent(node.left, a, b) || sameParent(node.right, a, b));
    }                                                                                                                                                                                                                                                                      // then return true, if not then return false

    int treeLevel(TreeNode root, TreeNode node, int level) {
        if (root == null)
            return 0;
        if (root == node)
            return level;
        int a = treeLevel(root.left, node, level + 1);
        if (a != 0)
            return a;
        return treeLevel(root.right, node, level + 1);
    }

    public static TreeNode preOrderArrayToBST(int[] arr) {
        TreeNode root = null;
        for (int j : arr) {
            root = insert(root, j);
        }
        return root;
    }

    public static TreeNode postOrderArrayToBST(int[] arr) {
        TreeNode root = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            root = insert(root, arr[i]);
        }
        return root;
    }

    TreeNode findLCA (TreeNode root,int node1, int node2){
        if (root == null) return root;
        if (root.data == node1 || root.data == node2)
            return root;
        TreeNode left = findLCA(root.left, node1, node2);
        TreeNode right = findLCA(root.right, node1, node2);
        if (left != null && right != null) return root;
        if (left == null && right == null) return null;
        if (left != null) return left;
        else return right;
    }
}
