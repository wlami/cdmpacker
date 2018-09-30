package de.wlami.cdmpacker;

import lombok.Data;

@Data
public class ParcelPackage {

  /**
   * The name of the package
   */
  private String name;
  /**
   * The version of the package
   */
  private String version;
}
