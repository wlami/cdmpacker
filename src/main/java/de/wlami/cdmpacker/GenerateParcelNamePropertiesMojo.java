package de.wlami.cdmpacker;

import lombok.Getter;
import lombok.Setter;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Calculates the folder name that has to be at the root level inside the generated parcel tar. Also
 * calculates the
 *
 * @author Wladislaw Mitzel
 * @see <a href="https://github.com/cloudera/cm_ext/wiki/Building-a-parcel">https://github.com/cloudera/cm_ext/wiki/Building-a-parcel</a>
 * @since 0.3.0
 */
@Mojo(name = "parcel-name-props", defaultPhase = LifecyclePhase.GENERATE_RESOURCES,
    threadSafe = true)
public class GenerateParcelNamePropertiesMojo extends AbstractCdmMojo {

  /**
   * default value for the folder name target property.
   */
  public static final String CDMPACKER_PARCEL_FOLDER_NAME = "cdmpacker.parcelFolderName";


  /**
   * default value for the file name  target property.
   */
  public static final String CDMPACKER_PARCEL_FILE_NAME = "cdmpacker.parcelFileName";

  /**
   * default value for the parcel name  target property.
   */
  public static final String CDMPACKER_PARCEL_NAME = "cdmpacker.parcelName";

  /**
   * The name of the parcel.
   */
  @Getter
  @Setter
  @Parameter(defaultValue = "${project.artifactId}", required = true)
  private String parcelName;

  /**
   * The version of the parcel.
   */
  @Getter
  @Setter
  @Parameter(defaultValue = "${project.version}", required = true)
  private String version;

  /**
   * The linux distribution this parcel is built for.
   */
  @Getter
  @Setter
  @Parameter(required = true)
  private ParcelDistroSuffix distroSuffix;

  /**
   * The property that shall contain the calculated parcel folder name.
   */
  @Getter
  @Setter
  @Parameter(defaultValue = CDMPACKER_PARCEL_FOLDER_NAME, required = true)
  private String parcelFolderName;

  /**
   * The property that shall contain the calculated parcel folder name.
   */
  @Getter
  @Setter
  @Parameter(defaultValue = CDMPACKER_PARCEL_FILE_NAME, required = true)
  private String parcelFileName;

  @Getter @Setter
  private ParcelNameSanitizer parcelNameSanitizer = new ParcelNameSanitizer();

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    String name = parcelNameSanitizer.sanitzieParcelName(parcelName, getLog());
    String folderName = name + "-" + version;
    String fileName = folderName + "-" + distroSuffix.name();
    defineProperty(parcelFileName, fileName);
    defineProperty(parcelFolderName, folderName);
  }

  private void defineProperty(String key, String value) {
    if (getMavenProject().getProperties().getProperty(key) != null) {
      getLog().warn("Property [" + key + "] already defined. Will override!");
    }
    getLog().info("setting property [" + key + "] to [" + value + "]");
    getMavenProject().getProperties().setProperty(key, value);
  }

}
