# Parseux

[![Build Status](https://travis-ci.org/jedcua/Parseux.svg?branch=master)](https://travis-ci.org/jedcua/Parseux) [![Coverage Status](https://coveralls.io/repos/github/jedcua/Parseux/badge.svg?branch=master)](https://coveralls.io/github/jedcua/Parseux?branch=master)
[![Code Climate](https://codeclimate.com/github/jedcua/Parseux/badges/gpa.svg)](https://codeclimate.com/github/jedcua/Parseux)

(NOTE: This project is WIP, its API _will_ change in the future)

Easily parse CSV/Excel files to DTOs

## Usage
Suppose you have a csv file
```
value1,value2
value1,value2
value1,value2
...
```

Easily parse that to a DTO by annotating your DTO class with `@JsonPropertyOrder`, which defines the order of field values to be used for parsing

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
* `Iterator<String>` where each `String` also 1 row
* `BufferedReader`
* `InputStreamReader`
* `ByteArrayStreamReader`
* `byte[]`

## Parsing Excel 
Parsing excel files to DTO is the same with CSV:

```java
List<MyDTO> dtos = new CsvAsDTO<>(
    new ExcelIterator(
        workbook
    )
    MyDTO.class
).asDTOs()
```

`ExcelIterator` in an implementation of `Iterator<String>` that run through all sheets and rows, and iteration of concatenated cells per row as String.

`workbook` is an instance of `XSSFWorkbook` from [Apache POI](https://github.com/apache/poi)

## Installation (Maven)
Clone this repository and install
```bash
$ git clone https://github.com/jedcua/Parseux.git
$ cd Parseux
$ mvn clean install
```

Add the following dependencies on your `pom.xml`
```xml
<dependency>
    <groupId>com.dragonfruit</groupId>
    <artifactId>Parseux</artifactId>
    <version>0.0.2</version>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-csv</artifactId>
    <version>2.9.0.pr3</version>
</dependency>
```

## License
Apache Version 2
