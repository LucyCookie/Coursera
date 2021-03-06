import java.io.*;
import java.util.ArrayList;
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
        for (int i = 0; i < (graph.nodes.size() * graph.nodes.size()); i++) {
            Graph trial = graph.replicate();
            while (trial.nodes.size() > 2) {
                    int seed = (int) Math.floor(Math.random() * trial.nodes.size());
                    trial.contraction(trial.nodes.get(seed));
            }
            int cut=0;
            for (Map.Entry entry: trial.nodes.get(0).edges.entrySet()){
                cut = (int) entry.getValue()+ cut;
            }
            if (cut < minCut) minCut = cut;
        }
        System.out.println(minCut);
        long te = System.currentTimeMillis();
        double tusec = (te - ts) / 1000;
        System.out.println(tusec);
    }

    public static Graph loadFile(File graphFile) {
        Graph graph = new Graph();
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
                    Node targetNode=graph.nodes.get(Integer.parseInt(nodeList[i])-1);
                    if (node.edges.get(targetNode)==null) {
                        node.edges.put(targetNode, 1);
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