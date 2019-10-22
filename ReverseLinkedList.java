import edu.princeton.cs.algs4.StdOut;

/*
 * Reverse Linked List Algorithms and Demonstrator
 * October 22, 2019
 * Elven Shum
 */

public class ReverseLinkedList {

    //ITERATIVE reverse List extension
    //Implementation at bottom of LinkedList.java, for organizational purposes.

    //RECURSIVE reverse List extension
    public static void reverseListRecur(Node curr, LinkedList l) {
        /* If last node mark it head*/
        if (curr.next == null) {
            //flop final node to be the new origin
            l.origin = curr;
        } else {
            Node nextNode = curr.next;
            reverseListRecur(nextNode, l);
            //pointer's next node pointer becomes itself
            curr.next.next = curr;
            curr.next = null;
        }
    }

    public static void main (String[] args){
        LinkedList listIterate = new LinkedList(new Node(3));
        listIterate.addItem(4);
        listIterate.addItem(5);
        listIterate.addItem(6);
        listIterate.addItem(7);

        listIterate.printList();
        listIterate.reverseListIter();
        listIterate.printList();

        LinkedList listRecur = new LinkedList(new Node(10));
        listRecur.addItem(11);
        listRecur.addItem(12);
        listRecur.addItem(13);
        listRecur.addItem(14);
        listRecur.addItem(15);
        listRecur.addItem(16);

        listRecur.printList();
        reverseListRecur(listRecur.origin, listRecur);
        listRecur.printList();
    }
}
