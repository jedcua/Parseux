# Parseux

Easily parse CSV/Excel files to DTOs

## Usage
Annotate your DTO class with `@JsonPropertyOrder`, which defines the order of field values to be used for parsing

```java
@JsonPropertyOrder({"field1", "field2"})
class MyDTO {
    private String field1;
    private String field2;
    
    // Getters and setters...
}
```

Instantiate `CsvAsDTO<>()` and retrieve parsed csv as DTO

```java
List<MyDTO> dtos = new CsvAsDTO<>(
    csv,
    MyDTO.class
).asDTOs();
```

`csv` can be:
* `Stream<String>` where each `String` is 1 row (i.e `"value1,value2"`)
* `BufferedReader`
* `InputStreamReader`
* `ByteArrayStreamReader`
* `byte[]`

## Parsing Excel 
Parsing excel files to DTO is the same with CSV:

```java
List<MyDTO> dtos = new CsvAsDTO<>(
    new IteratorAsStream<>(
        new ExcelIterator(
            workbook
        )
    ).toStream(),
    MyDTO.class
).asDTOs()
```

The Excel file is encapsulated by `ExcelIterator` and then by `IteratorAsStream` which can be used by `CsvAsDTO`.

`workbook` is an instance of `XSSFWorkbook` from [Apache POI](https://github.com/apache/poi)

## Installation (Maven)
```bash
// Clone this repository
$ git clone https://github.com/jedcua/Parseux.git
$ cd Parseux
$ mvn clean install
```

## License
Apache Version 2
