package edu.CSUSM.testTaker.Backend;

import java.util.HashMap;

public class TestRegistry {
	static final long serialVersionUID = 1L;
	static HashMap<String, Test> testStore;
	
	/*
	 * For now the program starts with an empty registry every startup.
	 */
	static {
		testStore = new HashMap<String, Test>();
	}
	
	/**
	 * @brief Get the test object with the given ID string from this registry
	 * @param queryID A globally unique ID string associated with an instance of Test
	 * @return A copy of the Test associated with the given ID.
	 */
	public static Test retrieve(String queryID){
		return testStore.get(queryID);
	}
	
	/**
	 * @brief Store or update a Test copy into the registry
	 * @param updated The Test to store
	 * @return true for success, false for failure
	 */
	public static boolean store(Test updated){
		testStore.put(updated.getID(), updated);
		return true;
	}
}
