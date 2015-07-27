import java.io.*;
import java.util.ArrayList;

/**
 * Created by Cookie on 22/7/2015.
 */
public class MinCut {
    public static void main(String[] args) {
        long ts = System.currentTimeMillis();
        int minCut;
//        File file = new File("/Users/qiqu/Google Drive/coursera/Coursera/Algorithm/kargerMinCut.txt");
        File file = new File("C:\\Users\\Joseph\\Desktop\\kargerMinCut.txt");
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
        }
        System.out.println(minCut);
        long te = System.currentTimeMillis();
        double tusec = (te - ts) / 1000;
        System.out.println(tusec);
    }

    public static Graph loadFile(File graphFile) {
        Graph graph = new Graph();
        int i;
        try {
            FileReader fileReader = new FileReader(graphFile);
            BufferedReader bufReader = new BufferedReader(fileReader);
            ArrayList<String> nodeLines = new ArrayList<>();
            String line;
            while ((line=bufReader.readLine()) != null) {
                nodeLines.add(line);
                Node node = new Node();
                String[] nodeList=line.split("\t");
                node.name=nodeList[0];
                graph.nodes.add(node);
            }
            for (int j=0;j<nodeLines.size();j++){
                String[] nodeList= nodeLines.get(j).split("\t");
                Node mainNode=graph.nodes.get(j);
                for (i = 1; i < nodeList.length; i++) {
                    Edge edge = new Edge();
                    Node targetNode=graph.nodes.get(Integer.parseInt(nodeList[i])-1);
                    edge.mainNode = mainNode;
                    edge.targetNode = targetNode;
                    if (!graph.existEdge(edge)) {
                        graph.edges.add(edge);
                        mainNode.edges.add(edge);
                        targetNode.edges.add(edge);
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
        Node removedNode=contractionEdge.targetNode;
        Node mainNode=contractionEdge.mainNode;
        for (int i = 0; i < removedNode.edges.size(); i++) {//relink all edges linked to the removed node to the main node
            if(removedNode.edges.get(i).mainNode.equals(removedNode)) removedNode.edges.get(i).mainNode=mainNode;
            else removedNode.edges.get(i).targetNode=mainNode;
        }
        for (int i = 0; i < removedNode.edges.size(); i++){//remove the loop edge
            if (removedNode.edges.get(i).mainNode.equals(removedNode.edges.get(i).targetNode)){
                this.edges.remove(removedNode.edges.get(i));
                mainNode.edges.remove(removedNode.edges.get(i));
            }else mainNode.edges.add(removedNode.edges.get(i));
        }
        this.nodes.remove(removedNode);
    }

    public Graph replicate() {
        Graph c = new Graph();
        for (int i = 0; i < this.nodes.size(); i++) {
            Node node=new Node();
            node.name=this.nodes.get(i).name;
            c.nodes.add(node);
        }
        for (int i = 0; i < this.nodes.size(); i++) {
            for (int j = 0; j < this.nodes.get(i).edges.size(); j++) {
                Edge edge=new Edge();
                edge.mainNode=c.nodes.get(Integer.parseInt(this.nodes.get(i).edges.get(j).mainNode.name)-1);
                edge.targetNode=c.nodes.get(Integer.parseInt(this.nodes.get(i).edges.get(j).targetNode.name)-1);
                if(!c.existEdge(edge)) {
                    edge.mainNode.edges.add(edge);
                    edge.targetNode.edges.add(edge);
                    c.edges.add(edge);
                }
            }
        }
        return c;
    }
}

class Edge {
    Node mainNode, targetNode;
    boolean equal(Edge edge) {
        if (edge.mainNode.equals(this.mainNode) && edge.targetNode.equals(this.targetNode) || (edge.mainNode.equals(this.targetNode) && edge.targetNode.equals(this.mainNode)))
            return true;
        return false;
    }
}

class Node {
    String name;
    ArrayList<Edge> edges=new ArrayList<>();
}