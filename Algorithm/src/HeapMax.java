/**
 * Created by qiqu on 8/22/15.
 */
public class HeapMax {
    private int[] Heap;
    private int maxsize;
    private int index = -1;

    public HeapMax(int size) {
        maxsize = size;
        Heap = new int[maxsize];
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

    public int getSize() {
        return index+1;
    }

    private void swap(int pos1, int pos2) {
        int tmp;
        tmp = Heap[pos1];
        Heap[pos1] = Heap[pos2];
        Heap[pos2] = tmp;
    }

    public int insert(int elem) {
        index++;
        Heap[index] = elem;
        int current = index;
        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
        return current;
    }

    public int extractMax() {
        index--;
        if (index >= 0) {
            swap(0, index + 1);
            pushdown(0);
        }
        return Heap[index + 1];
    }

    public int getMax(){
        return Heap[0];
    }

    private void pushdown(int position) {
        int smallestchild;
        while (!isleaf(position)) {
            smallestchild = leftchild(position);
            if ((smallestchild < index)
                    && (Heap[smallestchild] < Heap[smallestchild + 1]))
                smallestchild = smallestchild + 1;
            if (Heap[position] >= Heap[smallestchild])
                return;
            swap(position, smallestchild);
            position = smallestchild;
        }
    }
}
