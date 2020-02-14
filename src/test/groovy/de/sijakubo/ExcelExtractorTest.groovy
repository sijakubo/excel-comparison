package de.sijakubo

import spock.lang.Specification
import spock.lang.Unroll

import static de.xm.yangyin.Comparisons.JSON
import static de.xm.yangyin.FileSnapshots.current
import static de.xm.yangyin.FileSnapshots.snapshot

class ExcelExtractorTest extends Specification {

  def "should extract whole workbook information"() {
    given:
      def testExcelFile = new File(this.getClass().getResource("basic_sample.xls").getFile())

    when:
      def workbookInformation = ExcelExtractor.extractWorkbookValues(testExcelFile)

    then:
      snapshot(workbookInformation, JSON) == current(workbookInformation, JSON)
  }

  @Unroll
  def "should extract basic sheet information in file formats: #format"() {
    given:
      def testExcelFile = new File(this.getClass().getResource(filename).getFile())

    expect:
      def orderSheetValues = ExcelExtractor.extractSheetValues(testExcelFile, 0)
      def customerSheetValues = ExcelExtractor.extractSheetValues(testExcelFile, 1)

      snapshot([customerSheetValues, orderSheetValues], JSON) == current([customerSheetValues, orderSheetValues], JSON)

    where:
      filename            | format
      "basic_sample.xlsx" | "xlsx"
      "basic_sample.xls"  | "xls"
  }

  def "should extract sheet containing formula"() {
    given:
      def testExcelFile = new File(this.getClass().getResource("formula_sample.xlsx").getFile())

    when:
      def formularSheetValues = ExcelExtractor.extractSheetValues(testExcelFile, 0)

    then:
      snapshot(formularSheetValues, JSON) == current(formularSheetValues, JSON)
  }

  def "should extract sheet values by index"() {
    given:
      def testExcelFile = new File(this.getClass().getResource("basic_sample.xls").getFile())

    when:
      def workbookInformation = ExcelExtractor.extractSheetValues(testExcelFile, 0)

    then:
      snapshot(workbookInformation, JSON) == current(workbookInformation, JSON)
  }

  def "should extract sheet values by name"() {
    given:
      def testExcelFile = new File(this.getClass().getResource("basic_sample.xls").getFile())

    when:
      def workbookInformation = ExcelExtractor.extractSheetValues(testExcelFile, 'Customer')

    then:
      snapshot(workbookInformation, JSON) == current(workbookInformation, JSON)
  }
}
