import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        minCut = graph.edgeNum;
//        int maxContractionTimes=0;
        for (int i = 0; i < (graph.nodes.size() * graph.nodes.size()); i++) {
            Graph trial = graph.replicate();
//            int nodeSize=trial.nodes.size();
//            int contractionTimes=0;
            while (trial.nodes.size() > 2) {
//                if ((contractionTimes+trial.edges.size())>maxContractionTimes) {
                    int seed = (int) Math.floor(Math.random() * trial.nodes.size());
                    trial.contraction(trial.nodes.get(seed));
//                    nodeSize--;
//                    contractionTimes++;
//                }else break;
            }
//            if (contractionTimes>maxContractionTimes) maxContractionTimes=contractionTimes;
//            if (trial.edges.size() < minCut) minCut = trial.edges.size();
            int cut=0;
            for (Map.Entry entry: trial.nodes.get(0).edges.entrySet()){
                cut = (int) entry.getValue()+ cut;
            }
            if (cut < minCut) minCut = cut;
            System.out.println(cut);
        }
        System.out.println(minCut);
        long te = System.currentTimeMillis();
        double tusec = (te - ts) / 1000;
        System.out.println(tusec);
    }

    public static Graph loadFile(File graphFile) {
        Graph graph = new Graph();
//        int edgeNum=0;
//        int i;
        try {
            FileReader fileReader = new FileReader(graphFile);
            BufferedReader bufReader = new BufferedReader(fileReader);
            ArrayList<String> nodeLines = new ArrayList<>();
            String line;
            while ((line=bufReader.readLine()) != null) {
                nodeLines.add(line);
                Node node = new Node();
                String[] nodeList=line.split("\t");
                node.no= Integer.parseInt(nodeList[0])-1;
                graph.nodes.add(node);
            }
            for (int j=0;j<nodeLines.size();j++){
                String[] nodeList= nodeLines.get(j).split("\t");
                Node node=graph.nodes.get(j);
                for (int i = 1; i < nodeList.length; i++) {
//                    Edge edge = new Edge();
//                    Node targetNode=graph.nodes.get(Integer.parseInt(nodeList[i])-1);
//                    edge.mainNode = mainNode;
//                    edge.targetNode = targetNode;
//                    edge.sameEdges=1;
//                    if (!graph.existEdge(edge)) {
//                        graph.edges.add(edge);
//                        mainNode.edgesold.add(edge);
//                        targetNode.edgesold.add(edge);
//                    }
                    Node targetNode=graph.nodes.get(Integer.parseInt(nodeList[i])-1);
                    if (node.edges.get(targetNode)==null) {
                        node.edges.put(targetNode, 1);
//                        graph.nodes.get(targetNodeNum).edges.put(node.no,1);
                        graph.edgeNum++;
                    }
                }
            }
            if (graph.edgeNum%2==0){
                graph.edgeNum /= 2;
            }else System.out.println("edge size error");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
}

class Graph{
    ArrayList<Node> nodes = new ArrayList<>();
    int edgeNum=0;
    ArrayList<Edge> edges = new ArrayList<>();
    boolean existEdge(Edge edge) {
        for (int i = 0; i < edges.size(); i++) {
            if (edge.equal(edges.get(i))) {
                return true;
            }
        }
        return false;
    }

    void contraction(Node contractionNode) {
        ArrayList<Node> edgeMap=new ArrayList(contractionNode.edges.keySet());
        int seed = (int) Math.floor(Math.random() * edgeMap.size());
        Node targetNode=edgeMap.get(seed);
        for (Map.Entry entry:targetNode.edges.entrySet()){
            Node redirectNode= (Node) entry.getKey();
            if (contractionNode!=redirectNode) {
                int redirectEdgeNum= (int) entry.getValue();
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
//        Node removedNode=contractionNode.targetNode;
//        Node mainNode=contractionNode.mainNode;
//        for (int i = 0; i < removedNode.edgesold.size(); i++) {//relink all edgesold linked to the removed node to the main node
//            if(removedNode.edgesold.get(i).mainNode.equals(removedNode)) removedNode.edgesold.get(i).mainNode=mainNode;
//            else removedNode.edgesold.get(i).targetNode=mainNode;
//        }
//        mainNode.edgesold.remove(contractionNode);
//        this.edges.remove(contractionNode);
//        removedNode.edgesold.remove(contractionNode);//remove the loop edge
//        for (int i = 0; i < removedNode.edgesold.size(); i++){
//            if (mainNode.edgesold.contains(removedNode.edgesold.get(i))){
//
//            }else
//                mainNode.edgesold.add(removedNode.edgesold.get(i));
//
//        }
//        this.nodes.remove(removedNode);
    }

    public Graph replicate() {
        Graph c = new Graph();
        c.edgeNum=this.edgeNum;
        for (int i = 0; i < this.nodes.size(); i++) {
            Node node=new Node();
            node.no=this.nodes.get(i).no;
            c.nodes.add(node);
        }
        for (int i = 0; i < this.nodes.size(); i++) {
            Map<Node,Integer> edges=new HashMap<>();
            for (Map.Entry entry: this.nodes.get(i).edges.entrySet()){
                edges.put(c.nodes.get(((Node) entry.getKey()).no),(Integer)entry.getValue());
            }
            c.nodes.get(i).edges=edges;
//            for (int j = 0; j < this.nodes.get(i).edgesold.size(); j++) {
//                Edge edge=new Edge();
//                edge.mainNode=c.nodes.get(Integer.parseInt(this.nodes.get(i).edgesold.get(j).mainNode.name)-1);
//                edge.targetNode=c.nodes.get(Integer.parseInt(this.nodes.get(i).edgesold.get(j).targetNode.name)-1);
//                if(!c.existEdge(edge)) {
//                    edge.mainNode.edgesold.add(edge);
//                    edge.targetNode.edgesold.add(edge);
//                    c.edges.add(edge);
//                }
//            }
        }
        return c;
    }
}

class Edge {
    Node mainNode, targetNode;
    int sameEdges=0;
    boolean equal(Edge edge) {
        if (edge.mainNode.equals(this.mainNode) && edge.targetNode.equals(this.targetNode) || (edge.mainNode.equals(this.targetNode) && edge.targetNode.equals(this.mainNode)))
            return true;
        return false;
    }
}

class Node {
    int no;
    Map<Node, Integer> edges=new HashMap<>();
    ArrayList<Edge> edgesold =new ArrayList<>();
}