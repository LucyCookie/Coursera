import java.io.*;
import java.util.ArrayList;

/**
 * Created by Cookie on 22/7/2015.
 */
public class MinCut {
    public static void main(String[] args){
        int minCut;
        File file=new File("C:\\Users\\Joseph\\Desktop\\kargerMinCut.txt");
        Graph graph=loadFile(file);
        minCut=graph.edges.size();
        for (int i=0; i<(graph.graphNodes.size()*graph.graphNodes.size());i++){
            Graph trial=graph.replicate();
            while (trial.graphNodes.size()>2){
                int seed= (int) Math.floor(Math.random() * trial.edges.size());
                trial.contraction(trial.edges.get(seed));
            }
            if (trial.edges.size()<minCut) minCut=trial.edges.size();
        }
        System.out.println(minCut);
    }

    public static Graph loadFile(File graphFile){
        Graph graph=new Graph();
        int i;
        try {
            FileReader fileReader=new FileReader(graphFile);
            BufferedReader bufReader=new BufferedReader(fileReader);
            String nodeLine;
            while ((nodeLine=bufReader.readLine())!=null){
                String[] nodeList=nodeLine.split("\t");
                graph.graphNodes.add(nodeList[0]);
                for (i=1; i<nodeList.length;i++){
                    Edge edge=new Edge();
                    edge.NodeA=nodeList[0];
                    edge.NodeB=nodeList[i];
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

class Graph implements Cloneable{
    ArrayList<String> graphNodes= new ArrayList<String>();
    ArrayList<Edge> edges=new ArrayList<Edge>();
    boolean existEdge(Edge edge){
        for (int i=0;i<edges.size();i++){
            if (edge.equal(edges.get(i))){
                return true;
            }
        }
        return false;
    }
    void contraction(Edge contractionEdge){
        graphNodes.remove(contractionEdge.NodeB);
        for (int i=0;i<edges.size();i++){
            if(contractionEdge.equal(edges.get(i))){
                edges.remove(i);
                i--;
            }else edges.get(i).mergetoNodeA(contractionEdge);
        }
    }
    public Graph replicate(){
        Graph c=new Graph();
        for (int i=0;i<this.graphNodes.size();i++) c.graphNodes.add(this.graphNodes.get(i));
        for (int i=0;i<this.edges.size();i++){
            Edge edge=new Edge();
            edge.NodeA=this.edges.get(i).NodeA;
            edge.NodeB=this.edges.get(i).NodeB;
            c.edges.add(edge);
        }
        return c;
    }
}

class Edge implements Cloneable{
    String NodeA, NodeB;
    boolean equal(Edge edge){
        if (edge.NodeA.equals(this.NodeA) && edge.NodeB.equals(this.NodeB) || (edge.NodeA.equals(this.NodeB) && edge.NodeB.equals(this.NodeA))) return true;
        return false;
    }
    void mergetoNodeA(Edge contractionEdge){
        if (contractionEdge.NodeB.equals(this.NodeA)){
            this.NodeA=contractionEdge.NodeA;
        }else if (contractionEdge.NodeB.equals(this.NodeB)){
            this.NodeB=contractionEdge.NodeA;
        }
    }
}
