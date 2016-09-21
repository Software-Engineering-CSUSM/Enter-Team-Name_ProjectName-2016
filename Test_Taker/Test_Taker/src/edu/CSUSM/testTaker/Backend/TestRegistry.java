package edu.CSUSM.testTaker.Backend;

import java.util.HashMap;
import java.io.Serializable;

public class TestRegistry {
	static final long serialVersionUID = 1L;
	static HashMap<String, Test> testStore;
	
	public static Test retrieve(String queryID){
		return testStore.get(queryID);
	}
	
	public static boolean store(Test updated){
		testStore.put(updated.getID(), updated);
		return true;
	}
}
