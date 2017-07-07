/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ambari.server.api.services;

import java.util.Collections;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.ambari.server.api.resources.ResourceInstance;
import org.apache.ambari.server.api.util.ApiVersion;
import org.apache.ambari.server.controller.spi.Resource;

/**
 * Service for Mpacks Management.
 * Endpoint for Mpack Data
 */

public class MpacksService extends BaseService {

  public MpacksService(ApiVersion apiVersion) {
    super(apiVersion);
  }

  /**
   * Handles: GET /mpacks/
   *
   * @param headers http headers
   * @param ui      uri info
   * @param body    request body
   * @return All the existing mpack definitions
   *
   */
  @GET
  @Produces("text/plain")
  public Response getMpacks(String body, @Context HttpHeaders headers, @Context UriInfo ui) {
    return handleRequest(headers, body, ui, Request.Type.GET,
            createMpackResource(null));
  }

  /**
   * Handles: POST /mpacks/
   *
   * @param headers http headers
   * @param ui      uri info
   * @param body    request body
   * @return information regarding the created mpack
   */
  @POST
  @Produces("text/plain")
  public Response createMpacks(String body, @Context HttpHeaders headers, @Context UriInfo ui) {
    return handleRequest(headers, body, ui, Request.Type.POST, createMpackResource(null));
  }

  /***
   * Handles: GET /mpacks/{mpack_id}
   * Return a specific mpack given an mpack_id
   *
   * @param
   */
  @GET
  @Path("{mpack_id}")
  @Produces("text/plain")
  public Response getMpack(String body, @Context HttpHeaders headers, @Context UriInfo ui, @PathParam("mpack_id") String mpackId) {

    return handleRequest(headers, body, ui, Request.Type.GET,
            createMpackResource(mpackId));
  }

  /**
   * Create an mpack resource instance
   * @param mpackId
   * @return ResourceInstance
   */
  private ResourceInstance createMpackResource(String mpackId) {
    return createResource(Resource.Type.Mpack,
            Collections.singletonMap(Resource.Type.Mpack, mpackId));

  }

}
