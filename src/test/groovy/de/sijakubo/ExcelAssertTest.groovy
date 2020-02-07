package de.sijakubo

import org.apache.poi.ss.usermodel.WorkbookFactory
import spock.lang.Specification

class ExcelAssertTest extends Specification {

  def "should assert workbooks in different formats with same values equal"() {
    given:
      def xlsWorkbookFile = new File(this.getClass().getResource("basic_sample.xls").getFile())
      def xlsxWorkbookFile = new File(this.getClass().getResource("basic_sample.xlsx").getFile())

    expect:
      ExcelAssert.assertWorkbookEquals(xlsWorkbookFile, xlsxWorkbookFile)
  }

  def "should assert sheet in different formats with same values equal"() {
    given:
      def xlsWorkbookFile = new File(this.getClass().getResource("basic_sample.xls").getFile())
      def xlsxWorkbookFile = new File(this.getClass().getResource("basic_sample.xlsx").getFile())

    expect:
      ExcelAssert.assertSheetEquals(xlsWorkbookFile, xlsxWorkbookFile, 0)
      ExcelAssert.assertSheetEquals(xlsWorkbookFile, xlsxWorkbookFile, 1)
  }

  def "should assert not equals when values mismatch"() {
    given:
      def xlsWorkbookFile = new File(this.getClass().getResource("basic_mismatch.xls").getFile())

      def sheet1 = WorkbookFactory.create(xlsWorkbookFile).getSheetAt(0)
      def sheet2 = WorkbookFactory.create(xlsWorkbookFile).getSheetAt(1)

    expect:
      ExcelAssert.assertSheetEquals(sheet1, sheet2)
  }

  def "should assert not equals when values order mismatch"() {
    given:
      def xlsWorkbookFile = new File(this.getClass().getResource("basic_mismatch_order.xls").getFile())

      def sheet1 = WorkbookFactory.create(xlsWorkbookFile).getSheetAt(0)
      def sheet2 = WorkbookFactory.create(xlsWorkbookFile).getSheetAt(1)

    expect:
      ExcelAssert.assertSheetEquals(sheet1, sheet2)
  }
}
