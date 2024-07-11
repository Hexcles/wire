package com.squareup.wire.whiteboard

import com.squareup.wire.GrpcException

fun main(args: Array<String>) {
  require(args.size <= 1) {"At most one argument (server base URL) is expected"}
  val serverUrl = args.getOrElse(0) { "http://localhost:8080" }
  println("Starting client connecting to $serverUrl...")

  val client = GrpcClientProvider.createGrpcClient(serverUrl).create(WhiteboardClient::class)

  while (true) {
    try {
      println(client.Echo().executeBlocking(Point(1, 2, 3)))
    } catch (e: GrpcException) {
      println(e)
    }
    Thread.sleep(1000)
  }
}
