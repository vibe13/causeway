/**
 * Copyright (C) 2015 Red Hat, Inc. (jbrazdil@redhat.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.causeway.rest.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 *
 * @author Honza Brázdil &lt;janinko.g@gmail.com&gt;
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeName(value = "maven")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = MavenBuild.MavenBuildBuilder.class)
public class MavenBuild extends Build {

    @NonNull
    private final String groupId;
    @NonNull
    private final String artifactId;
    private final String version;

    @Builder
    private MavenBuild(String groupId, String artifactId, String version, String buildName,
            String buildVersion, String externalBuildSystem, int externalBuildID,
            String externalBuildURL, Date startTime, Date endTime, String scmURL,
            String scmRevision, BuildRoot buildRoot, Set<Logfile> logs,
            Set<Dependency> dependencies, Set<BuiltArtifact> builtArtifacts, String tagPrefix) {
        super(buildName, buildVersion, externalBuildSystem, externalBuildID, externalBuildURL,
                startTime, endTime, scmURL, scmRevision, buildRoot, logs, dependencies,
                builtArtifacts, tagPrefix);
        this.groupId = Objects.requireNonNull(groupId);
        this.artifactId = Objects.requireNonNull(artifactId);
        this.version = Objects.requireNonNull(version);
    }
    
    public String getVersion(){
        if(version == null){
            return getBuildVersion();
        }
        return version;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class MavenBuildBuilder {
    }
}
