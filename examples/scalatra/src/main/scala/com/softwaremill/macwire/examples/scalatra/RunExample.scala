package com.softwaremill.macwire.examples.scalatra

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
import org.eclipse.jetty.servlet.ServletHolder

object RunExample extends App {
  val port = if (System.getenv("PORT") != null) System.getenv("PORT").toInt else 8080

  val server = new Server(port)

  val modules = new ServletModule {}

  val context = new WebAppContext()
  context.setContextPath("/")
  context.setResourceBase("/WEB-INF")
  context.addServlet(new ServletHolder(modules.servlet1), "/*")
  context.addServlet(new ServletHolder(modules.authServlet), "/auth/*")

  server.setHandler(context)

  server.start()
  server.join()
}
