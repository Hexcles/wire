/*
 * Copyright (C) 2019 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.wire.whiteboard

import com.squareup.wire.GrpcClient
import java.time.Duration
import okhttp3.OkHttpClient
import okhttp3.Protocol.H2_PRIOR_KNOWLEDGE

object GrpcClientProvider {
  private val okHttpClient = OkHttpClient.Builder()
    .readTimeout(Duration.ofMinutes(1))
    .writeTimeout(Duration.ofMinutes(1))
    .callTimeout(Duration.ofMinutes(1))
    .protocols(listOf(H2_PRIOR_KNOWLEDGE))
    .build()

  fun createGrpcClient(serverUrl: String) = GrpcClient.Builder()
    .client(okHttpClient)
    .baseUrl(serverUrl)
    .build()
}
