package exer4;

import java.util.HashSet;
import java.util.Set;

/* A linkable graph node that supports both directed and undirected edges. */
public class UnweightedGraphNode {
  private HashSet<UnweightedGraphNode> connections;
  public UnweightedGraphNode() {
    connections = new HashSet<UnweightedGraphNode>();
  }
  
  public void addUndirectedEdge(UnweightedGraphNode other) {
    connections.add(other); // this ---> other
  }
  
  public void addDirectedEdge(UnweightedGraphNode other) {
    addUndirectedEdge(other); // this ---> other
    other.addUndirectedEdge(this); // other ---> this
  }
  
  public void delUndirectedEdge(UnweightedGraphNode other) {
    connections.remove(other); // this -X-> other
  }
  
  public void delDirectedEdge(UnweightedGraphNode other) {
    delUndirectedEdge(other); // this -X-> other
    other.delUndirectedEdge(this); // other -X-> this
  }
  
  /* Returns a Set of all nodes that this node is connected to. */
  @SuppressWarnings("unchecked")
  public <T> Set<T> getConnections() { // Allows it to implicitly be casted to the desired type
    return (Set<T>) connections;
  }
  
}
