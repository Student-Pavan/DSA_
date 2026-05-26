1class Node {
2    int val;
3    Node next;
4
5    Node(int val) {
6        this.val = val;
7        this.next = null;
8    }
9}
10
11class MyLinkedList {
12    Node head;
13    int size;
14
15    public MyLinkedList() {
16        head = null;
17        size = 0;
18    }
19    
20    public int get(int index) {
21        if (index < 0 || index >= size) return -1;
22        Node temp = head;
23        for (int i = 0; i < index; i++) temp = temp.next;
24        return temp.val;
25    }
26    
27    public void addAtHead(int val) {
28        Node newnode = new Node(val);
29        newnode.next = head;
30        head = newnode;
31        size++;
32    }
33    
34    public void addAtTail(int val) {
35        Node newnode = new Node(val);
36        if (head == null) {
37            head = newnode;
38            size++;
39            return;
40        }
41        Node temp = head;
42        while (temp.next != null) temp = temp.next;
43        temp.next = newnode;
44        size++;
45    }
46    
47    public void addAtIndex(int index, int val) {
48        if (index < 0 || index > size) return;
49        if (index == 0) {
50            addAtHead(val);
51            return;
52        }
53        Node temp = head;
54        for (int i = 0; i < index - 1; i++) temp = temp.next;
55        Node newnode = new Node(val);
56        newnode.next = temp.next;
57        temp.next = newnode;
58        size++;
59    }
60
61    public void deleteAtIndex(int index) {
62        if (index < 0 || index >= size) return;
63        if (index == 0) {
64            head = head.next;
65            size--;
66            return;
67        }
68        Node temp = head;
69        for (int i = 0; i < index - 1; i++) temp = temp.next;
70        temp.next = temp.next.next;
71        size--;
72    }
73}
74