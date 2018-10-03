package de.wlami.cdmpacker;

import static de.wlami.cdmpacker.GenerateParcelNamePropertiesMojo.CDMPACKER_PARCEL_FILE_NAME;
import static de.wlami.cdmpacker.GenerateParcelNamePropertiesMojo.CDMPACKER_PARCEL_FOLDER_NAME;

import org.junit.Test;

public class GenerateParcelNamePropertiesMojoTest extends AbstractParcelTest {

  public GenerateParcelNamePropertiesMojoTest() {
    super("src/test/resources/unit/folder-name/pom.xml");
  }

  @Test
  public void testExecute_propertyIsSet() throws Exception {
    GenerateParcelNamePropertiesMojo myMojo = (GenerateParcelNamePropertiesMojo) lookupMojo(
        "parcel-name-props", getPom());
    assertNotNull(myMojo);
    loadProject(myMojo, getPom());
    myMojo.setParcelFolderName(CDMPACKER_PARCEL_FOLDER_NAME);
    myMojo.setParcelFileName(CDMPACKER_PARCEL_FILE_NAME);
    myMojo.setParcelName("arti");
    myMojo.setVersion("111");
    myMojo.setDistroSuffix(ParcelDistroSuffix.el7);
    myMojo.execute();
    assertEquals("ARTI-111",
        myMojo.getMavenProject().getProperties().getProperty(CDMPACKER_PARCEL_FOLDER_NAME));
    assertEquals("ARTI-111-el7",
        myMojo.getMavenProject().getProperties().getProperty(CDMPACKER_PARCEL_FILE_NAME));
  }
}