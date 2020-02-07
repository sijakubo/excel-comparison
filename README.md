Excel-Comparision Framework which is able to create a human readable diff when comparing Excel Files. 

```java
  //using junit  
  //TODO
```

```groovy
  //using spock  
  def "should assert workbooks in different formats with same values equal"() {
    given:
      def xlsWorkbookFile = new File(this.getClass().getResource("basic_sample.xls").getFile())
      def xlsxWorkbookFile = new File(this.getClass().getResource("basic_sample.xlsx").getFile())

    expect:
      ExcelAssert.assertWorkbookEquals(xlsWorkbookFile, xlsxWorkbookFile)
  }
```
