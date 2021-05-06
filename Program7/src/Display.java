public class Display {
    public static void main(String[] args) {
        String[] files = {"demands1.txt", "demands2.txt", "demands3.txt", "demands4.txt", "demands5.txt", "demands6.txt"};
        for(String file: files){
            Graph graph = new Graph();
            graph.makeGraph(file);
            MaxFlow maxFlow = new MaxFlow(graph);
            displayOutput(file);
            displayMaxFlow(maxFlow);
            displayEdgeTransports(graph);
            displayMinCut(maxFlow);
            System.out.println("\n\n\n\n");
        }
    }

    //displays the output file
    public static void displayOutput(String file){
        System.out.println("OUTPUT: ");
        System.out.println(file + "\n");
    }

    //displays the Max Flow
    public static void displayMaxFlow(MaxFlow flow){
        System.out.println("MAX FLOW: ");
        flow.flowAlgorithm();//this should do the in between part
        System.out.println("\nProduced " + flow.maxFlow + ":\n");
    }

    //this will display how much edge transports
    public static void displayEdgeTransports(Graph graph){
        for(int from = 0; from < graph.flow.length; from++){
            for(int to = 0; to < graph.flow[from].length; to++){
                if(graph.flow[from][to][0] > 0){//this wil verify that there even was an edge there
                    if (graph.flow[from][to][0] != graph.flow[from][to][1]){//this make sure that the edge did something
                        int transported = graph.flow[from][to][0] - graph.flow[from][to][1];
                        System.out.printf("Edge (%d,%d) transports %d cases\n", from, to, transported);
                    }
                }
            }
        }
    }
    //this will display where we should make the cut
    public static void displayMinCut(MaxFlow flow){
        System.out.println("\nMIN CUT:");
        flow.minCut();
    }
}
