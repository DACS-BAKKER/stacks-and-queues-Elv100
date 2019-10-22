import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Josephus Problem w/ TestClient
 * October 22, 2019
 * Elven Shum
 */


public class Josephus {
    public static int solveJosephus(int total, int skip){
        int counter = 0;
        //makes queue with Total people
        Queue<Integer> q = new Queue<Integer>();
        for(int i = 0; i < total; i++){
            q.enqueue(i);
        }
        //handle's the killing
        while(q.size() > 1){
            if(counter == skip){
                q.dequeue();
                counter = 0;
            }
            else {
                q.enqueue(q.dequeue());
                counter++;
            }
        }
        return q.dequeue();
    }

    public static void main(String[] args){
        StdOut.println("Elven's Josephus Solver \n");
        StdOut.println("Total People:");
        int total = StdIn.readInt();
        StdOut.println("Skip num:");
        int skip = StdIn.readInt();
        StdOut.println("After the massacre, the person left was number:");
        StdOut.println(solveJosephus(total, skip));
    }
}
