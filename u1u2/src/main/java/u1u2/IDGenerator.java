package u1u2;

import org.apache.commons.collections.map.StaticBucketMap;

public class IDGenerator {
	static long id = 0;
	
	public long idGenerator() throws IDOverflow {
		if ( id > 999999) {
			throw new IDOverflow("ID Overflow");
		}
		return ++id;
	}	
}

class IDOverflow extends Exception {
	
	public IDOverflow() {
		super();
	}
	
	public IDOverflow(String message) {
		super(message);	
	}
}