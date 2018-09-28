package de.wlami;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Goal which touches a timestamp file.
 *
 * @goal touch
 * @phase process-sources
 */
@Mojo(name = "genparcel")
public class GenerateParcelJsonMojo extends AbstractMojo {

  public void execute() throws MojoExecutionException {
    getLog().info("Hello World!");

    }
  }
}
