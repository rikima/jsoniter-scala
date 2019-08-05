package com.github.plokhotnyuk.jsoniter_scala.benchmark

class MutableMapOfIntsToBooleansWritingSpec extends BenchmarkSpecBase {
  private val benchmark = new MutableMapOfIntsToBooleansWriting {
    setup()
  }
  
  "MutableMapOfIntsToBooleansWriting" should {
    "write properly" in {
      toString(benchmark.avSystemGenCodec()) shouldBe benchmark.jsonString
      toString(benchmark.circe()) shouldBe benchmark.jsonString
      toString(benchmark.dslJsonScala()) shouldBe benchmark.jsonString
      toString(benchmark.jacksonScala()) shouldBe benchmark.jsonString
      toString(benchmark.jsoniterScala()) shouldBe benchmark.jsonString
      toString(benchmark.preallocatedBuf, 0, benchmark.jsoniterScalaPrealloc()) shouldBe benchmark.jsonString
      toString(benchmark.playJson()) shouldBe benchmark.jsonString
      toString(benchmark.scalikeJackson()) shouldBe benchmark.jsonString
      //FIXME: uPickle doesn't support mutable maps
      //toString(benchmark.uPickle()) shouldBe benchmark.jsonString
    }
  }
}