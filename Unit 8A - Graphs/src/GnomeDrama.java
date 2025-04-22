import java.util.HashSet;
import java.util.Set;

public class GnomeDrama {
	public static void main(String[] args) {
		Cottage cottage = new Cottage();
		GNode Doc = new GNode("Doc",0);
		GNode Grumpy = new GNode("Grumpy",1);
		GNode Sleepy = new GNode("Sleepy",2);
		GNode Sneezy = new GNode("Sneezy",3);
		//HashSet<GNode> docBuddies = new HashSet<GNode>(Set.of(Grumpy,Sleepy));
		cottage.addGNode(Doc, null, false);
		cottage.addGNode(Grumpy, new HashSet<GNode>(Set.of(Doc)),false);
		System.out.println(cottage);
		cottage.addGNode(Sneezy, null, false);
		cottage.addGNode(Sleepy, null, false);
		cottage.addEdge(Doc, new HashSet<GNode>(Set.of(Grumpy,Sleepy,Sneezy)),true);
		System.out.println(cottage);
		//cottage.removeGNode(Sneezy);
		System.out.println(cottage);
		GNode Bashful = new GNode("Bashful",4);
		System.out.println(cottage.breakup(Sneezy, Bashful, false));
		cottage.breakup(Grumpy, Doc, true);
		System.out.println(cottage);
		
		Cottage letters = new Cottage();
		GNode[] gletters = new GNode[6];
		for(int i = 0; i<gletters.length;i++) {
			gletters[i] = new GNode("" + (char)('A'+i),10+i);
			letters.addGNode(gletters[i], null, true);
		}
		
		letters.addEdge(gletters[0], gletters[1], true);
		letters.addEdge(gletters[0], gletters[3], true);
		letters.addEdge(gletters[1], gletters[2], true);
		letters.addEdge(gletters[3], gletters[2], true);
		letters.addEdge(gletters[2], gletters[5], true);
		letters.addEdge(gletters[3], gletters[4], true);
		letters.addEdge(gletters[2], gletters[4], true);
		
		System.out.println(letters);
		System.out.println(letters.DFS(gletters[5]));
		System.out.println(letters.BFS(gletters[1]));
		
		
		
	}
}
