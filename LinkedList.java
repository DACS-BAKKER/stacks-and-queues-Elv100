import edu.princeton.cs.algs4.StdOut;

/*
 * LinkedList
 * October 22, 2019
 * Elven Shum
 */
public class LinkedList<Item>{
    Node<Item> origin;

    public LinkedList() {
        origin = null;
    }

    public LinkedList(Node first) {
        origin = first;
    }


    public void addItem(Item data) {
        if (origin == null) {
            origin = new Node<Item>(data);
        } else {
            Node<Item> temp = origin;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node<Item>(data);
        }
    }

    public int getItemPosition(Item data) {
        if (origin == null) {
            return -1;
        }
        Node<Item> currentNode = origin;
        int counter = 0;
        while (!(currentNode.data == data)) {
            if (currentNode.next == null) {
                return -1;
            }
            currentNode = currentNode.next;
            counter++;
        }
        return counter;


    }

    public void insert(Item data, int place) {
        Node<Item> currentNode = origin;
        //if start, then place node immediately at beginning
        if (place == 0) {
            origin = new Node<Item>(data);
            origin.next = currentNode;
        } else {
            int counter = 0;
            while (currentNode != null) {
                if (counter == place - 1) {
                    Node<Item> temp = currentNode.next;
                    temp.next = new Node<Item>(data);
                    temp.next.next = temp;

                } else {
                    currentNode = currentNode.next;
                }
                counter++;
            }

        }

    }

    public void remove(int place) {
        Node<Item> currentNode = origin;
        int counter = 0;
        while (counter != place - 1) {
            if (currentNode.next == null) {
                return;
            }
            currentNode = currentNode.next;
            counter++;
        }
        currentNode.next = currentNode.next.next;
    }

    public void printList() {
       Node<Item> currentNode = origin;
       while(currentNode.next != null){
           StdOut.print(currentNode.data + " ");
           currentNode = currentNode.next;
       }
        StdOut.print(currentNode.data + " \n");

    }

    //ITERATIVE reverse List extension
    public void reverseListIter(){
        Node prevNode = null;
        Node currentNode = origin;
        Node nextNode;
        while(currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = prevNode;
            prevNode = currentNode;
            currentNode = nextNode;
        }
        origin = prevNode;
    }
}