import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by qiqu on 8/9/15.
 */
public class ShortestPath {
    public static void main(String[] args) {
        long ts = System.currentTimeMillis();
        File file = new File("/Users/qiqu/Google Drive/coursera/dijkstraData.txt");
//        File file = new File("/Users/qiqu/Google Drive/coursera/1.txt");
        Graph graph = loadGraph(file);
        Node source = graph.nodes.get(0);
        source.distanceComputed = true;
        graph.distances.put(source, 0);
        for (Map.Entry entry : source.edges.entrySet()) {
            Node target = (Node) entry.getKey();
            target.greedyScore = source.edges.get(target);
            target.heapPosition = graph.heap.insert(target);
        }
        int nodeLeft = graph.nodes.size() - 1;
        while (nodeLeft > 0) {
            graph.shortestStep();
            nodeLeft--;
        }
        int[] targetDistance = new int[]{7, 37, 59, 82, 99, 115, 133, 165, 188, 197};
//        int[] targetDistance = new int[]{2,3,4};
        for (int i = 0; i < targetDistance.length; i++) {
            System.out.println(graph.distances.get(graph.nodes.get(targetDistance[i]-1)));
        }
        long te = System.currentTimeMillis();
        double tusec = (te - ts) / 1000;
        System.out.println(tusec);
    }

    public static Graph loadGraph(File graphFile) {
        Graph graph = new Graph();
        try {
            FileReader fileReader = new FileReader(graphFile);
            BufferedReader bufReader = new BufferedReader(fileReader);
            ArrayList<String> nodeLines = new ArrayList<>();
            String line;
            while ((line = bufReader.readLine()) != null) {
                nodeLines.add(line);
                Node node = new Node();
                String[] nodeList = line.split("\t");
                node.no = Integer.parseInt(nodeList[0]) - 1;
                graph.nodes.add(node);
                graph.unvisitedNodes.put(node,true);
            }
            graph.heap = new GraphHeapMin(graph.nodes.size());
            for (int j = 0; j < nodeLines.size(); j++) {
                String[] nodeList = nodeLines.get(j).split("\t");
                Node node = graph.nodes.get(Integer.parseInt(nodeList[0]) - 1);
                for (int i = 1; i < nodeList.length; i++) {
                    Node targetNode = graph.nodes.get(Integer.parseInt(nodeList[i].split(",")[0]) - 1);
                    if (node.edges.get(targetNode) == null) {
                        node.edges.put(targetNode, Integer.parseInt(nodeList[i].split(",")[1]));
                        targetNode.edges.put(node, Integer.parseInt(nodeList[i].split(",")[1]));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
}
