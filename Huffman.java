import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author geekyadi
 */

class Node {

  String letr = "";
  int freq = 0, data = 0;
  Node left = null, right = null;
}
class comp implements Comparator<Node> {

  @Override
  public int compare(Node o1, Node o2) {
    if (o1.freq > o2.freq) {
      return 1;
    } else if (o1.freq < o2.freq) {
      return -1;
    } else {
      return 0;
    }
  }
}

public class Huffman {

  public static void print_list(List li) {
    Iterator<Node> it = li.iterator();
    while (it.hasNext()) {
      Node n = it.next();
      System.out.print(n.freq + " ");
    }
    System.out.println();
  }


  public static Node make_huffmann_tree(List li) {
    li.sort(new comp());
    Node temp = null;
    Iterator it = li.iterator();
    while (true) {
      temp = new Node();
      Node a = new Node(), b = new Node();
      a = null;
      b = null;
      a = (Node) it.next();
      if (it.hasNext()) {
        b = (Node) it.next();
      }
    
      if (b != null) {
        temp.freq = a.freq + b.freq;
        a.data = 0;
        b.data = 1; 
        temp.left = a;
        temp.right = b;
        li.remove(0);
        li.remove(0); 
        li.add(temp); 
        
      }
      
      li.sort(new comp());
      it = li.iterator(); 
      if (li.size() == 1) {
        return (Node) it.next();
      } 
    }
  }

  public static void dfs(Node n, String ch) {
    Stack<Node> st = new Stack(); 
    int freq = n.freq; 
    find_path_and_encode(st, n, ch, freq);
  }

  public static void print_path(Stack<Node> st) {
    for (int i = 0; i < st.size(); i++) {
      System.out.print(st.elementAt(i).data + " ");
    }
  }

  public static void find_path_and_encode(
    Stack<Node> st,
    Node root,
    String s,
    int f
  ) {
    
    if (root != null) {
      if (root.freq != f) {
        st.push(root);
      } 
      if (root.letr.equals(s)) {
        print_path(st);
        return;
      } 
      find_path_and_encode(st, root.left, s, f);
      find_path_and_encode(st, root.right, s, f);

      st.pop();
    }
  }

  public static void main(String args[]) {
    List<Node> li = new LinkedList<>();
    try (Scanner in = new Scanner(System.in)) {
        System.out.println("Enter number of distinct letters ");
        int n = in.nextInt();
        String s[] = new String[n];
        System.out.print("Enter letters with its frequncy to encode\n");
        for (int i = 0; i < n; i++) {
          Node a = new Node();
          System.out.print("Enter letter : ");
          a.letr = in.next();
          s[i] = a.letr;
          System.out.print("Enter frequncy : ");
          a.freq = in.nextInt();
          System.out.println();
          li.add(a);
        }
        Node root = new Node();
        root = make_huffmann_tree(li);
        System.out.println("Letter\t\tEncoded Form");
        for (int i = 0; i < n; i++) {
          System.out.print(s[i] + "\t\t");
          dfs(root, s[i]);
          System.out.println();
        }
    }
  }
}

/* output 
Enter number of distinct letters 
4
Enter letters with its frequncy to encode
Enter letter : a
Enter frequncy : 6

Enter letter : b
Enter frequncy : 9

Enter letter : c
Enter frequncy : 10

Enter letter : d
Enter frequncy : 16

Letter          Encoded Form
a               1 1 0 
b               1 1 1 
c               1 0 

*/
