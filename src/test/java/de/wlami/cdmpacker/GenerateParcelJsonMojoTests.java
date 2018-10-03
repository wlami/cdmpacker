package de.wlami.cdmpacker;

import org.junit.Test;

public class GenerateParcelJsonMojoTests extends AbstractParcelTest {

  public GenerateParcelJsonMojoTests() {
    super("src/test/resources/unit/full/pom.xml");
  }

  @Test
  public void testExecute_hasConfiguredValues() throws Exception {
    GenerateParcelJsonMojo myMojo = (GenerateParcelJsonMojo) lookupMojo("generate-parcel-json",
        getPom());
    assertNotNull(myMojo);
    loadProject(myMojo, getPom());
    myMojo.setParcelJsonFile("parcel.json");
    myMojo.execute();
  }

}