package de.wlami.cdmpacker;

import lombok.Data;

/**
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
