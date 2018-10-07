/*
 * Copyright 2018 Wladislaw Mitzel
 *
 * Licensed under the Apache License, Version 2.0 (the “License”); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an “AS IS” BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */

package de.wlami.cdmpacker;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Setter
/**
 * This goal generates files that are required  in a CDM parcel. It creates a parcel.json and a
 * dummy env_script.
 *
 */
@Mojo(name = "generate-parcel-json", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
@Execute(phase = LifecyclePhase.GENERATE_RESOURCES)
public class GenerateParcelJsonMojo extends AbstractMojo {

  public static final String OUTPUT_DIR_NAME = "cdmpacker";
  public static final String DEFAULT_ENV_SCRIPT_NAME = "cdh_env.sh";
  private ObjectMapper objectMapper;

  @Parameter(readonly = true)
  private ParcelConfig parcel;

  @Parameter(readonly = true, name = "parcelJsonFile", defaultValue = "parcel.json")
  private String parcelJsonFile;

  @Parameter(defaultValue = "${project}", readonly = true)
  private MavenProject project;

  @Getter
  @Setter
  private ParcelNameSanitizer parcelNameSanitizer = new ParcelNameSanitizer();

  /**
   * Default constructor that initializes an {@link com.fasterxml.jackson.databind.ObjectMapper}.
   */
  public GenerateParcelJsonMojo() {
    objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    objectMapper.setSerializationInclusion(Include.NON_NULL);
  }

  @Override
  public void execute() throws MojoExecutionException {
    getLog().info("Project version: " + project.getVersion());
    if (parcel == null) {
      parcel = getDefaultConfig();
    }
    Path outputDir = Paths.get(project.getBuild().getDirectory(), OUTPUT_DIR_NAME);
    try {
      createOutputDir(outputDir);
      ParcelConfig enrichedConfig = enrichConfig(parcel, project, outputDir);
      try (OutputStream os = Files.newOutputStream(outputDir.resolve(parcelJsonFile))) {
        writeOutput(os, enrichedConfig);
      }
    } catch (IOException e) {
      throw new MojoExecutionException("Could not write parcel.json", e);
    }

  }

  void createOutputDir(Path dir) throws IOException {
    if (Files.notExists(dir)) {
      getLog().info("Creating directory: [" + dir + "]");
      Files.createDirectories(dir);
    }
  }



  ParcelConfig enrichConfig(ParcelConfig config, MavenProject project, Path outputDir) {
    ParcelConfig enriched = new ParcelConfig();
    String sanitizedName = parcelNameSanitizer.sanitzieParcelName(
            ofNullable(config.getName()).orElse(project.getArtifactId()), getLog());
    String version = ofNullable(config.getVersion()).orElse(project.getVersion());
    enriched.setName(sanitizedName);
    enriched.setVersion(version);
    enriched.setSetActiveSymlink(of(config.isSetActiveSymlink()).orElse(Boolean.TRUE));

    enriched.setDepends(config.getDepends());
    enriched.setReplaces(config.getReplaces());
    enriched.setConflicts(config.getConflicts());
    enriched.setProvides(config.getProvides());

    enriched.setScripts(ofNullable(config.getScripts()).orElseGet(() -> {
      getLog().info("Creating default env_script.");
      try {
        Path dummyScript = outputDir.resolve(DEFAULT_ENV_SCRIPT_NAME);
        if (!Files.exists(dummyScript)) {
          Files.createFile(dummyScript);
        }
      } catch (IOException e) {
        getLog().warn("Could not create default env_script.", e);
      }
      return getDefaultScript();
    }));

    enriched.setComponents(ofNullable(config.getComponents())
        .orElse(new ParcelComponent[]{getDefaultComponent(sanitizedName, version)}));
    enriched.setPackages(
        ofNullable(config.getPackages()).orElse(new ParcelPackage[]{getDefaultPackage(sanitizedName, version)}));

    enriched.setUsers(ofNullable(config.getUsers()).orElse(new HashMap<>()));
    enriched.setGroups(ofNullable(config.getGroups()).orElse(new String[0]));

    return enriched;
  }


  private void writeOutput(OutputStream outputStream, ParcelConfig config) throws IOException {
    objectMapper.writeValue(outputStream, config);
  }

  private ParcelScripts getDefaultScript() {
    ParcelScripts scripts = new ParcelScripts();
    scripts.setDefines(DEFAULT_ENV_SCRIPT_NAME);
    return scripts;
  }

  private ParcelComponent getDefaultComponent(String name, String version) {
    ParcelComponent component = new ParcelComponent();
    component.setName(name);
    component.setVersion(version);
    return component;
  }

  private ParcelPackage getDefaultPackage(String name, String version) {
    ParcelPackage pkg = new ParcelPackage();
    pkg.setName(name);
    pkg.setVersion(version);
    return pkg;
  }

  private ParcelConfig getDefaultConfig() {
    ParcelConfig config = new ParcelConfig();
    return config;
  }
}