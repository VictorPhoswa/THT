function navigate(roads, start, end) {
  // First, we need to find the shortest path from the start node to the end node.
  // We'll use Dijkstra's algorithm to do this.
  
  // First, initialize some variables we'll need for the algorithm.
  let unvisitedNodes = new Set(Object.keys(roads.graph.nodes));
  let distances = {};
  let previousNodes = {};
  let path = [];
  
  // Set the initial distances to infinity for all nodes except the start node,
  // which we'll set to 0.
  for (let nodeId in roads.graph.nodes) {
    distances[nodeId] = Infinity;
  }
  distances[start] = 0;
  
  // Now we'll loop through all the nodes until we've visited them all.
  while (unvisitedNodes.size > 0) {
    // First, find the unvisited node with the smallest distance.
    let currentNode = null;
    for (let node of unvisitedNodes) {
      if (currentNode === null || distances[node] < distances[currentNode]) {
        currentNode = node;
      }
    }
    
    // If we've found the end node, we're done!
    if (currentNode === end) {
      // Now we'll build the path by going backwards from the end node,
      // following the previousNodes links until we get to the start node.
      while (previousNodes[currentNode]) {
        path.push(parseInt(currentNode));
        currentNode = previousNodes[currentNode];
      }
      path.push(start);
      
      // Finally, we'll reverse the path array so that it goes from start to end.
      path.reverse();
      
      // Return an object containing the distance and the path.
      return {
        distance: distances[end],
        path: path
      };
    }
    
    // Otherwise, loop through all the neighbors of the current node and update
    // their distances if necessary.
    for (let edge of roads.graph.edges) {
      if (edge.source == currentNode || edge.target == currentNode) {
        let neighbor = edge.source == currentNode ? edge.target : edge.source;
        let distance = distances[currentNode] + edge.metadata.distance;
        if (distance < distances[neighbor]) {
          distances[neighbor] = distance;
          previousNodes[neighbor] = currentNode;
        }
      }
    }
    
    // Mark the current node as visited.
    unvisitedNodes.delete(currentNode);
  }
  
  // If we've visited all the nodes and still haven't found the end node,
  // there's no path between the two nodes.
  return {
    distance: null,
    path: null
  };
}



function navigate(roads, start, end) {
   
  
    // First, we need to find the shortest path from the start node to the end node.
    // We'll use Dijkstra's algorithm to do this.
    const distances = {};
    const previous = {};
    const queue = [];
    
    // First, initialize some variables we'll need for the algorithm.
    for (let i = 0; i < roads.graph.nodes.length; i++) {
      distances[roads.graph.nodes[i].id] = Infinity;
      previous[roads.graph.nodes[i].id] = null;
    }
  
    distances[start] = 0;
    queue.push(start);
  
    // Dijkstra's algorithm
    // Otherwise, loop through all the neighbors of the current node and update
    // their distances if necessary.
    while (queue.length > 0) {
      const current = queue.shift();
  
      for (let i = 0; i < roads.graph.edges.length; i++) {
        const edge = roads.graph.edges[i];
  
        if (edge.source === current || edge.target === current) {
          const neighbor = edge.source === current ? edge.target : edge.source;
          const distance = distances[current] + edge.metadata.distance;
  
          if (distance < distances[neighbor]) {
            distances[neighbor] = distance;
            previous[neighbor] = current;
            queue.push(neighbor);
          }
        }
      }
    }
  
    // construct path
    const path = [];
    let node = end;
  
    while (node !== null) {
      path.unshift(node);
      node = previous[node];
    }
  
    // If we've visited all the nodes and still haven't found the end node,
    // there's no path between the two nodes.
    // return result
    return {
      distance: distances[end],
      path: path
    };
  }
  