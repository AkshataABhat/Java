import java.util.*;

public class LinkedList {
     Node head;
     static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            next = null;
        }
    }
  
  
    public void insertAtFront(int a){
        Node newNode = new Node(a);
        newNode.next = head;
        head = newNode;
    }
  
  
    public void insertAtEnd(int a){
        Node newNode = new Node(a);
        if (head == null){
            head = newNode;
            head.next = null;
            return;
        }
        Node currentNode = head;
        while(currentNode.next != null){
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
    }
  
  
    public void insertAfter(int a, Node previousNode){
        Node newNode = new Node(a);
        if (previousNode == null){
            return;
        }
        newNode.next = previousNode.next;
        previousNode.next = newNode;
    }
  
  
    public Node insertAt(int a, int position) {
        if (head == null && position > 0) {
            return null;
        }
        if (position == 0) {
            Node newHead = new Node(a);
            newHead.next = head;
            return newHead;
        } else {
            head.next = insertAt(a, position - 1);
            return head;
        }
    }
  
  
    public void deleteNode(int a){           
        Node currentNode = head;              
        Node previousNode = null;
        if(currentNode!= null && currentNode.data == a){
            head = currentNode.next;
            return;
        }
        while(currentNode != null && currentNode.data != a){
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        if(currentNode == null){
            return;
        }
        else{
            previousNode.next = currentNode.next;
        }
    }
  
  
    public void deleteNodeAtPosition(int a){
        Node currentNode = head;
        int i = 0;
        if (head == null){
            return;
        }
        if (a==0){
            head= currentNode.next;
            return;
        }
        while (currentNode != null && i<=a){
            if (i==a-1){
                if (currentNode.next== null){
                    return;
                }
                currentNode.next = currentNode.next.next;
                return;
            }
            currentNode = currentNode.next;
            i++;
        }
        if (currentNode == null){
            return;
        }
    }
  
  
    public void deleteList(){
        head = null;
    }
  
    public int length(){
        Node currentNode = head;
        if (head==null){
            return 0;
        }
        int i = 1;
        while (currentNode.next != null){
            currentNode=currentNode.next;
            i++;
        }
        return i;
    }
  
  
    public int lengthRecursive(Node node){
        if (node == null){
            return 0;
        }
        else{
            return 1+lengthRecursive(node.next);
        }
    }
  
  
    public boolean search(int a){
        Node currentNode = head;
        if (head==null){
            return false;
        }
        while(currentNode!=null){
            if (currentNode.data == a){
                return true;
            }
            currentNode=currentNode.next;
        }
        return false;
    }
  
  public static void main(String[] args) {
        LinkedList llist = new LinkedList();
        Scanner sc = new Scanner(System.in);
        while(true){
            int a = sc.nextInt();
            if (a != -1){
                llist.insertAtEnd(a);
            }
            else{
                break;
            }
        }
        llist.printLinkedList();

//        Node sh = llist.insertAt(10,1);
//        printList(llist.head);
    }
  
}
