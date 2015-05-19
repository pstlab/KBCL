package it.istc.pst.kbcl.inference.kb.rdf;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RDFAgentDAOTest.class, RDFComponentDAOTest.class,
		RDFFunctionalityDAOTest.class })
public class RDFKnowledgeBaseDAOTestSuite {

}
