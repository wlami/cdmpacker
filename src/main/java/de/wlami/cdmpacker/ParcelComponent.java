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

import lombok.Data;

/**
 * This class represents configurations for the component attribute in the parcel.json.
 *
 * The components section is similar to the packages section, but there are two important
 * differences. Firstly, these entries correspond to the logical components inside the parcel -
 * which may not map 1:1 to any packages, and secondly, the component entries are consumed by
 * Cloudera Manager. They are used to populate the installed components table for Hosts in the CM UI
 * and are compared for cross-host consistency by the CM host inspector.
 *
 * The package related fields are intended to be optional; if your parcel is not built from the
 * contents of packages, these fields are not relevant and ought to be left out. However, initial
 * releases of CM (5.0.0, 5.0.1, 5.0.2) have a bug which makes pkg_version required. If the field is
 * not meaningful for your parcel, just repeat the version here.
 */
@Data
public class ParcelComponent {

  /**
   * The name of the component
   */
  private String name;
  /**
   * The version of the component
   */
  private String version;
  /**
   * The version of the equivalent packaged component
   */
  private String pkgVersion;
  /**
   * (Optional) The release string of the equivalent packaged component
   */
  private String pkgRelease;
}
