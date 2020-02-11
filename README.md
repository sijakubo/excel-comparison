Excel-Comparision Framework which is able to create a human readable diff when comparing Excel Files. 

## Integration 

### Gradle
```
repositories {
  jcenter()
}

dependencies {
  testImplementation 'de.sijakubo:excel-comparison:0.1.1'
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
	<version>0.1.1</version>
</dependency>
```

## General Usage

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
