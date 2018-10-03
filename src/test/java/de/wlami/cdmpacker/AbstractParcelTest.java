package de.wlami.cdmpacker;

import java.io.File;
import lombok.Getter;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.ReaderFactory;

public abstract class AbstractParcelTest extends AbstractMojoTestCase {


  private String pomPath;
  @Getter
  private File pom;

  public AbstractParcelTest(String pomPath) {
    this.pomPath = pomPath;
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    pom = getTestFile(pomPath);
    if (!pom.exists()) {
      throw  new RuntimeException("Could not load test pom.");
    }
  }

  void loadProject(AbstractCdmMojo mojo, File pom) throws Exception {
    MavenProject p = new MavenProject(new MavenXpp3Reader().read(ReaderFactory.newXmlReader(pom)));
    p.getBuild().setDirectory("target/");
    mojo.setMavenProject(p);
  }
}
