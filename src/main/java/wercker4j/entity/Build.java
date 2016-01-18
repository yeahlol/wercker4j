/*
 * Copyright 2016 yeahlol
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wercker4j.entity;

import java.util.List;

/**
 * @author yeahlol
 */
public class Build {
    public String id;

    public String url;

    public ApplicationSummary application;

    public String branch;

    public String commitHash;

    public String createdAt;

    public String finishedAt;

    public String message;

    public int progress;

    public String result;

    public String startedAt;

    public String status;

    public List<EnvVar> envVars;
}
