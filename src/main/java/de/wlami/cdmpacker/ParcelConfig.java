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

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * This class is used for configuration and creation of a parcel.json which represents the metadata
 * of a parcel.
 *
 * @see <a href="https://github.com/cloudera/cm_ext/wiki/The-parcel.json-file"
 * >https://github.com/cloudera/cm_ext/wiki/The-parcel.json-file</a>
 */
@Data
public class ParcelConfig {

  /**
   * currently only this version is supported
   */
  public static final int DEFAULT_SCHEMA_VERSION = 1;

  @JsonProperty("schema_version")
  private int schemaVersion = DEFAULT_SCHEMA_VERSION;
  /**
   * The name of the parcel.
   */
  private String name;
  /**
   * The version of the parcel.
   */
  private String version;
  private boolean setActiveSymlink;

  /**
   * List parcels that must be present for this parcel to function. This parcel cannot be activated
   * until the dependencies are present.
   */
  private String depends;
  /**
   * List parcels that are replaced by this parcel. This parcel cannot be activated until the
   * replaced parcels are removed.
   */
  private String replaces;
  /**
   * List parcels that conflict with this parcel. Only one of the conflicting parcels can be active
   * at a time.
   */
  private String conflicts;

  /**
   * This field is a list of strings, where each string is a tag that indicates that interesting
   * functionality the parcel provides for services being managed by Cloudera Manager
   */
  private String[] provides;

  /**
   * This script is sourced into the environment of each process that the parcel affects (based on
   * the tags). Even if a parcel doesn't require any environment variables to be defined, this
   * script must be provided (the script itself can be empty).
   */
  private ParcelScripts scripts;

  /**
   * The packages section is purely informative, and Cloudera Manager does not use any of the
   * information provided here. This section is intended for situations where a parcel is equivalent
   * to a set of packages (as is the case for CDH), and it allows for that set of packages to be
   * documented.
   */
  private ParcelPackage[] packages;

  /**
   * The components section is similar to the packages section, but there are two important
   * differences. Firstly, these entries correspond to the logical components inside the parcel -
   * which may not map 1:1 to any packages, and secondly, the component entries are consumed by
   * Cloudera Manager. They are used to populate the installed components table for Hosts in the CM
   * UI and are compared for cross-host consistency by the CM host inspector.
   */
  private ParcelComponent[] components;
  /**
   * This section describes any additional system users that are required by the programs contained
   * in the parcel. Each user is described by an entry in this section.<br>
   *
   * The key is the username (eg: "hdfs") while the value has additional information.<br>
   *
   * <i>Note that CM can be configured to not add the specified users. A product user can decide to
   * disable this if their IT policies or infrastructure do not allow local user creation.</i>
   */
  private Map<String, ParcelUser> users;

  /**
   * This is a list of any extra groups that should be created beyond the per-user groups that are
   * created for each of the users listed in the users section.<br>
   *
   * <i>Note that CM can be configured to not add the specified groups. A product user can decide
   * to disable this if their IT policies or infrastructure do not allow local group creation.</i>
   */
  private String[] groups;

}
