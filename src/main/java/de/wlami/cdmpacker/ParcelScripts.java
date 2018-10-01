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
 * This class represents configurations for the scripts attribute in the parcel.json.
 */
@Data
public class ParcelScripts {

  /**
   * This script is sourced into the environment of each process that the parcel affects (based on
   * the tags). Even if a parcel doesn't require any environment variables to be defined, this
   * script must be provided (the script itself can be empty).
   */
  private String defines;
}
