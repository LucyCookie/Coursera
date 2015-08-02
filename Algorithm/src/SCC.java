import java.io.*;
import java.util.*;

/**
 * Created by qiqu on 8/2/15.
 */
public class SCC {
    public static void main(String[] args) {
        long ts = System.currentTimeMillis();
        File file = new File("/Users/qiqu/Google Drive/coursera/Coursera/Algorithm/SCC.txt");
        Graph graph = loadGraph(file);
        boolean reverse=true;
        graph.DFSLoop(reverse);
        reverse=false;
        graph.DFSLoop(reverse);
        System.out.println(graph.maxSCC1);
        System.out.println(graph.maxSCC2);
        System.out.println(graph.maxSCC3);
        System.out.println(graph.maxSCC4);
        System.out.println(graph.maxSCC5);
        long te = System.currentTimeMillis();
        double tusec = (te - ts) / 1000;
        System.out.println(tusec);
    }

    public static Graph loadGraph(File graphFile) {
        Graph graph = new Graph();
        try {
            FileReader fileReader = new FileReader(graphFile);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufReader.readLine()) != null) {
                String[] pair = line.split(" ");
                int head = Integer.parseInt(pair[0]);
                int tail = Integer.parseInt(pair[1]);
                Node headNode, tailNode;
                if ((headNode = graph.nodeMap.get(head)) == null) {
                    headNode = new Node();
                    headNode.no = head;
                    graph.nodeMap.put(head, headNode);
                }
                if ((tailNode = graph.nodeMap.get(tail)) == null) {
                    tailNode = new Node();
                    tailNode.no = head;
                    graph.nodeMap.put(tail, tailNode);
                }
                headNode.outNodes.add(tailNode);
                tailNode.inNodes.add(headNode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
}