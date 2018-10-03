package de.wlami.cdmpacker;

import lombok.Getter;
import lombok.Setter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Common super class that provides access to the {@link MavenProject}.
 */
public abstract class AbstractCdmMojo extends AbstractMojo {

  @Getter
  @Setter
  @Parameter(defaultValue = "${project}", readonly = true)
  private MavenProject mavenProject;

}
