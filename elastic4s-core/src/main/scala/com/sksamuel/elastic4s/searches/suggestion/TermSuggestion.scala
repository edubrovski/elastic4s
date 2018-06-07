package com.sksamuel.elastic4s.searches.suggestion

import com.sksamuel.exts.OptionImplicits._

sealed trait SortBy
object SortBy {
  case object Score     extends SortBy
  case object Frequency extends SortBy
}

sealed trait SuggestMode
object SuggestMode {

  def valueOf(str: String): SuggestMode = str.toUpperCase match {
    case "MISSING" => Missing
    case "POPULAR" => Popular
    case "ALWAYS"  => Always
  }

  case object Missing extends SuggestMode
  case object Popular extends SuggestMode
  case object Always  extends SuggestMode

  def MISSING = Missing
  def POPULAR = Popular
  def ALWAYS  = Always
}

sealed trait StringDistanceImpl
object StringDistanceImpl {

  def valueOf(str: String): StringDistanceImpl = str.toUpperCase match {
    case "INTERNAL"            => INTERNAL
    case "DAMERAU_LEVENSHTEIN" => DAMERAU_LEVENSHTEIN
    case "LEVENSTEIN"          => LEVENSTEIN
    case "JAROWINKLER"         => JAROWINKLER
    case "NGRAM"               => NGRAM
  }

  case object INTERNAL            extends StringDistanceImpl
  case object DAMERAU_LEVENSHTEIN extends StringDistanceImpl
  case object LEVENSTEIN          extends StringDistanceImpl
  case object JAROWINKLER         extends StringDistanceImpl
  case object NGRAM               extends StringDistanceImpl
}

case class TermSuggestion(name: String,
                          fieldname: String,
                          text: Option[String] = None,
                          accuracy: Option[Double] = None,
                          lowercaseTerms: Option[Boolean] = None,
                          maxEdits: Option[Int] = None,
                          maxInspections: Option[Int] = None,
                          maxTermFreq: Option[Double] = None,
                          minDocFreq: Option[Double] = None,
                          minWordLength: Option[Int] = None,
                          prefixLength: Option[Int] = None,
                          sort: Option[SortBy] = None,
                          stringDistance: Option[StringDistanceImpl] = None,
                          suggestMode: Option[SuggestMode] = None,
                          analyzer: Option[String] = None,
                          size: Option[Int] = None,
                          shardSize: Option[Int] = None)
    extends Suggestion {

  def accuracy(accuracy: Double): TermSuggestion              = copy(accuracy = accuracy.some)
  def maxEdits(maxEdits: Int): TermSuggestion                 = copy(maxEdits = maxEdits.some)
  def maxInspections(maxInspections: Int): TermSuggestion     = copy(maxInspections = maxInspections.some)
  def maxTermFreq(maxTermFreq: Double): TermSuggestion        = copy(maxTermFreq = maxTermFreq.some)
  def minDocFreq(minDocFreq: Double): TermSuggestion          = copy(minDocFreq = minDocFreq.some)
  def lowercaseTerms(lowercaseTerms: Boolean): TermSuggestion = copy(lowercaseTerms = lowercaseTerms.some)
  def minWordLength(minWordLength: Int): TermSuggestion       = copy(minWordLength = minWordLength.some)
  def prefixLength(prefixLength: Int): TermSuggestion         = copy(prefixLength = prefixLength.some)
  def sort(sort: SortBy): TermSuggestion                      = copy(sort = sort.some)

  def stringDistance(dist: String): TermSuggestion =
    stringDistance(StringDistanceImpl.valueOf(dist.toUpperCase))
  def stringDistance(dist: StringDistanceImpl): TermSuggestion = copy(stringDistance = dist.some)

  def mode(suggestMode: String): TermSuggestion      = mode(SuggestMode.valueOf(suggestMode.toUpperCase))
  def mode(suggestMode: SuggestMode): TermSuggestion = copy(suggestMode = suggestMode.some)

  override def analyzer(analyzer: String): TermSuggestion = copy(analyzer = analyzer.some)
  override def text(text: String): TermSuggestion         = copy(text = Some(text))
  override def size(size: Int): TermSuggestion            = copy(size = size.some)
  override def shardSize(shardSize: Int): TermSuggestion  = copy(shardSize = shardSize.some)
}