public class GraphHeapMin {
    private Node[] Heap;
    private int maxsize;
    private int index = -1;

    public GraphHeapMin(int max) {
        maxsize = max;
        Heap = new Node[maxsize];
    }

    private int leftchild(int ind) {
        return 2 * ind + 1;
    }

    private int rightchild(int ind) {
        return 2 * (ind + 1);
    }

    private int parent(int ind) {
        return (ind - 1) / 2;
    }

    private boolean isleaf(int ind) {
        return ((2 * ind + 1 > index) && (ind <= index));
    }

    public int getIndex() {
        return index;
    }

    private void swap(int pos1, int pos2) {
        Node tmp;
        tmp = Heap[pos1];
        Heap[pos1].heapPosition = pos2;
        Heap[pos2].heapPosition = pos1;
        Heap[pos1] = Heap[pos2];
        Heap[pos2] = tmp;
    }

    public int insert(Node elem) {
        index++;
        Heap[index] = elem;
        int current = index;
        while (Heap[current].greedyScore < Heap[parent(current)].greedyScore) {
            swap(current, parent(current));
            current = parent(current);
        }
        return current;
    }

    public void update(int pos) {
        if (index > 0) {
            while (Heap[pos].greedyScore<Heap[parent(pos)].greedyScore && pos>0){
                Node tmp;
                tmp = Heap[pos];
                Heap[pos].heapPosition = parent(pos);
                Heap[parent(pos)].heapPosition = pos;
                Heap[pos] = Heap[parent(pos)];
                Heap[parent(pos)] = tmp;
                pos=parent(pos);
            }
            pushdown(pos);
        }
    }

    public Node extractMin() {
        index--;
        if (index >= 0) {
            swap(0, index + 1);
            pushdown(0);
        }
        return Heap[index + 1];
    }

    private void pushdown(int position) {
        int smallestchild;
        while (!isleaf(position)) {
            smallestchild = leftchild(position);
            if ((smallestchild < index)
                    && (Heap[smallestchild].greedyScore > Heap[smallestchild + 1].greedyScore))
                smallestchild = smallestchild + 1;
            if (Heap[position].greedyScore <= Heap[smallestchild].greedyScore)
                return;
            swap(position, smallestchild);
            position = smallestchild;
        }
    }
}
