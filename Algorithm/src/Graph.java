import java.util.*;

/**
 * Created by qiqu on 8/3/15.
 */
public class Graph {
    ArrayList<Node> nodes = new ArrayList<>();
    int edgeNum = 0;//for min cut
    Map<Integer, Node> nodeMap = new HashMap<Integer, Node>();//for SCC
    Stack<Node> orderedNodes = new Stack<Node>();//for SCC
    int maxSCC1 = 0, maxSCC2 = 0, maxSCC3 = 0, maxSCC4 = 0, maxSCC5 = 0, SCC = 0;
    Map<Node, Integer> distances = new HashMap<Node, Integer>();//for shortest path, the distances to the source
    GraphHeapMin heap;//for shortest path
    Map<Node, Boolean> unvisitedNodes = new HashMap<Node, Boolean>();

    public void shortestStep() {
        Node node = null;
        if (heap.getIndex() >= 0) {
            node = heap.extractMin();
        } else if (unvisitedNodes.size()>0){
            for (Node noConnect : unvisitedNodes.keySet()) {
                node = noConnect;
                node.greedyScore = 1000000;
                break;
            }
        } else return;
        distances.put(node, node.greedyScore);
        node.distanceComputed = true;
        unvisitedNodes.remove(node);
        for (Map.Entry entry : node.edges.entrySet()) {
            Node candidate = (Node) entry.getKey();
            if (!candidate.distanceComputed) {
                if (candidate.heapPosition == -1) {
                    candidate.greedyScore = distances.get(node) + node.edges.get(candidate);
                    candidate.heapPosition = heap.insert(candidate);
                } else if (candidate.greedyScore > distances.get(node) + node.edges.get(candidate)) {
                    candidate.greedyScore = distances.get(node) + node.edges.get(candidate);
                    heap.update(candidate.heapPosition);
                }
            }
        }
    }

    public void DFS(boolean reverse, Node i) {
        if (reverse) {
            if (i.inExplored == false) {
                i.inExplored = true;
                for (Node n : i.inNodes) DFS(reverse, n);
                this.orderedNodes.push(i);
            }
        } else {
            if (i.outExplored == false) {
                i.outExplored = true;
                SCC++;
                for (Node n : i.outNodes) DFS(reverse, n);
            }
        }
    }

    public void DFSLoop(boolean reverse) {
        if (reverse) {
            for (int i = nodeMap.size(); i > 0; i--) DFS(reverse, nodeMap.get(i));
        } else {
            while (!orderedNodes.isEmpty()) {
                SCC = 0;
                DFS(reverse, orderedNodes.pop());
                if (SCC > 0) {
                    if (SCC > maxSCC1) {
                        maxSCC5 = maxSCC4;
                        maxSCC4 = maxSCC3;
                        maxSCC3 = maxSCC2;
                        maxSCC2 = maxSCC1;
                        maxSCC1 = SCC;
                    } else if (SCC > maxSCC2) {
                        maxSCC5 = maxSCC4;
                        maxSCC4 = maxSCC3;
                        maxSCC3 = maxSCC2;
                        maxSCC2 = SCC;
                    } else if (SCC > maxSCC3) {
                        maxSCC5 = maxSCC4;
                        maxSCC4 = maxSCC3;
                        maxSCC3 = SCC;
                    } else if (SCC > maxSCC4) {
                        maxSCC5 = maxSCC4;
                        maxSCC4 = SCC;
                    } else if (SCC > maxSCC5) {
                        maxSCC5 = SCC;
                    }
                }
            }
        }
    }


    void contraction(Node contractionNode) {
        ArrayList<Node> edgeMap = new ArrayList(contractionNode.edges.keySet());
        int seed = (int) Math.floor(Math.random() * edgeMap.size());
        Node targetNode = edgeMap.get(seed);
        for (Map.Entry entry : targetNode.edges.entrySet()) {
            Node redirectNode = (Node) entry.getKey();
            if (contractionNode != redirectNode) {
                int redirectEdgeNum = (int) entry.getValue();
                if (contractionNode.edges.get(redirectNode) == null) {
                    contractionNode.edges.put(redirectNode, redirectEdgeNum);
                    redirectNode.edges.put(contractionNode, redirectEdgeNum);
                } else {
                    int newEdgeNum = contractionNode.edges.get(redirectNode) + redirectEdgeNum;
                    contractionNode.edges.put(redirectNode, newEdgeNum);
                    redirectNode.edges.put(contractionNode, newEdgeNum);
                }
                redirectNode.edges.remove(targetNode);
            }
        }
        contractionNode.edges.remove(targetNode);
        this.nodes.remove(targetNode);
    }

    public Graph replicate() {
        Graph c = new Graph();
        c.edgeNum = this.edgeNum;
        for (int i = 0; i < this.nodes.size(); i++) {
            Node node = new Node();
            node.no = this.nodes.get(i).no;
            c.nodes.add(node);
        }
        for (int i = 0; i < this.nodes.size(); i++) {
            Map<Node, Integer> edges = new HashMap<>();
            for (Map.Entry entry : this.nodes.get(i).edges.entrySet()) {
                edges.put(c.nodes.get(((Node) entry.getKey()).no), (Integer) entry.getValue());
            }
            c.nodes.get(i).edges = edges;
        }
        return c;
    }
}

class Node {
    int no;
    int heapPosition = -1, greedyScore;//for shortest path
    boolean outExplored;
    boolean inExplored;
    boolean distanceComputed;//for shortest path
    List<Node> outNodes = new ArrayList<Node>();
    List<Node> inNodes = new ArrayList<Node>();
    Map<Node, Integer> edges = new HashMap<>();
}