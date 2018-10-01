package de.wlami.cdmpacker;

import java.io.File;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.ReaderFactory;
import org.junit.Test;

public class GenerateParcelJsonMojoTests extends AbstractMojoTestCase {

  @Test
  public void testExecute_hasConfiguredValues() throws Exception {
    File pom = getTestFile("src/test/resources/unit/full/pom.xml");
    assertNotNull(pom);
    assertTrue(pom.exists());

    GenerateParcelJsonMojo myMojo = (GenerateParcelJsonMojo) lookupMojo("generate-parcel-json",
        pom);
    assertNotNull(myMojo);
    loadProject(myMojo, pom);
    myMojo.setParcelJsonFile("parcel.json");
    myMojo.execute();
  }

  private void loadProject(GenerateParcelJsonMojo mojo, File pom) throws Exception {
    MavenProject p = new MavenProject(new MavenXpp3Reader().read(ReaderFactory.newXmlReader(pom)));
    p.getBuild().setDirectory("target/");
    mojo.setProject(p);
  }

}