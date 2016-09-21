package edu.csusm.TestTaker;

import java.io.Serializable;

public class TestRegistry implements Serializable{
	static final long serialVersionUID = 1L;
	static java.util.HashMap<String, Test> testStore;
}
