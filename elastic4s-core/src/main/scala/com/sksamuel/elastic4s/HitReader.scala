package com.sksamuel.elastic4s

import com.sksamuel.elastic4s.requests.searches.InnerHit

import scala.util.Try

trait HitReader[T] {
  def read(hit: Hit): Try[T]
}

trait InnerHitReader[T] {
  def read(hit: InnerHit): Try[T]
}

trait AggReader[T] {
  def read(json: String): Try[T]
}
