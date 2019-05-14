package com.github.plokhotnyuk.jsoniter_scala.benchmark

import java.nio.charset.StandardCharsets.UTF_8

import com.avsystem.commons.serialization.json._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.AVSystemCodecs._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.CirceEncodersDecoders._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.GeoJSON._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.JacksonSerDesers._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.JsoniterScalaCodecs._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.PlayJsonFormats._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.SprayFormats._
//import com.github.plokhotnyuk.jsoniter_scala.benchmark.UPickleReaderWriters._
import com.github.plokhotnyuk.jsoniter_scala.core._
import io.circe.parser._
import org.openjdk.jmh.annotations.Benchmark
import play.api.libs.json.Json
import spray.json._

class GeoJSONReading extends GeoJSONBenchmark {
  @Benchmark
  def avSystemGenCodec(): GeoJSON = JsonStringInput.read[GeoJSON](new String(jsonBytes, UTF_8))

  @Benchmark
  def circe(): GeoJSON = decode[GeoJSON](new String(jsonBytes, UTF_8)).fold(throw _, identity)

  @Benchmark
  def jacksonScala(): GeoJSON = jacksonMapper.readValue[GeoJSON](jsonBytes)

  @Benchmark
  def jsoniterScala(): GeoJSON = readFromArray[GeoJSON](jsonBytes)

  @Benchmark
  def playJson(): GeoJSON = Json.parse(jsonBytes).as[GeoJSON](geoJSONFormat)

  @Benchmark
  def sprayJson(): GeoJSON = JsonParser(jsonBytes).convertTo[GeoJSON](geoJSONJsonFormat)
/* FIXME: upickle.core.AbortException: invalid tag for tagged object: com.github.plokhotnyuk.jsoniter_scala.benchmark.FeatureCollection at index 8
  @Benchmark
  def uPickle(): GeoJSON = read[GeoJSON](jsonBytes)
*/
}