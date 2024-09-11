import java.util.Iterator;
import java.util.NoSuchElementException;

public class Websites implements Iterable<String>{
	String[] sites;
	int size;
	
	public Websites() {
		sites = new String[4];
		sites[0] = "www.google.com";
		size = 1;
	}
	
	public void push(String site) {
		if(size < sites.length) {
			sites[size++] = site;
		}
		else {
			String temp[] = new String[sites.length*2];
			for(int i = 0; i < sites.length; i++) {
				temp[i] = sites[i];
			}
			
		}
	}
	
	public String peek() {
		if(size > 0) {
			return sites[size-1];
		}
		return null;
		//return size>0?sites[size-1]:null;
	}
	
	public String pop() {
		if(size > 0) {
			return sites[--size];
		}
		return null;
	}
	
	public boolean isEmpty() {
		return size > 0;
	}
	
	public boolean Empty() {
		return size == 0;
	}

	public void trim() {
		if(size<=sites.length/4) {
			String temp[] = new String[sites.length/2];
			for(int i = 0; i < size; i++) {
				temp[i] = sites[i];
			}
			sites = temp;
		}
	}

	@Override
	public Iterator<String> iterator() {
		return new WebIterator();
	}
	private class WebIterator implements Iterator<String>{
		int fingy = size - 1;
		@Override
		public boolean hasNext() {
			return fingy >= 0;
		}

		@Override
		public String next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return sites[fingy--];
		}
		
	}

}
