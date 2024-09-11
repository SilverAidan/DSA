import java.util.Iterator;
import java.util.Stack;

public class Stacksy {

	public static void main(String[] args) {
		Stack<Integer> st = new Stack<Integer>();
		st.push(3); st.push(4); st.push(5);
		System.out.println(st.peek());//5
		System.out.println(st.pop());//5
		
		for(int i = 0; i < 10; i++) {
			st.push((int)(Math.random()*10));
		}
		System.out.println(st);
		System.out.println(removeEvens(st));
		System.out.println(st);
		
		System.out.println(isPalindrome("ABCCBA"));//true
		System.out.println(isPalindrome("ABCDCBA"));//true
		System.out.println(isPalindrome("A"));//true
		System.out.println(isPalindrome("BABBBB"));//false
		System.out.println(isPalindrome("PAPAPAPA"));//false
		
		for(Integer i:st) {
			System.out.print(i);
		}
		Iterator<Integer> itr = st.iterator();
		System.out.println("Break");
		System.out.println(itr.next());
		String[] sites = new String[3];
		sites[0] = "google.com";
		sites[1] = "reuters.com";
		sites[2] = "glastonburyus.org";
		Websites webs = new Websites();
		for(String s:sites) {
			webs.push(s);
		}
		System.out.println(webs.size);
		System.out.println(webs.peek());
		webs.push("somewebsite.com");
		System.out.println(webs.sites.length);
		String exp1 = "({3+4[2x+3]-2})/2";
		String exp2 = "({3+4[2x+3]-2})/2";
		String exp3 = "({3+4[2x+3]-2})/2";
		//exp1 = stripExp(exp1);
		System.out.println(exp1);
		for(String w: webs) {
			System.out.println(w);
		}
		Iterator<String> webIt = webs.iterator();
		System.out.println(webIt.next());
		webIt.next();webIt.next();webIt.next();
		System.out.println(webIt.hasNext());
	}
	
	public static boolean isValidExp(String exp) {
		exp = stripExp(exp);
		Stack<Character> stk = new Stack<Character>();
		String open = "{[(";
		String closed = "}])|";
		for(char c:exp.toCharArray()) {
			if(open.indexOf(c)>-1) {
				stk.push(c);
			}else {
				if(stk.isEmpty()) {
					return false;
				}
				if(open.indexOf(c) == closed.indexOf(stk.peek())) {
					stk.pop();
				}
				else
					return false;
			}
		}
		return stk.isEmpty();
	}
	
	public static int removeEvens(Stack<Integer> s) {
		int count = s.size();
		Stack<Integer> temp = new Stack<Integer>();
		while(!s.isEmpty()) {
			if(s.peek() % 2 == 1) {
				temp.push(s.pop());
			}else {
				s.pop();
			}
		}
		count -= temp.size();
		while(!temp.isEmpty()) {
			s.push(temp.pop());
		}
		return count;
	}
	public static String stripExp(String exp) {
		String output = "";
		String brackets = "{[(||)]}";
		for(char c:exp.toCharArray()) {
			if(brackets.indexOf(c) > -1) {
				output += c;
			}
		}
		return output;
	}
	
	public static boolean isPalindrome(String word) {
		Stack<Character> temp = new Stack<Character>();
		for(int i = 0; i < word.length(); i++) {
			if(i<word.length()/2) {
				temp.push(word.charAt(i));
			}else if((word.length()%2 == 1) && (i==word.length()/2)){
				continue;
			}else {
				if(word.charAt(i) != temp.pop()) {
					return false;
				}
			}
		}
		return true;
	}

}
