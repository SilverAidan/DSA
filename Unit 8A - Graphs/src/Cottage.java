import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Cottage {
	HashMap<GNode,HashSet<GNode>> hiho;
	
	public Cottage() {
		hiho = new HashMap<GNode,HashSet<GNode>>();
	}
	
	public boolean addGNode(GNode gnome, HashSet<GNode> buddies, boolean isUndirected) {
		/*dumb way to do it
		for(GNode buddy:buddies)
			if(!hiho.containsKey(buddy))
				return false;
				*/
		if(buddies == null)
			buddies = new HashSet<GNode>();
		if(gnome==null)
			return false;
	
		buddies.remove(gnome);//keep it simple
		if(!hiho.keySet().containsAll(buddies))
			return false;
		hiho.put(gnome, buddies);
		if(isUndirected&&buddies!=null) {
			for(GNode buddy:buddies) {
				hiho.get(buddy).add(gnome);
			}
		}
		
		return true;
	}
	public boolean addEdge(GNode gnome1,GNode gnome2,  boolean isUndirected ) {
		return addEdge(gnome1,new HashSet<GNode>(Set.of(gnome2)),isUndirected);
	}
	public boolean addEdge(GNode gnome, HashSet<GNode> buddies, boolean isUndirected) {
		if(gnome==null||buddies==null)
			return false;
		buddies.remove(gnome);
		if(hiho.containsKey(gnome)) {
			for(GNode buddy:buddies)
				if(hiho.containsKey(buddy)) {
					hiho.get(gnome).add(buddy);
					if(isUndirected)
						hiho.get(buddy).add(gnome);
				}
			
			
			return true;
		}
		
		return false;
		
	}
	public boolean removeGNode(GNode gnome) {
		if(gnome==null)
			return false;
		for(HashSet<GNode> values:hiho.values())
			values.remove(gnome);
		hiho.remove(gnome);
		return true;
	}
	
	/**
	 * Returns true only if either break up happens
	 * @param gnome1
	 * @param gnome2
	 * @param isMutual
	 * @return
	 */
	public boolean breakup(GNode gnome1, GNode gnome2, boolean isMutual) {
		if(gnome1==null||gnome2==null||!hiho.containsKey(gnome1)||!hiho.containsKey(gnome2))
			return false;
		boolean b = hiho.get(gnome1).remove(gnome2);
		boolean c = false;
		if(isMutual)
			c = hiho.get(gnome2).remove(gnome1);
		return b||c;
	}
	public HashSet<GNode> BFS(GNode gnome){
		if(gnome==null||!hiho.containsKey(gnome))
			return null;
		HashSet<GNode> visited = new HashSet<GNode>();
		Queue<GNode> toVisit = new LinkedList<GNode>();
		toVisit.offer(gnome);
		while(!toVisit.isEmpty()) {
			GNode current = toVisit.poll();
			
			if(visited.add(current))
				System.out.println(current);
			for(GNode g:hiho.get(current)) {
				if(!visited.contains(g))
					toVisit.offer(g);
			}
		}
		return visited;
		
	}
	
	
	public HashSet<GNode> DFS(GNode gnome){
		if(gnome==null||!hiho.containsKey(gnome))
			return null;
		HashSet<GNode> visited = new HashSet<GNode>();
		Stack<GNode> toVisit = new Stack<GNode>();
		toVisit.push(gnome);
		while(!toVisit.empty()) {
			GNode current = toVisit.pop();
			
			if(visited.add(current))
				System.out.println(current);
			for(GNode g:hiho.get(current)) {
				if(!visited.contains(g))
					toVisit.push(g);
			}
		}
		return visited;
	}
	
	public HashSet<GNode> neighbors(GNode gnome){
		
		return gnome!=null&&hiho.containsKey(gnome)?hiho.get(gnome):null;
		
	}
	
	
	public String toString() {
		String output = "";
		for(GNode gnome:hiho.keySet())
			output += gnome + "-->" + hiho.get(gnome)+"\n";
		return output;
	}

}
