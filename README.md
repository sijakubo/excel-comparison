Excel-Comparision Framework which is able to create a human readable diff when comparing Excel Files. The Values are extracted using the `org.apache.poi` Framework  

## Integration 

Check out the latest Version at: [ ![Download](https://api.bintray.com/packages/sijakubo/excel-comparison/excel-comparison/images/download.svg) ](https://bintray.com/sijakubo/excel-comparison/excel-comparison/_latestVersion)

### Gradle
```
repositories {
  jcenter()
}

dependencies {
  testImplementation 'de.sijakubo:excel-comparison:***'
}
```
### Maven
```
<repositories>
  <repository>
    <id>jcenter</id>
    <url>https://jcenter.bintray.com/</url>
  </repository>
</repositories>
...
<dependency>
  <groupId>de.sijakubo</groupId>
  <artifactId>excel-comparison</artifactId>
  <version>***</version>
</dependency>
```

## General Usage Assertions

To compare workbook / sheet content you can use the `ExcelAssert.java`. This provides methods to either compare Files or `org.apache.poi.ss.usermodel.Workbook` 

### JUnit
```java
public class ExcelComparisonTest {
  
  //using junit  
  @Test
  void assertExcelCreation() {
    File expectedWorkbook = new File(this.getClass().getResource("basic_sample.xls").getFile());
    File actualWorkbook = new MyExcelGeneration().build();

    Assert.assertEquals(expectedWorkbook , actualWorkbook);
  }
}
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

## General Usage Extractions

In order to compare the Content of an Excel file, i needed to extract the information to `basic` Java Types. 
You can also use that to compare Excel Content as you like using the `ExcelExtractor.java`

### Spock
```groovy
  //using spock  
  def "should extract sheet containing formula"() {
    given:
      def testExcelFile = new File(this.getClass().getResource("formula_sample.xlsx").getFile())
  
      when:
        List<Map<String, Object>> formularSheetValues = ExcelExtractor.extractSheetValues(testExcelFile, 0)
  
      then:
        snapshot(formularSheetValues, JSON) == current(formularSheetValues, JSON)
  }
```
