Base64 Encoder
==============

It converts any given file to Base64 representation.

## Uses

1. It is usually used to hard-code tiny images in webpages in order to avoid unnecessary network request overhead
2. It is also used to transfer binary files where the transfer medium is designed to handle only textual data such as **email**, **XML**

More about Base64 [here](https://en.wikipedia.org/wiki/Base64)

## Usage

In order to use this library, include `Base64Encoder.jar` in your classpath

```
Base64Encoder encoder = Base64Encoder(new FileInputStream("/path/to/your/file"));

/* To get Base64 representation in String */
String base64 = encoder.getBase64Value();

/* To write to a file */
encoder.writeToFile(new FileOutputStream("path/to/destination/file"));
```


