Excel-Comparision Framework which is able to create a human readable diff when comparing Excel Files. 

## Integration 

### Gradle
```
TODO
```
### Maven
```
TODO
```

## General Usage

### JUnit
```java
  //using junit  
  TODO
```

### Spock
```groovy
  //using spock  
  def "should assert workbooks in different formats with same values equal"() {
    given:
      def expectedWorkbook = new File(this.getClass().getResource("basic_sample.xls").getFile())
    
    when:
      def actualWorkbook = new MyExcelGeneration().build()

    expect:
      ExcelAssert.assertWorkbookEquals(actualWorkbook, expectedWorkbook)
  }
```
