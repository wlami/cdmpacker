package de.wlami.cdmpacker;

import lombok.Getter;

public enum ParcelDistroSuffix {

  el5("Redhat Enterprise Linux 5 and clones"),
  el6("Redhat Enterprise Linux 6 and clones"),
  el7("Redhat Enterprise Linux 7 and clones"),
  sles11("SuSE Linux Enterprise Server 11.x"),
  lucid("Ubuntu Linux 10.04 LTS"),
  precise("Ubuntu Linux 12.04 LTS"),
  trusty("Ubuntu Linux 14.04 LTS"),
  squeeze("Debian 6.x"),
  wheezy("Debian 7.x");

  @Getter
  private String description;

  ParcelDistroSuffix(String description) {
    this.description = description;
  }
}
