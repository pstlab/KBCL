package it.istc.pst.kbcl.inference.kb.uc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ UC1DiscoverAndBuildAgentFunctionalitiesTest.class,
		UC2DiscoverAndBuildAgentInternalCompositionTest.class,
		UC3DiscoverAndBuildAgentExternalDpendenciesTest.class })
public class DataExtractionUseCasesTestSuite {

}
