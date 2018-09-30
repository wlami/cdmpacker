package de.wlami.cdmpacker;

import java.util.List;
import lombok.Data;

@Data
public class ParcelUser {

  /**
   * The descriptive name of the user
   */
  private String longName;
  /**
   * The user's home directory
   */
  private String home;
  /**
   * The user's shell program
   */
  private String shell;
  /**
   * A list of additional groups to add the user to. If you are adding a user to another user's
   * group, that user must appear before this user in @{@link ParcelConfig#users}.
   */
  private List<String> extraGroups;
}
