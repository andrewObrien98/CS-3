import java.util.ArrayList;

public class MaxFlow {
    Graph graph;
    GraphNode[] graphNode;
    int maxFlow;
    int s;//standard convention that s will stand for where we start from
    int t;//standard convention that t will stand for where we want to end

    public MaxFlow(Graph g){
        graph = g;
        graphNode = graph.G;
        s = 0;
        maxFlow = 0;
        t = graphNode[graphNode.length - 1].nodeID;
    }

    /*
    this will call augmented path and then taking the path that it creates will find what the smallest
    value is that passes through the path, it will then go through that path and subtract that small
    value from each edge on the path
     */
    public void flowAlgorithm(){
        while(!augmentedPath()){
            ArrayList<Integer> flowList = new ArrayList<>();//this is here just so we can print out the path
            int minFlow = 10000;
            int currentID = t;
            GraphNode node = graphNode[currentID];
            //very simply this part will start at the last node and go back through the path that was selected and fin the minimum flow
            flowList.add(t);
            while(node != graphNode[s]){
                int parent = node.parent;
                flowList.add(parent);
                if(graph.flow[parent][currentID][1] < minFlow){
                    minFlow = graph.flow[parent][currentID][1];
                }
                node = graphNode[parent];
                currentID = parent;
            }
            currentID = t;//should this be here
            displayMaxFlow(flowList, minFlow);
            node = graphNode[t];
            //this will go back through the same list as the one up above and change their current value
            while(node != graphNode[s]){
                int parent = node.parent;
                graph.flow[parent][currentID][1]  = graph.flow[parent][currentID][1] - minFlow;
                node = graphNode[parent];
                currentID = parent;
            }
        }
    }

    //This will find paths from the start node to the end node. If a path has zero flow left in it then it wont take it
    //Only paths that have flow left will be considered
    public boolean augmentedPath(){
        QueueMaxHeap<GraphNode> queue = new QueueMaxHeap<>();
        graphNode[0].visited = true;//this is so nodes are not chosen again
        queue.add(graphNode[0]);
        boolean check = false;
        while(!queue.isEmpty()){//goes through and grabs each node and visits that nodes successors, will stop when reaches T or no flow left in system from S to T
            GraphNode node = queue.get();
            for(int i = 0; i < node.succ.size(); i++){//this will get all the nodes successors
                GraphNode.EdgeInfo info = node.succ.get(i);
                int id = info.to;
                if(!graphNode[id].visited && graph.flow[info.from][info.to][1] != 0){//this part just make sure it hasn't been visited and that it still has flow
                    graphNode[id].visited = true;
                    graphNode[id].parent = info.from;
                    queue.add(graphNode[id]);
                    if(id == t){//breaks here because it has found the last node
                        check = true;
                        setNodesToUnvisited();
                        break;
                    }
                }
            }
            if(check){
                break;
            }
        }
        return queue.isEmpty();
    }

    //this will just run through the nodes and find where to make a cut, once found it prints it out
    public void minCut(){
        QueueMaxHeap<GraphNode> queue = new QueueMaxHeap<>();
        setNodesToUnvisited();
        queue.add(graphNode[0]);
        graphNode[0].visited = true;
        while(!queue.isEmpty()){//if queue is empty it means that we have made all the cuts that need to be cut and we cant get anywhere else
            GraphNode node = queue.get();
            int from = node.nodeID;
            for(int i = 0; i < node.succ.size(); i++){//get the successor of each node and then checks each of the edges between the node and that successor
                GraphNode.EdgeInfo info = node.succ.get(i);
                int to = info.to;
                if(graph.flow[from][to][1] == 0){//if it has no flow then it prints out that line
                    System.out.printf("Edge (%d, %d) transports %d cases\n", from, to, graph.flow[from][to][0]);
                } else {//adds it to the queue if it still has flow to it and if we haven't gone there yet
                    if(!graphNode[to].visited){
                        graphNode[to].visited = true;
                        queue.add(graphNode[to]);
                    }
                }
            }
        }
    }

    public void setNodesToUnvisited(){//this will just make sure that when we run back through that the nodes are set as unvisited
        for(GraphNode node: graphNode){
            if(node.visited){
                node.visited = false;
            }
        }
    }

    //this will just print out how much flow we found in the path that is found in the flowAlgorithm
    public void displayMaxFlow(ArrayList<Integer> flowList, Integer minFlow){
        maxFlow += minFlow;
        System.out.print("Found flow " + minFlow + ":");
        for(int i = flowList.size() - 1; i > -1; i--){//this has to printed backwards as it is put in with the last as first
            System.out.print(" " + flowList.get(i));
        }
        System.out.println();
    }

}
