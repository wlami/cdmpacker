package de.wlami.cdmpacker;

import lombok.Data;

@Data
public class ParcelScripts {

  /**
   * This script is sourced into the environment of each process that the parcel affects (based on
   * the tags). Even if a parcel doesn't require any environment variables to be defined, this
   * script must be provided (the script itself can be empty).
   */
  private String defines;
}
