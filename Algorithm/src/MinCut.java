import java.io.*;
import java.util.ArrayList;

/**
 * Created by Cookie on 22/7/2015.
 */
public class MinCut {
    public static void main(String[] args) {
        long ts = System.currentTimeMillis();
        int minCut;
        File file = new File("/Users/qiqu/Google Drive/coursera/Coursera/Algorithm/kargerMinCut.txt");
        Graph graph = loadFile(file);
        minCut = graph.edges.size();
        for (int i = 0; i < (graph.nodes.size() * graph.nodes.size()); i++) {
            Graph trial = graph.replicate();
            int nodeSize=trial.nodes.size();
            while (nodeSize > 2) {
                int seed = (int) Math.floor(Math.random() * trial.edges.size());
                trial.contraction(trial.edges.get(seed));
                nodeSize--;
            }
            if (trial.edges.size() < minCut) minCut = trial.edges.size();
            System.out.println(trial.edges.size());
        }
        System.out.println(minCut);
        long te = System.currentTimeMillis();
        double tusec = (ts - te) / 1000;
        System.out.println(tusec);
    }

    public static Graph loadFile(File graphFile) {
        Graph graph = new Graph();
        int i;
        try {
            FileReader fileReader = new FileReader(graphFile);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String nodeLine;
            while ((nodeLine = bufReader.readLine()) != null) {
                Node node = new Node();
                String[] nodeList= nodeLine.split("\t");
                node.name = nodeList[0];
                graph.nodes.add(node);
                for (i = 1; i < nodeList.length; i++) {
                    Edge edge = new Edge();
                    edge.mainNodeName = node.name;
                    edge.targetNodeName = nodeList[i];
                    node.edges.add(nodeList[i]);
                    if (!graph.existEdge(edge)) {
                        graph.edges.add(edge);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
}

class Graph{
    ArrayList<Node> nodes = new ArrayList<>();
    ArrayList<Edge> edges = new ArrayList<>();
    boolean existEdge(Edge edge) {
        for (int i = 0; i < edges.size(); i++) {
            if (edge.equal(edges.get(i))) {
                return true;
            }
        }
        return false;
    }

    void contraction(Edge contractionEdge) {
        Node removedNode=nodes.get(Integer.parseInt(contractionEdge.targetNodeName)-1);
        Node mainNode=nodes.get(Integer.parseInt(contractionEdge.targetNodeName)-1);
        mainNode.edges.remove(contractionEdge.targetNodeName);
        edges.removeAll(contractionEdge);
        for (int i = 0; i < removedNode.edges.size(); i++) {
            if (!mainNode.edges.contains(removedNode.edges.get(i))){
                mainNode.edges.add(removedNode.edges.get(i));
            }
        }
        this.edges.remove(contractionEdge);
    }

    public Graph replicate() {
        Graph c = new Graph();
        for (int i = 0; i < this.nodes.size(); i++) {
            Node node=new Node();
            node.name=this.nodes.get(i).name;
            for (int j=0;j<this.nodes.get(i).edges.size();j++){
                node.edges.add(this.nodes.get(i).edges.get(j));
            }
            c.nodes.add(node);
        }
        for (int i = 0; i < this.edges.size(); i++) {
            Edge edge = new Edge();
            edge.mainNodeName = this.edges.get(i).mainNodeName;
            edge.targetNodeName = this.edges.get(i).targetNodeName;
            c.edges.add(edge);
        }
        return c;
    }
}

class Edge {
    String mainNodeName, targetNodeName;

    boolean equal(Edge edge) {
        if (edge.mainNodeName.equals(this.mainNodeName) && edge.targetNodeName.equals(this.targetNodeName) || (edge.mainNodeName.equals(this.targetNodeName) && edge.targetNodeName.equals(this.mainNodeName)))
            return true;
        return false;
    }

    void mergetoNodeA(Edge contractionEdge) {
        if (contractionEdge.targetNodeName.equals(this.mainNodeName)) {
            this.mainNodeName = contractionEdge.mainNodeName;
        } else if (contractionEdge.targetNodeName.equals(this.targetNodeName)) {
            this.targetNodeName = contractionEdge.mainNodeName;
        }
    }
}

class Node {
    String name;
    ArrayList<String> edges=new ArrayList<>();
}