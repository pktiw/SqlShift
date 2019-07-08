name := "sqlshift"

organization := "com.goibibo"

version := "0.1"

scalaVersion := "2.11.12"

logLevel := Level.Info

resolvers ++= Seq(
    "Typesafe" at "http://repo.typesafe.com/typesafe/releases/",
    "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    "Cloudera" at "https://repository.cloudera.com/content/repositories/releases",
    "jitpack" at "https://jitpack.io",
    "redshift" at "http://redshift-maven-repository.s3-website-us-east-1.amazonaws.com/release"
)

val sparkVersion = "2.4.3"
val awsSDKVersion = "1.10.22"
val dockerItScalaVersion = "0.9.0"
val scalaTestVersion = "3.0.1"

libraryDependencies ++= Seq(
    "mysql" % "mysql-connector-java" % "5.1.47",
    "org.apache.spark" %% "spark-core" % sparkVersion % Provided,
    "org.apache.spark" %% "spark-sql" % sparkVersion % Provided,
    ("com.databricks" %% "spark-redshift" % "1.1.0").
            exclude("org.apache.avro", "avro"),
    ("com.amazonaws" % "aws-java-sdk-core" % awsSDKVersion).
            exclude("com.fasterxml.jackson.core", "jackson-core").
            exclude("com.fasterxml.jackson.core", "jackson-databind").
            exclude("com.fasterxml.jackson.core", "jackson-annotations").
            exclude("commons-codec", "commons-codec").
            exclude("commons-logging", "commons-logging").
            exclude("joda-time", "joda-time").
            exclude("org.apache.httpcomponents", "httpclient"),
    ("com.amazonaws" % "aws-java-sdk-s3" % awsSDKVersion).
            exclude("com.amazonaws", "aws-java-sdk-core"),
    ("org.apache.hadoop" % "hadoop-aws" % "2.6.0-cdh5.8.2").
            exclude("com.amazonaws", "aws-java-sdk-s3").
            exclude("com.fasterxml.jackson.core", "jackson-databind").
            exclude("com.fasterxml.jackson.core", "jackson-annotations")
            exclude("org.apache.hadoop", "hadoop-common"),
    "com.amazon.redshift" % "redshift-jdbc4" % "1.2.10.1009",
    "com.github.scopt" %% "scopt" % "3.5.0",
    "javax.mail" % "mail" % "1.4.7",
    "io.dropwizard" % "dropwizard-metrics" % "1.0.5",
    "com.github.goibibo" % "dataplatform_utils_2.10" % "v1.6",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.7.8",
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
    "com.whisk" %% "docker-testkit-scalatest" % dockerItScalaVersion % Test,
    "com.whisk" %% "docker-testkit-impl-spotify" % dockerItScalaVersion % Test,
    "com.databricks" %% "spark-csv" % "1.1.0" % Test
).map(_.exclude("org.slf4j", "slf4j-log4j12"))

envVars in Test := Map("DOCKER_HOST" -> "unix:///var/run/docker.sock")

parallelExecution in Test := false

scalacOptions ++= Seq(
    "-encoding", "UTF-8",
    "-unchecked",
    "-deprecation",
    "-Xfuture",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard"
)

