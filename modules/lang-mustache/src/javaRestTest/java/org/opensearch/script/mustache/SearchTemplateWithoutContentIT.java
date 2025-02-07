/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/*
 * Modifications Copyright OpenSearch Contributors. See
 * GitHub history for details.
 */

package org.opensearch.script.mustache;

import org.opensearch.client.Request;
import org.opensearch.client.ResponseException;
import org.opensearch.test.rest.OpenSearchRestTestCase;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;

public class SearchTemplateWithoutContentIT extends OpenSearchRestTestCase {

    public void testSearchTemplateMissingBody() throws IOException {
        ResponseException responseException = expectThrows(ResponseException.class, () -> client().performRequest(
                new Request(randomBoolean() ? "POST" : "GET", "/_search/template")));
        assertEquals(400, responseException.getResponse().getStatusLine().getStatusCode());
        assertThat(responseException.getMessage(), containsString("request body or source parameter is required"));
    }

    public void testMultiSearchTemplateMissingBody() throws IOException {
        ResponseException responseException = expectThrows(ResponseException.class, () -> client().performRequest(
                new Request(randomBoolean() ? "POST" : "GET", "/_msearch/template")));
        assertEquals(400, responseException.getResponse().getStatusLine().getStatusCode());
        assertThat(responseException.getMessage(), containsString("request body or source parameter is required"));
    }
}
