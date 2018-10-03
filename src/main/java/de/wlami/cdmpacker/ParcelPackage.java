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
 * This class represents configurations for the package attribute in the parcel.json.
 */
@Data
public class ParcelPackage {

  /**
   * The name of the package.
   */
  private String name;
  /**
   * The version of the package.
   */
  private String version;
}
